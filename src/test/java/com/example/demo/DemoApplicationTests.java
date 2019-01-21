package com.example.demo;

import com.example.demo.dao.LearnResourceMapper;
import com.example.demo.domain.LearnResource;
import com.example.demo.service.impl.LearnResourceServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
//	LearnResourceServiceImpl learnResourceService;
            LearnResourceMapper learnResourceMapper;


    @Test
    public void contextLoads() {
    }

    @Test
    public void getLearnResource1() {
        long id=1000;
        LearnResource resource = learnResourceMapper.getLearnResourceById(id);
        System.out.println(resource.getAuthor());
    }

}

