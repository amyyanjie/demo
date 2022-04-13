package com.example.demos.threadDemo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2022/04/12 17:25
 */
public class TestCallable implements Callable<Boolean> {

    String url;
    String filePath;
    public TestCallable(String url, String filePath) {
        this.url = url;
        this.filePath = filePath;
    }

    // 重写call方法，有返回值
    @Override
    public Boolean call() {
        downLoad(url, filePath);  // 下载图片
        return true;
    }

    // 下载图片
    public void downLoad(String url, String filePath) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(filePath));
            System.out.println("下载了文件：" + filePath);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 创建线程方法三：实现Callable

        // 实现Callable接口，重写call()方法
        TestCallable callable1 = new TestCallable("https://deppwang.oss-cn-beijing.aliyuncs.com/blog/2019-12-31-143856.png","src/main/resources/static/1.jpg");
        TestCallable callable2 = new TestCallable("https://amy-resource.oss-cn-beijing.aliyuncs.com/images/imagesThreadState.png","src/main/resources/static/2.jpg");

        // 创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(2); // 线程池
        // 提交执行
        Future<Boolean> r1 = ser.submit(callable1); // submit提交有返回值。execute提交没有返回值
        Future<Boolean> r2 = ser.submit(callable2);
        // 获取结果
        try {
            System.out.println("r1结果："+r1.get());
            System.out.println("r2结果："+r2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // 关闭服务
        ser.shutdown();
    }
}
