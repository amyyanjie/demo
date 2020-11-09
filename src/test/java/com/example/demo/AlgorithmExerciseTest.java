package com.example.demo;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/11/09 16:38
 */
public class AlgorithmExerciseTest {

    public static void main(String[] args) {

    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    //给定一个链表: 1->2->3->4->5, 和 k = 2.
    //返回链表 4->5.
    public ListNode getKthFromEnd(ListNode head, int k) {
        //两个相隔 k-1 的指针
        ListNode fir = head;
        ListNode sec = head;
        for (int i = 1; i < k; i++) {
            fir = fir.next;
        }
        while (fir.next != null) {
            fir = fir.next;
            sec = sec.next;
        }
        return sec;
    }
}
