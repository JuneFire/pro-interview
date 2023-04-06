package com.test;

/**
 * @author zkCheng
 * @date 2023/4/4 10:05
 */
public class TryCactch {

    public static void main(String[] args){
        TryCactch test = new TryCactch();
        test.tryCatch1();
    }

    public void tryCatch1(){

        try {
            tryCatch2();
        }catch (Exception e){
            System.out.println("tryCatch1...");
        }
    }

    public void tryCatch2() throws Exception{

        try {
            tryCatch3();
        }catch (Exception e){
            System.out.println("tryCatch2...");
        }
    }

    public int tryCatch3() throws Exception{
        int a , b , c = 1;
        return 1/0;
    }

}
