package com.example.demo.demos.finalExample;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/11/18 15:58
 */
public class FinalTest {
    /**
     * final修饰变量
     */
    /*与static一起使用，做类常量，必须声明时初始化赋值*/
    // private final static String a; //报错
    private final static String a = null;

    /*可以在声明时初始化赋值，也可以在构造函数中赋值*/
    private final int b = 0;
    private final String c;

    public FinalTest(String c) {
        this.c = c;
    }

    private final int[] array_i={1,2};

    public static void main(String[] args) {
        FinalTest finalTest = new FinalTest("str1");
        finalTest.array_i[0]=3;// final修饰的引用类型，不可变的是引用指向的对象地址，对象的内容可变
    }

    /**
     * final 修饰的方法不能被子类重写
     * 若是 private final，子类可以定义同名方法，不会报错
     */
    private final void finalMethod() {
        System.out.println("finalMethod");
        array_i[0]=4;// final修饰的引用类型，不可变的是引用指向的对象地址，对象的内容可变
    }


    /**
     * final 类 不能被继承。
     * final 类中的变量可以为final，也可以非final
     * final 类中的方法默认为final，因为不会被覆写。
     */
    public final class FinalClass {
        String a;

        void m() {

        }
    }
}
