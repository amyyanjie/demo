package com.example.demo.demos;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/11/24 17:50
 */
public class ThreadLocalDemo {

    /**
     * 使⽤ InheritableThreadLocal 可以实现多个线程访问 ThreadLocal 的值
     */
    private static void test() {
        final ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        final ThreadLocal<String> threadLocal = new ThreadLocal<>();

        inheritableThreadLocal.set("test_inheritableThreadLocal");
        threadLocal.set("test_threadLocal");
        System.out.println("主线程中 inheritableThreadLocal 值：" + inheritableThreadLocal.get());
        System.out.println("主线程中 threadLocal 值：" + threadLocal.get());
        Thread t = new Thread(() -> {
            System.out.println("t线程中 inheritableThreadLocal 值" + inheritableThreadLocal.get());
        });
        t.start();
    }

    public static void main(String[] args) {
        test();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, new SynchronousQueue<>());
    }
}
