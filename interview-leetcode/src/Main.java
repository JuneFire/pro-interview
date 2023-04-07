import jdk.nashorn.internal.runtime.ListAdapter;
import leetcode.Structure.ListNode;
import leetcode.Structure.TreeNode;
import org.apache.commons.lang3.StringUtils;
import sun.font.FontRunIterator;
import test.DemoDO;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     *
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
    public boolean isTrue(TreeNode root1, TreeNode root2) {
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

    public List<List<Integer>> indexNum(int[] nums) {

        Map<Integer, List<Integer>> map = new LinkedHashMap<>();
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

        for (Integer key : map.keySet()) {
            result.add(map.get(key));
        }

        return result;
    }

    public static void main(String[] args) {
//        Main main = new Main();
//        List<List<Integer>> list = main.indexNum(new int[]{1,1,2,3,4,3,1,1,2,3,3,4,1,4});
//        list.forEach(System.out::println);
//        String test = "";
//        System.out.println(test.isEmpty());
//
        List<DemoDO> list = new ArrayList<>();
        DemoDO demoDO = new DemoDO();
//        demoDO.setName("1213");
//        demoDO.setValue("2123");
        list.add(demoDO);

        System.out.println(checkObjAllFieldsIsNulls(list));
    }
    /**
     *判断List对象中属性值是否全为空
     */
    private static boolean checkObjAllFieldsIsNulls(List<?> list){
        try {
            for (Object objet : list){
                for (Field f : objet.getClass().getDeclaredFields()){
//                    f.setAccessible(true);
                    Field field = objet.getClass().getDeclaredField(f.getName());
                    field.setAccessible(true);
                    System.out.println(f.getName()+ " " + field.get(objet));
                    if(field.get(objet) != null){
                        return true;
                    }
                }
                Class<?> superClazz = objet.getClass().getSuperclass();
                for (Field f : superClazz.getDeclaredFields()){
                    Field field = superClazz.getDeclaredField(f.getName());
                    field.setAccessible(true);
                    System.out.println(f.getName() + " " + field.get(objet));

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
      return false;
    }

    //    public static boolean checkObjAllFieldsIsNull(Object object) {
//        if (null == object) {
//            return true;
//        }
//        try {
//            for (Field f : object.getClass().getDeclaredFields()) {
//                f.setAccessible(true);
//                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
//                    return false;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return true;
//    }


    /**
     *判断对象中属性值是否全为空
     */
    public static boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                System.out.print(f.getName() + ":");
                System.out.println(f.get(object));
                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

//    public static boolean checkObjAllFieldsIsNull(Object object) {
//        if (null == object) {
//            return true;
//        }
//        try {
//            for (Field f : object.getClass().getDeclaredFields()) {
//                f.setAccessible(true);
//                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
//                    return false;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return true;
//    }


    /**
     *判断一个实体类对象实例的所有成员变量是否为空
     *@param obj 校验的类对象实例
     *@return List
     *@throws Exception
     */
    public static List<String> isObjectFieldEmpty(Object obj) {
        List<String> list = new ArrayList<>();
        try {
            Class<?> clazz = obj.getClass();  //得到类对象
            Field[] fs = clazz.getDeclaredFields(); //得到属性集合
            for (Field field : fs) {            //遍历属性
                field.setAccessible(true); //设置属性是可以访问的（私有的也可以）
                if (field.get(obj) == null || field.get(obj) == "" || "null".equals(field.get(obj))) {
                    String name = field.getName();
                    list.add(name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}



























