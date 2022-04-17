package com.example.demos.threadDemo.gaoji;

import lombok.SneakyThrows;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2022/04/17 16:01
 */
// 死锁：多个线程互相抱有对方需要的资源，然后形成僵持。
public class TestDeadLock {
    public static void main(String[] args) {
        MakeUp g1 = new MakeUp(0, "girl1");
        MakeUp g2 = new MakeUp(1, "girl2");
        g1.start();
        g2.start();
    }
}

//两个资源：
class Lipstick {
}

class Mirror {
}

class MakeUp extends Thread {
    // 需要的资源只有一份，用static来保证只有一份
    final static Lipstick lipstick = new Lipstick();
    final static Mirror mirror = new Mirror();

    int choice;

    public MakeUp(int choice, String name) {
        super(name);
        this.choice = choice;
    }

    @Override
    public void run() {

        //
        makeupWithNoDeadLock();

        // 某个同步块同时拥有"两个以上对象的锁"时，就可能会发上死锁的问题。
        //        makeupWithDeadLock();


    }

    // 造成死锁：互相持有对方的锁
    @SneakyThrows
    public void makeupWithDeadLock() {
        if (choice == 0) {
            synchronized (mirror) {
                System.out.println(Thread.currentThread().getName() + "获得镜子的锁");
                Thread.sleep(1000); // 1秒以后想获得口红
                synchronized (lipstick) {
                    System.out.println(Thread.currentThread().getName() + "获得口红的锁");
                }
            }
        } else {
            synchronized (lipstick) {
                System.out.println(Thread.currentThread().getName() + "获得口红的锁");
                //
                Thread.sleep(2000);
                synchronized (mirror) {
                    System.out.println(Thread.currentThread().getName() + "获得镜子的锁");
                }
            }
        }

    }

    //
    @SneakyThrows
    public void makeupWithNoDeadLock() {
        if (choice == 0) {
            synchronized (mirror) {
                System.out.println(Thread.currentThread().getName() + "获得镜子的锁");
                Thread.sleep(1000); // 1秒以后想获得口红

            }
            synchronized (lipstick) {
                System.out.println(Thread.currentThread().getName() + "获得口红的锁");
            }
        } else {
            synchronized (lipstick) {
                System.out.println(Thread.currentThread().getName() + "获得口红的锁");
                Thread.sleep(2000);
            }
            synchronized (mirror) {
                System.out.println(Thread.currentThread().getName() + "获得镜子的锁");
            }
        }

    }
}

