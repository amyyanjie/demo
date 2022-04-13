package com.example.demos.threadDemo;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2022/04/13 10:56
 */
public class StaticProxy {
    public static void main(String[] args) {
        // 静态代理模式
        // 真实对象和代理对象实现同一个接口
        // 代理对象可以做真实对象做不了的事情
        // 真实对象专注做自己的事情

        new Thread(() -> System.out.println("runnable run")).start(); // class Thread implements Runnable{...}

        new WeddingCompany(new MarryTarget()).happyMarry();
    }
}


interface Marry {
    void happyMarry();
}

class MarryTarget implements Marry {
    @Override
    public void happyMarry() {
        System.out.println("wedding beginning");
    }
}

class WeddingCompany implements Marry {
    MarryTarget target;

    public WeddingCompany(MarryTarget target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        before();
        this.target.happyMarry();
        after();
    }

    private void before() {
        System.out.println("prepare for wedding");
    }

    private void after() {
        System.out.println("ending the wedding");
    }

}
