package com.sql.datax.admin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库查询服务
 */
public interface DatasourceQueryService {

    /**
     * 获取db列表
     */
    List<String> getDBs(Long id) throws IOException;

    /**
     * 根据数据源表id查询出可用的表
     */
    List<String> getTables(Long id, String tableSchema) throws IOException;

    /**
     * 获取CollectionNames
     */
    List<String> getCollectionNames(long id, String dbName) throws IOException;

    /**
     * 根据数据源id，表名查询出该表所有字段
     */
    List<String> getColumns(Long id, String tableName) throws IOException;

    /**
     * 根据 sql 语句获取字段
     */
    List<String> getColumnsByQuerySql(Long datasourceId, String querySql) throws SQLException;

    /**
     * 获取PG table schema
     */
    List<String> getTableSchema(Long id);
}
