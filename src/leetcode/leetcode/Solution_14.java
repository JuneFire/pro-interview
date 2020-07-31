package src.leetcode;

import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.*;

public class Solution_14 {

    public void sumOfNumber(Double[] nums, Double[] number){
        if (nums == null || nums.length == 0) throw new IllegalArgumentException("ERROR PARAMS");
        Arrays.sort(nums);
        Arrays.sort(number);
        double sum = sumEntry(nums);
        Map<Double,List<Double>> map = initMap(number);
        int index = 1;
        while (index <= nums.length){
            int r = 0;
            Double remain = 0d,result = 0d;

            index++;
        }

    }

    public boolean remainNum(Double result, Double remain){
        return remain.doubleValue() - result.doubleValue() >= 0;
    }

    public double sumEntry(Double[] nums){
        BigDecimal sum = new BigDecimal("0");
        for (Double num : nums)
            sum = sum.add(new BigDecimal("" + num.doubleValue()));
        return sum.doubleValue();
    }

    public double sumMap(Map<Double,List<Double>> map){
        BigDecimal sum = new BigDecimal("0");
        for (List<Double> list : map.values())
            sum = sum.add(new BigDecimal("" + sumList(list)));
        return sum.doubleValue();
    }

    public double sumList(List<Double> list){
        return sumEntry((Double[])list.toArray());
    }

    public int isEqual(Stack<Pair<Integer,Double>> stack, double[] number){
        BigDecimal sum = new BigDecimal("0");
        for (Pair<Integer,Double> num : stack){
            sum = sum.add(new BigDecimal(""+num.getValue()));
        }
        for (double num : number){
            if (num == sum.doubleValue()) return 0;
        }
        return number[number.length - 1] > sum.doubleValue() ? -1 : number[number.length - 1] == sum.doubleValue() ? 0 : 1;
    }

    public Map<Double,List<Double>> initMap(Double[] nums){
        Map<Double,List<Double>> map = new HashMap<>();
        for (double num : nums){
            List list = new ArrayList<Double>();
            map.put(num,list);
        }
        return map;
    }

}
