package com.example.demos;

import com.example.domain.User;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: yanjie
 * @Description: java.util.function 该包下所有的接口都是函数式接口：`@FunctionalInterface`。
 * 主要分为四大类：`Supplier`、`Consumer`、`Function`、`Predicate`。
 * @Date: 2020/12/17 11:54
 */
public class FunctionalInterfaceExample {

    /**
     * 一、Supplier<T> 供给型
     */
//    @FunctionalInterface
//    public interface Supplier<T> {
//        T get();
//    }
    public static void supplierExample() {
        /*1、使用Supplier接口实现方法*/
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt();
            }
        };
        /*2、使用lambda简写*/
        Supplier<Integer> supplier1 = () -> new Random().nextInt();
        Supplier<Double> supplier2 = Math::random;
        System.out.println(supplier1.get());
        System.out.println(supplier2.get());
        /*3、在JDK8 中常见用处*/
        // Optional 对象的 orElseGet 和 orElseThrow 方法
        User user = null;
        user = Optional.ofNullable(user).orElseGet(User::new);
        user = Optional.ofNullable(user).orElseThrow(() -> new IllegalArgumentException("user is null"));
    }

    /**
     * 二、Consumer<T> 消费型
     */
//    @FunctionalInterface
//    public interface Consumer<T> {
//        void accept(T t);
//    }
    public static void consumerExample() {
        /*1、使用Consumer接口实现方法*/
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.toLowerCase());
            }
        };
        /*2、使用lambda简写*/
        Consumer<String> consumer1 = (s) -> {
            System.out.println(s.toLowerCase());
        };
        consumer1.accept("ABC");

        /*3、在JDK8 中常见用处*/
        // Iterator的forEach方法，Stream的forEach方法需要的参数
        List<User> userList = new ArrayList<>();
        userList.forEach((i) -> i.setIdCardNo(""));

        Stream<String> stringStream = Stream.of("a", "b", "c");
        stringStream.forEach(System.out::println); // stringStream.forEach(i-> System.out.println(i));
    }

    /**
     * 三、断言型 Predicate<T>
     */
//    @FunctionalInterface
//    public interface Predicate<T> {
//        boolean test(T t);
//    }
    public static void predicateExample() {
        /*1、使用Predicate接口实现方法*/
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer < 100;
            }
        };
        /*2、使用lambda简写*/
        Predicate<Integer> predicate1 = (i -> i < 1000);
        System.out.println(predicate1.test(100));
        /*3、在JDK8 中常见用处*/
        // 如 Stream 的 filter 方法需要的参数就是 Predicate 接口
        IntStream.of(1, 2, 3, 4, 5, 6)
                .filter(i -> i % 2 == 0) // 符合条件的偶数构成新的 Stream
                .forEach(System.out::println);

        String key = "";
        Optional.of(key).filter(i -> !StringUtils.isEmpty(i)).orElseThrow(() -> new IllegalArgumentException("key is empty"));

    }

    /**
     * 四、Function<T, R> 功能型，将输入数据转换成另一种形式的输出数据。
     */
//    @FunctionalInterface
//    public interface Function<T, R> {
//        R apply(T t);
//    }
    public static void functionExample() {
        /*1、使用 Function 接口实现方法*/
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        /*2、使用lambda简写*/
        Function<String, Integer> function1 = String::length;//  Function<String,Integer> function1=(s)->s.length();
        System.out.println(function1.apply("abcd"));
        Function<User, String> function2 = (user) -> {
            return user.getMobile().replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1******$2");
        };
        //  Function<User, String> function2 = (user) -> user.getMobile().replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1******$2");
        User user = new User();
        user.setMobile("500239199709272921");
        System.out.println(function2.apply(user));
        /*3、在JDK8 中常见用处*/
        // 如 HashMap.computeIfAbsent
        // 如 Stream 的 map 转换流
        Map<Integer, User> map = new HashMap<>();
        int id = 1;
        user = map.computeIfAbsent(id, i -> new User(id));
        // java8 之前写法： if (map.get(id) == null) { map.put(id, new User(id)); }

        List<User> userList=new ArrayList<>();
        userList.add(new User(1,"张三","xxx","xxx"));
        userList.add(new User(2,"李四","xxx","xxx"));
        userList.add(new User(3,"王五","xxx","xxx"));
        List<String> names = userList.stream().map(User::getName).collect(Collectors.toList());
        System.out.println(names);
    }

    public static void main(String[] args) {
        supplierExample();
        consumerExample();
        predicateExample();
        functionExample();
    }


}

