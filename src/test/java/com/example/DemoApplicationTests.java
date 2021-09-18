package com.example;

import com.example.domain.LearnResource;
import com.example.domain.User;
import com.example.service.IUserService;
import com.example.service.impl.LearnResourceServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.net.URLEncoder;
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
        String url=URLEncoder.encode("/activity/goldCoin2018.html");
        System.out.println(url);
        System.out.println("org.springframework.web.servlet.DispatcherServlet".lastIndexOf(46));
        System.out.println("org.springframework".lastIndexOf(100));
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

    @Test
    public void testStopWatch() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("morning");
        System.out.println("1:" + stopWatch.currentTaskName());
        Thread.sleep(2000);
        stopWatch.stop();
        stopWatch.start("afternoon");
        System.out.println("2:" + stopWatch.currentTaskName());
        Thread.sleep(1000);
        stopWatch.stop();
        stopWatch.start("evening");
        System.out.println("3:" + stopWatch.currentTaskName());
        Thread.sleep(3000);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.getLastTaskName());
        System.out.println(stopWatch.getLastTaskInfo());
        System.out.println(stopWatch.getTaskCount());
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}

