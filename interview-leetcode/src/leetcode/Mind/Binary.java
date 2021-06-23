package leetcode.Mind;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zkcheng
 * @Date: 2021/06/21/21:59
 * @Description:
 */
public class Binary {

    /**
     * 1、计算在网格中从原点到特定点的最短路径长度
     * 1091. Shortest Path in Binary Matrix(Medium)
     */
    public int shortestPathBinaryMartix(int[][] grids){
        // 排错
        if(grids == null || grids.length == 0 || grids[0].length == 0){
            return -1;
        }
        // 点(x,y)的八个方向： 左上，正上,右上，正左，正右，左下，正下，右下
        int[][] direction = {{-1,1},{0,1},{1,1},{-1,0},{1,0},{-1,-1},{0,-1},{1,-1}};
        int m = grids.length, n = grids[0].length;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(0,0));
        int pathLength = 0;
        while (!queue.isEmpty()){  // 入队出队
            int size = queue.size();
            pathLength++;
            while (size-- > 0){
                Pair<Integer, Integer> cur = queue.poll();
                int cr = cur.getKey(), cc = cur.getValue();
                if(grids[cr][cc] == 1){  // 不通的路直接跳过
                    continue;
                }
                if(cr == m - 1 && cc == n - 1){  //走完了
                    return pathLength;
                }
                grids[cr][cc] = 1; //标记为已经走过的路
                for (int[] d : direction){ //判断grids[cr][cc]有无路可走
                    int nr = cr + d[0], nc = cc + d[1];
                    if( nr < 0 || nr >= m || nc < 0 || nc >= n){ //此路不通
                        continue;
                    }
                    queue.add(new Pair<>(nr,nc)); //加入队列中继续判断
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Binary binary = new Binary();
        System.out.println(binary.shortestPathBinaryMartix(new int[][]{{0,0,0},{1,1,0},{1,1,0}}));
    }

}
