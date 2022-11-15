package com.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zkCheng
 * @date 2022/11/8 16:38
 */
public class streamDemo {

    public static void main(String[] args){
        
        
        List<Map<String,String>> queryList= new ArrayList<>();
        queryList.add(new HashMap<String, String>() {{
            put("NAME", "白班");
            put("CODE", "BB");
            put("NUM", "8");
            put("START_TIME", "08:00:00");
            put("END_TIME", "17:59:59");
        }});
        queryList.add(new HashMap<String, String>() {{
            put("NAME", "中夜班");
            put("CODE", "ZYB");
            put("NUM", "3");
            put("START_TIME", "18:00:00");
            put("END_TIME", "07:59:59");
        }});
        queryList.add(new HashMap<String, String>() {{
            put("NAME", "中班");
            put("CODE", "ZYB");
            put("NUM", "5");
            put("START_TIME", "13:00:00");
            put("END_TIME", "17:59:59");
        }});
        //图1.结果集根据单个字段分组构造新的 Map<String, Map<String, String>>
        Map<String, Map<String, String>> groupMap = queryList.stream().collect(Collectors.toMap(e -> e.get("CODE"), Function.identity(), (k1, k2) -> k1));

        //图2.结果集抽出两个字段构造新的 Map<String, String>
        Map<String, String> codeName = queryList.stream().collect(Collectors.toMap(e -> e.get("CODE"), e -> e.get("NAME"), (k1, k2) -> k1));

        //图3.结果集根据字段分组后构造新的 Map<String, List<Map<String, String>>>
        Map<String, List<Map<String, String>>> groupList = queryList.stream().collect(Collectors.groupingBy(e -> e.get("CODE")));

        //加条件分组
        Map<Object, List<Map>> collect = queryList.stream().filter(item -> item.get("CODE").equals("ZYB")).collect(Collectors.groupingBy(item -> item.get("NAME")));

        //1、Double 双精度版本
        Double SUM = queryList.stream().mapToDouble((x) -> Double.valueOf(x.get("NUM"))).summaryStatistics().getSum();

//2、Integer 整数版本
//        Integer SUM = queryList.stream().mapToInt((x) -> Integer.parseInt(x.get("NUM"))).sum();

//3、结果集，根据某个字段计算总和，只计算不为空的
//        double sum11 = queryList.stream().filter(item -> CPUtil.isNotEmpty(item.get("NUM"))).collect(Collectors.summarizingDouble(item -> Double.valueOf(item.get("NUM")))).getSum();

        List<String> list = queryList.stream().map(e -> e.get("NAME")).collect(Collectors.toList());

    }
}
