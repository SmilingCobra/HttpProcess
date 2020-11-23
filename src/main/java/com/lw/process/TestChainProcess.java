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
public class TestChainProcess extends AbstractMethodProcess {

    @Autowired
    private TestHttpService testHttpService;
    @Override
    public void beforeProcess(BaseRequest request, HttpServletRequest httpServletRequest) {
        IOUtil.print("beforeProcess ...");
    }

    @Override
    public BaseResponse doProcess(BaseRequest request, HttpServletRequest httpServletRequest) {
        JSONObject service = null;
        BaseResponse response = new BaseResponse();
        try {
            service = testHttpService.service(request);
            response.setData(service);
        } catch (Exception e) {
            IOUtil.print(e.getMessage());
        }

        return response;
    }

    @Override
    public void afterProcess(BaseRequest request, BaseResponse response, HttpServletRequest httpServletRequest) {
        IOUtil.print(response == null ? "nothing got":response.getData().toJSONString());
        IOUtil.print("afterProcess ...");
    }
}
