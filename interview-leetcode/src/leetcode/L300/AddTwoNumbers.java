package leetcode.L300;

import leetcode.Structure.ListNode;

/**
 * https://leetcode.wang/leetCode-2-Add-Two-Numbers.html
 */

public class AddTwoNumbers {


    /**
     * 正序 2->4->3 + 5->6->4
     * 得到 7->0->8
     * @param l1
     * @param l2
     */
    private static ListNode sum(ListNode l1, ListNode l2){
        ListNode head = new ListNode(0);
        ListNode p = l1, q = l2, curr = head;
        int carry = 0;
        while (p != null || q != null){
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + carry;
            carry  = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        // 处理尾部溢值
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return head.next;
    }

    /**
     * 反序
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head){
        if(head == null) return null;
        ListNode pre = null;
        ListNode next;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 递归反序
     * @param head
     * @return
     */
    public ListNode reverseListRecursion(ListNode head){
        ListNode newHead;
        if(head==null||head.next==null ){
            return head;
        }
        newHead = reverseListRecursion(head.next); //head.next 作为剩余部分的头指针
        head.next.next = head; //head.next 代表新链表的尾，将它的 next 置为 head，就是将 head 加到最后了。
        head.next = null;
        return newHead;
    }

    // 快排
    public static int getIndex(int[] arr, int low, int high){
        int temp = arr[low]; //基准数
        while (high > low){
            while(low < high && arr[high] >= temp){
                high--;  // 移动指针
            }
            arr[low] = arr[high]; // 当arr[high]小于等于temp时，将其赋值给arr[low]
            while (low < high && arr[low] <= temp){
                low++;
            }
            arr[high] = arr[low];
        }
        // 跳出循环，此时插入temp在对应的位置
        arr[low] = temp;
        return low;
    }

    public static void quickSort(int[] arr, int low, int high){
        if(low < high){
            int index = getIndex(arr, low, high);
            quickSort(arr, low, index-1);
            quickSort(arr, index + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22 };
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.print(" " + i);
        }
    }

}
