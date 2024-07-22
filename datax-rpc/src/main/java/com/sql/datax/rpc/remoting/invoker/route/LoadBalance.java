package com.sql.datax.rpc.remoting.invoker.route;

import com.sql.datax.rpc.remoting.invoker.route.impl.*;

public enum LoadBalance {

    RANDOM(new XxlRpcLoadBalanceRandomStrategy()),
    ROUND(new XxlRpcLoadBalanceRoundStrategy()),
    LRU(new XxlRpcLoadBalanceLRUStrategy()),
    LFU(new XxlRpcLoadBalanceLFUStrategy()),
    CONSISTENT_HASH(new XxlRpcLoadBalanceConsistentHashStrategy());

    public final XxlRpcLoadBalance xxlRpcInvokerRouter;

    LoadBalance(XxlRpcLoadBalance xxlRpcInvokerRouter) {
        this.xxlRpcInvokerRouter = xxlRpcInvokerRouter;
    }

}