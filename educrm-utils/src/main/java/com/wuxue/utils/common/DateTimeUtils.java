package com.wuxue.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        String format1 = "00:00";
        if (endDate != null){
            format1 = sdf2.format(endDate);
        }

        String time = format + " "+format1;

        try {
            Date parse = sdf.parse(time);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }


//    /**
//     * @param args
//     * @throws ParseException
//     */
//    public static void main(String[] args) throws ParseException {
//        // TODO Auto-generated method stub
//        String date = "2018-09-15";
//        Date date1 = stringToDate(date);
//        convertWeekByDate(date1);
////        System.out.println(list);
//
//    }


    /**
     * 返回指定日期所在周周一和周六日期
     * @param time
     */

    public static Map<String, Date> convertWeekByDate(Date time) {

//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        System.out.println(dayWeek);
//        if(1 == dayWeek) {
//            cal.add(Calendar.DAY_OF_MONTH, 0);
//        }
        if (7==dayWeek){ //如果是周六 加一返回下一周
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        //System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date monday = cal.getTime();//所在周星期一的日期
//        System.out.println("要计算日期为:"+sdf.format(cal.getTime()));
        cal.add(Calendar.DATE, 5);//所在周星期五的日期
        Date saturday = cal.getTime();
//        System.out.println("要计算日期为:"+sdf.format(cal.getTime()));
        Map<String,Date> map = new HashMap<>();
        map.put("monday",monday);
        map.put("saturday",saturday);
        return map;

    }

    /**
     * 获取时间区间内的时间集合
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }

    }
