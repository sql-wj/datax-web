package com.sql.datax.rpc.remoting.invoker.annotation;

import com.sql.datax.rpc.remoting.invoker.call.CallType;
import com.sql.datax.rpc.remoting.invoker.route.LoadBalance;
import com.sql.datax.rpc.remoting.net.Client;
import com.sql.datax.rpc.remoting.net.impl.netty.client.NettyClient;
import com.sql.datax.rpc.serialize.Serializer;
import com.sql.datax.rpc.serialize.impl.HessianSerializer;

import java.lang.annotation.*;

/**
 * rpc service annotation, skeleton of stub ("@Inherited" allow service use "Transactional")
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface XxlRpcReference {

    Class<? extends Client> client() default NettyClient.class;

    Class<? extends Serializer> serializer() default HessianSerializer.class;

    CallType callType() default CallType.SYNC;

    LoadBalance loadBalance() default LoadBalance.ROUND;

    //Class<?> iface;
    String version() default "";

    long timeout() default 1000;

    String address() default "";

    String accessToken() default "";

    //XxlRpcInvokeCallback invokeCallback() ;

}
