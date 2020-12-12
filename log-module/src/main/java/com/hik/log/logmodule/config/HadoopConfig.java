package com.hik.log.logmodule.config;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
@Configuration
public class HadoopConfig {

    @Value("${hbase.config.fs.defaultFS}")
    private String nameNode;
    @Bean
    public FileSystem fileSystem() throws IOException {
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("fs.defaultFS",nameNode);
        return FileSystem.newInstance(configuration);
    }
}
