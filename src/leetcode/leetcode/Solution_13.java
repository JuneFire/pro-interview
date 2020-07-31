package src.leetcode;

import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class Solution_13 {

    public Map<Double,List<String>> sumOfNumber(double[] nums,double[] number){

        if (nums == null || nums.length == 0) throw new IllegalArgumentException("ERROR PARAMS");
        Arrays.sort(nums);
        Arrays.sort(number);
        int index = 1,sum;
        Map<Double,List<String>> map = initMap(number);
//        while (index < nums.length){
//            for (int i = 0; i < nums.length - index; i++){
//                sum = 0;
//                for (int j = i; j <= index + i;j++){
//                    sum+=nums[j];
//                    list.add(nums[j]);
//                    if (sum > number) break;
//                }
//                if (sum == number && list.size() == index + 1){
//                    System.out.println(Arrays.toString(list.toArray()));
//                }
//                list.clear();
//            }
//            index++;
//        }
        Stack<Pair<Integer,Double>> stack = new Stack<>();
        Pair<Integer,Double> pair;
        while (index <= nums.length) {
            for (int i = 1; i <= index; i++) stack.push(new Pair<Integer, Double>(i,nums[i - 1]));
            int j = 0;
            while (!stack.isEmpty()){
                int status = isEqual(stack,number);
                if (status > 0){stack.pop();continue;}
                if (stack.size() == index && status == 0) display(stack,map);
                pair = stack.pop();
                if (pair.getKey() == nums.length) continue;
                if (nums.length - pair.getKey() + stack.size() >= index){
                    int size = stack.size();
                    for (int len = 1; len <= index - size; len++)
                        stack.push(new Pair<Integer, Double>(pair.getKey() + len, nums[pair.getKey() + len - 1]));
                }
            }
            index++;
        }
        return map;
    }

    public int isEqual(Stack<Pair<Integer,Double>> stack,double[] number){
        BigDecimal sum = new BigDecimal("0");
        for (Pair<Integer,Double> num : stack){
            sum = sum.add(new BigDecimal(""+num.getValue()));
        }
        for (double num : number){
            if (num == sum.doubleValue()) return 0;
        }
        return number[number.length - 1] > sum.doubleValue() ? -1 : number[number.length - 1] == sum.doubleValue() ? 0 : 1;
    }

    public void display(Stack<Pair<Integer,Double>> stack,Map<Double,List<String>> map){
        StringBuffer sb = new StringBuffer();
        BigDecimal sum = new BigDecimal("0");
        for (Pair<Integer,Double> pair : stack) {
            sum = sum.add(new BigDecimal(""+pair.getValue()));
            sb.append(pair.getValue() + " ");
            System.out.print(pair.getValue() + " ");
        }
        sb.append("总额 : " + sum.doubleValue());
        List<String> list = map.get(sum.doubleValue());
        list.add(sb.toString());
        System.out.print("总额 : " + sum.doubleValue());
        System.out.println();
    }

    public Map<Double,List<String>> initMap(double[] nums){
        Map<Double,List<String>> map = new HashMap<>();
        for (double num : nums){
            List list = new ArrayList<String>();
            map.put(num,list);
        }
        return map;
    }

    public void writeFile(Map<Double,List<String>> map){
        BufferedWriter outputStream = null;
        try {
            outputStream = new BufferedWriter(new FileWriter("D:\\logs\\result.txt"));
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<Double, List<String>> entry : map.entrySet()) {
                sb.append("总金额 : ").append(entry.getKey()).append(" , 组合总数 : ").append(entry.getValue().size()).append("\r\n");
                for (String str : entry.getValue())
                    sb.append(str).append("\r\n");
                sb.append("------------------------------------------------------------------------\r\n");
            }
            outputStream.write(sb.toString());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args){
        Date curDate = new Date();
        double[] nums = { 23127.62,1883.76,65960.37,294127.96,126315.08,16886.42,100553.16,65511.24,1246.07,34177.36, 2393.5,28325.81,12294.02,8549.3,63775.07,22804,28505,11402,39222.88,43099.56,
                4560.8,66385.71,6380.14,8956.78,96330,1528.83,14131.19,32564.28,972,14670,
                6894.6,76662,41595.41,2280.34,188853.94};
        double[] number = {149594.24,557491.57,214950.63,630889.76};
        Solution_13 solution = new Solution_13();
//        Map<Double,List<String>> map = solution.sumOfNumber(nums,number);
        System.out.println("处理时间 : " + (new Date().getTime() - curDate.getTime()));
        Map<Double,List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("560.8 11402.0 22804.0 28505.0 39222.88 43099.56 总额 : 149594.24");
        list.add("1528.83 1883.76 2280.34 8549.3 12294.02 23127.62 28325.81 28505.0 43099.56 总额 : 149594.24");
        list.add("972.0 2280.34 2393.5 4560.8 6380.14 6894.6 14670.0 22804.0 23127.62 65511.24 总额 : 149594.24");
        map.put(149594.24,list);
        solution.writeFile(map);
    }

}
