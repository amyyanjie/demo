package com.example.demo.controller;

import com.example.demo.Result.Result;
import com.example.demo.domain.DateFormatTest;
import com.example.demo.domain.LearnResource;
import com.example.demo.service.ILearnResourceService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/learn")
public class LearnResourceController {
    private static Logger logger = LoggerFactory.getLogger(LearnResourceController.class);

    @Autowired
    private ILearnResourceService learnResourceService;

    @RequestMapping("/test")
    public ModelAndView getLearnResource() {
        List<LearnResource> learnList = new ArrayList<>();
        log.debug("添加学习资源");
        log.info("添加学习资源");
        LearnResource bean = new LearnResource("官方参考文档", "Spring Boot Reference Guide", "http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#getting-started-first-application");
        learnList.add(bean);
        bean = new LearnResource("官方SpriongBoot例子", "官方SpriongBoot例子", "https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples");
        learnList.add(bean);
        bean = new LearnResource("龙国学院", "Spring Boot 教程系列学习", "http://www.roncoo.com/article/detail/125488");
        learnList.add(bean);
        bean = new LearnResource("嘟嘟MD独立博客", "Spring Boot干货系列 ", "http://tengj.top/");
        learnList.add(bean);
        bean = new LearnResource("后端编程嘟", "Spring Boot教程和视频 ", "http://www.toutiao.com/m1559096720023553/");
        learnList.add(bean);
        bean = new LearnResource("程序猿DD", "Spring Boot系列", "http://www.roncoo.com/article/detail/125488");
        learnList.add(bean);
        bean = new LearnResource("纯洁的微笑", "Sping Boot系列文章", "http://www.ityouknow.com/spring-boot");
        learnList.add(bean);
        bean = new LearnResource("CSDN——小当博客专栏", "Sping Boot学习", "http://blog.csdn.net/column/details/spring-boot.html");
        learnList.add(bean);
        bean = new LearnResource("梁桂钊的博客", "Spring Boot 揭秘与实战", "http://blog.csdn.net/column/details/spring-boot.html");
        learnList.add(bean);
        bean = new LearnResource("林祥纤博客系列", "从零开始学Spring Boot ", "http://412887952-qq-com.iteye.com/category/356333");
        learnList.add(bean);
        //渲染的页面路径开头别加'/',否则本地正常 部署后404
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("learnList", learnList);
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/get/id")
    public Result getLearnResourceById(Integer id) {
        return Result.ok(learnResourceService.getLearnResourceById(id));
    }

    @GetMapping("/test/get")
    @ResponseBody
    public Result getTime() {
        Map<String, Object> map = new HashMap<>();
        map.put("localDateTime", LocalDateTime.now());//2019-01-22T15:06:52.441
        map.put("testEmpty", ""); //jackson配置后不会返回
        map.put("testNull", null);
        map.put("strTime", "2019-01-22");
        Date date = new Date();
        map.put("newDate", date);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("formatDate", df.format(date));
        System.out.println(df.format(date));
        return Result.ok(map);
    }

    @PostMapping("test/set")
    @ResponseBody
    public Result setTime(@RequestBody DateFormatTest test) {
        Map<String, Object> map = new HashMap<>();
        map.put("dateProperties", test.getDateProperties()); //输出格式为str类型
        map.put("localDateTime", LocalDateTime.now());//2019-01-22T15:06:52.441
        map.put("intProperties", test.getIntProperties());
        map.put("strProperties", test.getStrProperties());
        return Result.ok(map);
    }

}
