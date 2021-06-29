package leetcode.Mind;

import javafx.util.Pair;
import leetcode.Structure.TreeNode;

import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @Author: zkcheng
 * @Date: 2021/06/24/21:52
 * @Description:
 */
public class Search {

    /**
     * 1. 计算在网格中从原点到特定点的最短路径长度
     * 1091. Shortest Path in Binary Matrix(Medium)
     *
     * BFS查找最短路径
     */
    public int shortestPathBinaryMatrix(int[][] grids) {
        if (grids == null || grids.length == 0 || grids[0].length == 0) {
            return -1;
        }
        int[][] direction = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
        int m = grids.length, n = grids[0].length;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(0, 0));
        int pathLength = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            pathLength++;
            while (size-- > 0) {
                Pair<Integer, Integer> cur = queue.poll();
                int cr = cur.getKey(), cc = cur.getValue();
                if (grids[cr][cc] == 1) {
                    continue;
                }
                if (cr == m - 1 && cc == n - 1) {
                    return pathLength;
                }
                grids[cr][cc] = 1; // 标记
                for (int[] d : direction) {
                    int nr = cr + d[0], nc = cc + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }
                    queue.add(new Pair<>(nr, nc));
                }
            }
        }
        return -1;
    }

    /**
     * 279. 完全平方数
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     * todo  这种方法还是不太明白
     */
    public int numSquares(int n) {
        List<Integer> squares = generateSquares(n);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[n + 1];
        queue.add(n);
        marked[n] = true;
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            while (size-- > 0) {
                int cur = queue.poll();
                for (int s : squares) {
                    int next = cur - s;
                    if (next < 0) {
                        break;
                    }
                    if (next == 0) {
                        return level;
                    }
                    if (marked[next]) {
                        continue;
                    }
                    marked[next] = true;
                    queue.add(next);
                }
            }
        }
        return n;
    }

    /**
     * 生成小于 n 的平方数序列
     * @return 1,4,9,...
     */
    private List<Integer> generateSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while (square <= n) {
            squares.add(square);
            square += diff;
            diff += 2;
        }
        return squares;
    }


    /**
     * 3. 最短单词路径
     * 127. Word Ladder (Medium)
     *
     * BFS
     */

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        int N = wordList.size();
        int start = N - 1;
        int end = 0;
        while (end < N && !wordList.get(end).equals(endWord)) {
            end++;
        }
        if (end == N) {
            return 0;
        }
        List<Integer>[] graphic = buildGraphic(wordList);
        return getShortestPath(graphic, start, end);
    }

    /**
     * 建图
     * @param wordList
     * @return
     */
    private List<Integer>[] buildGraphic(List<String> wordList) {
        int N = wordList.size();
        List<Integer>[] graphic = new List[N];
        for (int i = 0; i < N; i++) {
            graphic[i] = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if (isConnect(wordList.get(i), wordList.get(j))) {
                    graphic[i].add(j);
                }
            }
        }
        return graphic;
    }

    /**
     * 判断s1 和 s2是否可通
     * @param s1
     * @param s2
     * @return
     */
    private boolean isConnect(String s1, String s2) {
        int diffCnt = 0;
        for (int i = 0; i < s1.length() && diffCnt <= 1; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCnt++;
            }
        }
        return diffCnt == 1;
    }

    /**
     * 生成最短路径长度
     * @param graphic
     * @param start
     * @param end
     * @return
     */
    private int getShortestPath(List<Integer>[] graphic, int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] marked = new boolean[graphic.length];
        queue.add(start);
        marked[start] = true;
        int path = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            path++;
            while (size-- > 0) {
                int cur = queue.poll();
                for (int next : graphic[cur]) {
                    if (next == end) {
                        return path;
                    }
                    if (marked[next]) {
                        continue;
                    }
                    marked[next] = true;
                    queue.add(next);
                }
            }
        }
        return 0;
    }


    /**
     * DFS
     * 1. 查找最大的连通面积
     * 695. Max Area of Island (Medium)
     * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
     *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
     *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
     *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
     */
    private int m, n;
    private int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, dfs(grid, i, j));
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return 0;
        }
        grid[r][c] = 0;
        int area = 1;
        for (int[] d : direction) {
            area += dfs(grid, r + d[0], c + d[1]);
        }
        return area;
    }

    /**
     * 2. 矩阵中的连通分量数目
     * 200. Number of Islands (Medium)
     * 可以将矩阵表示看成一张有向图。
     * Input:
     * 11000
     * 11000
     * 00100
     * 00011
     *
     * Output: 3
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int islandsNum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '0') {
                    dfs(grid, i, j);
                    islandsNum++;
                }
            }
        }
        return islandsNum;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        for (int[] d : direction) {
            dfs(grid, i + d[0], j + d[1]);
        }
    }

    /**
     * 4. 填充封闭区域
     * 130. Surrounded Regions (Medium)
     *
     * 题目描述：使被 'X' 包围的 'O' 转换为 'X'。
     * 先填充最外侧，剩下的就是里侧了。
     * @param board
     */

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        m = board.length;
        n = board[0].length;

        for (int i = 0; i < m; i++) {
            dfs2(board, i, 0);
            dfs2(board, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            dfs2(board, 0, i);
            dfs2(board, m - 1, i);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs2(char[][] board, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'T';
        for (int[] d : direction) {
            dfs(board, r + d[0], c + d[1]);
        }
    }

    /**
     * 5. 能到达的太平洋和大西洋的区域
     * 417. Pacific Atlantic Water Flow (Medium)
     * Given the following 5x5 matrix:
     *
     *   Pacific ~   ~   ~   ~   ~
     *        ~  1   2   2   3  (5) *
     *        ~  3   2   3  (4) (4) *
     *        ~  2   4  (5)  3   1  *
     *        ~ (6) (7)  1   4   5  *
     *        ~ (5)  1   1   2   4  *
     *           *   *   *   *   * Atlantic
     *
     * Return:
     * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
     * 左边和上边是太平洋，右边和下边是大西洋，内部的数字代表海拔，海拔高的地方的水能够流到低的地方，求解水能够流到太平洋和大西洋的所有位置。
     */
    private int[][] matrix;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ret = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return ret;
        }

        m = matrix.length;
        n = matrix[0].length;
        this.matrix = matrix;
        boolean[][] canReachP = new boolean[m][n];
        boolean[][] canReachA = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(i, 0, canReachP);
            dfs(i, n - 1, canReachA);
        }
        for (int i = 0; i < n; i++) {
            dfs(0, i, canReachP);
            dfs(m - 1, i, canReachA);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canReachP[i][j] && canReachA[i][j]) {
                    ret.add(Arrays.asList(i, j));
                }
            }
        }

        return ret;
    }

    private void dfs(int r, int c, boolean[][] canReach) {
        if (canReach[r][c]) {
            return;
        }
        canReach[r][c] = true;
        for (int[] d : direction) {
            int nextR = d[0] + r;
            int nextC = d[1] + c;
            if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n
                    || matrix[r][c] > matrix[nextR][nextC]) {

                continue;
            }
            dfs(nextR, nextC, canReach);
        }
    }

    /**
     * Backtracking
     *
     * Backtracking（回溯）属于 DFS。
     *
     * 普通 DFS 主要用在 可达性问题 ，这种问题只需要执行到特点的位置然后返回即可。
     * 而 Backtracking 主要用于求解 排列组合 问题，例如有 { 'a','b','c' } 三个字符，求解所有由这三个字符排列得到的字符串，
     * 这种问题在执行到特定的位置返回之后还会继续执行求解过程。
     * 因为 Backtracking 不是立即返回，而要继续求解，因此在程序实现时，需要注意对元素的标记问题：
     *
     * 在访问一个新元素进入新的递归调用时，需要将新元素标记为已经访问，这样才能在继续递归调用时不用重复访问该元素；
     * 但是在递归返回时，需要将元素标记为未访问，因为只需要保证在一个递归链中不同时访问一个元素，可以访问已经访问过但是不在当前递归链中的元素。
     */

    /**
     * 1. 数字键盘组合
     * 17. Letter Combinations of a Phone Number (Medium)
     * 'digits' must consist of values from 2 to 9 only
     */
    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digit){
        List<String> combination = new ArrayList<>();
        if(digit == null || digit.length() == 0){
            return combination;
        }
        doCombination(new StringBuilder(), combination, digit) ;
        return combination;
    }

    public void doCombination(StringBuilder prefix, List<String> combination, final String digit){
        if(prefix.length() == digit.length()){  //达到标准就退出
            combination.add(prefix.toString());
            return;
        }
        int curDigit = digit.charAt(prefix.length()) - '0'; // 获取匹配的字母
        String letters = KEYS[curDigit];
        for(char c : letters.toCharArray()){
            prefix.append(c);  //添加
            doCombination(prefix, combination, digit);
            prefix.deleteCharAt(prefix.length() - 1); //删除
        }
    }


    /**
     * 2. IP 地址划分
     * 93. Restore IP Addresses(Medium)
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> addresses = new ArrayList<>();
        StringBuilder tempAddress = new StringBuilder();
        doRestore(0, tempAddress, addresses, s);
        return addresses;
    }

    private void doRestore(int k, StringBuilder tempAddress, List<String> addresses, String s) {
        if (k == 4 || s.length() == 0) {
            if (k == 4 && s.length() == 0) {
                addresses.add(tempAddress.toString());
            }
            return;
        }
        for (int i = 0; i < s.length() && i <= 2; i++) {
            if (i != 0 && s.charAt(0) == '0') {
                break;
            }
            String part = s.substring(0, i + 1);
            if (Integer.valueOf(part) <= 255) {
                if (tempAddress.length() != 0) {
                    part = "." + part;
                }
                tempAddress.append(part);
                doRestore(k + 1, tempAddress, addresses, s.substring(i + 1));
                tempAddress.delete(tempAddress.length() - part.length(), tempAddress.length());
            }
        }
    }
 // 还有一种暴力解法

    /**
     * 3. 在矩阵中寻找字符串
     * 79. Word Search (Medium)
     */
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return true;
        }
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        m = board.length;
        n = board[0].length;
        boolean[][] hasVisited = new boolean[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (backtracking(0, r, c, hasVisited, board, word)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean backtracking(int curLen, int r, int c, boolean[][] visited, final char[][] board, final String word) {
        if (curLen == word.length()) {
            return true;
        }
        if (r < 0 || r >= m || c < 0 || c >= n
                || board[r][c] != word.charAt(curLen) || visited[r][c]) {

            return false;
        }

        visited[r][c] = true;

        for (int[] d : direction) {
            if (backtracking(curLen + 1, r + d[0], c + d[1], visited, board, word)) {
                return true;
            }
        }

        visited[r][c] = false;

        return false;
    }

    /**
     * 4. 输出二叉树中所有从根到叶子的路径
     * 257. Binary Tree Paths (Easy)
     */
    public List<String> binaryTreePaths(TreeNode root){
        List<String> paths = new ArrayList<>();
        if(root == null){
            return null;
        }
        List<Integer> values = new ArrayList<>(); //存储每条路径的值
        //回溯
        backtracking(root, paths, values);
        return paths;
    }

    // 回溯
    private void backtracking(TreeNode node, List<String> paths, List<Integer> values){
        if(node == null){
            return;
        }

        values.add(node.val); //存储路径
        if(node.left == null && node.right == null){ //叶子节点
            paths.add(buildPath(values));  // 一条路径走完啦
        } else {
          backtracking(node.left, paths, values); // 向左dfs
          backtracking(node.right, paths, values);  // 向右dfs
        }
        values.remove(values.size() - 1); //向上回溯
    }

    private String buildPath(List<Integer> values){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            if(i != values.size() - 1){
                builder.append(i + "->");
            }else {
                builder.append(i);
            }
        }
        return builder.toString();
    }

    /**
     * 5. 排列
     * 46. Permutations (Medium)
     * [1,2,3] have the following permutations:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     *
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutes = new ArrayList<>();
        List<Integer> permuteList = new ArrayList<>();
        boolean[] hasVisited = new boolean[nums.length];
        backtracking(permuteList, permutes, hasVisited, nums);
        return permutes;
    }

    private void backtracking(List<Integer> permuteList, List<List<Integer>> permutes, boolean[] visited, final int[] nums) {
        if (permuteList.size() == nums.length) {
            permutes.add(new ArrayList<>(permuteList)); // 重新构造一个 List
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            permuteList.add(nums[i]);
            backtracking(permuteList, permutes, visited, nums);
            permuteList.remove(permuteList.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * 6. 含有相同元素求排列
     * 47. Permutations II (Medium)
     *
     * [1,1,2] have the following unique permutations:
     * [[1,1,2], [1,2,1], [2,1,1]]
     * 数组元素可能含有相同的元素，进行排列时就有可能出现重复的排列，要求重复的排列只返回一个。
     *
     * 在实现上，和 Permutations 不同的是要先排序，然后在添加一个元素时，判断这个元素是否等于前一个元素，
     * 如果等于，并且前一个元素还未访问，那么就跳过这个元素。(换句话说，遇见相同的数字，只能选后面的)
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutes = new ArrayList<>();
        List<Integer> permuteList = new ArrayList<>();
        Arrays.sort(nums);  // 排序
        boolean[] hasVisited = new boolean[nums.length];
        backtracking2(permuteList, permutes, hasVisited, nums);
        return permutes;
    }

    private void backtracking2(List<Integer> permuteList, List<List<Integer>> permutes, boolean[] visited, final int[] nums) {
        if (permuteList.size() == nums.length) {
            permutes.add(new ArrayList<>(permuteList));
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;  // 防止重复
            }
            if (visited[i]){
                continue;
            }
            visited[i] = true;
            permuteList.add(nums[i]);
            backtracking(permuteList, permutes, visited, nums);
            permuteList.remove(permuteList.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * 7. 组合
     * 77. Combinations (Medium)
     *
     * If n = 4 and k = 2, a solution is:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> combineList = new ArrayList<>();
        backtracking(combineList, combinations, 1, k, n);
        return combinations;
    }

    private void backtracking(List<Integer> combineList, List<List<Integer>> combinations, int start, int k, final int n) {
        if (k == 0) {
            combinations.add(new ArrayList<>(combineList));
            return;
        }
        for (int i = start; i <= n - k + 1; i++) {  // 剪枝
            combineList.add(i);
            backtracking(combineList, combinations, i + 1, k - 1, n);  // 递归取2位
            combineList.remove(combineList.size() - 1);
        }
    }

    /**
     * 8. 组合求和
     * 39. Combination Sum (Medium)
     *
     * given candidate set [2, 3, 6, 7] and target 7,
     * A solution set is:
     * [[7],[2, 2, 3]]
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        backtracking(new ArrayList<>(), combinations, 0, target, candidates);
        return combinations;
    }

    private void backtracking(List<Integer> tempCombination, List<List<Integer>> combinations,
                              int start, int target, final int[] candidates) {

        if (target == 0) {
            combinations.add(new ArrayList<>(tempCombination));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                tempCombination.add(candidates[i]);
                backtracking(tempCombination, combinations, i, target - candidates[i], candidates);
                tempCombination.remove(tempCombination.size() - 1);
            }
        }
    }

    /**
     * 9. 含有相同元素的组合求和
     * 40. Combination Sum II (Medium)
     * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
     * A solution set is:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     * candidate要有序
     * candidate的数不能重复使用，既start 要+1
     */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> combinations = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(new ArrayList<>(), combinations, new boolean[candidates.length], 0, target, candidates);
        return combinations;
    }

    private void backtracking(List<Integer> tempCombination, List<List<Integer>> combinations,
                              boolean[] hasVisited, int start, int target, final int[] candidates) {

        if (target == 0) {
            combinations.add(new ArrayList<>(tempCombination));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i != 0 && candidates[i] == candidates[i - 1] && !hasVisited[i - 1]) {  //防止重复
                continue;
            }
            if (candidates[i] <= target) {
                tempCombination.add(candidates[i]);
                hasVisited[i] = true;
                backtracking(tempCombination, combinations, hasVisited, i + 1, target - candidates[i], candidates);
                hasVisited[i] = false;
                tempCombination.remove(tempCombination.size() - 1);
            }
        }
    }

    /**
     * 10. 1-9 数字的组合求和
     * 216. Combination Sum III (Medium)
     * 从 1-9 数字中选出 k 个数不重复的数，使得它们的和为 n。
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtracking(k, n, 1, path, combinations);
        return combinations;
    }

    private void backtracking(int k, int n, int start,
                              List<Integer> tempCombination, List<List<Integer>> combinations) {

        if (k == 0 && n == 0) {
            combinations.add(new ArrayList<>(tempCombination));
            return;
        }
        if (k == 0 || n == 0) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            tempCombination.add(i);
            backtracking(k - 1, n - i, i + 1, tempCombination, combinations);
            tempCombination.remove(tempCombination.size() - 1);
        }
    }

    /**
     * 11. 子集
     * 78. Subsets (Medium)
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> tempSubset = new ArrayList<>();
        for (int size = 0; size <= nums.length; size++) {  //从空子集开始，分别筛选其子集
            backtracking(0, tempSubset, subsets, size, nums); // 不同的子集大小
        }
        return subsets;
    }

    private void backtracking(int start, List<Integer> tempSubset, List<List<Integer>> subsets,
                              final int size, final int[] nums) {

        if (tempSubset.size() == size) {
            subsets.add(new ArrayList<>(tempSubset));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            tempSubset.add(nums[i]);
            backtracking(i + 1, tempSubset, subsets, size, nums);
            tempSubset.remove(tempSubset.size() - 1);
        }
    }



    public static void main(String[] args){
        Search search = new Search();
        System.out.println(search.letterCombinations("12"));
    }

}
