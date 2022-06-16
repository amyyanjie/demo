package com.example.demos;

import com.example.domain.User;

import java.util.*;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/11/11 15:50
 */
public class ComparatorTest {
    private final String m;

    private final int b;
    public ComparatorTest(int b) {
        this.m = "2";
        this.b = b;
    }

    //自定义比较器
    public static class UserComparator implements Comparator<User> {

        @Override
        public int compare(User o1, User o2) {
            if (o1 == null || o2 == null) {
                return 0;
            }
            return o1.getUserId() - o2.getUserId();
        }
    }
    //1+2+4+8

    public static void testNullInTreeMap(User user1, User user2) {
        /**
         * 测试TreeMap的键值是否能为 null
         * 值可以为 null，键不建议为null，key为null 可能抛空指针
         */
        // 未指定Comparator时，按key自然排序，key为null时 会抛出 NullPointerException
        TreeMap<String, Object> treeMap = new TreeMap<>();
//        treeMap.put(null,"m"); // throw new NullPointerException();
        treeMap.put("str1", null);
        System.out.println(treeMap.get("str1")); //输出 null

        // 若指定Comparator，
        // 若实现的比较器未对null进行判断，可能抛出 NullPointerException
        // 若实现的比较器有对null进行判断，可以存入，但注意值覆盖问题。若是在比较器中判断为null时比较结果返回0，注意在TreeMap中比较结果为0时会发生值替换。
        // 值可以为 null
        // 在TreeMap中比较结果为0发生值替换。
        TreeMap<User, Object> userTreeMap = new TreeMap<>(new UserComparator());
        userTreeMap.put(null, "b");
        System.out.println(userTreeMap.get(null)); //输出 b

        userTreeMap.put(user1, "c");
        System.out.println(userTreeMap.get(user1)); // 输出 c
        System.out.println(userTreeMap.get(null)); // 此时输出 c，而不是b，因为存入时 user1 与 键null 比较时结果为0，value直接替换
        System.out.println("later user1:"+userTreeMap.get(user1)); // 输出c

        userTreeMap.put(user2, null);
        System.out.println(userTreeMap.get(user2));  //输出 null

        System.out.println(userTreeMap.get(null)); //此时输出 null，因为存入时 user2 与 键null 比较时结果为0，value直接替换
        System.out.println("later user2:"+userTreeMap.get(user2)); // 输出null
    }

    public static void main(String[] args) {
        User user1 = new User(1);
        User user2 = new User(2);
        User user3 = null;

        /* 测试User比较器*/
        UserComparator userComparator = new UserComparator();
        System.out.println(userComparator.compare(user1, user2)); //返回-1

        /* 测试将User比较器作为参数用于排序类某个方法*/
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
//        Collections.sort(userList, new UserComparator());
        userList.sort(new UserComparator());
        System.out.println(userList);

        /**/
        testNullInTreeMap(user1, user2);


    }

}
