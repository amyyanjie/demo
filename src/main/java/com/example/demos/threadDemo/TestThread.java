package com.example.demos.threadDemo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2022/04/12 17:33
 */
public class TestThread implements Runnable {
    String url;
    String filePath;
    public TestThread(String url, String filePath) {
        this.url = url;
        this.filePath = filePath;
    }
    @Override
    public void run() {
        downLoad(url, filePath);
    }

    public static class MyThread extends Thread {
        String url;
        String filePath;
        public MyThread(String url, String filePath) {
            this.url = url;
            this.filePath = filePath;
        }
        @Override
        public void run() {
            downLoad(url, filePath);  // 下载图片
        }
    }

    // 下载图片
    public static void downLoad(String url, String filePath) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(filePath));
            System.out.println("下载了文件："+ filePath);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }


    public static void main(String[] args) {

        // 创建线程方法一：继承Thread类，通过子类对象直接启动
        new MyThread("https://deppwang.oss-cn-beijing.aliyuncs.com/blog/2019-12-31-143856.png","src/main/resources/static/1.jpg").start();


        // 创建线程方法二：实现Runnable接口，将实现对象传入Thread构造函数
        new Thread(new TestThread("https://amy-resource.oss-cn-beijing.aliyuncs.com/images/imagesThreadState.png","src/main/resources/static/2.jpg")).start();

        // 创建线程方法三：实现Callable接口，见TestCallable类
    }
}
