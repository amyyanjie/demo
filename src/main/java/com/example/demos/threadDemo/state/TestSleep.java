package com.example.demos.threadDemo.state;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TestSleep {
    public static void main(String[] args) throws InterruptedException {
        // 模拟倒计时
        tenDown();

        // 打印时间
        LocalTime now;
        for (int i1 = 0; i1 < 10; i1++) {
            now = LocalTime.now();
            System.out.println(now.format(DateTimeFormatter.ofPattern("HH:mm:ss"))); // 打印当前系统时间
            Thread.sleep(1000);
        }
    }

    // 模拟倒计时
    public static void tenDown() throws InterruptedException {
        int num = 10;
        while (true) {
            Thread.sleep(1000);
            System.out.println(num--);
            if (num <= 0) {
                break;
            }
        }
    }

}
