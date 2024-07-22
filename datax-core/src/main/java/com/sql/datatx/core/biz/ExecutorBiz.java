package com.sql.datatx.core.biz;

import com.sql.datatx.core.biz.model.LogResult;
import com.sql.datatx.core.biz.model.ReturnT;
import com.sql.datatx.core.biz.model.TriggerParam;

public interface ExecutorBiz {

    /**
     * beat
     */
    ReturnT<String> beat();

    /**
     * idle beat
     */
    ReturnT<String> idleBeat(int jobId);

    /**
     * kill
     */
    ReturnT<String> kill(int jobId);

    /**
     * log
     */
    ReturnT<LogResult> log(long logDateTim, long logId, int fromLineNum);

    /**
     * run
     */
    ReturnT<String> run(TriggerParam triggerParam);
}
