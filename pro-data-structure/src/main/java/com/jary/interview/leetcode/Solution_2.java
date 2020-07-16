package demo2;

/*
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */



public class Solution_2 {

    public static class ListNode {

        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;

        while(l1!=null || l2!=null){
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int add = x + y + carry;

            carry = add / 10;  // 向下取整
            add = add % 10;

            cur.next = new ListNode(add);  // 头节点为空
            cur = cur.next;

            if(l1!=null)
                l1 = l1.next;
            if(l2!=null)
                l2 = l2.next;
        }

        if(carry > 0){
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    void printList(ListNode last) {
        while (last != null) {
            System.out.print(last.val + ",");
            last = last.next;
        }
    }
    public static void main(String[] args){
        ListNode l1 = new ListNode(8);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);

        ListNode l2 = new ListNode(4);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);

        Solution_2 solution2 = new Solution_2();
        ListNode listNode = solution2.addTwoNumbers(l1,l2);
        solution2.printList(listNode);
    }
}
