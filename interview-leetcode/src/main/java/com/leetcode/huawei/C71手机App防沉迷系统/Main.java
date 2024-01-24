package com.leetcode.huawei.C71手机App防沉迷系统;
/*题目描述智能手机方便了我们生活的同时，也侵占了我们不少的时间。“手机App防沉迷系统”能够让我们每天合理地规划手机App使用时间，在正
        确的时间做正确的事。
        它的大概原理是这样的：
        1.在一天24小时内，可以注册每个App的允许使用时段
        2.一个时间段只能使用一个App
        3. App有优先级，数值越高，优先级越高。注册使用时段时，如果高优先级的App时间和低优先级的时段有冲突，则系统会自动注销低

        优先级的时段，如果App的优先级相同，则后添加的App不能注册。
        请编程实现，根据输入数据注册App，并根据输入的时间点，返回时间点使用的App名称，如果该时间点没有注册任何App，请返回字符串$^{u}\mathbb{NA}^{v}$。
        输入描述
        第一行表示注册的App数量 N ($\mathbb{N}\leq100)$
        第二部分包括 N 行，每行表示一条App注册数据
        最后一行输入一个时间点，程序即返回该时间点使用的App
        数据说明如下：
        1.N行注册数据以空格分隔，四项数依次表示：App名称、优先级、起始时间、结束时间2.优先级1~5, 数字越大，优先级越高
        3.时间格式 HH:MM, 小时和分钟都是两位，不足两位前面补0
        4.起始时间需小于结束时间，否则注册不上
        5.注册信息中的时间段包含起始时间点，不包含结束时间点

        输出描述输出一个字符串，表示App名称，或NA表示空闲时间
        用例1
        输入
        2
        App1 1 09:00 10:00
        App2 2 09:10 09:30
        09:20
       输出
        App2*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class App {
    String name;
    int priority;
    String startTime;
    String endTime;

    App(String name, int priority, String startTime, String endTime) {
        this.name = name;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

class TimeSlot {
    String startTime;
    String endTime;
    App app;

    TimeSlot(String startTime, String endTime, App app) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.app = app;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            int priority = scanner.nextInt();
            String startTime = scanner.next();
            String endTime = scanner.next();
            App app = new App(name, priority, startTime, endTime);
            addTimeSlot(timeSlots, new TimeSlot(startTime, endTime, app));
        }
        String timePoint = scanner.next();
        System.out.println(getAppNameAtTimePoint(timeSlots, timePoint));
    }

    static void addTimeSlot(List<TimeSlot> timeSlots, TimeSlot newTimeSlot) {
        for (int i = 0; i < timeSlots.size(); i++) {
            TimeSlot timeSlot = timeSlots.get(i);
            if (isOverlap(timeSlot, newTimeSlot)) {
                if (timeSlot.app.priority < newTimeSlot.app.priority) {
                    timeSlots.set(i, newTimeSlot);
                    return;
                } else {
                    return;
                }
            }
        }
        timeSlots.add(newTimeSlot);
    }

    static boolean isOverlap(TimeSlot timeSlot1, TimeSlot timeSlot2) {
        return (timeSlot1.startTime.compareTo(timeSlot2.startTime) >= 0 && timeSlot1.startTime.compareTo(timeSlot2.endTime) < 0) ||
                (timeSlot1.endTime.compareTo(timeSlot2.startTime) > 0 && timeSlot1.endTime.compareTo(timeSlot2.endTime) <= 0) ||
                (timeSlot1.startTime.compareTo(timeSlot2.startTime) <= 0 && timeSlot1.endTime.compareTo(timeSlot2.endTime) >= 0);
    }

    static String getAppNameAtTimePoint(List<TimeSlot> timeSlots, String timePoint) {
        for (TimeSlot timeSlot : timeSlots) {
            if (timeSlot.startTime.compareTo(timePoint) <= 0 && timeSlot.endTime.compareTo(timePoint) > 0) {
                return timeSlot.app.name;
            }
        }
        return "NA";
    }
}
