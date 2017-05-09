package com.blackniuza.stock.index.receiver.stock;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.blackniuza.stock.common.exception.ReceiverException;
import com.blackniuza.stock.common.type.DateFormat;
import com.blackniuza.stock.common.type.Stock;
import com.blackniuza.stock.common.type.StockType;
import com.blackniuza.stock.common.util.DateUtils;
import com.blackniuza.stock.index.pojo.stock.USAStockIndex;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.apache.spark.storage.StorageLevel;

/**
 * var gb_symbol="美股中文名称,最新价,涨跌幅,数据更新时间（北京时间）,涨跌额,开盘价,最高价,最低价,52周最高价,52周最低价,成交量（单位股）,平均成交量（单位股）,市值（单位元）,每股收益,市盈率,fpe,
 * 贝塔系数,股息,收益率,总股本（单位元）,instown,盘前盘后价,盘前盘后涨跌幅,盘前盘后涨跌额,盘前盘后数据更新时间（美东）,盘中数据更新时间（美东）,昨收价,盘前盘后成交量"
 * var hq_str_gb_tsla="特斯拉,308.3500,4.36,2017-05-06 08:17:37,12.8900,298.0000,308.5500,296.8000,327.6600,178.1900,
 * 8177347,6284885,50230215000,-4.68,--,0.00,1.14,0.00,0.00,162900000,64.00,309.0000,0.21,0.65,May
 * 05 08:00PM EDT,May 05 04:00PM EDT,295.4600,206661.00";
 *
 * @author niuza
 * @date 2017/5/7
 */
public class USAStockReceiver extends SinaStockReceiver<USAStockIndex> {

    private static final long serialVersionUID = 9177440374604351099L;
    private static Logger logger = Logger.getLogger(USAStockReceiver.class);
    private static final long INTERVAL = 1000L;

    private ExecutorService threadPool;
    private List<Stock> usStocks;
    private Boolean isShutdown;

    public USAStockReceiver() {
        super(StorageLevel.MEMORY_AND_DISK_2());
    }

    @Override
    public void onStart() {
        usStocks = Lists.newArrayList();
        for(Stock p: Stock.values()) {
            if(p.getStockType() == StockType.US) {
                usStocks.add(p);
            }
        }
        isShutdown = false;
        threadPool = Executors.newFixedThreadPool(usStocks.size() + 1);
        threadPool.execute(() -> {
            while (!isShutdown) {
                try {
                    long st = System.currentTimeMillis();
                    for (Stock pd : usStocks) {
                        threadPool.execute(() -> {
                            try {
                                Optional<USAStockIndex> idxOpt = queryUSAStock(pd.getStockCode());
                                if (idxOpt.isPresent()) {
                                    USAStockReceiver.this.store(idxOpt.get());
                                }
                            } catch (Exception e) {
                                throw new RuntimeException("Failed to run receiver.", e);
                            }
                        });
                    }
                    long rest = INTERVAL - (System.currentTimeMillis() - st);
                    if(rest > 0) {
                        TimeUnit.MINUTES.sleep(rest);
                    } else {
                        logger.warn("USAStockReceiver is too busy.");
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Failed to run receiver.", e);
                }
            }
        });
    }

    @Override
    public void onStop() {
        isShutdown = true;
        if(threadPool!=null) {
            threadPool.shutdownNow();
        }
    }

    private Optional<USAStockIndex> queryUSAStock(String stockCode) throws ReceiverException {
        String resp = queryStock(stockCode);
        try {
            String[] values = resp.split(",");
            USAStockIndex idx = new USAStockIndex(
                stockCode
                , DateUtils.formatToDate(values[3], DateFormat.YYYYMMDDHHMMSS)
                , Double.valueOf(values[1]));
            return Optional.of(idx);
        } catch (Exception e) {
            logger.error("Failed to parse usa stock: " + stockCode + ", " + resp, e);
            return Optional.absent();
        }
    }

}