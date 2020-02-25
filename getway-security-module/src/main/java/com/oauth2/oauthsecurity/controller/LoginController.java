package com.oauth2.oauthsecurity.controller;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    @ApiOperation(value = "测试用户",notes = "测试用户",produces = "application/json")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String user(@RequestParam("code") String code, Model model) {
        model.addAttribute("code",code);
        return "auth";
    }

    @RequestMapping("/getToken")
    @ResponseBody
    public String getToken(){

        return "getToken";
    }
}
