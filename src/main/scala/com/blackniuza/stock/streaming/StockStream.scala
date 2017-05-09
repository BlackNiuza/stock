package com.blackniuza.stock.streaming

import com.blackniuza.stock.common.constants.Env
import com.blackniuza.stock.facotry.StockFactory
import com.blackniuza.stock.index.pojo.{AbstractIndex, PredictIndex}
import com.blackniuza.stock.index.receiver.stock.USAStockReceiver
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Minutes, StreamingContext}

import scala.collection.JavaConversions._

/**
  * @author niuza 
  * @date 2017/5/7
  */
class StockStream(master: String, name: String) {

  val STREAM_DURATION = Minutes(1)
  val SLIDE_DURATION = Minutes(1)

  private var ssc: StreamingContext

  def init(): Unit = {
    val conf = new SparkConf()
      .setAppName(name)
      .setMaster(master)
    ssc = new StreamingContext(conf, STREAM_DURATION)
  }

  def start(): Unit = {
    //init rdd
    val indexRDD: RDD[AbstractIndex] = geneRDD()

    //init receive
    val stockDStream: DStream[AbstractIndex] = geneDStream()
    storeIndex(stockDStream, Env.STOCK_INDEX_PATH)

    //predict，使用windows构造维度数据，预测新值
    val predictDStream: DStream[PredictIndex] = predict(indexRDD, stockDStream)
    storeIndex(stockDStream, Env.PREDICT_PATH)

    //启动
    ssc.start() // Start the computation
    ssc.awaitTermination() // Wait for the computation to terminate
  }

  def geneRDD(): RDD[AbstractIndex] = {
    ssc.sparkContext.parallelize(Seq(), 1)
  }

  def geneDStream(): DStream[AbstractIndex] = {
    ssc.receiverStream(new USAStockReceiver).map(_.asInstanceOf[AbstractIndex])
  }

  def storeIndex(stockDStream: DStream[AbstractIndex], path: String) = {
    stockDStream.filter(_ != null).saveAsTextFiles(path)
  }

  def predict(indexRDD: RDD[AbstractIndex], stockDStream: DStream[AbstractIndex]): DStream[PredictIndex] = {
    StockFactory.getInstance().getPredictServices.map((ps) => {
      val streamCodes = ps.dstreamFacCodes().toSet ++ Set(ps.predictStock().getStockCode)
      val rddCodes = ps.rddFacCodes().toSet;
      val filterRDD = indexRDD.filter(idx => rddCodes.contains(idx.getCode))
      stockDStream.filter(idx => streamCodes.contains(idx.getCode))
        .window(Minutes(ps.minToWindow()), SLIDE_DURATION)
        .transform((rdd: RDD[AbstractIndex]) => {
          val pIdx = ps.predict(rdd.union(filterRDD).collect().toList)
          ssc.sparkContext.parallelize(Seq(pIdx), 1)
        })
    }).reduce((a, b) => a.union(b))
  }

}

object StockStream {

  def main(args: Array[String]): Unit = {
    val ss = new StockStream(args(0), "StockStream")
    ss.init()
    ss.start()
  }

}
