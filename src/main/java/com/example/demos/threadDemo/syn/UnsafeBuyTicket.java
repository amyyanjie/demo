package com.example.demos.threadDemo.syn;


// 线程不安全，买到负数
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket(); // buy方法加锁后，锁的是BuyTicket类的实例对象
        //多个线程同时操作同一个对象
        new Thread(station, "张三").start();
        new Thread(station, "李四").start();
        new Thread(station, "黄牛").start();
    }
}

class BuyTicket implements Runnable {
    int ticketNum = 10;
    boolean flag = true; // 外部停止标志

    @Override
    public void run() {
        while (flag) {
            buy();
        }
    }

    // 线程安全： public synchronized void buy()
    // synchronized 锁的是this对象。当前类的实例对象

    public void buy() {
        // 判断是否有票
        if (ticketNum <= 0) {
            flag = false;
            return;
        }
        // 延时，放大问题的可能性
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 买票
        System.out.println(Thread.currentThread().getName() + "买到" + ticketNum--);
    }
}