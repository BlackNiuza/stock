package com.blackniuza.stock.index.receiver;

import com.blackniuza.stock.common.exception.NetworkException;
import com.blackniuza.stock.common.exception.ReceiverException;
import com.blackniuza.stock.common.util.HttpUtils;
import com.blackniuza.stock.index.pojo.SinaStockIndex;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;

/**
 * @author niuza
 * @date 2017/5/7
 */
public abstract class SinaStockReceiver<T> extends Receiver<T> {

    private final static String SINA_STOCK_URL = "http://hq.sinajs.cn/list=";

    public SinaStockReceiver(StorageLevel storageLevel) {
        super(storageLevel);
    }

    //gb_tsla
    protected Optional<SinaStockIndex> queryStock(String stockCode) throws ReceiverException {
        Preconditions.checkNotNull(stockCode);
        try {
            String resp = HttpUtils.httpGet(getStockUrl(stockCode));
        } catch (NetworkException e) {
            throw new ReceiverException("Failed to query stock: " + stockCode, e);
        }
    }

    private String getStockUrl(String stockCode) {
        return SINA_STOCK_URL + stockCode;
    }

}
