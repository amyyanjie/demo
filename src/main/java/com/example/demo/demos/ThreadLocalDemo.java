package com.example.demo.demos;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/11/24 17:50
 */
public class ThreadLocalDemo {

    /**
     * 使⽤ InheritableThreadLocal 可以实现多个线程访问`ThreadLocal`的值
     */
    private static void test() {
        final ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

        threadLocal.set("test_1");
        Thread t = new Thread(() -> {
            System.out.println(threadLocal.get());
        });
        t.start();
    }

    public static void main(String[] args) {
        test();
    }
}
