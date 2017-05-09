package com.blackniuza.stock.facotry;

import java.util.List;

import com.blackniuza.stock.predict.PredictService;
import com.blackniuza.stock.predict.Tesla5MinPredictServiceImpl;
import com.google.common.collect.Lists;

/**
 * @author niuza
 * @date 2017/5/8
 */
public class StockFactory {

    private final static StockFactory sf;

    private List<PredictService> predictServices;

    static {
        sf = new StockFactory();
        sf.predictServices = Lists.newArrayList(
            new Tesla5MinPredictServiceImpl()
        );
    }

    public static StockFactory getInstance() {
        return sf;
    }

    public List<PredictService> getPredictServices() {
        return predictServices;
    }
}
