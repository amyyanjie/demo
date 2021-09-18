package com.example.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
//@PropertySource("classpath:application-dev.properties")
@ConfigurationProperties(prefix = "com.user")
/*
1.使用@ConfigurationProperties注解将一配置文件信息封装成实体
  可以直接private String nickName;不再需要添加@Value("${}")注解

2.默认配置文件的路径是application.properties。就是绑定application.properties中的属性

3.若其他路径，需要使用注解@Configuration,@PropertySource("classpath:**.properties")

4.在启动类中用@EnableConfigurationProperties({TestConfigBean.class, ConfigBean.class})明确指定哪个实体类来装在配置信息
  或者在配置类中添加注解@Configuration
*/

@Component
@Data
//5.调用方通过自动注入ConfigBean后, ConfigBean.getName即可获得application.properties文件中name："user"
public class ConfigBean {

    //此处字段的属性与配置application.properties字段一致
    private String nickName;
    private String mobile;
    private String name;
    private String property;
    private String number;
    /*
    6.若使用@ConfigurationPropertie注解，可直接private String name。
    7.若使用@Component注解，直接private String name获取不到配置文件信息。需要@Value注解。
     */
    @Value("${com.user.passwd}")
    public String passwd;

    @Value("${com.user.age:19}")
    private String age;
}
