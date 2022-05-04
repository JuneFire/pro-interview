import jdk.nashorn.internal.runtime.ListAdapter;
import leetcode.Structure.ListNode;
import leetcode.Structure.TreeNode;
import sun.font.FontRunIterator;

import java.util.*;

/**
 * @Author: zkcheng
 * @Date: 2021/08/09/18:59
 * @Description:
 */
public class Main {
/*
    public static void main(String[] args) {
//        ArrayList<String> strings = new ArrayList<String>();
//        strings.add("Hello, World!");
//        strings.add("Welcome to online interview system of Acmcoder.");
//        strings.add("This system is running Java 8.");
//
//        for (String string : strings) {
//            System.out.println(string);
//        }
//
//        int a, b;
//        Scanner in = new Scanner(System.in);
//        while(in.hasNextInt()) {
//            a = in.nextInt();
//            b = in.nextInt();
//            System.out.printf("Your result is : %d\n", a + b);
//        }

        Main main = new Main();
        TreeNode node = new TreeNode();
        node.val = 3;
        node.left = new TreeNode();
        node.left.val = 1;
        node.right = new TreeNode();
        node.right.val = 1;
        node.left.left = new TreeNode();
        node.left.left.val = 2;
        node.right.right = new TreeNode();
        node.right.right.val = 3;
        System.out.println(main.isTrue(node, node));
    }
*/


    /**
     * 递减子串
     * @param nums
     * @return
     */
//    public List desort(int[] nums){
//
//        if(nums.length == 0){
//            return null;
//        }
//        int len = nums.length;
//        int i  = 0;
//        Stack<Integer> stack = new Stack<>();
//        ArrayList<Integer> list = new ArrayList<>();
//        stack.push(nums[0]);
//
//        while (!stack.isEmpty()){
//            if(len > i++){
//                break;
//            }
//
//            if(stack.peek() > nums[i] ){  // 栈顶的元素大于数组下一个遍历数，存栈
//                stack.push(nums[i]);
//            } else {  // 否则出栈  （9 4 3 2  5 4 3 2 ） (9 5 4 10 0) -> 9540
//                stack.pop();
//            }
//        }
//
//    }

    public boolean isTrue(TreeNode root1, TreeNode root2){
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isTrue(root1.left, root2.right) && isTrue(root1.right, root2.left);
    }

    //

    public List<List<Integer>> indexNum(int[] nums){

        Map<Integer, List<Integer>> map  = new LinkedHashMap<>();
        for (int num : nums) {
            map.put(num, new ArrayList<>());
        }

        for (int i = 0; i < nums.length; i++) {
            List<Integer> list = map.get(nums[i]);
            list.add(i);
            Collections.sort(list);
            map.put(nums[i], list);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (Integer key : map.keySet()){
            result.add(map.get(key));
        }

        return result;
    }

    public static void main(String[] args){
        Main main = new Main();
        List<List<Integer>> list = main.indexNum(new int[]{1,1,2,3,4,3,1,1,2,3,3,4,1,4});
        list.forEach(System.out::println);
    }
}



























