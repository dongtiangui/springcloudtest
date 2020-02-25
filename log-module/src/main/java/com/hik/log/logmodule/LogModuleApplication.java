package com.hik.log.logmodule;

import com.hik.log.logmodule.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.hik.log.logmodule"})
@RestController
public class LogModuleApplication {

  @Autowired
  private RedisUtil redisUtil;

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
  public String getRedis(){
    redisUtil.set("1","2");
    return (String) redisUtil.get("1");
  }
  @Bean
  @LoadBalanced
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }
}
