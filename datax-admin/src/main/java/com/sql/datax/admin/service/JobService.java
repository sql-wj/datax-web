package com.sql.datax.admin.service;


import com.sql.datatx.core.biz.model.ReturnT;
import com.sql.datax.admin.dto.DataXBatchJsonBuildDto;
import com.sql.datax.admin.entity.JobInfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * core job action for datax-web
 */
public interface JobService {

    /**
     * page list
     */
    Map<String, Object> pageList(int start, int length, int jobGroup, int triggerStatus, String jobDesc, String glueType, int userId,Integer[] projectIds);

    List<JobInfo> list();

    /**
     * add job
     */
    ReturnT<String> add(JobInfo jobInfo);

    /**
     * update job
     */
    ReturnT<String> update(JobInfo jobInfo);

    /**
     * remove job
     */
    ReturnT<String> remove(int id);

    /**
     * start job
     */
    ReturnT<String> start(int id);

    /**
     * stop job
     */
    ReturnT<String> stop(int id);

    /**
     * dashboard info
     */
    Map<String, Object> dashboardInfo();

    /**
     * chart info
     */
    ReturnT<Map<String, Object>> chartInfo();

    /**
     * batch add
     */
    ReturnT<String> batchAdd(DataXBatchJsonBuildDto dto) throws IOException;
}
