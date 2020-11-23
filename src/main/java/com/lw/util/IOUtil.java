package com.lw.util;

import com.lw.config.LogContext;
import com.lw.config.LogSession;

public class IOUtil {
    /**
     * 模拟日志打印
     */
    public static void print(String text){
        LogContext logContext = LogSession.logSession.get();
        System.out.println("logInfo: "+text+"\t"
        +"logId: "+logContext.getLogId()+"\t"
                +"module: "+logContext.getModule()
        );
    }
}
