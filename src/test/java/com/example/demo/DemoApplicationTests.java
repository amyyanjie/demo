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

import java.util.List;


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
        long id=1000;
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

}

