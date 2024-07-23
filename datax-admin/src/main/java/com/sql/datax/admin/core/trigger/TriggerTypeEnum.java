package com.sql.datax.admin.core.trigger;

import com.sql.datax.admin.core.util.I18nUtil;
import lombok.Getter;

/**
 * trigger type enum
 */
@Getter
public enum TriggerTypeEnum {

    MANUAL(I18nUtil.getString("jobconf_trigger_type_manual")),
    CRON(I18nUtil.getString("jobconf_trigger_type_cron")),
    RETRY(I18nUtil.getString("jobconf_trigger_type_retry")),
    PARENT(I18nUtil.getString("jobconf_trigger_type_parent")),
    API(I18nUtil.getString("jobconf_trigger_type_api"));

    TriggerTypeEnum(String title) {
        this.title = title;
    }

    private final String title;

}
