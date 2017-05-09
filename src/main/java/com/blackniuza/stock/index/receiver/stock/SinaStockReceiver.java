package com.blackniuza.stock.index.receiver.stock;

import com.blackniuza.stock.common.exception.NetworkException;
import com.blackniuza.stock.common.exception.ReceiverException;
import com.blackniuza.stock.common.util.HttpUtils;
import com.google.common.base.Preconditions;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;

/**
 * @author niuza
 * @date 2017/5/7
 */
public abstract class SinaStockReceiver<T> extends Receiver<T> {

    private final static String SINA_STOCK_URL = "http://hq.sinajs.cn/list=";
    private static final long serialVersionUID = 138454325728851310L;

    public SinaStockReceiver(StorageLevel storageLevel) {
        super(storageLevel);
    }

    //gb_tsla
    protected String queryStock(String stockCode) throws ReceiverException {
        Preconditions.checkNotNull(stockCode);
        try {
            return HttpUtils.httpGet(getStockUrl(stockCode));
        } catch (NetworkException e) {
            throw new ReceiverException("Failed to query stock: " + stockCode, e);
        }
    }

    private String getStockUrl(String stockCode) {
        return SINA_STOCK_URL + stockCode;
    }

}
