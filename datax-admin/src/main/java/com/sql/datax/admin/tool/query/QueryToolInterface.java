package com.sql.datax.admin.tool.query;

import com.sql.datax.admin.tool.database.ColumnInfo;
import com.sql.datax.admin.tool.database.TableInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 基础查询接口
 */
public interface QueryToolInterface {
    /**
     * 构建 tableInfo对象
     */
    TableInfo buildTableInfo(String tableName);

    /**
     * 获取指定表信息
     */
    List<Map<String, Object>> getTableInfo(String tableName);

    /**
     * 获取当前schema下的所有表
     */
    List<Map<String, Object>> getTables();

    /**
     * 根据表名获取所有字段
     */
    List<ColumnInfo> getColumns(String tableName);

    /**
     * 根据表名和获取所有字段名称（不包括表名）
     */
    List<String> getColumnNames(String tableName, String datasource);


    /**
     * 获取所有可用表名
     */
    List<String> getTableNames(String schema);

    /**
     * 获取所有可用表名
     */
    List<String> getTableNames();

    /**
     * 通过查询sql获取columns
     */
    List<String> getColumnsByQuerySql(String querySql) throws SQLException;

    /**
     * 获取当前表maxId
     */
    long getMaxIdVal(String tableName, String primaryKey);

}
