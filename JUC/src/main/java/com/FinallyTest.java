package com;

/**
 * @author zkCheng
 * @date 2022/11/1 10:16
 */
public class FinallyTest {
    public static void main(String[] args) {
        System.out.println(func2());
    }

    public static int func() {
        int a = 10;
        try {
            System.out.println("try中的代码块");
            return a += 10;
        } catch (Exception e) {
            System.out.println("catch中的代码块");
        } finally {
            System.out.println("finally中的代码块");
            if (a > 10) {
                System.out.println("a>10," + "a=" + a);
            }
            return 100;
        }
//        return 10;
    }

    public static int func2(){
        int a = 10;
        try{
            System.out.println("try中的代码块");
            a = a /0;
            return a += 10;
        }catch (Exception e){
            System.out.println("catch中的代码块");
            return a += 15;
        }finally {
            System.out.println("finally中的代码块");
            if (a > 10){
                System.out.println("a > 10, a = "+a);
            }
            a += 50;
            System.out.println(a);
//            return 111;
        }
    }


}

/**
 * （1）如果try中有return语句，finally中也有return语句，最终执行的是finally中的return语句.
 * （2）如果finally代码块中写了return语句，那么finally之后的return语句就变成不可到达的语句，需要注释掉，否则编译不过.
 * 对于基本数据类型来说，finally中对返回值的修改不会影响try中的返回变量的值。
 * 对于引用数据类型来说，finally中对返回值的修改会影响try中的返回变量的值。
 * try语句块中发生异常，try语句异常后的内容不会执行，return语句也不会执行，执行的是捕获到的catch语句块和finally语句块。
 *
 * try中发生异常之后，catch中的return语句先执行，确定了返回值之后（保存起来，finally中的语句对返回值无影响）再去finally语句块，执行完之后再返回a的值，finally中对a的修改对返回值无效
 */
