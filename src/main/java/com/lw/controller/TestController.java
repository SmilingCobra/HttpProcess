package com.lw.controller;

import com.alibaba.fastjson.JSONObject;
import com.lw.config.ResultParseUtil;
import com.lw.entity.BaseRequest;
import com.lw.http.TestHttpService;
import com.lw.process.TestChainProcess;
import com.lw.process.TestProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestProcess testProcess;
    @Autowired
    private TestChainProcess testChainProcess;

@RequestMapping("/hello")
    public String hello(BaseRequest baseRequest, HttpServletRequest httpServletRequest){
   return ResultParseUtil.callback(baseRequest,"hello",
           request1->
        testProcess.hello(baseRequest,httpServletRequest)
   );
}

    @RequestMapping("/helloChain")
    public String helloChain(BaseRequest baseRequest, HttpServletRequest httpServletRequest){
        return ResultParseUtil.callback(baseRequest,"helloChain",
                request1->
                        testChainProcess.process(baseRequest,httpServletRequest)
        );
    }

}
