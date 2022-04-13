package com.example.demos.threadDemo;


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
            System.out.println("t线程中 inheritableThreadLocal 值" + inheritableThreadLocal.get()); // 输出test_inheritableThreadLocal
            System.out.println("t线程中 threadLocal 值" + threadLocal.get()); // 输出null
        });
        t.start();
    }

    public static void main(String[] args) {
        test();
    }

    public static class MyThread  extends Thread{
        @Override
        public void run() {
            System.out.println(2);
        }
    }

    public static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
