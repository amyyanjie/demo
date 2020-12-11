package com.example.demo.demos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/12/10 15:13
 */
public class StreamExample {
    /**
     * 通过 Supplier 创建 Stream，会不断调用 Supplier.get() 来不断生成下一个元素。调用 limit() 截取前面若干元素，变成有限序列。
     * 编写一个能输出斐波拉契数列（Fibonacci）的 LongStream
     */
    // 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
    public static void fibonacci(int n) {
        Stream<Long> stream = Stream.generate(new FibonacciSupplier());
        stream.limit(n).forEach(System.out::println);
    }

    public static class FibonacciSupplier implements Supplier<Long> {
        long a = 1; // 1, 0, 1, 1, 2, 3, 5
        long b = 0;

        @Override
        public Long get() {
            long c = a + b;
            a = b;
            b = c;
            return c;
        }
    }

    /**
     * map() 方法用于将 Stream 的每个元素对应到应用了目标函数的结果上，并转换成一个新的 Stream
     * map() 方法接收的对象是 Function 接口对象，它定义了一个apply 方法： R apply(T t);负责将 T 类型转换成 R 类型。
     */
    public static void mapExample() {
        Stream.of("apple ", " banana", "Pear", "ORANGE")
                .map(String::toLowerCase)
                .map(String::trim)
                .forEach(System.out::println);
        // 使用 map() 把一组 String 转换为 LocalDate 并打印
        Stream.of("2020-12-10", "2020-12-11", "2020-12-12")
                .map(LocalDate::parse)
                .forEach(System.out::println);
    }

    /**
     * filter() 方法用于对 Stream 的每个元素进行测试，符合条件的元素被过滤后生成一个新的 Stream
     * filter() 方法接收的是 Predicate 接口对象，它定义了一个 test 方法，判断元素是否符合条件。
     */
    public static void filterExample() {
        IntStream.of(1, 2, 3, 4, 5, 6)
                .filter(i -> i % 2 == 0) // 符合条件的偶数构成新的 Stream
                .forEach(System.out::println);
        // filter 可用于任何 Java 对象，例如：从一组给定的 LocalDate 中过滤掉工作日，以便得到休息日：
        Stream.generate(new LocalDateSupplier())
                .limit(10)
                .filter(i -> i.getDayOfWeek().equals(DayOfWeek.SATURDAY) || i.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                .forEach(System.out::println);
    }
    public static class LocalDateSupplier implements Supplier<LocalDate> {
        LocalDate now = LocalDate.now();
        int i = 1;
        @Override
        public LocalDate get() {
            i = i - 1;
            return now.plusDays(i);
        }
    }

    /**
     * reduce() 是一个聚合方法，reduce() 方法将一个 Stream 的每个元素依次作用于 BinaryOperator，并将结果合并。
     * reduce() 传入的对象是 BinaryOperator 接口，BinaryOperator 继承自 BiFunction，它定义了一个 apply 方法：R apply(T t, U u);
     *
     */
    public static void reduceExample() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Optional.ofNullable(stream.reduce(Integer::sum)).orElse(Optional.of(0));




    }

    public static void main(String[] args) {
        filterExample();
    }

}
