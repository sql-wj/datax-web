package com.sql.datax.rpc.remoting.net.impl.netty.client;

import com.sql.datax.rpc.remoting.net.Client;
import com.sql.datax.rpc.remoting.net.common.ConnectClient;
import com.sql.datax.rpc.remoting.net.params.XxlRpcRequest;

/**
 * netty client
 */
public class NettyClient extends Client {
    private final Class<? extends ConnectClient> connectClientImpl = NettyConnectClient.class;

    @Override
    public void asyncSend(String address, XxlRpcRequest xxlRpcRequest) throws Exception {
        ConnectClient.asyncSend(xxlRpcRequest, address, connectClientImpl, xxlRpcReferenceBean);
    }

}
