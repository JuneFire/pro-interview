package com.leetcode.huawei.C57机场航班调度;
/*题目描述XX市机场停放了多架飞机，每架飞机都有自己的航班号CA3385，CZ6678,SC6508等，航班号的前2个大写字母(或数字)代表航空公司
        的缩写，后面4个数字代表航班信息。但是XX市机场只有一条起飞用跑道，调度人员需要安排目前停留在机场的航班有序起飞。为保障航班的有序起飞，调度员首先按照航空公司的缩写(航班号前2个字母)对所有航班进行排序，同一航空公司的航班再按照航班号的后4个数字进行排序最终获得安排好的航班的起飞顺序。请编写一段代码根据输入的航班号信息帮助调度员输出航班的起飞顺序。航空公司缩写排序按照从特殊符号$\$\&^*,0_9$, AZ排序；

        输入描述
        第一行输入航班信息，多个航班号之间用逗号 $(^u,^n)$ 分隔，输入的航班号不超过100个例如：

        CA3385,CZ6678,SC6508,DU7523,HK4456,MK0987备注：航班号为6位长度，后4位为纯数字，不考虑存在后4位重复的场景

        输出描述
        CA3385,CZ6678,DU7523,HK4456,MK0987,SC6508*/
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] flights = sc.nextLine().split(",");
        // 我们需要对航班号进行排序，排序的规则是先按照航空公司的缩写排序，然后再按照航班号的后4个数字排序
        Arrays.sort(flights, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int cmp = o1.substring(0, 2).compareTo(o2.substring(0, 2));
                if (cmp != 0) {
                    return cmp;
                } else {
                    return Integer.compare(Integer.parseInt(o1.substring(2)), Integer.parseInt(o2.substring(2)));
                }
            }
        });
        System.out.println(String.join(",", flights));
    }
}
