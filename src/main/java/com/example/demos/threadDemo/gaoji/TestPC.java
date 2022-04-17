package com.example.demos.threadDemo.gaoji;


/**
 * @Author: yanjie
 * @Description:
 * @Date: 2022/04/17 18:06
 */
// 测试生产者消费者问题 管程法
public class TestPC {
    public static void main(String[] args) {
        //  生产者 消费者 缓冲区 产品
        SynContainer container = new SynContainer();
        new Producer(container).start();
        new Consumer(container).start();
    }

}

// 生产者
class Producer extends Thread {
    SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.push(new Product(i));
            System.out.println("生产了===》" + i + "产品");
        }
    }
}

// 消费者
class Consumer extends Thread {
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了===》" + container.pop().id + "产品");
        }
    }
}

// 缓冲区
class SynContainer {
    // 需要一个容器大小
    Product[] array = new Product[10];
    // 容器计数器
    int count = 0;

    // synchronized同步
    public synchronized void push(Product product) {
        // 容器满了，生产等待，等待消费者消费
        if (count == array.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 容器没满，加入产品
        array[count] = product;
        count++;
        // 通知消费者消费
        this.notifyAll();
    }

    // synchronized同步
    public synchronized Product pop() {
        // 不能消费，等待生产者生产
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 可以消费
        count--;
        Product p = array[count];
        // 通知生产者生产
        this.notifyAll();
        return p;
    }

}

// 产品
class Product {
    int id;

    public Product(int id) {
        this.id = id;
    }


}