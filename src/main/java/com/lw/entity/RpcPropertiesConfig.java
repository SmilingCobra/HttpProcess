package com.lw.entity;

import com.lw.config.HttpConfigLoader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "libs")
public class RpcPropertiesConfig implements InitializingBean {

    private  List<RpcProperties> rpcPropertiesList = new ArrayList<RpcProperties>();

    public List<RpcProperties> getRpcPropertiesList() {
        return rpcPropertiesList;
    }

    public void setRpcPropertiesList(List<RpcProperties> rpcPropertiesList) {
        this.rpcPropertiesList = rpcPropertiesList;
    }

    public void afterPropertiesSet() throws Exception {
        HttpConfigLoader.setRpcPropertiesList(rpcPropertiesList);
        HttpConfigLoader.load();
    }
}
