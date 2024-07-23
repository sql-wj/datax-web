package com.sql.datax.admin.core.route;

import com.sql.datatx.core.biz.model.ReturnT;
import com.sql.datatx.core.biz.model.TriggerParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class ExecutorRouter {
    protected static Logger logger = LoggerFactory.getLogger(ExecutorRouter.class);

    /**
     * route address
     */
    public abstract ReturnT<String> route(TriggerParam triggerParam, List<String> addressList);

}
