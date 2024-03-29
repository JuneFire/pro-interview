package com.test;

import java.io.File;
import java.io.FileInputStream;

public class FileReadTest {
    public static void main(String[] args) throws Exception{
        File file = new File("D:\\java-projects\\pro-interview\\interview-leetcode\\src\\main\\java\\com\\test\\file.txt");
        //创建字节输入流
        FileInputStream fis=new FileInputStream(file.getPath());
        //创建一个长度为1024的竹筒
        byte[] bbuf=new byte[1024];
        //用于保存实际读取的字节数
        int hasRead=0;
        //使用循环来重复取水过程
        while((hasRead=fis.read(bbuf))>0){
            //取出竹筒中的水滴（字节），将字节数组转换成字符串输入
            System.out.println(new String(bbuf,0,hasRead));
        }
        //关闭文件输入流，放在finally块里更安全
        fis.close();
    }
}
