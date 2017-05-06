package com.blackniuza

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext._

import scala.math.random

/**
  * @author niuza
  * @date 2017/5/3
  */
object SparkPi {
  def main(args: Array[String]) {
    val spark = SparkSession.builder
      .appName("Spark Pi")
      .master(args(0))
      .getOrCreate()
    val slices = if (args.length > 0) args(1).toInt else 2
    val n = math.min(100000L * slices, Int.MaxValue).toInt // avoid overflow
    val count = spark.sparkContext
      .parallelize(1 until n, slices)
      .map { i =>
        val x = random * 2 - 1
        val y = random * 2 - 1
        if (x * x + y * y <= 1) 1 else 0
      }
      .reduce(_ + _)
    println("Pi is roughly " + 4.0 * count / (n - 1))
    spark.stop()
  }
}
