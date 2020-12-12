package com.member.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@MapperScan("com.member.bootstrap.mapper")
public class MemberModuleApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    @RequestMapping("/index")
    public String index() {
        return "Hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(MemberModuleApplication.class, args);
    }

//    启用服务远程调用
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate template = new RestTemplate();
        template.setErrorHandler(new DefaultResponseErrorHandler());
        return template;
    }

}
