package com.example.controller;

import com.example.config.ConfigBean;
import com.example.Result.Result;
import com.example.Result.ResultStatus;
import com.example.config.TestConfigBean;
import com.example.domain.User;
import com.example.service.IUserService;
import com.example.utils.StringUtil;
import com.example.vars.DemoVars;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("user")
@RestController
public class UserController {
    //通过注解@Value("${}")绑定到属性字段上
    @Value("${com.user.name}")
    private String name;
    @Value("${com.user.property}")
    private String property;
    //若是一个个绑定，只能在此类调用。提倡绑定一个对象的bean。
    @Autowired
    private TestConfigBean testConfigBean;
    @Autowired
    private ConfigBean configBean;
    @Autowired
    private DemoVars demoVars;
    //获取DemoVars属性，（1）：可以通过注入，直接demoVars.get()获取属性
    //                （2）：单例模式

    @RequestMapping(value = "/")
    public String getUser() {

//   1. 获取@Value绑定的属性字段
//    return name+property;

        log.debug("name:{}", configBean.getName());
        log.info("property:{}", testConfigBean.getProperty());
//        2.注入配置信息实体类，获取属性
        return configBean.getName() + configBean.getProperty()
                + configBean.getPasswd() + configBean.getAge();

//        3.可有多个配置实体类
//        return testConfigBean.getName() + testConfigBean.getProperty();

//        4.DemoVars类，通过单例模式，获取属性
//        DemoVars vars = DemoVars.getInstance();
//        return vars.getAge() + vars.getPasswd();

//        5.DemoVars类，依赖注入，获取属性
//        return demoVars.getAge()+demoVars.getPasswd();
    }

    @Autowired
    private IUserService userService;

    @GetMapping("get/list")
    public Result getUserList(@RequestParam(defaultValue = "1", required = false) int pageNum,
                              @RequestParam(defaultValue = "5", required = false) int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.getAllUserList();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return Result.ok(pageInfo);
    }

    @PostMapping(value = "/add")
    public Result addUser(@RequestBody User user) {
        if (StringUtil.isEmpty(user.getMobile())) {
            return Result.error(ResultStatus.ERROR_PRAMATER_NULL);
        }
        User exsitedUser = userService.getUserByMobile(user.getMobile());
        if (exsitedUser != null) {
            return Result.error(ResultStatus.ERROR_MOBILE_EXIST);
        }
        int result = userService.addUser(user);
        if (result < 1) {
            return Result.error("添加用户失败");
        }
        return Result.ok();
    }

    @GetMapping("/get/by/mobile")
    public Result getUser(String mobile) {
        return Result.ok(userService.getUserByMobile(mobile));
    }
}
