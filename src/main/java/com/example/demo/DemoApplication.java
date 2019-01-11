package com.example.demo;

import com.example.demo.config.ConfigBean;
import com.example.demo.config.TestConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@EnableConfigurationProperties({TestConfigBean.class,ConfigBean.class})//此注解用来指定用TestConfigBean，ConfigBean实体类来装载配置信息
@SpringBootApplication
public class DemoApplication {
    private static ApplicationContext context;
    private static Environment environment;

    public static void main(String[] args) {
        context = SpringApplication.run(DemoApplication.class, args);
        environment = context.getEnvironment();
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static Environment getEnvironment() {
        return environment;
    }
}

