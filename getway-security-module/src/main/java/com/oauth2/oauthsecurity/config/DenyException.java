package com.oauth2.oauthsecurity.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import javax.servlet.http.HttpServletRequest;
@ControllerAdvice
public class DenyException {

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public String customErrorType(HttpServletRequest request){
        return "forward:";
    }
}
