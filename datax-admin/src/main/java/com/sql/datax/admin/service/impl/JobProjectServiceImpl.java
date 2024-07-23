package com.sql.datax.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sql.datax.admin.entity.JobProject;
import com.sql.datax.admin.mapper.JobProjectMapper;
import com.sql.datax.admin.service.JobProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * JobProjectServiceImpl
 */
@Service("jobProjectService")
public class JobProjectServiceImpl extends ServiceImpl<JobProjectMapper, JobProject> implements JobProjectService {

    @Resource
    private JobProjectMapper jobProjectMapper;

    @Override
    public IPage<JobProject> getProjectListPaging(Integer pageSize, Integer pageNo, String searchName) {
        Page<JobProject> page = new Page<>(pageNo, pageSize);
        return jobProjectMapper.getProjectListPaging(page, searchName);
    }
}