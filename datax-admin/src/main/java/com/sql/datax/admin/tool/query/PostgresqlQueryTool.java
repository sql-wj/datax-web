package com.sql.datax.admin.tool.query;

import com.sql.datax.admin.entity.JobDatasource;

import java.sql.SQLException;

/**
 * TODO
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName PostgresqlQueryTool
 * @Version 1.0
 * @since 2019/8/2 11:28
 */
public class PostgresqlQueryTool extends BaseQueryTool implements QueryToolInterface {
    public PostgresqlQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }

}
