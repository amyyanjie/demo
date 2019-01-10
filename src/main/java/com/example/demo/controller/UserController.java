package com.example.demo.controller;

import config.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
//    @Value("${com.user.name}")
//    private String name;
//    @Value("${com.user.property}")
//    private String property;
    @Autowired
    private ConfigBean configBean;

    @RequestMapping(value = "/")
    public String getUser() {
        configBean.setMobile("141233");
        configBean.setNickName("jicne");
//        return name+property;
        return configBean.getMobile() + configBean.getNickName();
    }

}
