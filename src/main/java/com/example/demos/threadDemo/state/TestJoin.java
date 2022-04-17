package com.example.demos.threadDemo.state;

/**
 * @Author: yanjie
 * @Description: 由线程对象调用join()，待此线程执行完成后，再执行其他线程。其他线程阻塞
 * @Date: 2022/04/13 15:59
 */
public class TestJoin implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        // 启动t线程
        Thread t = new Thread(new TestJoin());
        t.start();

        // 主线程
        for (int i = 0; i < 500; i++) {
            if (10 == i) {
                t.join(); // t线程插队，此时主线程阻塞
            }
            System.out.println("main:" + i);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("thread vip coming：" + i);
        }
    }
}
