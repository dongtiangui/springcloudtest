package com.ribbon.boostrap.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class FeignServicesImpl {

    @Autowired
    private FeignServices feignServices;

    @RequestMapping("main")
    public String get(){
        return  feignServices.getPortTwo();
    }
}
