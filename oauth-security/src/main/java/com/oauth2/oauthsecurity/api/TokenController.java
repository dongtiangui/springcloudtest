package com.oauth2.oauthsecurity.api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * api接口
 */
@Controller
public class TokenController {
    @ResponseBody
    @PreAuthorize("hasAuthority('list:add')")
    @RequestMapping(value = "/user/admin")
    public String user(){
        return "admin";
    }
    @ResponseBody
    @PreAuthorize("hasAuthority('list:add')")
    @RequestMapping(value = "/user/user")
    public String userAdmin(){
        return "user";
    }

    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/me",params = "access_token")
    public String me(){
        return "me";
    }

    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/authenticated")
    public String Authenticated(){
        return "Authenticated";
    }
    @ResponseBody
    @RequestMapping(value = "/api/1")
    @PreAuthorize("isAuthenticated()")
    public String api(){
        return "api";
    }
}
