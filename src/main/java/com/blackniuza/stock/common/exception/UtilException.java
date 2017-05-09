package com.blackniuza.stock.common.exception;

/**
 * @author niuza
 * @date 2017/5/7
 */
public class UtilException extends Exception {
    private static final long serialVersionUID = -765010937508975221L;

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }
}
