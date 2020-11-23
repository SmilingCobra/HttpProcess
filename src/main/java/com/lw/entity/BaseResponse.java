package com.lw.entity;

import com.alibaba.fastjson.JSONObject;

public class BaseResponse {

    private JSONObject data;

    private int statusCode = 0;

    private String statusMsg = "success";

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }
}
