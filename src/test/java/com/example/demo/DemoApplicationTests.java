package com.example.demo;

import com.example.demo.dao.LearnResourceMapper;
import com.example.demo.domain.LearnResource;
import com.example.demo.domain.User;
import com.example.demo.service.IUserService;
import com.example.demo.service.impl.LearnResourceServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    LearnResourceServiceImpl learnResourceService;
    @Autowired
    IUserService userService;


    @Test
    public void contextLoads() {
    }

    @Test
    public void getLearnResource1() {
        long id = 1000;
        LearnResource resource = learnResourceService.getLearnResourceById(id);
        System.out.println(resource.getAuthor());
    }

    @Test
    public void getAllUserList() {
        int pageNum = 1;
        int pageSize = 2;
        List<User> list = userService.getAllUserList();
        System.out.println("size" + list.size());
        for (User user : list) {
            System.out.println("userId:" + user.getUserId());
        }
    }

    @Test
    public void testTime() {

        System.out.println("newDate:" + new Date());
        System.out.println("defaultTimeZone:" + TimeZone.getDefault()); //输出当前默认时区
        final TimeZone zone = TimeZone.getTimeZone("GMT+8"); //获取中国时区
        TimeZone.setDefault(zone); //设置时区
        System.out.println("setAfter" + TimeZone.getDefault()); //输出验证
    }

}

