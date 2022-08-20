package src;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author zkCheng
 * @date 2022/8/20 10:02
 */
public class Main {
    public static void main(String[] args) {
//        DecimalFormat df = new DecimalFormat("0.00");
//        System.out.println(df.format(10.2));

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] p = new int[n];   // 概率
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }

        int score[] = new int[n];  // 分数
        for (int i = 0; i < n; i++) {
            score[i] = sc.nextInt();
        }

        handle(n, m, p, score);

    }


    public static void handle(int n, int m, int[] p, int[] score) {
        if (m > n) {
            m = n;
        }

        // 优先复习分值高的题，这样才能达到最大值

        Map<Integer,Integer> map = new HashMap<>();

//        List<Map> list = new ArrayList<>();

        int max = 0;
        int[] question = new int[n];

        for (int i = 0; i < n; i++) {
            question[i] = (p[i] * score[i]);    // 将所有分数塞进去
            map.put(i, question[i]);
        }
//        Arrays.sort(question);
        map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));// 对map进行排序

        Set<Integer> set = map.keySet();
        int count = 0;
        for (Integer o : set){
            if(count == m)
                break;
            count++;
            map.put(o, score[o] * 100);
        }

        for (Integer o : set){
            max += map.get(o);
        }

        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(df.format(max));
    }

}


//
//
//    //信标
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//
//        int[] x1 = new int[2];
//        int[] x2 = new int[2];
//        int[] x3 = new int[2];
//
//        for (int i = 0; i < 2; i++) {
//            x1[i] = sc.nextInt();
//        }
//        for (int i = 0; i < 2; i++) {
//            x2[i] = sc.nextInt();
//        }
//        for (int i = 0; i < 2; i++) {
//            x3[i] = sc.nextInt();
//        }
//
//        int[] ans = new int[3];
//        for (int i = 0; i < 3; i++) {
//            ans[i] = sc.nextInt();
//        }
//
//        handleMap(x1, x2, x3, ans, n);
//    }
//
//
//    public static void handleMap(int[] x1, int[] x2, int[] x3, int[] ans, int n) {
//        List<int[]> xb = new ArrayList();  // 用来装答案
//
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if ((Math.abs(x1[0] - i) + Math.abs(x1[1] - j)) == ans[0]) {
//                    int[] temp = new int[2];
//                    temp[0] = i + 1;
//                    temp[1] = j + 1;
//                    xb.add(temp);
//                }
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if ((Math.abs(x2[0] - i) + Math.abs(x2[1] - j)) == ans[1]) {
//                    int[] temp = new int[2];
//                    temp[0] = i + 1;
//                    temp[1] = j + 1;
//                    xb.add(temp);
//                }
//            }
//        }
//
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if ((Math.abs(x3[0] - i) + Math.abs(x3[1] - j)) == ans[2]) {
//                    int[] temp = new int[2];
//                    temp[0] = i + 1;
//                    temp[1] = j + 1;
//                    xb.add(temp);
//                }
//            }
//        }
//
//        int[] result = new int[2];
//
//        if (xb.size() > 0) {
//            result = xb.get(0);
//            for (int i = 0; i < xb.size(); i++) {
//                if ((result[0] + result[1]) > (xb.get(i)[0] + xb.get(i)[1])) {
//                    result = xb.get(i);
//                } else if ((result[0] + result[1]) == (xb.get(i)[0] + xb.get(i)[1])) {
//                    if (result[0] > xb.get(i)[0]) {
//                        result = xb.get(i);
//                    }
//                }
//
//            }
//        }
//        System.out.println(result[0] + " " + result[1]);
//    }
//
//}
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//
//        sc.nextLine();
//        String strA = sc.nextLine();
//        String strB = sc.nextLine();
//        if (strA.length() != n && n != strB.length() ){
//            return;
//        }
//
//
//        handleChar(strA, strB);
//    }
//
//
//    public static void handleChar(String A, String B){
//        if(A == null || B == null){
//            return;
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < A.length(); i++) {
//            stringBuilder.append(A.charAt(i));
//            stringBuilder.append(B.charAt(i));
//        }
//        System.out.println(stringBuilder.toString());
//    }
//}
