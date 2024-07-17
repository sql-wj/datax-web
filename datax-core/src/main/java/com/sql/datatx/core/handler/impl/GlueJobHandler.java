package com.sql.datatx.core.handler.impl;

import com.sql.datatx.core.biz.model.ReturnT;
import com.sql.datatx.core.biz.model.TriggerParam;
import com.sql.datatx.core.handler.IJobHandler;
import com.sql.datatx.core.log.JobLogger;

/**
 * glue job handler
 *
 * @author xuxueli 2016-5-19 21:05:45
 */
public class GlueJobHandler extends IJobHandler {
    private final long glueUpdatetime;
    private final IJobHandler jobHandler;

    public GlueJobHandler(IJobHandler jobHandler, long glueUpdatetime) {
        this.jobHandler = jobHandler;
        this.glueUpdatetime = glueUpdatetime;
    }

    public long getGlueUpdatetime() {
        return glueUpdatetime;
    }

    @Override
    public ReturnT<String> execute(TriggerParam tgParam) throws Exception {
        JobLogger.log("----------- glue.version:" + glueUpdatetime + " -----------");
        return jobHandler.execute(tgParam);
    }
}
