package com.example.demos;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2021/05/26 11:18
 */

public class MyLinkedList {

    /**
     * Initialize your data structure here.
     */
    ListNode head; // sentinel node as pseudo-head
    ListNode tail; // sentinel node as pseudo-tail
    int size;

    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;

    }

    class ListNode {
        int val;
        ListNode prev;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        // 0,1,2,3,0。 size=3,index=2

        ListNode curr = head;
        if (index < size - index) { // to move from the head
            for (int i = 0; i <= index; i++) {
                curr = curr.next;
            }
        } else { // to move from the tail
            curr = tail;
            for (int i = 0; i < size - index; i++) {
                curr = curr.prev;
            }
        }
        return curr.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    // 0,1,2
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        // find predecessor and successor of the node to be added
        ListNode pred, succ;

        if (index < size - index) { // 从头部开始较快
            pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            succ = pred.next;

        } else {
            succ = tail;
            for (int i = 0; i < size - index; i++) {
                succ = succ.prev;
            }
            pred = succ.prev;
        }
        // insertion itself
        ++size;
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        // find predecessor and successor of the node to be deleted
        ListNode prev, succ;
        if (index < size - index) {
            prev = head;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            succ = prev.next.next;
        } else {
            // 0,1,2,3,0 size=3,index=2
            succ = tail;
            for (int i = 0; i < size - index - 1; i++) {
                succ = succ.prev;
            }
            prev = succ.prev.prev;
        }
        // delete pred.next
        --size;
        prev.next = succ;
        succ.prev = prev;
    }
}
