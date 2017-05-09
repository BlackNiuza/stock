package com.blackniuza.stock.common.type;

/**
 * @author niuza
 * @date 2017/5/7
 */
public enum DateFormat {

    YYYYMMDDHHMMSS("yyyy-MM-dd HH:mm:ss");

    String format;

    DateFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
