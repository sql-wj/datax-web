package com.sql.datax.rpc.remoting.invoker.call;

public abstract class XxlRpcInvokeCallback<T> {
    public abstract void onSuccess(T result);
    public abstract void onFailure(Throwable exception);

    // ---------------------- thread invoke callback ----------------------
    private static final ThreadLocal<XxlRpcInvokeCallback> threadInvokerFuture = new ThreadLocal<>();

    /**
     * get callback
     */
    public static XxlRpcInvokeCallback getCallback() {
        XxlRpcInvokeCallback invokeCallback = threadInvokerFuture.get();
        threadInvokerFuture.remove();
        return invokeCallback;
    }

    /**
     * set future
     */
    public static void setCallback(XxlRpcInvokeCallback invokeCallback) {
        threadInvokerFuture.set(invokeCallback);
    }

    /**
     * remove future
     */
    public static void removeCallback() {
        threadInvokerFuture.remove();
    }

}
