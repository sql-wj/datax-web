package com.sql.datax.admin.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Map;

/**
 * 分页工具类
 */
public class PageUtils {
    /**
     * 用于保存分页查询用到的关键字
     */
    public static final List<String> PAGE_QUERY_KEY_LIST = ImmutableList.of("current", "size", "sortBy", "orderby", "order", "sort", "ifCount", "ascs", "descs");


    /**
     * 过滤pageHelper的参数、空值等
     * 返回查询条件
     */
    public static Map<String, Object> filterColumnQueryParams(Map<String, Object> map) {
        return MapUtil.filter(map, e -> {
            if (StrUtil.isBlank(StrUtil.toString(e.getValue()))) {
                return false;
            }
            return !PAGE_QUERY_KEY_LIST.contains(e.getKey());
        });
    }

    /**
     * 返回pageHelper用到的参数
     */
    public static Map<String, Object> filterPageParams(Map<String, Object> map) {
        return MapUtil.filter(map, e -> {
            if (StrUtil.isBlank(StrUtil.toString(e.getValue()))) {
                return false;
            }
            return PAGE_QUERY_KEY_LIST.contains(e.getKey());
        });
    }
}
