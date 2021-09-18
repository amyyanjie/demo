package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:test.properties")
//装载其他配置文件(test.properties)信息
@ConfigurationProperties(prefix = "com.test")
@Data
public class TestConfigBean {
    private String name;
    private String property;
}
