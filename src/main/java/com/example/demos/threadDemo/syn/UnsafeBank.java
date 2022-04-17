package com.example.demos.threadDemo.syn;

import org.checkerframework.checker.units.qual.A;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2022/04/13 18:36
 */
public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account("基金", 100);

        // 1.继承Thread
        // 线程安全：使用synchronized同步块，
        Drawing a = new Drawing(account, 50, "a");
        Drawing b = new Drawing(account, 50, "b");
        Drawing c = new Drawing(account, 50, "c");
//        a.start();
//        b.start();
//        c.start();

        // 2.实现Runnable
        // 线程安全：run方法加synchronized，锁的是Drawing1实例
        Drawing1 drawing1 = new Drawing1(account, 50);
        new Thread(drawing1, "d").start();
        new Thread(drawing1, "e").start();
        new Thread(drawing1, "f").start();
    }
}

// 账户
class Account {
    String name; //卡名
    int money;

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }
}

class Drawing1 implements Runnable {
    Account account;
    int drawingMoney;

    public Drawing1(Account account, int drawingMoney) {
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    // 线程安全：public synchronized void run()
    @Override
    public void run() {
        if (drawingMoney > account.money) {
            System.out.println("余额为" + account.money + "，钱不够");
            return;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        account.money -= drawingMoney;
        System.out.println(Thread.currentThread().getName() + "取钱：" + drawingMoney);
        System.out.println("账户还有" + account.money);
    }


}

// 取钱 不涉及多个线程操作同一个对象，可以继承Thread类创建线程
class Drawing extends Thread {
    Account account;
    int drawingMoney;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name); //线程名
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        // 线程安全，synchronized锁account对象。锁的对象是变化的量。
//        synchronized (account) {
            // 判断金额是否足够
            if (drawingMoney > account.money) {
                System.out.println(account.name + "钱不够，取不了");
            }
            //  sleep 可以放大问题的发生性
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money -= drawingMoney;
            //this.getName() = Thread.currentThread().getName()
            System.out.println(this.getName() + "取钱" + drawingMoney + "还剩" + account.money);
//        }
    }

}
