package test;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Handler;

/**
 * @Author: zkcheng
 * @Date: 2022/05/04/3:36
 * @Description:
 */
public class Scholar {
    public static void main(String[] args) {
//        Map<String, Integer> map1 = new HashMap<>();
//        map1.put("abc1", 5);
//        map1.put("abc2", 3);
//        map1.put("abc3", 20);
//        map1.put("abc4", 80);
//        map1.put("abc5", 1);
//        map1.put("abc6", 10);
//        map1.put("abc7", 12);
        Scholar scholar = new Scholar();
        Map<String, Integer> map1 = scholar.generaterText();
        scholar.sortText(map1);
    }


    // 文本测试
    private String textTest(String text){
//        String text = " Citations 203      Versions 6Citations list http://scholar.google.com/scholar?cites=4321621849235925508&as_sdt=2005&sciodt=0,5&hl=en Versions list http://scholar.google.com/scholar?cluster=4321621849235925508&hl=en&as_sdt=0,5";
        int index = text.indexOf("Citations ");
//        System.out.println(index);
        int index2 = index + "Citations ".length();
//        System.out.println(text.substring(index2,  index2 + 1));
        StringBuilder str2 = new StringBuilder();
        for (int i = index2; i < text.length(); i++) {
            if (text.charAt(i) >= 48 && text.charAt(i) <= 57) {
                String s = String.valueOf(text.charAt(i));
                // 为什么不用＋号拼接，当然是因为＋号拼接效率最慢的问题啊
                str2.append(s);
            }else {
                break;
            }

        }
        System.out.println(str2.toString());
        return str2.toString();
    }

    // 生成文本内容
    private Map<String, Integer> generaterText() {
        // 使用高层流读取文件
        File file = new File("C:\\Users\\zkcheng\\Desktop\\scholar\\output\\Deep Image Deblurring.txt");
        Reader reader = null;
        BufferedReader br = null;
        List<String> list = new ArrayList<>();
        try {
            // 构造基础流
            reader = new FileReader(file);
            // 在基础流之上构造高层流
            br = new BufferedReader(reader);
            // 读到的一行
            String line = null;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    builder.append(line + "\r\n");
                    System.out.println(builder.toString());


                    if (builder.length() == 0) {
                        continue;
                    }
                    list.add(builder.toString());
                    builder.delete(0, builder.length());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 先关闭高层流
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }

        Map<String, Integer> map = new HashMap<>();
        for (String line : list){
            String value = textTest(line);
            map.put(line, Integer.valueOf(value));
        }
        return map;
    }

    // 输出排序后的文本
    private void sortText(Map<String, Integer> map) {

        //自定义比较器
        Comparator<Map.Entry<String, Integer>> valCmp = (o1, o2) -> {
            // TODO Auto-generated method stub
            return o2.getValue() - o1.getValue();  // 降序排序，如果想升序就反过来
        };
        //将map转成List，map的一组key，value对应list一个存储空间
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet()); //传入maps实体
        Collections.sort(list, valCmp); // 注意此处Collections 是java.util包下面的,传入List和自定义的valCmp比较器
        //输出map 到文本
        BufferedWriter out = null;
        String path = "C:\\Users\\zkcheng\\Desktop\\scholar\\output2\\Deep Image Deblurring.txt";
        File file = new File(path);
        //如果没有文件就创建
        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out = new BufferedWriter(new FileWriter(path));
            for (int i = 0; i < list.size(); i++) {
                String aLine = list.get(i).getKey() + "\r\n";
                //处理每一行，并添加到输出文件
//                aLine = aLine.replaceAll("(?!\\r)\\n", "\r\n");
                out.write(aLine);
                System.out.print(aLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 先关闭高层流
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
