package com.sql.datax.admin.core.thread;

import com.sql.datatx.core.enums.RegistryConfig;
import com.sql.datax.admin.core.conf.JobAdminConfig;
import com.sql.datax.admin.entity.JobGroup;
import com.sql.datax.admin.entity.JobRegistry;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * job registry instance
 */
public class JobRegistryMonitorHelper {
    private static final Logger logger = LoggerFactory.getLogger(JobRegistryMonitorHelper.class);

    @Getter
    private static final JobRegistryMonitorHelper instance = new JobRegistryMonitorHelper();

    private Thread registryThread;
    private volatile boolean toStop = false;

    public void start() {
        registryThread = new Thread(() -> {
            while (!toStop) {
                try {
                    // auto registry group
                    List<JobGroup> groupList = JobAdminConfig.getAdminConfig().getJobGroupMapper().findByAddressType(0);
                    if (groupList != null && !groupList.isEmpty()) {
                        // remove dead address (admin/executor)
                        List<Integer> ids = JobAdminConfig.getAdminConfig().getJobRegistryMapper().findDead(RegistryConfig.DEAD_TIMEOUT, new Date());
                        if (ids != null && !ids.isEmpty()) {
                            JobAdminConfig.getAdminConfig().getJobRegistryMapper().removeDead(ids);
                        }

                        // fresh online address (admin/executor)
                        HashMap<String, List<String>> appAddressMap = new HashMap<>();
                        List<JobRegistry> list = JobAdminConfig.getAdminConfig().getJobRegistryMapper().findAll(RegistryConfig.DEAD_TIMEOUT, new Date());
                        if (list != null) {
                            for (JobRegistry item : list) {
                                if (RegistryConfig.RegistType.EXECUTOR.name().equals(item.getRegistryGroup())) {
                                    String appName = item.getRegistryKey();
                                    List<String> registryList = appAddressMap.get(appName);
                                    if (registryList == null) {
                                        registryList = new ArrayList<>();
                                    }

                                    if (!registryList.contains(item.getRegistryValue())) {
                                        registryList.add(item.getRegistryValue());
                                    }
                                    appAddressMap.put(appName, registryList);
                                }
                            }
                        }

                        // fresh group address
                        for (JobGroup group : groupList) {
                            List<String> registryList = appAddressMap.get(group.getAppName());
                            StringBuilder addressListStr = null;
                            if (registryList != null && !registryList.isEmpty()) {
                                Collections.sort(registryList);
                                addressListStr = new StringBuilder();
                                for (String item : registryList) {
                                    addressListStr.append(item).append(",");
                                }
                                addressListStr = new StringBuilder(addressListStr.substring(0, addressListStr.length() - 1));
                            }
                            assert addressListStr != null;
                            group.setAddressList(addressListStr.toString());
                            JobAdminConfig.getAdminConfig().getJobGroupMapper().update(group);
                        }
                    }
                } catch (Exception e) {
                    if (!toStop) {
                        logger.error(">>>>>>>>>>> datax-web, job registry monitor thread error", e);
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(RegistryConfig.BEAT_TIMEOUT);
                } catch (InterruptedException e) {
                    if (!toStop) {
                        logger.error(">>>>>>>>>>> datax-web, job registry monitor thread error", e);
                    }
                }
            }
            logger.info(">>>>>>>>>>> datax-web, job registry monitor thread stop");
        });
        registryThread.setDaemon(true);
        registryThread.setName("datax-web, admin JobRegistryMonitorHelper");
        registryThread.start();
    }

    public void toStop() {
        toStop = true;
        // interrupt and wait
        registryThread.interrupt();
        try {
            registryThread.join();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
