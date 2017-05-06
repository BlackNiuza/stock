package com.blackniuza.stock.common.exception;

/**
 * @author niuza
 * @date 2017/5/7
 */
public class NetworkException extends Exception {
    private static final long serialVersionUID = -8597069934825419278L;

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
