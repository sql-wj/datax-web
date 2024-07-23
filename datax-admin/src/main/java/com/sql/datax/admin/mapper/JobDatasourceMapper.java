package com.sql.datax.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sql.datax.admin.entity.JobDatasource;
import org.apache.ibatis.annotations.Mapper;

/**
 * jdbc数据源配置表数据库访问层
 */
@Mapper
public interface JobDatasourceMapper extends BaseMapper<JobDatasource> {
    int update(JobDatasource datasource);

}