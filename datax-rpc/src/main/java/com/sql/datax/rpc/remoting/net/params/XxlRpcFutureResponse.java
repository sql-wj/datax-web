package com.sql.datax.rpc.remoting.net.params;

import com.sql.datax.rpc.remoting.invoker.XxlRpcInvokerFactory;
import com.sql.datax.rpc.remoting.invoker.call.XxlRpcInvokeCallback;
import com.sql.datax.rpc.util.XxlRpcException;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * call back future
 */
public class XxlRpcFutureResponse implements Future<XxlRpcResponse> {
    private final XxlRpcInvokerFactory invokerFactory;

    // net data
    private final XxlRpcRequest request;
    private XxlRpcResponse response;

    // future lock
    private boolean done = false;
    private final Object lock = new Object();

    // callback, can be null
    private final XxlRpcInvokeCallback invokeCallback;

    public XxlRpcFutureResponse(final XxlRpcInvokerFactory invokerFactory, XxlRpcRequest request, XxlRpcInvokeCallback invokeCallback) {
        this.invokerFactory = invokerFactory;
        this.request = request;
        this.invokeCallback = invokeCallback;

        // set-InvokerFuture
        setInvokerFuture();
    }

    // ---------------------- response pool ----------------------

    public void setInvokerFuture() {
        this.invokerFactory.setInvokerFuture(request.getRequestId(), this);
    }

    public void removeInvokerFuture() {
        this.invokerFactory.removeInvokerFuture(request.getRequestId());
    }

    // ---------------------- get ----------------------

    public XxlRpcRequest getRequest() {
        return request;
    }

    public XxlRpcInvokeCallback getInvokeCallback() {
        return invokeCallback;
    }


    // ---------------------- for invoke back ----------------------

    public void setResponse(XxlRpcResponse response) {
        this.response = response;
        synchronized (lock) {
            done = true;
            lock.notifyAll();
        }
    }

    // ---------------------- for invoke ----------------------

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        // TODO
        return false;
    }

    @Override
    public boolean isCancelled() {
        // TODO
        return false;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public XxlRpcResponse get() throws InterruptedException {
        try {
            return get(-1, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            throw new XxlRpcException(e);
        }
    }

    @Override
    public XxlRpcResponse get(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException {
        if (!done) {
            synchronized (lock) {
                if (timeout < 0) {
                    lock.wait();
                } else {
                    long timeoutMillis = (TimeUnit.MILLISECONDS == unit) ? timeout : TimeUnit.MILLISECONDS.convert(timeout, unit);
                    lock.wait(timeoutMillis);
                }
            }
        }

        if (!done) {
            throw new XxlRpcException("xxl-rpc, request timeout at:" + System.currentTimeMillis() + ", request:" + request.toString());
        }
        return response;
    }

}
