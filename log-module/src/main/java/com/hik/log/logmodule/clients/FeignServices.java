package com.hik.log.logmodule.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

// value 表示调用springcloud 服务名
@FeignClient(value = "MEMBER")
public interface FeignServices {
    // 声明接口调用

    @RequestMapping("/")
    String getPortTwo();

}
