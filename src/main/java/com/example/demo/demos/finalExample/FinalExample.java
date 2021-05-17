package com.example.demo.demos.finalExample;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/11/18 17:50
 */
public class FinalExample {
    // 对于 final 域，编译器和处理器要遵守两个重排序规则：

    // 在构造函数内对一个 final 域的写，与随后把这个构造对象的引用赋值给一个变量，这两个操作之间不能重排序
    // 写final域的重排序规则可以确保：在对象引用为任意线程可见之前，对象的final域已经被正确初始化过了，而普通域不具有这个保障

    // 初次读一个包含 final 域的对象的引用，与随后初次读这个 final 域，这两个操作之间不能重排序
    // 读final域的重排序规则可以确保：在读一个对象的final域之前，一定会先读包含这个final域的对象的引用

    // 通过为final域增加写和读重排序规则，可以为Java程序员提供初始化安全保证：只要对象是正确构造的（被构造对象的引用在构造函数中没有“逸出”），
    // 那么不需要使用同步（指lock和volatile的使用）就可以保证任意线程都能看到这个final域在构造函数中被初始化之后的值。

    // final关键字的可见性是指：被final修饰的字段在构造器中一旦初始化完成，并且构造器没有把"this"的引用传递出去，那在其他线程就能看到final的值。


    int i;                            //普通变量
    final int j;                      //final变量
    static FinalExample obj;

    public FinalExample() {     //构造函数
        i = 1;                        //写普通域
        j = 2;                        //写final域
    }

    public static void writer() {    //写线程A执行
        obj = new FinalExample();
    }

    public static void reader() {       //读线程B执行
        FinalExample object = obj;       //读对象引用
        int a = object.i;                //读普通域
        int b = object.j;                //读final域
    }
}
