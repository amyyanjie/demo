package com.example.demos.Singleton;

import java.io.Serializable;

/**
 * @Author: yanjie
 * @Description: 单例模式--synchronized双重检查锁定
 * @Date: 2021/04/26 14:58
 */
public class DoubleCheckedLocking implements Serializable{
    private DoubleCheckedLocking() { // 私有构造函数
        // 防止反射攻击
        if (instance != null) {
            throw new IllegalArgumentException("实例已经存在，请通过 getInstance()方法获取");
        }

    }

    private volatile static DoubleCheckedLocking instance; // 单例对象，静态。volatile禁止重排序

    public static DoubleCheckedLocking getInstance() {
        if (instance == null) {                             // 第一次检查，可以降低synchronized带来的性能开销
            synchronized (DoubleCheckedLocking.class) {     // 加锁，保证只有一个线程能创建对象，锁住整个类
                if (instance == null) {                     // 第二次检查，若A已创建，
                    instance = new DoubleCheckedLocking();  // 分配对象的内存空间；初始化对象；设置instance指向刚分配的内存地址。volatile禁止重排序
                }
            }
        }
        return instance;
    }

    /**
     * 防止反序列化攻击
     * 如果单例类实现了序列化接口Serializable，可以反序列化破坏单例（反序列化得到新对象，即能创建两个对象）。
     * 要么不实现序列化。如果非得实现序列化，需要重写readResolve()返回instance。
     * 当 ObjectInputStream 类反序列化时，如果对象存在 readResolve 方法，则会调用该方法返回对象。
     */
    private Object readResolve() {
        return instance;
    }
}
