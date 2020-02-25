package com.oauth2.oauthsecurity.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class LoginErrorAspect {

    @Pointcut(value = "@annotation(com.oauth2.oauthsecurity.aspect.LogAnnotation)")
    public void Pointcut(){}

    @Around(value = "Pointcut()")
    public Object around(){

        return null;
    }
//    异常通知
    @AfterThrowing(value = "Pointcut()")
    public void sendLoginHttp(){

    }

}
