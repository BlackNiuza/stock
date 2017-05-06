package com.blackniuza.stock.index.receiver.stock;

import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;
import com.blackniuza.stock.index.pojo.stock.TeslaStockIndex;

/**
 * @author niuza
 * @date 2017/5/7
 */
public class TeslaStockReceiver extends Receiver<TeslaStockIndex> {

    public TeslaStockReceiver(StorageLevel storageLevel) {
        super(storageLevel);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
