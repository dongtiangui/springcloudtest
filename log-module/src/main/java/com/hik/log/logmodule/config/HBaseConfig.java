package com.hik.log.logmodule.config;

import com.hik.log.logmodule.domain.HBaseProperties;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

import java.util.Map;
import java.util.Set;

@Configuration
@EnableConfigurationProperties(HBaseProperties.class)
public class HBaseConfig {

    @Autowired
    private HBaseProperties hBaseProperties;

    @Bean
    public HbaseTemplate hbaseTemplate(){
        HbaseTemplate template = new HbaseTemplate();
        template.setConfiguration(configuration());
        template.setAutoFlush(true);
        return template;
    }

    public org.apache.hadoop.conf.Configuration configuration(){
        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        Map<String, String> config = hBaseProperties.getConfig();
        Set<String> keySet = config.keySet();
        for (String ks : keySet) {
            configuration.set(ks,config.get(ks));
        }
        return configuration;
    }
}
