package src;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zkCheng
 * @date 2022/8/19 22:07
 */
public class SystemIn {

    // 第一行是数组的长度，第二行是数组
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()){
//            int len = sc.nextInt();
//            int[] arr = new int[len];
//            for (int i = 0; i < len; i++) {
//                arr[i] = sc.nextInt();
//            }
//        }
//    }
/*
    public static void main(String[] args){
        //创建对象
        Scanner sc = new Scanner(System.in);
        System.out.println("输入数据:");
        //多行输入
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        String[] str = new String[m];

        //int等基本数据类型的数组,用nextInt()，同行或不同都可以
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        //String字符串数组, 读取用next()，以空格划分
        for(int i=0; i<m; i++) {
            str[i] = sc.next();
        }


        //调用方法进行操作
        TestSc(n, m, arr);
        TestStr(str);

        System.out.println("Test01 End");

        //关闭
        sc.close();

    }*/
    public static void TestSc(int n, int m, int[] arr) {
        System.out.println("数据n：" + n + ", 数据m：" + m);
        System.out.println(Arrays.toString(arr));
    }

    public static void TestStr(String[] str) {
        System.out.println(Arrays.toString(str));
    }


    /**
     * 第一行输入n, m
     *
     * 第二行开始输入二维数组。
     */


//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        System.out.println("输入数据:");
//
//        //二维数组
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//        int[][] arr2 = new int[n][m];
//        System.out.println("Test02 输入二维数组数据：");
//
//        //可以直接读入
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                arr2[i][j] = sc.nextInt();
//            }
//        }
//
////        TestSc(n, m, arr2);
//        //关闭
//        sc.close();
//    }


    /**
     * 第一行输入n，
     *
     * 第二行开始输入n行字符串，字符串中包含空格。
     * @param args
     */
  /*  public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String[] strs = new String[n];

        sc.nextLine(); 	//注意！！！光标换到下一行

        for(int i=0; i<n; i++) {
            String str = sc.nextLine();
            strs[i] = str;
        }

        Tes2(strs);
        System.out.println("End");
        sc.close();
    }
*/

    public static void Tes2(String[] strs) {
        for(int i=0; i<strs.length; i++) {
            String str = strs[i];
            System.out.println(str);
        }
    }


 /*   //换行输入数字和字符串
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        //注意！！！光标换到下一行
        sc.nextLine();

        String s = sc.nextLine();
        String str = sc.nextLine();

        System.out.println("n = " + n + " , m = " + m);
        System.out.println("s = " + s);
        System.out.println("str = " + str);

        sc.close();

    }*/




    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            int n = sc.nextInt();
//            sc.nextLine();
//            String str = sc.nextLine();
            String str = sc.next(); // 没有空格
            Tes(n, str);
        }

        sc.close();
    }

    public static void Tes(int n, String str) {
        System.out.println("n = " + n);
        System.out.println("str = " + str);
        System.out.println("str.length = " + str.length());
    }


}
