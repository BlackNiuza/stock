package com.blackniuza.stock.predict;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.blackniuza.stock.common.type.Stock;
import com.blackniuza.stock.index.pojo.AbstractIndex;
import com.blackniuza.stock.index.pojo.PredictIndex;
import com.clearspring.analytics.util.Lists;
import com.google.common.collect.Sets;

/**
 * @author niuza
 * @date 2017/5/9
 */
public class Tesla5MinPredictServiceImpl implements PredictService{

    @Override
    public Stock predictStock() {
        return Stock.TESLA;
    }

    @Override
    public Set<String> rddFacCodes() {
        //TODO
        return Sets.newHashSet();
    }

    @Override
    public Set<String> dstreamFacCodes() {
        //TODO
        return Sets.newHashSet();
    }

    @Override
    public Long minToPredict() {
        return 5L;
    }

    @Override
    public Long minToWindow() {
        return 50L;
    }

    @Override
    public PredictIndex predict(List<AbstractIndex> indexes) {
        //TODO
        return new PredictIndex(predictStock().getStockCode(), new Date(), 123.0,456L);
    }
}
