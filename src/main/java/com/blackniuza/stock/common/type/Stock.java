package com.blackniuza.stock.common.type;

/**
 * @author niuza
 * @date 2017/5/9
 */
public enum Stock {

    TESLA(StockType.US, "gb_tsla");

    StockType stockType;
    String stockCode;

    Stock(StockType stockType, String stockCode) {
        this.stockType = stockType;
        this.stockCode = stockCode;
    }

    public StockType getStockType() {
        return stockType;
    }

    public String getStockCode() {
        return stockCode;
    }

}
