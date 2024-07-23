package com.sql.datax.admin.tool.query;

import com.sql.datax.admin.entity.JobDatasource;

import java.sql.SQLException;

public class PostgresqlQueryTool extends BaseQueryTool implements QueryToolInterface {
    public PostgresqlQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }

}
