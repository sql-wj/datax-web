package com.sql.datax.rpc.util;

public class XxlRpcException extends RuntimeException {

    private static final long serialVersionUID = 42L;

    public XxlRpcException(String msg) {
        super(msg);
    }

    public XxlRpcException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public XxlRpcException(Throwable cause) {
        super(cause);
    }

}