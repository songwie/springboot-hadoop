package com.songwie.hadoop.base;

public class MogoException  extends RuntimeException{
    public MogoException() {
        super();
    }
    public MogoException(String message) {
        super(message);
    }
    public MogoException(String message, Throwable cause) {
        super(message, cause);
    }
    public MogoException(Throwable cause) {
        super(cause);
    }

    protected MogoException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
