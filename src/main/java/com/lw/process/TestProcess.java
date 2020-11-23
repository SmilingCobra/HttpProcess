package com.lw.process;

import com.alibaba.fastjson.JSONObject;
import com.lw.entity.BaseRequest;
import com.lw.entity.BaseResponse;
import com.lw.http.TestHttpService;
import com.lw.util.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class TestProcess {
    @Autowired
    private TestHttpService testHttpService;

    public BaseResponse hello(BaseRequest baseRequest, HttpServletRequest httpServletRequest){
        JSONObject service = null;
        BaseResponse response = new BaseResponse();
        try {
            service = testHttpService.service(baseRequest);
            response.setData(service);
        } catch (Exception e) {
            IOUtil.print(e.getMessage());
        }

        return response;
    }
}
