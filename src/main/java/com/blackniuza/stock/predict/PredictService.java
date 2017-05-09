package com.blackniuza.stock.predict;

import java.util.List;
import java.util.Set;

import com.blackniuza.stock.common.type.Stock;
import com.blackniuza.stock.index.pojo.AbstractIndex;
import com.blackniuza.stock.index.pojo.PredictIndex;

/**
 * @author niuza
 * @date 2017/5/8
 */
public interface PredictService {

    Stock predictStock();

    Set<String> rddFacCodes();

    Set<String> dstreamFacCodes();

    Long minToPredict();

    Long minToWindow();

    PredictIndex predict(List<AbstractIndex> indexes);

}
