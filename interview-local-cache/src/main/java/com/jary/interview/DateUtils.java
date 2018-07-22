package com.jary.interview;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtils {

    public static final String DATE_FORM_FORMAT = "YYYY-MM-dd hh:mm:ss";

    public static String toString(Date date,String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date toDate(String dateStr){
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date addDate(Date date,int min){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, min);// 24小时制
        date = cal.getTime();
        return date;
    }

    public static void main(String[] args){
        Date date = new Date();
        String dateStr = DateUtils.toString(date,DateUtils.DATE_FORM_FORMAT);
        log.info("dateStr = {}",dateStr);
        log.info("date = {}",DateUtils.toDate(dateStr));
        log.info("add = {}",DateUtils.addDate(date,30));
    }

}
