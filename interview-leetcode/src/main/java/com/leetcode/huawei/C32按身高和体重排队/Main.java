package com.leetcode.huawei.C32按身高和体重排队;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
某学校举行运动会，学生们按编号(1、2、3...n)进行标识，现需要按照身高由低到高排列，
        对身高相同的人，按体重由轻到重排列；
        对于身高体重都相同的人，维持原有的编号顺序关系。请输出排列后的学生编号。
        输入描述
        两个序列，每个序列由n个正整数组成 $(0<n<=100)$ 。
        第一个序列中的数值代表身高，第二个序列中的数值代表体重。
        输出描述
        排列结果，每个数值都是原始序列中的学生编号，编号从1开始*/
class  Student {
    int shengao;
    int tizhong;
    int index;
}
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] shengao = new int[n];
        int[] tizhong = new int[n];
        for (int i = 0; i < n; i++) {

            shengao[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            tizhong[i] = scanner.nextInt();
        }
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            students[i] = new Student();
            students[i].shengao = shengao[i];
            students[i].tizhong = tizhong[i];
            students[i].index = i;
        }
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.shengao != o2.shengao) {
                    return o1.shengao - o2.shengao;
                } else if (o1.tizhong != o2.tizhong) {
                    return o1.tizhong - o2.tizhong;
                } else {
                    return 0;
                }
            }
        });
        for (int i = 0; i < n; i++) {
            System.out.print(students[i].index + 1 + " ");
        }
    }

}
