package com.example.demos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.annotation.security.DenyAll;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2022/05/05 15:13
 */
public class EqualsAndHashCodeTest {
    public static void main(String[] args) {
        String res = test();
        System.out.println(res);
//        ADetail a1 = new ADetail("1", "white");
//        ADetail a2 = new ADetail("2", "white");
//        boolean res = a1.equals(a2);
//        System.out.printf(String.valueOf(res));
    }

    public static String test() {
        String s = "s";
        try {
            System.out.println("res:" + s);

        } finally {
//            s = "abc";
            s= s +"c";
            System.out.println("execute testFinally1");
        }
        return s;
    }
}

@Data
@EqualsAndHashCode(callSuper = true)
// @EqualsAndHashCode // callSuper默认false，不调用父类的属性，那么子类属性里面的相同的话，那hashcode的值就相同啦
class ADetail extends A {
    private String color;

    public ADetail(String id, String color) {
        super(id);
        this.color = color;
    }
}

@Data
class A {
    private String id;
    public A() {
    }
    public A(String id) {
        this.id = id;
    }
}

