package com.eureka.bootstrap.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@EnableEurekaServer
@SpringBootApplication
@Controller
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getToken")
    public Map<String,Object> getToken() {
        Map<String,Object> map = new HashMap<>();
        map.put("token","123");
        return map;
    }
}
