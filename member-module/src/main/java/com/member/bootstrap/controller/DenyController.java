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
}



