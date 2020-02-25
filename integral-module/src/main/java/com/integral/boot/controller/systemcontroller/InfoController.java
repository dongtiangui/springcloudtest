package com.integral.boot.controller.systemcontroller;

import com.integral.boot.annotation.LogAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class InfoController {

    @LogAnnotation(value = "print")
    @RequestMapping("/info")
    public String info(){
        return "info";
    }
}
