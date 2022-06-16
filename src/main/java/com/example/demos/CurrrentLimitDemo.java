package com.example.demos;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: yanjie
 * @Description: 限流算法
 * @Date: 2020/11/16 17:25
 */
public class CurrrentLimitDemo {
    /**
     * Guava 提供了限流工具类 RateLimiter，基于令牌桶算法实现
     */

    public void rateLimiter() {
        // 创建一个限流器，设置每秒放置的令牌数：2 个
        // 返回的 RateLimiter 对象可以保证1秒内不会给超过2个令牌，并且是固定速率的放置。达到平滑输出的效果
        RateLimiter r = RateLimiter.create(2);

        // acquire() 获取一个令牌，并且返回这个获取这个令牌所需要的时间。如果桶里没有令牌则等待，直到有令牌。
        // 如果需要对某些突发的流量进行处理的话，可以对这个返回值设置一个阈值，根据不同的情况进行处理，比如过期丢弃。
        System.out.println(r.acquire(2));
        System.out.println(r.acquire(1));
        System.out.println(r.acquire(1));

        // 考虑超时情况下面请求直接结束
        boolean isPermit = r.tryAcquire(500, TimeUnit.MILLISECONDS);
        if (!isPermit) {
            throw new RuntimeException("business overheated.");
        }
    }


    /**
     * 固定窗口计数器
     */
    //有一个「固定周期」会触发的定时器将数值清零。
    private AtomicInteger reqCount = new AtomicInteger(0);
    private final int limit = 100; // 时间窗口内最大请求数

    public boolean counterOfFixedTime() {
        if (reqCount.get() >= limit) {
            return false;
        }
        return reqCount.incrementAndGet() <= limit;
    }


    /**
     * 漏桶算法伪代码
     */
    private long timeStamp = getNowTime();
    private int capacity = 100; // 桶的容量 100
    private float rate = 0.1f; // 水漏出的速度 0.1（浮点型 * 整数结果去除精度）
    private int water = 0; // 当前水量(当前累积请求数) 0

    public boolean grant() {
        long now = getNowTime();
        water = (int) Math.max(0, water - (now - timeStamp) * rate); // 先执行漏水，计算剩余水量
        timeStamp = now;
        if ((water + 1) < capacity) {
            // 尝试加水,并且水还未满
            water += 1;
            return true;
        } else {
            // 水满，拒绝加水
            return false;
        }
    }

    private long getNowTime() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        Long l = null;
        System.out.println(l.longValue());
    }
}
