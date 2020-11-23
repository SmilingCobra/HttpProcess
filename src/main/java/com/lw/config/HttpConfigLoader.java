package com.lw.config;

import com.lw.entity.RpcProperties;
import org.apache.http.client.config.RequestConfig;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpConfigLoader {

    private static List<RpcProperties> rpcPropertiesList = new ArrayList<RpcProperties>();

    public static void setRpcPropertiesList(List<RpcProperties> rpcPropertiesList) {
        HttpConfigLoader.rpcPropertiesList = rpcPropertiesList;
    }

    public static void load(){
        Map<String,RpcProperties> rpcPropertiesMap = new HashMap<String, RpcProperties>();
        Map<String, RequestConfig> requestConfigMap = new HashMap<String, RequestConfig>();
        if(rpcPropertiesList!=null){
            for(RpcProperties rpcProperties : rpcPropertiesList){
                String serviceName = rpcProperties.getServiceName();
                rpcPropertiesMap.put(serviceName,rpcProperties);

                RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(rpcProperties.getConnectionTimeout())
                        .setSocketTimeout(rpcProperties.getSocketTimeout()).build();
                requestConfigMap.put(serviceName,requestConfig);
            }
        }
        HttpConfigManager.rpcPropertiesMap = rpcPropertiesMap;
        HttpConfigManager.requestConfigMap = requestConfigMap;
        System.out.println(HttpConfigManager.rpcPropertiesMap);
    }
}
