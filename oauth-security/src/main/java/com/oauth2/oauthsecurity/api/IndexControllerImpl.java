package com.oauth2.oauthsecurity.api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/api")
public class IndexControllerImpl{

    @RequestMapping(value = "/index")
    public String findWelcome() {
        return "我是统一认证中心";
    }
}
