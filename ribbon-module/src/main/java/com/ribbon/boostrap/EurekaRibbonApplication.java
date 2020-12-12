package com.ribbon.boostrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * 负载均衡
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.ribbon.boostrap.clients"})
@EnableHystrix
@EnableOAuth2Client
public class EurekaRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaRibbonApplication.class, args);
    }
}
