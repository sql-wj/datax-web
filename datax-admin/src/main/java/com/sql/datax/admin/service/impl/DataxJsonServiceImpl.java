package com.sql.datax.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.sql.datax.admin.dto.DataXBatchJsonBuildDto;
import com.sql.datax.admin.dto.DataXJsonBuildDto;
import com.sql.datax.admin.entity.JobDatasource;
import com.sql.datax.admin.entity.JobInfo;
import com.sql.datax.admin.entity.JobTemplate;
import com.sql.datax.admin.mapper.JobInfoMapper;
import com.sql.datax.admin.mapper.JobTemplateMapper;
import com.sql.datax.admin.service.DatasourceQueryService;
import com.sql.datax.admin.service.DataxJsonService;
import com.sql.datax.admin.service.JobDatasourceService;
import com.sql.datax.admin.tool.datax.DataxJsonHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * com.sql.datax json构建实现类
 *
 * @author jingwk
 * @ClassName DataxJsonServiceImpl
 * @Version 2.0
 * @since 2020/01/11 17:15
 */
@Service
public class DataxJsonServiceImpl implements DataxJsonService {

    @Autowired
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