package com.leetcode.huawei.C68核酸检测;
/*为了达到新冠疫情精准防控的需要，为了避免全员核酸检测带来的浪费，需要精准圈定可能被感染的人群。现在根据传染病流调以及大数据分析，得到了每个人之间在时间、空间上是否存在轨迹的交叉。
        现在给定一组确诊人员编号 (X1,X2,X3,...Xn)在所有人当中，找出哪些人需要进行核酸检测，输出需要进行核酸检测的数。
        (注意：确诊病例自身不需要再做核酸检测需要进行核酸检测的人，是病毒传播链条上的所有人员，即有可能通过确诊病例所能传播到的所
        有人。
        例如：A是确诊病例，A和B有接触、B和C有接触 C和D有接触，D和E有接触。那么B、C、D、E都是需要进行核酸检测的人
        输入描述
        第一行为总人数N
        第二行为确证病例人员编号 (确证病例人员数量<N),用逗号隔开
        接下来N行，每一行有N个数字，用逗号隔开，其中第i行的第1个数字表名编号i是否与编号j接触过。0表示没有接触，1表示有接触，1表示有接触
        备注：
        人员编号从0开始
        $0<N<100~0<N<1000<N<100$
        输出描述
        输出需要做核酸检测的人数
        用例1
        输入
        5
        1,2
        1,1,0,1,0
        1,1,0,0,0
        0,0,1,0,1
        1,0,0,1,0
        0,0,1,0,1
        输出
        3

        */

//通过深度优先搜索（DFS）或广度优先搜索（BFS）来解决。我们可以将问题看作是在图中寻找所有与给定节点直接或间接相连的节点。在这个问题中，节点表示人，边表示人与人之间的接触。给定的确诊病例人员编号就是我们搜索的起点
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        String[] confirmedCases = sc.nextLine().split(",");
        int[][] contacts = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = sc.nextLine().split(",");
            for (int j = 0; j < N; j++) {
                contacts[i][j] = Integer.parseInt(line[j]);
            }
        }
        boolean[] visited = new boolean[N];
        for (String caseId : confirmedCases) {
            int id = Integer.parseInt(caseId);
            dfs(contacts, visited, id);
        }
        int count = 0;
        for (boolean b : visited) {
            if (b) {
                count++;
            }
        }
        System.out.println(count - confirmedCases.length);
    }

    private static void dfs(int[][] contacts, boolean[] visited, int id) {
        if (visited[id]) {
            return;
        }
        visited[id] = true;
        for (int i = 0; i < contacts.length; i++) {
            if (contacts[id][i] == 1 && !visited[i]){
                dfs(contacts, visited, i);
            }
        }
    }
}
