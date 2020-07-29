package leetcode;

public class Solution_3 {

    public String toLowerCase(String str) {

        if (str == null || str.length() == 0) return "";

        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++){
            if (chars[i] >= 'A' && chars[i] <= 'Z') chars[i] = (char) (chars[i] + 32);
        }

        return new String(chars);
    }


    public static void main(String[] args){
        String str = "Hello";
        Solution_3 solution = new Solution_3();
        System.out.println(solution.toLowerCase(str));
    }

}
