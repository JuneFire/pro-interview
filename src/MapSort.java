package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapSort {

    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("aaa", "333");
        map1.put("ccc", "222");
        map1.put("bbb", "111");

        // 第一种Map排序方式, 根据key排序
        List<Map.Entry<String, String>> entryList1 = new ArrayList<Map.Entry<String, String>>(map1.entrySet());
   /*     Collections.sort(entryList1, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Entry<String, String> me1, Entry<String, String> me2) {
                return me1.getKey().compareTo(me2.getKey()); // 升序排序
                //return me2.getKey().compareTo(me1.getKey()); // 降序排序
            }
        });*/
        Collections.sort(entryList1, Comparator.comparing(Entry::getKey));
        System.out.println("第一种Map排序方式, 根据key排序: \n" + entryList1);

        // 第一种Map排序方式, 根据value排序
        List<Map.Entry<String, String>> entryList2 = new ArrayList<Map.Entry<String, String>>(map1.entrySet());
    /*    Collections.sort(entryList2, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Entry<String, String> me1, Entry<String, String> me2) {
                return me1.getValue().compareTo(me2.getValue()); // 升序排序
                //return me2.getValue().compareTo(me1.getValue()); // 降序排序
            }
        });*/
        Collections.sort(entryList2, Comparator.comparing(Entry::getValue));
        System.out.println("\n第一种Map排序方式, 根据value排序结果: \n" + entryList2);

        // 有序LinkedHashMap, 第一种Map排序方式, 根据key排序
        Map<String, String> result1 = new LinkedHashMap<String, String>();
        map1.entrySet()
                .stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> result1.put(x.getKey(), x.getValue()));
        System.out.println("\n有序LinkedHashMap, 第一种Map排序方式, 根据key排序: \n" + result1);

        // 有序LinkedHashMap, 第一种Map排序方式, 根据value排序
        Map<String, String> result2 = new LinkedHashMap<>();
        map1.entrySet()
                .stream().sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));
        System.out.println("\n有序LinkedHashMap, 第一种Map排序方式, 根据value排序: \n" + result2);
    }
}