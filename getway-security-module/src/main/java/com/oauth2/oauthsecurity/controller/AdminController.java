//package com.oauth2.oauthsecurity.controller;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
///**
// * 后端管理路由
// */
//
//@RequestMapping(value = "/admin")
//@Controller
//public class AdminController {
//
////    @PreAuthorize, @PostAuthorize, @Secured
////    @Secured 基于角色控制
////    @PreAuthorize 注解适合进入方法前的权限验证
////    @PostAuthorize 注解适合进入方法后进行认证
////    @PreAuthorize("isAuthenticated()")
//    @GetMapping(value = "/login/success.htm")
//    public String getAdminPage(HttpServletRequest request,
//                               HttpServletResponse response,
//                               Model model){
//        User user = (User) request.getSession().getAttribute("user");
//        model.addAttribute("username",user.getUsername());
//        model.addAttribute("authorities",user.getAuthorities());
//        return "home";
//    }
//}
