package leetcode.Structure;

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
