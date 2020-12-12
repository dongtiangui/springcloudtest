package com.hik.log.logmodule;

import com.hik.log.logmodule.domain.User;
import com.hik.log.logmodule.services.HbaseServices;
import com.hik.log.logmodule.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.DelegatingApplicationListener;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.hik.log.logmodule"})
@RestController
public class LogModuleApplication {


  @Autowired
  private HbaseServices hbaseServices;


  public static void main(String[] args) {
    /**
     * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
     * 解决netty冲突后初始化client时还会抛出异常
     * java.lang.IllegalStateException: availableProcessors is already set to [8], rejecting [8]
     */
    System.setProperty("es.set.netty.runtime.available.processors", "false");
    SpringApplication.run(LogModuleApplication.class, args);
  }
  @RequestMapping("/")
  public String getRedis() throws IOException {
//    redisUtil.set("1","2");
//    return (String) redisUtil.get("1");
    return hbaseServices.searchAll("user", User.class).toString();
  }


  @Bean
  @LoadBalanced
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }
}
