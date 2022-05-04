package test;

import leetcode.Structure.ListNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zkcheng
 * @Date: 2022/05/04/3:47
 * @Description:
 */
public class IODemo {
    public static void main(String[] args) throws IOException {
        // 使用高层流读取文件
        File file = new File("C:\\Users\\zkcheng\\Desktop\\scholar\\output\\A Survey on Image Deblurring.txt");
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
                if(!line.isEmpty()){
                    builder.append(line);
                    System.out.println(builder.toString());
                }else {
                    if(builder.length() == 0){
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
    }

}
