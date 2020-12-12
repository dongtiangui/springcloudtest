package com.ribbon.boostrap.api;

import com.ribbon.boostrap.domain.ResponseInfo;
import com.ribbon.boostrap.domain.po.User;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@RestController
@RequestMapping(value = "/api/v1/user/")
public class ApiController {
    @CrossOrigin(value = "*",maxAge = 3600)
    @PostMapping(value = "login")
    public ResponseInfo<User> responseUser(@RequestBody Map<String,String> map){
        System.out.println(map);
        User user = new User(map.get("username"), map.get("password"));
        ResponseInfo<User> info = new ResponseInfo<>();
        info.setData(user);
        info.setCode(200);
        info.setMsg("success");
        return info;
    }
    @GetMapping(value = "getUser")
    public ResponseInfo<User> getUser(HttpServletRequest request){
        ResponseInfo<User> info = new ResponseInfo<>();
        System.out.println(request.getHeader("token"));
        info.setCode(200);
        info.setMsg("success");
        return info;
    }
}
