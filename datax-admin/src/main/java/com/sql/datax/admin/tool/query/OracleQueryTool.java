package com.sql.datax.admin.tool.query;

import com.sql.datax.admin.entity.JobDatasource;

import java.sql.SQLException;

/**
 * Oracle数据库使用的查询工具
 */
public class OracleQueryTool extends BaseQueryTool implements QueryToolInterface {

    public OracleQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }
}
