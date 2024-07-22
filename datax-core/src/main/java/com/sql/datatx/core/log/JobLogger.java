package com.sql.datatx.core.log;

import com.sql.datatx.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class JobLogger {
    private static final Logger logger = LoggerFactory.getLogger("datax-web logger");

    /**
     * append log
     */
    private static void logDetail(StackTraceElement call, String appendLog) {
        // "yyyy-MM-dd HH:mm:ss [fileName.MethodName-LineNumber] log";
        assert call.getFileName() != null;
        String formatAppendLog = DateUtil.formatDateTime(new Date()) + " " +
                "[" + call.getFileName().replace("java", "") + call.getMethodName() +
                "-" + call.getLineNumber() + "]" + " " +
                (appendLog != null ? appendLog : "");

        String logFileName = JobFileAppender.contextHolder.get();
        if (logFileName != null && !logFileName.trim().isEmpty()) {
            JobFileAppender.appendLog(logFileName, formatAppendLog);
        } else {
            logger.info(">>> {}", formatAppendLog);
        }
    }

    /**
     * append log with pattern
     *
     * @param appendLogPattern   like "aaa {} bbb {} ccc"
     * @param appendLogArguments like "111, true"
     */
    public static void log(String appendLogPattern, Object... appendLogArguments) {

        FormattingTuple ft = MessageFormatter.arrayFormat(appendLogPattern, appendLogArguments);
        String appendLog = ft.getMessage();

        /*appendLog = appendLogPattern;
        if (appendLogArguments!=null && appendLogArguments.length>0) {
            appendLog = MessageFormat.format(appendLogPattern, appendLogArguments);
        }*/

        StackTraceElement callInfo = new Throwable().getStackTrace()[1];
        logDetail(callInfo, appendLog);
    }

    /**
     * append exception stack
     */
    public static void log(Throwable e) {

        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        String appendLog = stringWriter.toString();

        StackTraceElement callInfo = new Throwable().getStackTrace()[1];
        logDetail(callInfo, appendLog);
    }

}
