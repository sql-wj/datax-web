package com.sql.datax.executor.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 系统工具
 */
public class SystemUtils {
    private static String DATAX_HOME;

    private SystemUtils() {
    }

    /**
     * 获取环境变量中的Datax路径
     */
    public static String getDataXHomePath() {
        if (StringUtils.isNotEmpty(DATAX_HOME)) return DATAX_HOME;
        String dataXHome = System.getenv("DATAX_HOME");
        if (StringUtils.isBlank(dataXHome)) {
            //LOGGER.warn("DATAX_HOME 环境变量为NULL");
            return null;
        }
        DATAX_HOME = dataXHome.endsWith(File.separator) ? dataXHome : dataXHome.concat(File.separator);
        //LOGGER.info("DATAX_HOME:{}", DATAX_HOME);
        return DATAX_HOME;
    }
}
