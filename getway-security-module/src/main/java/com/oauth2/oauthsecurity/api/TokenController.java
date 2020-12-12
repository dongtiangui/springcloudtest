//package com.oauth2.oauthsecurity.api;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * api token接口
// */
//@RestController
//@RequestMapping(value = "/api/v1/token")
//public class TokenController {
//    @PreAuthorize("hasAuthority('list:add')")
//    @RequestMapping(value = "/user/admin")
//    public String user(){
//        return "admin";
//    }
//    @PreAuthorize("hasAuthority('list:add')")
//    @RequestMapping(value = "/user/user")
//    public String userAdmin(){
//        return "user";
//    }
//    @PreAuthorize("isAuthenticated()")
//    @RequestMapping(value = "/me",params = "access_token")
//    public String me(){
//        return "me";
//    }
//    @PreAuthorize("isAuthenticated()")
//    @RequestMapping(value = "/authenticated")
//    public String Authenticated(){
//        return "Authenticated";
//    }
//    @RequestMapping(value = "/api/1")
//    @PreAuthorize("isAuthenticated()")
//    public String api(){
//        return "api";
//    }
//}
