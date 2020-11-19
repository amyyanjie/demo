package com.example.demo;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2020/11/09 16:38
 */
public class AlgorithmExerciseTest {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    // -1->1->2->3->4->2
    public ListNode removeElements(ListNode head, int val) {
        ListNode res = new ListNode(-1);//哑结点，可以不用专门讨论删除头节点的情况
        res.next = head;
        ListNode pre = res;   //前继指针
        ListNode cur = head;  //当前指针
        while (cur != null) {
            if (cur.val == val) { // 若当前节点cur需删除，将前继指针pre指向当前节点cur的下一节点，cur向前移动一步。
                pre.next = cur.next;
            } else { // 若当前节点不需删除，pre和cur向前移动一步。
                pre = pre.next;
            }
            cur = cur.next;
        }
        return res.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        ListNode l2 = reverseBetween(l1, 1, 4);
        System.out.println(l2.val);
        System.out.println(l2.next.val);
        System.out.println(l2.next.next.val);
        System.out.println(l2.next.next.next.val);
        System.out.println(l2.next.next.next.next.val);


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

        // 反转m到n
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


}
