package com.blackniuza.stock.index.pojo.stock;

import java.util.Date;

import com.blackniuza.stock.index.pojo.AbstractIndex;

/**
 * @author niuza
 * @date 2017/5/7
 */
public class SinaStockIndex extends AbstractIndex {
    private static final long serialVersionUID = 7312647674577183573L;

    public SinaStockIndex(String code, Date bizDate, Double value) {
        super(code, bizDate, value);
    }
}
