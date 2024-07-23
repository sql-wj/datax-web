package com.sql.datax.admin.tool.datax;

import com.sql.datax.admin.tool.pojo.DataxHbasePojo;
import com.sql.datax.admin.tool.pojo.DataxHivePojo;
import com.sql.datax.admin.tool.pojo.DataxMongoDBPojo;
import com.sql.datax.admin.tool.pojo.DataxRdbmsPojo;

import java.util.Map;

/**
 * 插件基础接口
 */
public interface DataxPluginInterface {
    /**
     * 获取reader插件名称
     */
    String getName();

    /**
     * 构建
     *
     * @return dataxPluginPojo
     */
    Map<String, Object> build(DataxRdbmsPojo dataxPluginPojo);


    /**
     * hive json构建
     */
    Map<String, Object> buildHive(DataxHivePojo dataxHivePojo);

    /**
     * hbase json构建
     */
    Map<String, Object> buildHbase(DataxHbasePojo dataxHbasePojo);

    /**
     * mongodb json构建
     */
    Map<String, Object> buildMongoDB(DataxMongoDBPojo dataxMongoDBPojo);

    /**
     * 获取示例
     */
    Map<String, Object> sample();
}
