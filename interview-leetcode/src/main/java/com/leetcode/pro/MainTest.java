package com.leetcode.pro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

public class MainTest {

    // TOP - 100

    // 从一个大数据文本里，取出前top-100 的数据 （100, 10000, 11111, ....）

    public static void main(String[] args) {
        int max = 20000;
        BitSet bitSet = new BitSet(max); //保存位值
        Random random = new Random();
        for (int i = 0; i < max; i++) {
            bitSet.set(Math.abs(random.nextInt(max))); //
        }

        int[] top100 = new int[100];
        int location = 0;
        for (int i = max; i >= 0; i--) {
            boolean bool = bitSet.get(i);
            if (location == 100) {
                break;
            }
            if (bool) {
                top100[location] = i;
                location++;
            }
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(top100[i]);
        }

    }

    // 从100亿中取出top-100
    public int[] top100(String filepath) {
        int[] nums = new int[100];
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            while((reader.readLine()) != null){
                String[] s = reader.readLine().split(" ");
                int n=s.length-1;
                int[] res=new int[n];
                for (int i=0;i<n;i++) {
                    res[i] = Integer.parseInt(s[i]);
                }
                Arrays.sort(res);
                // 取 res 后 100 位
                // 和 nums 比较，更新 nums
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
