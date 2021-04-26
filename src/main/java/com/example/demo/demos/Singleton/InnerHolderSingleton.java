package com.example.demo.demos.Singleton;

/**
 * @Author: yanjie
 * @Description: 静态内部类
 * @Date: 2021/04/26 15:30
 */
public class InnerHolderSingleton {

    private InnerHolderSingleton() {
    }

    private static class InstanceHolder {
        private static final InnerHolderSingleton INSTANCE = new InnerHolderSingleton();
    }

    public static InnerHolderSingleton getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
