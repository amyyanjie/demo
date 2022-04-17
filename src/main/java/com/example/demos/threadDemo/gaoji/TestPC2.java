package com.example.demos.threadDemo.gaoji;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2022/04/17 21:22
 */
// 消费者生产者问题：利用标志位
public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

// 表演者
class Player extends Thread {
    TV tv;

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            tv.play(i+"");
        }
    }
}

// 观众
class Watcher extends Thread {
    TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            tv.watch();
        }
    }
}

// 产品：节目
class TV {
    // 演员拍摄，观众等待 T
    // 观众观看，演员等待 F
    boolean flag = true;
    String voice;

    public synchronized void play(String voice) {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演了" + voice);
        this.voice = voice;
        this.flag = !flag;
        this.notifyAll();

    }

    public synchronized void watch() {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众观看了：" + voice);
        this.flag = !flag;
        this.notifyAll();
    }
}
