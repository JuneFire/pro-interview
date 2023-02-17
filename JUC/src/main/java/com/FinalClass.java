package com;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Formatter;

/**
 * @author zkCheng
 * @date 2022/10/27 17:29
 */
public final class FinalClass {

    public final void command(){
        System.out.println("commmand");
    }

    public static void main(String[] args){
        float i = (float) (1.0 / 3.0);
        int j = (int) (1.0 / 3.0 * 100);
        System.out.println(String.format("%.2f", 0.00));
        System.out.println(j);
        StringBuilder stringBuilder;
        StringBuffer stringBuffer;
    }

    public static String format1(double value) {

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.toString();
    }

    public static String format2(double value) {

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(value);
    }

    public static String format3(double value) {

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        /*
         * setMinimumFractionDigits设置成2
         *
         * 如果不这么做，那么当value的值是100.00的时候返回100
         *
         * 而不是100.00
         */
        nf.setMinimumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.HALF_UP);
        /*
         * 如果想输出的格式用逗号隔开，可以设置成true
         */

        nf.setGroupingUsed(false);
        return nf.format(value);
    }

    public static String format4(double value) {
        /*
         * %.2f % 表示 小数点前任意位数 2 表示两位小数 格式后的结果为 f 表示浮点型
         */
        return new Formatter().format("%.2f", value).toString();
    }

    public static String format5(double value) {

        return String.format("%.2f", value).toString();
    }



}
