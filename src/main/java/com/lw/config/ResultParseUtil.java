package com.lw.config;

import com.alibaba.fastjson.JSONObject;
import com.lw.entity.BaseRequest;
import com.lw.entity.BaseResponse;
import com.lw.process.ParseRunnable;

public class ResultParseUtil {

    public static String callback(BaseRequest request, ParseRunnable runnable){
        BaseResponse baseResponse = null;

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
