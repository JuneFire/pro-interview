package com.niukewang;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Main{

    public boolean hasCycle(ListNode head){

        if(head == null || head.next == null){
            return false;

        }

        ListNode node1 = head;
        ListNode node2 = head.next;

        while(node1 != node2){
            if(node2 == null || node2.next == null){
                return false;
            }
            node1 = node1.next;
            node2 = node2.next.next;
        }
        return true;
    }

}
