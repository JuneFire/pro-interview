package leetcode.Structure;

import java.util.Stack;

/**
 * @Author: zkcheng
 * @Date: 2021/07/01/20:29
 * @Description:
 */
public class LinkListTrain {

    //删除重复的节点
    public ListNode  deleteDuplicate(ListNode node){
        if(node == null || node.next == null){
            return node;
        }
        node.next = deleteDuplicate(node.next);
        return node.val == node.next.val ? node.next : node;
    }

    //删除链表的倒数第 n 个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        if (fast == null) return head.next;
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    // 链表反转(头插法)
    public ListNode reversList(ListNode head){
        ListNode newNode = new ListNode(-1);
        while (head != null){
            ListNode next = head.next; // 获取下一个节点
            head.next = newNode.next;  // head 的下一个节点 指向 newNode 的下一个节点
            newNode.next = head;  // 插入head
            head = next;  // 下一个节点
        }
        return newNode.next;
    }

    public ListNode ReverseList(ListNode head) {
        Stack<ListNode> stack= new Stack<>();
        //把链表节点全部摘掉放到栈中
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty())
            return null;
        ListNode node = stack.pop();
        ListNode dummy = node;
        //栈中的结点全部出栈，然后重新连成一个新的链表
        while (!stack.isEmpty()) {
            ListNode tempNode = stack.pop();
            node.next = tempNode;
            node = node.next;
        }
        //最后一个结点就是反转前的头结点，一定要让他的next
        //等于空，否则会构成环
        node.next = null;
        return dummy;
    }

    public static void main(String[] args){
        ListNode l1 = new ListNode(1);
        l1.add(2);
        l1.add(3);
//        int a = 2;
//        while (a-- > 0){
//            l1 = l1.next;
//            System.out.println(l1.val);
//        }
        LinkListTrain train = new LinkListTrain();
        System.out.println(train.removeNthFromEnd(l1,3));

    }
}
