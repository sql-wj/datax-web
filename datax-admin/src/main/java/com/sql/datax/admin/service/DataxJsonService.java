package com.sql.datax.admin.service;

import com.sql.datax.admin.dto.DataXJsonBuildDto;

/**
 * com.sql.datax json构建服务层接口
 */
public interface DataxJsonService {

    /**
     * build datax json
     */
    String buildJobJson(DataXJsonBuildDto dto);
}
