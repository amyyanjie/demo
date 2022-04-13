package com.example.demos.threadDemo;


/**
 * @Author: yanjie
 * @Description:
 * @Date: 2022/04/12 16:47
 */
public class Race implements Runnable {

    //
    private static String winner;

    // 模拟龟兔赛跑
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i <= 100; i++) {
            // 兔子休息
            if ("rabbit".equals(name) && i % 70 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (gameOver(i)) {
                break;
            }
            System.out.println(name + "跑了" + i + "步");
        }
    }

    // 比赛是否结束
    public boolean gameOver(int steps) {
        if (winner != null) {
            return true;
        } else {
            if (steps >= 100) {
                winner = Thread.currentThread().getName();
                System.out.println("winner is :" + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "rabbit").start();
        new Thread(race, "tortoise").start();
    }
}
