package com.example.demos.threadDemo.gaoji;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2022/04/17 17:23
 */
public class TestLock {
    public static void main(String[] args) {

        TestTicketLock r = new TestTicketLock();
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }
}

class TestTicketLock implements Runnable{
    int ticketNum = 10;
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock(); // 加锁
                if (ticketNum > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketNum--);
                } else {
                    break;
                }
            } finally { // 解锁，写入finally语句块
                lock.unlock();
            }
        }
    }
}
