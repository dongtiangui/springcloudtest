package com.ribbon.boostrap.clients;

import com.ribbon.boostrap.fallback.ErrorFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

// value 表示调用springcloud 注册中心ID
@FeignClient(value = "MEMBER",fallbackFactory = ErrorFallbackFactory.class)
public interface FeignServices {
    // 声明接口调用
    @RequestMapping("/")
    String getPortTwo();
}
