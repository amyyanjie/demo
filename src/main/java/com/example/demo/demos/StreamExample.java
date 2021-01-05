package com.example.demo.demos;

import com.example.demo.utils.StringUtil;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: yanjie
 * @Description: Stream
 * 一、转换操作：把一个 stream 转换成另一个 stream，如 map(),filter()。转换操作不会触发任何计算。转换操作只是保存转换规则。
 * 二、聚合操作：对 stream 的每个元素进行计算，得到一个确定结果，如 reduce()
 * 三、其他：sorted()、distinct()、skip()、limit()、Stream.concat(s1, s2)、flatMap()、parallel()、forEach()...
 * <p>
 * Stream 提供的常用操作有：
 * <p>
 * 转换操作：map()，filter()，sorted()，distinct()；
 * <p>
 * 合并操作：concat()，flatMap()；
 * <p>
 * 并行处理：parallel()；
 * <p>
 * 聚合操作：reduce()，collect()，count()，max()，min()，sum()，average()；
 * <p>
 * 其他操作：allMatch(), anyMatch(), forEach()。
 * @Date: 2020/12/10 15:13
 */
public class StreamExample {

    public static void createStream() {
        /* 1.Stream.of() 静态方法，创建能输出确定元素的 Stream*/
        Stream<String> stream1 = Stream.of("A", "B", "C", "D");
        stream1.forEach(System.out::println);// foreach，可传入符合Consumer接口的void accept(T t)的方法引用
        /* 2.基于数组或 Collection*/
        String[] array = new String[]{"A", "B", "C"};
        Stream<String> stream2 = Arrays.stream(array); // 把数组变成 Stream 使用 Arrays.stream() 方法
        List<String> list = new ArrayList<>();list.add("D");
        Stream<String> stream3 = list.stream(); // 对于 Collection（List、Set、Queue 等），直接调用 stream() 方法就可以获得 Stream。

        /* 3.基过 Supplier 创建 Stream*/
        // 创建 Stream 还可以通过 Stream.generate() 方法，它需要传入一个 Supplier 对象：
        // 基于 Supplier 创建的 Stream 会不断调用 Supplier.get() 方法来不断产生下一个元素，这种 Stream 保存的不是元素，而是算法，它可以用来表示无限序列。
        // 调用 limit() 截取前面若干元素，变成有限序列。
        // 例如，编写一个能输出斐波拉契数列（Fibonacci）的 LongStream：// 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
        Stream<Long> stream = Stream.generate(new FibonacciSupplier());// stream的转换不会触发任何计算，这里不会打印get()中的：调用get
        stream.limit(5).forEach(System.out::println);// 这里会先打印：调用get，再输出 n。

        /*4、基本类型的 Stream*/
        // 使用 Stream<Integer> 会产生频繁的装箱、拆箱操作
        // 为了提高效率，Java 标准库提供了 IntStream、LongStream 和 DoubleStream 这三种使用基本类型的 Stream。
        IntStream intStream = IntStream.of(1, 2, 3);
        IntStream intStream1 = Arrays.stream(new int[]{1, 2, 3});
    }
    public static class FibonacciSupplier implements Supplier<Long> {
        long a = 1; // 1, 0, 1, 1, 2, 3, 5
        long b = 0;
        @Override
        public Long get() {
            System.out.println("调用get");
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
     * Stream 的 map, foreach, peek 的区别？
     */
    public static void mapAndForeachAndPeek() {
        // map 入参是 Function 接口函数，返回自定义流
        // foreach 入参是 Consumer 接口函数，会中断流
        // peak 入参是 Consumer 接口函数，不会中断流，后面可以对流继续操作，在debug 场景比较方便
        Stream.of("apple ", " banana", "Pear", "ORANGE")
                .map(new Function<String, String>() { // Function 函数，有入参有出参，返回自定义流，此时不会触发计算
                    @Override
                    public String apply(String i) {
                        return i.toLowerCase() + ",";
                    }
                })
                .peek(new Consumer<String>() { // Consumer 函数，有入参无出参，不会中断流，后面可以对流继续操作
                    @Override
                    public void accept(String s) {
                        System.out.println(s);
                    }
                })
                .forEach(new Consumer<String>() { // Consumer 函数，有入参无出参，会中断流。
                    @Override
                    public void accept(String s) {
                        System.out.println("fruit:" + s);
                    }
                });


        //以下为简写：
        Stream.of("apple ", " banana", "Pear", "ORANGE")
                .map(i -> i.toLowerCase() + ",")
                .peek(System.out::println)
                .forEach(s -> System.out.println("fruit:" + s));
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
     * reduce() 是聚合方法，聚合方法会立刻对 Stream 进行计算。
     * reduce() 传入的对象是 BinaryOperator 接口，BinaryOperator 继承自 BiFunction，它定义了一个 apply 方法：R apply(T t, U u);
     */
    public static void reduceExample() {
        /*1、设定初始值，返回的是初始值类型（T) 的数据*/
        int sum = Stream.of(1, 2, 3, 4, 5).reduce(0, Integer::sum); //等同于 stream.reduce(0, (a, b) -> a + b);
        // reduce操作首先初始化结果为指定值（这里为0），紧接着，reduce() 对每个元素依次调用 (a, b) -> a + b，其中，a 是上次计算的结果。
        //sum, min, max, average 均可
        int max = Stream.of(1, 2, 3, 4, 5).reduce(0, Math::max);
        int multiply = Stream.of(1, 2, 3, 4, 5).reduce(1, (a, b) -> a * b); // 求积运算

        /*2、不设定初始值，返回的是 Optional<T>类型，因为 stream 的元素可能为 0 个，返回 Optional 对象，需要进一步判断结果是否存在，*/
        Optional<Integer> optional = Stream.of(1, 2, 3, 4, 5).reduce(Math::min);
        optional.ifPresent(System.out::println);

        /*3、用于对象，用map和reduce将众多map汇集成一个*/
        Stream<String> stream = Stream.of("profile=native", "debug=true", "logging=warn", "interval=500");
        Map<String, String> map = stream.map(kv -> {
            String s[] = kv.split("\\=", 2); // 分割成2个字符串，regex 为正则表达式，对=应该加\\转义处理。
            Map<String, String> m = new HashMap<>();
            m.put(s[0], s[1]);
            return m;
        }).reduce(new HashMap<String, String>(), (m, kv) -> {
            m.putAll(kv);
            return m;
        });
        map.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        // 以上map后reduce，可用forEach实现，将字符串分割后放入map
        List<String> st = Stream.of("profile=native", "debug=true", "logging=warn", "interval=500").collect(Collectors.toList());
        Map<String, String> stringMap = new HashMap<>();
        st.forEach(s -> {
            String[] array = s.split("\\=", 2);
            stringMap.put(array[0], array[1]);

        });
        stringMap.forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });
        // 也可以直接输出 map
        Map<String, String> stringMap1 = Stream.of("profile=native", "debug=true", "logging=warn", "interval=500")
                .collect(Collectors.toMap(s -> s.split("\\=", 2)[0], s -> s.split("\\=", 2)[1]));
    }

    /**
     * 输出集合
     */
    public static void collect() {
        // 1、输出为 List，是一个聚合操作，会强制 Stream 输出每个元素
        // collect(Collectors.toList())，collect(Collectors.toSet())
        List<String> list = Stream.of("Apple", "", null, "Pear", "  ", "Orange").filter(s -> !StringUtil.isEmpty(s)).collect(Collectors.toList());
        System.out.println(list);
        // 2、输出为数组。toArray传入的是IntFunction<A[]> generator。
        // 这里的String[]::new，它的签名实际上是 IntFunction<String[]> 定义的 String[] apply(int)，即传入 int 参数，获得 String[] 数组的返回值。
        String[] strs = Stream.of("Apple", "Pear", "Orange").toArray(String[]::new);
        System.out.println(Arrays.toString(strs));
        int[] ints = IntStream.of(1, 2, 3).toArray();
        // 3、输出为 Map
        Stream<String> stream1 = Stream.of("APPL:Apple", "MSFT:Microsoft");
        Map<String, String> map = stream1.collect(Collectors.toMap(s -> s.substring(0, s.indexOf(":")), s -> s.substring(s.indexOf(":") + 1)));
        System.out.println(map);
        // 4、分组输出 collect(Collectors.groupingBy()),groupBy需要提供两个函数，一个是分组的 key（这里是首字母），一个是分组的 value（这里输出为list）
        Stream<String> stream2 = Stream.of("Apple", "Banana", "Blackberry", "Coconut", "Avocado", "Cherry", "Apricots");
        Map<String, List<String>> group = stream2.collect(Collectors.groupingBy(s -> s.substring(0, 1), Collectors.toList()));
    }

    public static void main(String[] args) {

        Stream<String> stream2 = Stream.of("Apple", "Banana", "Blackberry", "Coconut", "Avocado", "Cherry", "Apricots");
        String m = stream2.filter(s -> s.startsWith("A")).findFirst().map(String::toLowerCase).orElse("null");
//        System.out.println(m);

        Stream.of("apple", "apple", " banana", "Pear", "ORANGE").distinct().forEach(System.out::println);

        String[][] level_Info = {{"1", "10000"}, {"2", "50000"}, {"3", "100000"},
                {"4", "500000"}, {"5", "1000000"}, {"6", "5000000"}};

        BigDecimal amount = new BigDecimal("999");
        String grade = Arrays.stream(level_Info).filter(info -> new BigDecimal(info[1]).compareTo(amount) > 0).findFirst().map(info -> info[0]).orElse("0");
        List list = Stream.of(level_Info).sorted((o1, o2) -> new BigDecimal(o2[0]).compareTo(new BigDecimal(o1[0]))).map(info -> info[0]).collect(Collectors.toList());
        System.out.println(list);

        Stream.of("a","b","c").peek(i-> System.out.println("peek"+i)).forEach(System.out::println);

        mapAndForeachAndPeek();
    }
}
