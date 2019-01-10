package config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "com.user")
@Data
public class ConfigBean {
    private String nickName;
    private String mobile;
}
