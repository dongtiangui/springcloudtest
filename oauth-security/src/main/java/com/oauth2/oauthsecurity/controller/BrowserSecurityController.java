//package com.oauth2.oauthsecurity.controller;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
//import org.springframework.security.web.savedrequest.RequestCache;
//import org.springframework.security.web.savedrequest.SavedRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * 需要认证的路径进入
// */
//@Controller
//public class BrowserSecurityController {
//    private Logger logger = LoggerFactory.getLogger(getClass());
//    // 原请求信息的缓存及恢复
//    private RequestCache requestCache = new HttpSessionRequestCache();
//    // 用于重定向
//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//    @RequestMapping("/authentication/require")
//    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
//    public String cheek(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        SavedRequest savedRequest = requestCache.getRequest(request, response);
//        PrintWriter out = null;
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
//        if (savedRequest!=null){
//            String url = savedRequest.getRedirectUrl();
//            if (StringUtils.contains(url,".html")){
//                redirectStrategy.sendRedirect(request,response,"http://localhost:8080/login");
//            }
//        }
//        return "401";
//    }
//  /**
//   * SavedRequest savedRequest = requestCache.getRequest(request, response); // 默认的重定向地址 String
//   * redirectUrl = "/index.html"; // 注意1
//   * 如果用户直接访问login.html，这时候ExceptionTranslationFilter不会有任何操作，SavedRequest可能为null // 注意2
//   * 只有GET请求才会说进行重定向，如果原请求为POST等，没有重定向的价值，仍使用默认的重定向地址 if (savedRequest != null) {
//   * if("GET".equals(savedRequest.getMethod())) { redirectUrl = savedRequest.getRedirectUrl(); } //
//   * 最后记得删除requestCache // 注意这里的removeRequest传入的参数仍然是当前的request和response对象
//   * requestCache.removeRequest(request, response); } // 重定向到目标地址，这个是Spring
//   * MVC的写法，其他的MVC框架请采用各自自己的做法 return "redirect:" + redirectUrl;
//   */
//}
