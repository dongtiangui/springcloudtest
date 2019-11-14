package com.integral.boot.configuration;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPages implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {

        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404page"),
                new ErrorPage(HttpStatus.BAD_GATEWAY,"/500page"));

    }
    @RequestMapping(value = "/404page")
    public String page404(){
        return "error/404";
    }
    @RequestMapping(value = "/500page")
    public String page500(){
        return "error/500";
    }

}
