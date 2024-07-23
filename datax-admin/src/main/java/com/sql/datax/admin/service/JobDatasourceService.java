package com.sql.datax.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sql.datax.admin.entity.JobDatasource;

import java.io.IOException;
import java.util.List;

/**
 * jdbc数据源配置表服务接口
 */
public interface JobDatasourceService extends IService<JobDatasource> {
    /**
     * 测试数据源
     */
    Boolean dataSourceTest(JobDatasource jdbcDatasource) throws IOException;

    /**
     * 更新数据源信息
     */
    int update(JobDatasource datasource);

    /**
     * 获取所有数据源
     */
    List<JobDatasource> selectAllDatasource();
}