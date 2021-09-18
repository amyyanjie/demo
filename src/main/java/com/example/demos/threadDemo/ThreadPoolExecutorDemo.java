package com.example.demos.threadDemo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/12/23 18:00
 */
public class ThreadPoolExecutorDemo {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);

    public static void main(String[] args) {
        testDynamicModifyExecutor();
    }

    /**
     * 定义线程池
     * corePoolSize
     * maximumPoolSize
     * keepAliveTime
     * unit
     * workQueue
     * threadFactory。不定义时默认 Executors.defaultThreadFactory()。
     */
//    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, new ResizableCapacityLinkedBlockingQueue<>(10), r -> new Thread(r, "testThread#" + poolNumber.getAndIncrement()));

    // 测试自定义队列 ResizableCapacityLinkedBlockingQueue
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS, new ResizableCapacityLinkedBlockingQueue<>(10), r -> new Thread(r, "testThread#" + poolNumber.getAndIncrement()));

    public static void testDynamicModifyExecutor() {
        for (int i = 0; i < 15; i++) {
            Future f = executor.submit(() -> { // 15个耗时10秒的任务
                printThreadPoolStatus(executor, "创建任务");
                try {
                    TimeUnit.SECONDS.sleep(10); //Thread.sleep(ms, ns);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
//        printThreadPoolStatus(executor, "改变之前");// 最大线程数为5，队列会装满10个。全部执行完需要3*10秒。
        // 修改核心线程数时，注意修改最大线程数
        executor.setCorePoolSize(10);
        executor.setMaximumPoolSize(10);
        // 测试通过自定义队列，增加长度
        ResizableCapacityLinkedBlockingQueue queue = (ResizableCapacityLinkedBlockingQueue) executor.getQueue();
        queue.setCapacity(20);
        printThreadPoolStatus(executor, "改变之后");// 最大线程数为10，队列会装5个。全部执行完需要2*10秒
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printThreadPoolStatus(ThreadPoolExecutor executor, String desc) {
        ResizableCapacityLinkedBlockingQueue queue = (ResizableCapacityLinkedBlockingQueue) executor.getQueue();

        System.out.println(Thread.currentThread().getName() + "-" + desc + "-:" +
                "核心线程数:" + executor.getCorePoolSize() +
                ",活动线程数:" + executor.getActiveCount() +
                ",最大线程数:" + executor.getMaximumPoolSize() +
                ",任务完成数:" + executor.getCompletedTaskCount() +
                ",队列大小:" + (queue.size() + queue.remainingCapacity()) +
                ",当前排队线程数:" + queue.size() +
                ",队列剩余大小:" + queue.remainingCapacity());
    }


}
