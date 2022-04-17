package com.example.demos.threadDemo.syn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2022/04/17 13:47
 */
public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            // 加锁
            synchronized (list) {
                new Thread(() -> list.add(Thread.currentThread().getName())).start();
            }
        }

        // JUC（java util concurrent）包下的线程安全类
        // 底层数组array用transient volatile修饰。
        // 用ReentrantLock锁
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        System.out.println(list.size());
    }
}
