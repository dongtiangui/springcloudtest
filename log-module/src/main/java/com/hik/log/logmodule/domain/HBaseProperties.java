package com.hik.log.logmodule.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(value = "hbase")
public class HBaseProperties {

    private Map<String, String> config;

    public Map<String, String> getConfig() {
        return config;
    }
    public void setConfig(Map<String, String> config) {
        this.config = config;
    }
}
