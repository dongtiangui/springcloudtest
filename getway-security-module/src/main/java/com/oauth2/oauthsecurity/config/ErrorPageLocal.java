package com.oauth2.oauthsecurity.config;

import com.oauth2.oauthsecurity.domain.CustomErrorType;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *  error page
 */

@Controller
@RequestMapping(value = "/error")
public class ErrorPageLocal implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage page404 = new ErrorPage("/error/page404");
        ErrorPage page500 = new ErrorPage("/error/page500");
        ErrorPage page403 = new ErrorPage("/error/page403");
        registry.addErrorPages(page403,page404,page500);
    }

    @RequestMapping(value = "/404page")
    @ResponseBody
    public ResponseEntity<CustomErrorType> page404(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new CustomErrorType(status.value(), ex.getMessage()), status);
    }
    @ResponseBody
    @RequestMapping(value = "/page500")
    public ResponseEntity<CustomErrorType> page500(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new CustomErrorType(status.value(), ex.getMessage()), status);
    }
    @ResponseBody
    @RequestMapping(value = "/page403")
    public ResponseEntity<CustomErrorType> page403(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new CustomErrorType(status.value(), ex.getMessage()), status);
    }
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
