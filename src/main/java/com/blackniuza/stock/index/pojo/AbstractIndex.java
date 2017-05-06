package com.blackniuza.stock.index.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author niuza
 * @date 2017/5/6
 */
public abstract class AbstractIndex implements Serializable {

    private static final long serialVersionUID = 4664177340819121016L;

    private Date bizDate;

    private Double value;

    public String getName() {
        return this.getClass().getName();
    }

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
