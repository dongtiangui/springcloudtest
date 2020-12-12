package com.hik.consul.hikconsul;

import com.corundumstudio.socketio.SocketIOServer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ServletComponentScan
@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableDiscoveryClient
@EnableCircuitBreaker
public class HikConsulApplication implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(HikConsulApplication.class, args);
  }

  @Autowired
  private SocketIOServer socketIOServer;

  @Override
  public void run(String... args) throws Exception {
//    socketIOServer.start();
    System.out.println("socket.io启动成功");
  }

  @LoadBalanced
  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }
  @Bean
  public IRule ribbonRule() {
    return new RetryRule(new RandomRule());//这里配置策略，和配置文件对应
  }

}
