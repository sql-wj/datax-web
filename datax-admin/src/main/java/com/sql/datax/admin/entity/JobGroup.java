package com.sql.datax.admin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class JobGroup {

    @ApiModelProperty("执行器Id")
    private int id;
    @ApiModelProperty("执行器AppName")
    private String appName;
    @ApiModelProperty("执行器名称")
    private String title;
    @ApiModelProperty("排序")
    private int order;
    @ApiModelProperty("执行器地址类型：0=自动注册、1=手动录入")
    private int addressType;
    @ApiModelProperty("执行器地址列表，多地址逗号分隔(手动录入)")
    private String addressList;

    // registry list
    private List<String> registryList;  // 执行器地址列表(系统注册)
    public List<String> getRegistryList() {
        if (addressList!=null && !addressList.trim().isEmpty()) {
            registryList = new ArrayList<>(Arrays.asList(addressList.split(",")));
        }
        return registryList;
    }

}
