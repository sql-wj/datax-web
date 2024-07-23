package com.sql.datax.admin.core.conf;

import com.sql.datax.admin.core.scheduler.JobScheduler;
import com.sql.datax.admin.mapper.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * xxl-job config
 */
@Component
public class JobAdminConfig implements InitializingBean, DisposableBean {
    @Getter
    private static JobAdminConfig adminConfig = null;

    // ---------------------- XxlJobScheduler ----------------------

    private JobScheduler xxlJobScheduler;

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;

        xxlJobScheduler = new JobScheduler();
        xxlJobScheduler.init();
    }

    @Override
    public void destroy() throws Exception {
        xxlJobScheduler.destroy();
    }

    // ---------------------- XxlJobScheduler ----------------------

    // conf
    @Getter
    @Value("${datax.job.i18n}")
    private String i18n;

    @Getter
    @Value("${datax.job.accessToken}")
    private String accessToken;

    @Getter
    @Value("${spring.mail.username}")
    private String emailUserName;

    @Value("${datax.job.triggerpool.fast.max}")
    private int triggerPoolFastMax;

    @Value("${datax.job.triggerpool.slow.max}")
    private int triggerPoolSlowMax;

    @Value("${datax.job.logretentiondays}")
    private int logretentiondays;

    @Setter
    @Getter
    @Value("${datasource.aes.key}")
    private String dataSourceAESKey;

    // dao, service

    @Getter
    @Resource
    private JobLogMapper jobLogMapper;
    @Getter
    @Resource
    private JobInfoMapper jobInfoMapper;
    @Getter
    @Resource
    private JobRegistryMapper jobRegistryMapper;
    @Getter
    @Resource
    private JobGroupMapper jobGroupMapper;
    @Getter
    @Resource
    private JobLogReportMapper jobLogReportMapper;
    @Getter
    @Resource
    private JavaMailSender mailSender;
    @Getter
    @Resource
    private DataSource dataSource;
    @Getter
    @Resource
    private JobDatasourceMapper jobDatasourceMapper;

    public int getTriggerPoolFastMax() {
        return triggerPoolFastMax < 200 ? 200 : triggerPoolFastMax;
    }

    public int getTriggerPoolSlowMax() {
        return triggerPoolSlowMax < 100 ? 100 : triggerPoolSlowMax;
    }

    public int getLogretentiondays() {
        return logretentiondays < 7 ? -1 : logretentiondays;
    }

}
