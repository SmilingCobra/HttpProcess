package com.lw.config;

import com.alibaba.fastjson.JSONObject;
import com.lw.entity.BaseRequest;
import com.lw.entity.BaseResponse;
import com.lw.process.ParseRunnable;

import java.util.UUID;

public class ResultParseUtil {

    public static String callback(BaseRequest request,String module, ParseRunnable runnable){
        BaseResponse baseResponse = null;
        LogContext logContext = LogSession.logSession.get();
        if(logContext == null){
            logContext = new LogContext();
        }
        logContext.setLogId(UUID.randomUUID().toString());
        logContext.setModule(module);
        LogSession.logSession.set(logContext);
        try {
            baseResponse = runnable.run(request);
        } catch (Exception e) {
            baseResponse = new BaseResponse();
            baseResponse.setStatusCode(-1);
            baseResponse.setStatusMsg("response error");
        }

        return JSONObject.toJSONString(baseResponse);
    }
}
