package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

//@EnableConfigurationProperties({TestConfigBean.class, ConfigBean.class})//此注解用来指定用TestConfigBean，ConfigBean实体类来装载配置信息
//若不指定，可在ConfigBean或TestConfigBean等配置文件中添加注解@Configuration
@SpringBootApplication
public class DemoApplication {
    private static ApplicationContext context;
    private static Environment environment;

    public static void main(String[] args) {
        context = SpringApplication.run(DemoApplication.class, args);
//      context=new SpringApplicationBuilder(DemoApplication.class)
//              .properties("spring.application.name=demo").properties("server.port=8080")
//              .run(args);
        //这里设置properties与在.properties配置文件中设置效果相同，但.properties优先级要高于这里
        environment = context.getEnvironment();
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static Environment getEnvironment() {
        return environment;
    }
}

