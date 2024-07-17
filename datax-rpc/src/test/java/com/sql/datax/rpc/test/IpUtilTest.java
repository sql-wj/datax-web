package com.sql.datax.rpc.test;

import com.sql.datax.rpc.util.IpUtil;

/**
 * @author xuxueli 2018-10-23
 */
public class IpUtilTest {

    public static void main(String[] args) {
        System.out.println(IpUtil.getIp());
        System.out.println(IpUtil.getIpPort(8080));
    }

}
