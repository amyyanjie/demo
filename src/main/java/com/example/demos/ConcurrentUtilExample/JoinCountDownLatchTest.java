package com.example.demos.ConcurrentUtilExample;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: yanjie
 * @Description: CountDownLatch  允许一个或多个线程一直等待，直到一组在其他线程执行的操作全部完成。
 * @Date: 2020/11/07 13:01
 */
public class JoinCountDownLatchTest {
    /*CountDownLatch*/

    public static void main(String[] args) throws InterruptedException {
        //计数器：等待2个线程完成
        CountDownLatch c = new CountDownLatch(2);
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1");
            c.countDown();
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("thread2");
            c.countDown();
        });
        thread1.start();
        thread2.start();
        c.await();
        System.out.println("two thread finish");
        joinTest();
    }

    public static void coundownLatchOfNSteps(String[] args) throws InterruptedException {
        //计数器：等待一个线程的2个步骤完成。
        CountDownLatch c = new CountDownLatch(2);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("step1");
                c.countDown();
                System.out.println("step2");
                c.countDown();
            }
        });
        thread.start();
        c.await(); // 会阻塞当前线程（这里为主线程），直到计数器变成0
        System.out.println("two step finish");
    }



    /*join*/
    public static void joinTest() throws InterruptedException {
        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser1 working");
            }
        });
        Thread parser2 = new Thread(() -> {
            System.out.println("parser2 working");
        });
        parser1.start();
        parser2.start();
        // join 用于让当前执行线程等待 join 线程执行结束。
        // 其实现原理是不停检查 join 线程是否存活，如果 join 线程存活则让当前线程永远等待。
        parser1.join();
        parser2.join();
        System.out.println("all parser finish");
    }
}
