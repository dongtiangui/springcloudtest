package com.integral.boot.controller;

import com.integral.boot.messagequeue.BuyShop;
import com.integral.boot.messagequeue.BuyShopAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Topic;

@RestController
public class ProducerController {

//    事件注册
    @Autowired
    private BuyShopAware buyShopAware;
//    事件源
    @Autowired
    private BuyShop buyShop;

    @Autowired
    private Topic topic;

    @GetMapping("/index.html")
    public String fa(){
        buyShopAware.send(buyShop);
        buyShop.sendMessage(topic,"你好");
        return "成功";
    }
}
