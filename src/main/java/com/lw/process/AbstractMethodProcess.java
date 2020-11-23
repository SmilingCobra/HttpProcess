package com.lw.process;

import com.lw.entity.BaseRequest;
import com.lw.entity.BaseResponse;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractMethodProcess<Req extends BaseRequest,Res extends BaseResponse> {


    public Res process(Req request, HttpServletRequest httpServletRequest){

        beforeProcess(request,httpServletRequest);
        Res response = doProcess(request,httpServletRequest);
        afterProcess(request,response,httpServletRequest);

        return response;
    }

    protected abstract void beforeProcess(Req request, HttpServletRequest httpServletRequest);


    protected abstract Res doProcess(Req request, HttpServletRequest httpServletRequest);

    protected abstract void afterProcess(Req request,Res response, HttpServletRequest httpServletRequest);


}
