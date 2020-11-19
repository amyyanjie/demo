package com.example.demo.demos.finalExample;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/11/19 14:32
 */
public class FinalReferenceEscapeExample {
    // 在构造函数返回前，被构造对象的引用不能为其他线程所见，因为此时的final域可能还没有被初始化。
    // 在构造函数返回后，任意线程都将保证能看到final域正确初始化之后的值。

    final int i;
    static FinalReferenceEscapeExample obj;

    public FinalReferenceEscapeExample() {
        i = 1;                     // 1.写final 域
        obj = this;                  // 2.这里非正确构造，被构造对象的 this 引用在构造函数中 “逸出”，执行 read () 方法的线程仍然可能无法看到 final 域被初始化后的值
    }

    public static void writer() {
        new FinalReferenceEscapeExample();
    }

    public static void reader() {
        if (obj != null) {                     //3
            int temp = obj.i;                 //4
        }
    }
}
