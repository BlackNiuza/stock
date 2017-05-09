package com.blackniuza.stock.common.constants;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author niuza
 * @date 2017/5/7
 */
public class Env {

    /**
     * hdfs地址
     */
    public static final String HDFS = "hdfs://hadoop:9000";

    /**
     * index存储前缀
     */
    public static final String BASE_PATH = HDFS + "/blackniuza/stock";

    /**
     * stock index存储前缀
     */
    public static final String STOCK_INDEX_PATH = BASE_PATH + "/index";

    /**
     * predict存储前缀
     */
    public static final String PREDICT_PATH = BASE_PATH + "/predict";

}
