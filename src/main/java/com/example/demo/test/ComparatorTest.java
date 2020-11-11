package com.example.demo.test;

import com.example.demo.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/11/11 15:50
 */
public class ComparatorTest  {

    //自定义比较器
    public static class UserComparator implements Comparator<User> {

        @Override
        public int compare(User o1, User o2) {
            if (o1 == null || o2 == null) {
                return 0;
            }
            return o1.getAge() - o2.getAge();
        }
    }

    public static void main(String[] args) {
        User user1 = new User(1, 18);
        User user2 = new User(2, 15);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        //userList.sort(new UserComparator());
        Collections.sort(userList,new UserComparator());
        System.out.println(userList);
    }

}
