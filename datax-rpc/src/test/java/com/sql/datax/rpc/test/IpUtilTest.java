package com.sql.datax.rpc.test;

import com.sql.datax.rpc.util.IpUtil;

public class IpUtilTest {

    public static void main(String[] args) {
        System.out.println(IpUtil.getIp());
        System.out.println(IpUtil.getIpPort(8080));
    }

}
