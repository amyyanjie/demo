package com.example.demo.demos.ConcurrentUtilExample;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: yanjie
 * @Description: CyclicBarrier 一组线程会互相等待，直到所有线程都到达一个同步点。
 * @Date: 2021/09/02 15:34
 */
public class CyclicBarrierTest {

    /**
     * 例如：一组运动员比赛，只有在所有人都准备完成之后，裁判吹口哨，然后一起开跑
     * @param args
     */
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                // 线程都达到屏障时执行的操作
                try {
                    System.out.println("等裁判吹口哨...");
                    //这里停顿两秒更便于观察线程执行的先后顺序
                    Thread.sleep(2000);
                    System.out.println("裁判吹口哨->>>>>");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /*
        * 定义一个 Runner 类代表运动员，其内部维护一个共有的 CyclicBarrier，每个人都有一个准备时间，准备完成之后，会调用 await 方法，等待其他运动员。
        * 当所有人准备都 OK 时，优先执行CyclicBarrier构造器传入的吹口罩操作，然后就可以开跑了。
        * */
        MyRunner runner1 = new MyRunner(barrier, "张三");
        MyRunner runner2 = new MyRunner(barrier, "李四");
        MyRunner runner3 = new MyRunner(barrier, "王五");
        MyRunner runner4 = new MyRunner(barrier, "赵六");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(runner1);
        executor.execute(runner2);
        executor.execute(runner3);
        executor.execute(runner4);

        executor.shutdown();
    }


    public static class MyRunner implements Runnable{
        private CyclicBarrier barrier; // 线程维护一个共同的barrier
        private String name;

        public MyRunner(CyclicBarrier barrier, String name){
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                //模拟准备耗时
                Thread.sleep(new Random().nextInt(5000));
                System.out.println(name + ":准备OK");
                barrier.await(); // 线程等待，直到所有的线程（初始化时parties参数设置的个数）都到达。
                System.out.println(name + ": 开跑");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }
}
