package com.example.demo;

import com.fasterxml.jackson.core.sym.NameN;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/11/09 16:38
 */
public class AlgorithmExerciseTest {

    /**
     * 链表结构
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 树结构
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 双向链表，如LinkedList的底层结构
     */
//    public static class Node<E> {
//        E item;
//        Node<E> next;
//        Node<E> prev;
//
//        Node(E element, Node<E> next, Node<E> prev) {
//            this.item = element;
//            this.next = next;
//            this.prev = prev;
//        }
//    }

    public static class Node {
        int val;
        Node next;
        Node random;

        Node(int x) {
            this.val = x;
            this.next = null;
            this.random = null;
        }
    }

    //反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
    //输入: 1->2->3->4->5->NULL, m = 2, n = 4
    //输出: 1->4->3->2->5->NULL
    //1 ≤ m ≤ n ≤ 链表长度
    public static ListNode reverseBetween(ListNode head, int m, int n) {

        // 先找到反转链表头的前一节点 con，即m的前一节点
        ListNode cur = head;
        for (int i = 2; i < m; i++) {
            cur = cur.next;
        }
        // prev前驱节点，cur当前节点
        ListNode con = cur;
        ListNode prev = cur.next;
        ListNode tail = cur.next;

        // 反转 m 到 n
        for (int j = m; j <= n; j++) {
            ListNode tempNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tempNext;
        }
        // 将三部分连接

        tail.next = cur;

        con.next = prev;
        return head;
    }

    //"RDDR"
    //DRRDRDRDRDDRDRDR
    //
    //
    public static String predictPartyVictory(String senate) {
        int sum = 0;
        int length = senate.length();
        // 尽可能消灭异己，要消灭最近的异党
        // 双指针， i

        // R 表示成 -1，D 表示成 1。
        //
        for (int i = 0; i < length; i++) {

            sum += senate.charAt(i) == 'R' ? -1 : 1;
        }
        // 若是结果 =0，最后一个是 D，则 R 赢
        if (sum == 0) {
            return senate.charAt(0) == 'D' ? "Dire" : "Radiant";
        }
        return sum < 0 ? "Radiant" : "Dire";
    }


    public static void main(String[] args) {
        // 第一轮投完: DRRRRDDD 第二轮: DRRR 第三轮: RR
        // DRRDRDRD
        System.out.println(predictPartyVictory("DRRDRDRD RDDRDRDR"));
    }







}
