package com.blackniuza.stock.index.pojo.stock;

import java.util.Date;

/**
 *
 * @author niuza
 * @date 2017/5/7
 */
public class USAStockIndex extends SinaStockIndex {

    private static final long serialVersionUID = -1L;

    public USAStockIndex(String code, Date bizDate, Double value) {
        super(code, bizDate, value);
    }
}
