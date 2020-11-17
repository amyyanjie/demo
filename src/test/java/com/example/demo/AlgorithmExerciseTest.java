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

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next=new ListNode(5);
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

    //反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
    //输入: 1->2->3->4->5->NULL, m = 2, n = 4
    //输出: 1->4->3->2->5->NULL
    //1 ≤ m ≤ n ≤ 链表长度
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode rev = null;
        int i = 1;
        ListNode pre = null;
        ListNode l =head;
        ListNode res = head;
        while (i <= n) {
            if (i >= m) {
                ListNode temp = l.next;
                rev = l;
                rev.next = pre;
                pre = rev;
                l = temp;
            } else {
                l = l.next;
            }

        }


        return null;
    }




}
