package com.sql.datax.rpc.remoting.invoker.call;

/**
 * rpc call type
 */
public enum CallType {

    SYNC,
    FUTURE,
    CALLBACK,
    ONEWAY;

    public static CallType match(String name, CallType defaultCallType) {
        for (CallType item : CallType.values()) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        return defaultCallType;
    }

}
