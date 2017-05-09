package com.blackniuza.stock.index.pojo;

import java.io.Serializable;
import java.util.Date;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import org.apache.parquet.Strings;

/**
 * @author niuza
 * @date 2017/5/6
 */
public abstract class AbstractIndex implements Serializable {

    private static final long serialVersionUID = 4664177340819121016L;

    private String code;

    private Date bizDate;

    private Double value;

    public AbstractIndex(String code, Date bizDate, Double value) {
        this.code = code;
        this.bizDate = bizDate;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public Date getBizDate() {
        return bizDate;
    }

    public Double getValue() {
        return value;
    }

    public String toStoreString() {
        return Joiner.on(",").join(code, bizDate.getTime(), value);
    }

    @Override
    public String toString() {
        return "AbstractIndex{" +
            "code='" + code + '\'' +
            ", bizDate=" + bizDate +
            ", value=" + value +
            '}';
    }
}
