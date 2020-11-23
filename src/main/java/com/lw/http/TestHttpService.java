package com.lw.http;

import com.alibaba.fastjson.JSONObject;
import com.lw.entity.BaseRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TestHttpService extends AbstractHttpService{
    @Override
    protected void init() {
        serviceName = "wechat";
    }

    @Override
    protected JSONObject parse2JSONObject(String response) {
        return JSONObject.parseObject(response);
    }

    //?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
    @Override
    protected void makeParams(BaseRequest request, Map params) {
        params.put("appid","APPID");
        params.put("js_code","JSCODE");
        params.put("grant_type","authorization_code");

    }

    @Override
    protected JSONObject parseResponse(JSONObject jsonRes, BaseRequest request, Map params) {
        return jsonRes;
    }
}
