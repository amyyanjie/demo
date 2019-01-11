package com.example.demo.utils;

import com.example.demo.DemoApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanUtil {

    public static ApplicationContext getApplicationContext() {
        return DemoApplication.getContext();
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> T) {
        return getApplicationContext().getBean(T);
    }

}
