package com.test;

public class throwException {
    public static void main(String[] args){
        test();
    }

    public static void test(){
        //代码2
        try{
            int i  = 1 / 0;
            throw new Exception("参数越界");
        }catch(Exception e) {
            e.getMessage();
        }
        System.out.println("异常后");//可以执行
    }
}

