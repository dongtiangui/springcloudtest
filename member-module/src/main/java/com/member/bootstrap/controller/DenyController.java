package com.member.bootstrap.controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class DenyController {
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public void customErrorType(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8080/login");
    }
    /**
     * sudo docker run --name nginx -p 8088:80 -v /Users/apple/docker/nginx/data/nginx/www:/private/usr/share/nginx/html -v /Users/apple/docker/nginx/data/nginx/conf/nginx.conf:/private/etc/nginx/nginx.conf -v /Users/apple/docker/nginx/data/nginx/logs:/private/var/log/nginx -v /Users/apple/docker/nginx/data/nginx/conf.d:/private/etc/nginx/conf.d -d nginx
     */
}



