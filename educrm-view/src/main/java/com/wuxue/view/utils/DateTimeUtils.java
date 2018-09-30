package com.wuxue.view.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTimeUtils {

    /**
     * 区间格式转换
     *
     * @param dateTime
     * @return
     */
    public static List<Date> StringSubToDate(String dateTime) {
        List<Date> dateList = new ArrayList<>();
        if (dateTime != null && !"".equals(dateTime)) {
            String substring = dateTime.substring(0, 10);
            String substring1 = dateTime.substring(12);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date strTime = simpleDateFormat.parse(substring);
                Date endTime = simpleDateFormat.parse(substring1);
                dateList.add(strTime);
                dateList.add(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dateList;
    }

    public static String  subStringDate(String dateTime){
        //2018-01-01-2019-01-01
        String substring = dateTime.substring(0, 4);
        String substring1 = dateTime.substring(12,4);
        String time = substring + "-"+substring1;
        return time;
    }

    /**
     * 日期格式转换
     * @param date
     * @return
     */
    public static Date getDate(Date date,String format){

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String format1 = sdf.format(date);
        try{
            Date parse = sdf.parse(format1);
            return parse;
        }catch (Exception e){
            return null;
        }
    }
    public static Date getStringToDate(String date,String format){

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try{
            Date parse = sdf.parse(date);
            return parse;
        }catch (Exception e){
            return null;
        }
    }

    public static Date stringToDate(String dateTime) {
        Date date;
        if (dateTime != null && !"".equals(dateTime)) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = simpleDateFormat.parse(dateTime);
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static Date stringToDateBy(String dateTime) {
        Date date;
        if (dateTime != null && !"".equals(dateTime)) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = simpleDateFormat.parse(dateTime);
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static Date stringToDateHM(String dateTime) {
        Date date;
        if (dateTime != null && !"".equals(dateTime)) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            try {
                date = simpleDateFormat.parse(dateTime);
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static Date stringToDateYYYY(String dateTime) {
        Date date;
        if (dateTime != null && !"".equals(dateTime)) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            try {
                date = simpleDateFormat.parse(dateTime);
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static String DateToString(Date date) {
        String str = "";
        if(date != null && !"".equals(date)){
            SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd" );
            str = sdf.format(date);
        }

        return str;
    }

    /**
     * 拼接时间
     * @param startDate
     * @param endDate
     * @return
     */
    public static Date getSplicingTime(Date startDate,Date endDate){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2= new SimpleDateFormat("HH:mm");
        String format = sdf1.format(startDate);
        String format1 = sdf2.format(endDate);
        String time = format + " "+format1;

        try {
            Date parse = sdf.parse(time);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static String getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        return String.valueOf(year);
    }

    /**
     * 获取上个月月份
     *
     * @return
     */
    public static Byte getLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        int month = calendar.get(Calendar.MONTH) + 1;
        return (byte) month;
    }

}
