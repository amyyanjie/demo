package com.example.demos.finalExample;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/11/19 10:47
 */
public class FinalReferenceExample {
    // 对于引用类型，写final域的重排序规则对编译器和处理器增加了如下约束：
    // 在构造函数内对一个final引用的对象的成员域的写入，与随后在构造函数外把这个被构造对象的引用赋值给一个引用变量，这两个操作之间不能重排序。

    final int[] intArray;                 // final 是引用类型
    static FinalReferenceExample obj;

    public FinalReferenceExample() {      // 构造函数
        intArray = new int[1];  // 对 final 域的写入
        intArray[0] = 1;        // 对这个 final 域引用的对象的成员域的写入
    }

    public static void writerOne() {          // 写线程 A
        obj = new FinalReferenceExample(); // 把被构造的对象的引用赋值给某个引用变量
    }

    public static void writerTwo() {          // 写线程 B
        obj.intArray[0] = 2;
    }

    // JMM可以确保读线程C至少能看到写线程A在构造函数中对final引用对象的成员域的写入。
    // 如果想要确保读线程C看到写线程B对数组元素的写入，写线程B和读线程C之间需要使用同步原语（lock或volatile）来确保内存可见性。
    public static void reader() {             // 读线程 C
        FinalReferenceExample object = obj;
        if (object != null) {
            int temp1 = object.intArray[0];
        }
    }
}
