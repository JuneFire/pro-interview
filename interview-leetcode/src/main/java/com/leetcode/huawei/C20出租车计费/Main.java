package com.leetcode.huawei.C20出租车计费;

/**
 * 程序员小明打了一辆出租车去上班。出于职业敏感，他注意到这辆出租车的计费表有点问题，总是偏大。出租车司机解释说他不喜欢数字4，所以改装了计费表，任何数字位置遇到数字4就直接跳过，其余功能都正常。
 *  比如：
 *
 * 1.23再多一块钱就变为25；
 * 2.39再多一块钱变为50；
 * 3. 399再多一块钱变为500；
 *  小明识破了司机的伎俩，淮备利用自己的学识打败司机的阴谋。
 *  给出计费表的表面读数，返回实际产生的费用。
 *  输入描述
 *  只有一行，数字N，表示里程表的读数。
 *  $(1<=N<=888888888)$。
 *  输出描述
 *  一个数字，表示实际产生的费用。以回车结束。
 */



import java.util.Scanner;


public class Main {
/*    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int correct = 0;
        for (char c : line.toCharArray()) {  // 9 进制
            int digit = c - '0';
            if (digit > 4) {
                digit--;
            }
            correct = correct * 9 + digit;
        }
        System.out.println(correct);
    }*/

    public static void main(String[] args){
        int input = 17; // 输入的整数
        int count = 0; // 初始化计数器
        for (int i = 0; i <= input; i++) { // 遍历从0到输入的整数的每一个数字
            if (String.valueOf(i).contains("4")) { // 如果数字包含4
                count++; // 计数器加1
            }
        }
        System.out.println(input - count); // 输出计数器的值
    }
}




/*
// 二进制算法
public class Main.java {
    public static void main(String[] args){
        String binaryString = "1010"; // 二进制字符串
        int decimalNumber = binaryToDecimal(binaryString); // 转换为十进制
        System.out.println(decimalNumber); // 输出结果
    }

    public static int binaryToDecimal(String binaryString) {
        int decimal = 0;
        int binaryLength = binaryString.length();
        for (int i = 0; i < binaryLength; i++) {
            // 从最后一位开始，每一位的值乘以2的相应次方
            decimal += Integer.parseInt(String.valueOf(binaryString.charAt(i))) * Math.pow(2, binaryLength - 1 - i);
        }
        return decimal;
    }
}*/


//    int temp = Integer.parseInt(s);
//    int j = index;
//                    while (j < s.length()){
//        temp = temp - (int)Math.pow(10, String.valueOf(i).length() - j - 1);
//        j++;
//        }
//        return Integer.parseInt(s1 + temp);
