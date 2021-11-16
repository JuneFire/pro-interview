package leetcode.Structure;

/**
 * @Author: zkcheng
 * @Date: 2021/07/01/20:30
 * @Description:
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int val){
        this.val = val;
    }

    public void add(int val){
        ListNode listNode = new ListNode(val);
        if(this.next == null){
            this.next = listNode;
        }else {
            this.next.add(val);
        }
    }
}
