package com.blackniuza.stock.index.pojo;

import java.util.Date;

import com.google.common.base.Joiner;

/**
 * @author niuza
 * @date 2017/5/9
 */
public class PredictIndex extends AbstractIndex {

    private static final long serialVersionUID = -5295415762770946181L;

    private Long minToPredict;

    public PredictIndex(String code, Date bizDate, Double value, Long minToPredict) {
        super(code, bizDate, value);
        this.minToPredict = minToPredict;
    }

    public Long getMinToPredict() {
        return minToPredict;
    }

    public void setMinToPredict(Long minToPredict) {
        this.minToPredict = minToPredict;
    }

    @Override
    public String toStoreString() {
        return Joiner.on(",").join(getCode(), getBizDate().getTime(), getValue(), getMinToPredict());
    }
}
