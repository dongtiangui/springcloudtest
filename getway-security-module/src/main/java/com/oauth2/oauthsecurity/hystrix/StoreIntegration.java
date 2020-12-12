package com.oauth2.oauthsecurity.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/hystrix")
public class StoreIntegration {
    @HystrixCommand(fallbackMethod = "stubMyService",
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
            }
    )
    @RequestMapping("/test")
    public Object getStores(Map<String, Object> parameters) {
        return new RuntimeException();
    }
    public Object stubMyService(Map<String, Object> parameters) {
        return "服务降级了";
    }
}
