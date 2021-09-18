package com.example.demos.Singleton;

/**
 * @Author: yanjie
 * @Description: 静态内部类
 * @Date: 2021/04/26 15:30
 */
public class InnerHolderSingleton {

    private InnerHolderSingleton() {
    }

    /**
     * 实例由静态内部类构建。JVM 在加载外部类时不会加载静态内部类
     * 只有当静态内部类的方法或属性被调用时才会被加载，并初始化其静态属性
     * 静态属性static修饰，只会被实例化一次，并严格保证实例化顺序
     */
    private static class InstanceHolder {
        private static final InnerHolderSingleton INSTANCE = new InnerHolderSingleton();
    }

    public static InnerHolderSingleton getInstance() {
        return InstanceHolder.INSTANCE; // 第一次调用时，这里会导致InstanceHolder内部类初始化
    }
}
