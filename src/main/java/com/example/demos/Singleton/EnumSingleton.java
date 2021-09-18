package com.example.demos.Singleton;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2021/04/26 15:38
 */

/**
 * 枚举类型是线程安全的，只会装载一次
 * 枚举类型是所有单例实现中唯一一种不会被破坏的单例实现模式
 * 不仅可以防止利用反射强制构建单例对象，而且可以在枚举类对象被反序列时，保证反序列化的返回结果是同一对象
 */
public enum EnumSingleton {
    INSTANCE;
}
