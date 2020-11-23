package com.lw.http;

import com.alibaba.fastjson.JSONObject;
import com.lw.config.HttpConfigManager;
import com.lw.entity.BaseRequest;
import com.lw.entity.RpcProperties;
import com.lw.util.IOUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractHttpService<Req extends BaseRequest> implements InitializingBean {

   private CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    protected String serviceName;

    /**
     * 设置serviceName
     */
    protected abstract void init();

    public void afterPropertiesSet() throws Exception {
        init();
    }

    public JSONObject service(Req request) throws Exception{
        JSONObject result = null;
        if(serviceName == null){
            throw new NullPointerException("service name can not be null");
        }
        RpcProperties rpcProperties = HttpConfigManager.rpcPropertiesMap.get(serviceName);
        if(rpcProperties == null){
            throw new NullPointerException("the http config of"+serviceName+"not found");
        }
        Map<String,Object> params  = new HashMap<String, Object>();
       //把request中的参数设置到params中
        try {
            makeParams(request, params);
        } catch (Exception e) {
            throw new  Exception(serviceName+"makeParams method error");
        }

        JSONObject jsonRes = query(rpcProperties,params);

        try {
            result = parseResponse(jsonRes,request,params);
        } catch (Exception e) {
            throw new  Exception(serviceName+"parseResponse method error");
        }
        return result;
    }

    protected abstract JSONObject parseResponse(JSONObject jsonRes, Req request, Map<String, Object> params);

    private  JSONObject query(RpcProperties rpcProperties, Map<String, Object> params) throws  Exception{
        JSONObject result = null;
        String url = rpcProperties.getUrl();
        RequestConfig requestConfig = HttpConfigManager.requestConfigMap.get(serviceName);
        String httpMethod = rpcProperties.getHttpMethod();
        String encode = rpcProperties.getEncode();
        HttpRequestBase httpRequestBase = null;
        switch (httpMethod){
            case "post":
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(toStringEntity(params,encode));
                httpRequestBase = httpPost;
               IOUtil.print(String.format("%s post request send",serviceName));
                break;
            case "get":
                HttpGet httpGet = null;
                if(params!=null && params.size()>0){
                    String str = paramsToString(params,encode);
                    IOUtil.print(String.format("get method params is %s",str));
                    httpGet = new HttpGet(url+"?"+str);
                }else {
                    httpGet = new HttpGet(url);
                }
                httpRequestBase = httpGet;
                IOUtil.print(String.format("%s get request send",serviceName));
                break;
            default:
                break;
        }
        if(httpRequestBase!=null){
            httpRequestBase.setConfig(requestConfig);
            CloseableHttpResponse execute = httpClient.execute(httpRequestBase);
            HttpEntity entity = execute.getEntity();
            String response = EntityUtils.toString(entity,encode);
            result = parse2JSONObject(response);
        }
        return result;
    }

    protected abstract JSONObject parse2JSONObject(String response);

    private  String paramsToString(Map<String, Object> params, String encode) throws Exception {
        if(params == null || params.size()<=0){
            return "";
        }
        String str = EntityUtils.toString(toStringEntity(params,encode));
        return str;
    };

    private StringEntity toStringEntity(Map<String, Object> params, String encode) throws UnsupportedEncodingException {
        if(params == null || params.size()<=0){
            return null;
        }
        List<NameValuePair> nvps = new ArrayList<>();
       for(Map.Entry<String,Object> entry:params.entrySet()){
           if(entry.getValue()!=null){
               nvps.add(new BasicNameValuePair(entry.getKey(),entry.getValue().toString()));
           }
       }

       StringEntity stringEntity = new UrlEncodedFormEntity(nvps,encode);
return  stringEntity;

    };

    ;

    protected abstract void makeParams(Req request, Map<String, Object> params) ;

}
