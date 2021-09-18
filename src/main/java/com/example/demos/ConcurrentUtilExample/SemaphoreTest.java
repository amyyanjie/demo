package com.example.demos.ConcurrentUtilExample;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: yanjie
 * @Description: Semaphore，信号量，用来控制同一时间，资源可被访问的线程数量，一般可用于流量的控制。
 * @Date: 2021/09/02 16:53
 */
public class SemaphoreTest {
    static int count = 20;
    /**
     * 例如：一段公路十分拥挤，规定同一时间最多只能通过5辆车。其他车辆只能等待。只有拿到许可的车辆可通过，等车辆通过之后，再归还许可，然后把它发给等待的车辆，获得许可的车辆再通行，依次类推。
     * 现在有20辆车要通过这个路段。
     * 运行结果：第一批是五个车同时通行。然后，后边的车才可以依次通行。但是同时通行的不超过五辆。
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(count);

        // 指定最多只能有五个线程同时执行
        Semaphore semaphore = new Semaphore(5);// 若还传入参数 true：使用公平锁，锁的机制是通过AQS：AbstractQueuedSynchronizer

        Random random = new Random();

        for (int i = 0; i < count; ++i) {
            int no = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获得许可
                        semaphore.acquire();
                        System.out.println(no + "号车可通行");
                        // 模拟车辆通行耗时
                        Thread.sleep(random.nextInt(2000));
                        // 释放许可
                        semaphore.release();
                        //System.out.println(no + "号车释放许可");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
