package com.sql.datax.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.sql.datax.admin.dto.DataXJsonBuildDto;
import com.sql.datax.admin.entity.JobDatasource;
import com.sql.datax.admin.service.DataxJsonService;
import com.sql.datax.admin.service.JobDatasourceService;
import com.sql.datax.admin.tool.datax.DataxJsonHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * com.sql.datax json构建实现类
 */
@Service
public class DataxJsonServiceImpl implements DataxJsonService {

    @Resource
    private JobDatasourceService jobJdbcDatasourceService;

    @Override
    public String buildJobJson(DataXJsonBuildDto dataXJsonBuildDto) {
        DataxJsonHelper dataxJsonHelper = new DataxJsonHelper();
        // reader
        JobDatasource readerDatasource = jobJdbcDatasourceService.getById(dataXJsonBuildDto.getReaderDatasourceId());
        // reader plugin init
        dataxJsonHelper.initReader(dataXJsonBuildDto, readerDatasource);
        JobDatasource writerDatasource = jobJdbcDatasourceService.getById(dataXJsonBuildDto.getWriterDatasourceId());
        dataxJsonHelper.initWriter(dataXJsonBuildDto, writerDatasource);

        return JSON.toJSONString(dataxJsonHelper.buildJob());
    }
}