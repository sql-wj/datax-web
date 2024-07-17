package com.wugui.datax.rpc.util;

import java.util.concurrent.*;

/**
 * @author xuxueli 2019-02-18
 */
public class ThreadPoolUtil {

    /**
     * make server thread pool
     */
    public static ThreadPoolExecutor makeServerThreadPool(final String serverType, int corePoolSize, int maxPoolSize) {
        return new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000),
                r -> new Thread(r, "xxl-rpc, " + serverType + "-serverHandlerPool-" + r.hashCode()),
                (r, executor) -> {
                    throw new XxlRpcException("xxl-rpc " + serverType + " Thread pool is EXHAUSTED!");
                });
    }

}
