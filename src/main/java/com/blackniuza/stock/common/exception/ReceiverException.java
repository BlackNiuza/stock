package com.blackniuza.stock.common.exception;

/**
 * @author niuza
 * @date 2017/5/7
 */
public class ReceiverException extends Exception {
    private static final long serialVersionUID = 3801368893001383648L;

    public ReceiverException(String message) {
        super(message);
    }

    public ReceiverException(String message, Throwable cause) {
        super(message, cause);
    }
}
