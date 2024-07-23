package com.sql.datax.admin.core.route.strategy;

import com.sql.datatx.core.biz.ExecutorBiz;
import com.sql.datatx.core.biz.model.ReturnT;
import com.sql.datatx.core.biz.model.TriggerParam;
import com.sql.datax.admin.core.route.ExecutorRouter;
import com.sql.datax.admin.core.scheduler.JobScheduler;
import com.sql.datax.admin.core.util.I18nUtil;

import java.util.List;

public class ExecutorRouteBusyover extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        StringBuilder idleBeatResultSB = new StringBuilder();
        for (String address : addressList) {
            // beat
            ReturnT<String> idleBeatResult;
            try {
                ExecutorBiz executorBiz = JobScheduler.getExecutorBiz(address);
                idleBeatResult = executorBiz.idleBeat(triggerParam.getJobId());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                idleBeatResult = new ReturnT<>(ReturnT.FAIL_CODE, ""+e );
            }
            idleBeatResultSB.append((idleBeatResultSB.length() > 0) ? "<br><br>" : "").append(I18nUtil.getString("jobconf_idleBeat")).append("：")
                    .append("<br>address：").append(address)
                    .append("<br>code：").append(idleBeatResult.getCode())
                    .append("<br>msg：").append(idleBeatResult.getMsg());

            // beat success
            if (idleBeatResult.getCode() == ReturnT.SUCCESS_CODE) {
                idleBeatResult.setMsg(idleBeatResultSB.toString());
                idleBeatResult.setContent(address);
                return idleBeatResult;
            }
        }
        return new ReturnT<>(ReturnT.FAIL_CODE, idleBeatResultSB.toString());
    }

}
