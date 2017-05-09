package com.blackniuza.stock.index.pojo.stock;

import java.util.Date;

/**
 * var 股票代码=“英文名，中文名，开盘，昨收，最高，最低，当前，涨跌额，涨跌幅，卖一，买一，成交额，成交量，市盈率，收益率，52周最高，52周最低，行情日期，行情时间”
 *
 * @author niuza
 * @date 2017/5/7
 */
public class HKStockIndex extends SinaStockIndex {

    private static final long serialVersionUID = -1L;

    public HKStockIndex(String code, Date bizDate, Double value) {
        super(code, bizDate, value);
    }
}
