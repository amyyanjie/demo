package com.example.demo.demos.Singleton;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2021/04/26 15:47
 */
public class SingleAttackDemo {
    /**
     * 常用单例模式方法（除枚举外），可看出这些方法具有共同点之一是私有的构造函数。这是为了防止在该类的外部直接调用构建函数创建对象了
     * 但是该做法却无法防御反射攻击：获得单例类的构造器；把构造器设置为可访问；使用 newInstance 方法构造对象。
     */
    public static void reflectAttackTest() throws Exception {
        // 1.常用单例模式方法
        DoubleCheckedLocking instance = DoubleCheckedLocking.getInstance();
        Constructor<DoubleCheckedLocking> constructor = DoubleCheckedLocking.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        DoubleCheckedLocking reflectInstance = constructor.newInstance();
        System.out.println(instance == reflectInstance); // 输出false，无法防御反射攻击

        // 2.枚举，即使成功获取构造函数，newInstance处也会报异常 IllegalAccessException，不能反射创建枚举对象
        EnumSingleton enumInstance = EnumSingleton.INSTANCE;
        // Java 生成的枚举类都会继承 Enum 抽象类，其只有一个构造函数：    protected Enum(String name, int ordinal) {//... }
//        Constructor<EnumSingleton> enumConstructor = EnumSingleton.class.getDeclaredConstructor(); // getDeclaredConstructor()会抛出异常，NoSuchMethodException，因为EnumSingleton无构造函数
        Constructor<EnumSingleton> enumConstructor2 = EnumSingleton.class.getDeclaredConstructor(String.class, int.class);
        enumConstructor2.setAccessible(true);
        EnumSingleton reflectEnumInstance = enumConstructor2.newInstance("ENUM_INSTANCE", 1); // IllegalAccessException: Cannot reflectively create enum objects
        System.out.println(enumInstance == reflectEnumInstance);
    }

    /**
     * 反序列化攻击：如果单例类实现了序列化接口 Serializable, 就可以通过反序列化破坏单例。
     * 所以我们可以不实现序列化接口，如果非得实现序列化接口，可以重写反序列化方法 readResolve()， 反序列化时直接返回相关单例对象。
     */
    public static void deserializeAttackTest() throws Exception {
        // 1.常用单例模式方法
        DoubleCheckedLocking instance = DoubleCheckedLocking.getInstance();
        byte[] bytes = serialize(instance);
        Object deserializeInstance = deserialize(bytes);
        System.out.println(instance == deserializeInstance);
        // 如果DoubleCheckedLocking 实现了序列化接口 Serializable，输出 false，能创建两个不同对象
        // 如果DoubleCheckedLocking 不实现序列化接口，抛出 NotSerializableException。

        // 2.枚举
        EnumSingleton enumInstance = EnumSingleton.INSTANCE;
        byte[] enumBytes = serialize(enumInstance);
        Object enumDeserializeInstance = deserialize(enumBytes);
        System.out.println(enumInstance == enumDeserializeInstance); // 枚举反序列得到的仍是同样的对象
    }


    private static byte[] serialize(Object object) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    private static Object deserialize(byte[] bytes) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }




    public static void main(String[] args) {
        try {
//            reflectAttackTest();
            deserializeAttackTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
