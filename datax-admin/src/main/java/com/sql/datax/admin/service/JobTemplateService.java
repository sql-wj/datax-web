package com.sql.datax.admin.service;


import com.sql.datatx.core.biz.model.ReturnT;
import com.sql.datax.admin.entity.JobTemplate;

import java.util.Map;

/**
 * core job action for datax-web
 */
public interface JobTemplateService {

    /**
     * page list
     */
    Map<String, Object> pageList(int start, int length, int jobGroup, String jobDesc, String executorHandler, int userId, Integer[] projectIds);

    /**
     * add job
     */
    ReturnT<String> add(JobTemplate jobTemplate);

    /**
     * update job
     */
    ReturnT<String> update(JobTemplate jobTemplate);

    /**
     * remove job
     */
    ReturnT<String> remove(int id);
}
