package com.leetcode.huawei.C31学生排名或智能成绩表;
/*

题目描述小明来到学校当老师，需要将学生按考试总分或单科分数讲行排名，你能帮帮他吗？
        输入描述
        第 1 行输入两个整数，学生人数 n 和科目数量 m。
        $\cdot0<n<100$
        $\cdot0<m<10$ 第 2 行输入 m 个科目名称，彼此之间用空格隔开。

        ·科目名称只由英文字母构成，单个长度不超过10个字符。·科目的出现顺序和后续输入的学生成绩——对应。
        ·不会出现重复的科目名称。
        第 3 行开始的 n 行，每行包含一个学生的姓名和该生 m 个科目的成绩 (空格隔开)
        ·学生不会重名。
        ·学生姓名只由英文字母构成，长度不超过10个字符。
        · 成绩是0~100的整数，依次对应第2行种输入的科目。
        第n+2行，输入用作排名的科目名称。若科目不存在，则按总分进行排序。输出描述
        输出一行，按成绩排序后的学生名字，空格隔开。成绩相同的按照学生姓名字典顺序排序。
*/

import java.util.*;

class Student {
    String name;
    int[] scores;
    int totalScore;

    Student(String name, int[] scores) {
        this.name = name;
        this.scores = scores;
        this.totalScore = Arrays.stream(scores).sum();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();

        String[] subjects = scanner.nextLine().split(" ");
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");
            int[] scores = new int[m];
            for (int j = 0; j < m; j++) {
                scores[j] = Integer.parseInt(line[j + 1]);
            }
            students.add(new Student(line[0], scores));
        }

        String subject = scanner.nextLine();
        int subjectIndex = Arrays.asList(subjects).indexOf(subject);

        if (subjectIndex == -1) {
            students.sort(Comparator.comparing((Student s) -> -s.totalScore).thenComparing(s -> s.name)); // 按总分降序排序
        } else {
            students.sort(Comparator.comparing((Student s) -> -s.scores[subjectIndex]).thenComparing(s -> s.name)); // 按指定科目降序排序
        }

        for (Student student : students) {
            System.out.print(student.name + " ");
        }
    }
}
