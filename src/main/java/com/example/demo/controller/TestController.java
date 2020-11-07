package com.example.demo.controller;

import com.example.demo.Result.Result;
import com.example.demo.domain.DateFormatTest;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("/get/time")
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

    @PostMapping("/set/time")
    public Result setTime(@RequestBody DateFormatTest test) {
        Map<String, Object> map = new HashMap<>();
        map.put("dateProperties", test.getDateProperties()); //输出格式为str类型
        map.put("localDateTime", LocalDateTime.now());//2019-01-22T15:06:52.441
        map.put("intProperties", test.getIntProperties());
        map.put("strProperties", test.getStrProperties());
        return Result.ok(map);
    }

    @GetMapping("/jackson")
    public Result testJacksonConfig() {
        double lNumber = 2.133;
        float dNumber = 2.122f;
        Date now = new Date();
        BigDecimal bigDecimal = new BigDecimal(1000);
        Map<String, Object> map = new HashMap<>();
        map.put("lNumber", lNumber);
        map.put("dNumber", dNumber);
        map.put("now", now);
        map.put("bigDecimal", bigDecimal);
        return Result.ok(map);
    }

    @GetMapping("/stackTrace")
    public Result testStackTrace() {
        //获取堆栈轨迹
        StackTraceElement[] stackTraceElements;
        stackTraceElements = new RuntimeException().getStackTrace();
        return Result.ok(stackTraceElements);
    }


}
