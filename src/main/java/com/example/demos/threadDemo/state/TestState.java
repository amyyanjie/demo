package com.example.demos.threadDemo.state;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2022/04/13 16:51
 */
public class TestState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("run" + i);
            }
            System.out.println("//////");
        });

        // 观察状态
        Thread.State state = thread.getState();
        System.out.println(state); //NEW

        // 观察启动后
        thread.start();
        state = thread.getState();
        System.out.println(state); // RUNNABLE

        while (Thread.State.TERMINATED != thread.getState()) {// 只要线程不终止，就一直输出当前状态
            Thread.sleep(1000);
            state = thread.getState();
            System.out.println(state);
        }


    }
}
