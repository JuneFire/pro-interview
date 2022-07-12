package leetcode.L300;

import leetcode.Structure.ListNode;

/**
 * @Author: zkcheng
 * @Date: 2022/06/23/15:40
 * @Description: 检测环形链表
 */
public class Solution_142_detectCycle {

    public ListNode detectCycle(ListNode head){
        if(head == null) return null;
        ListNode fast = head, slow = head;
        boolean loopExists = false;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                loopExists = true;
            }
        }
        // 查找环入口
        if(loopExists){
            slow = head;
            while (slow != fast){
                slow = slow.next;
                fast = fast.next;
            }
            return  slow;
        }
        return null;

    }

    // 反转链表
    public ListNode reverse(ListNode head){
        if(head == null) return null;
        ListNode curt = head;
        ListNode preNode = null;
        while (curt != null){
            ListNode next = curt.next;
            curt.next = preNode;
            preNode = curt;
            curt = next;
        }

        return preNode;
    }

    // 利用反转链表实现回文链表
    public boolean isPalinedrome(ListNode head){
        if(head == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        // 处理奇数长度的链表
        if(fast != null){
            slow = slow.next;
        }
        // 反转slow链表
        slow = reverse(slow);
        fast = head;
        // 开始比对
        while (slow != null){
            if(slow.val != fast.val){
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public static void main(String[] args){
        ListNode test = new ListNode(9);
        test.add(0);
    }
}
