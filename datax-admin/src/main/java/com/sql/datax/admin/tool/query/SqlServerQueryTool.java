package com.sql.datax.admin.tool.query;

import com.sql.datax.admin.entity.JobDatasource;

import java.sql.SQLException;

/**
 * sql server
 */
public class SqlServerQueryTool extends BaseQueryTool implements QueryToolInterface {
    public SqlServerQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }
}
