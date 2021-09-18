package com.example.vars;

import com.example.utils.SpringContextUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class DemoVars {
    //单例对象
    private static DemoVars instance;

    public static DemoVars getInstance() {
        if (instance == null) {
            //获取单例实例
            instance = SpringContextUtil.getBean(DemoVars.class);
//            instance = BeanUtil.getBean(DemoVars.class);
            //SpringContextUtil是获取ApplicationContext，getBean(DemoVars.class);
            //BeanUtil是获取DemoApplication.getContext().getBean(DemoVars.class);
        }
        return instance;
    }

    @Value("${com.user.passwd}")
    private String passwd;

    @Value("${com.user.name}")
    private String name;

    @Value("${com.user.age:18}")
    private String age;

}
