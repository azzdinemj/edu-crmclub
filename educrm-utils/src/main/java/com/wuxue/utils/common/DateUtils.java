package com.wuxue.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {


    /**
     * 获取当前时间之后 n 天
     * @param n
     * @return
     */
    public static Date getAfterDate(Integer n,Date date){

//        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE,n);
        date = cal.getTime();

        return date;
    }

    /**
     * 当前日期加n月
     * @param n
     * @param date
     * @return
     */
    public static Date getAfterMonthDate(Integer n,Date date){

//        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH,n);
        date = cal.getTime();

        return date;
    }

    /**
     * 判断日期是周几
     * @param date
     * @return
     */
    public  static  int  dayForWeek(Date date){


//        Calendar cal = new GregorianCalendar();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return  cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据所传数  返回本周周几日期   1开始（周一）
     * */
    public  static  Date  getWeekDate(int num)  {
        //获取当前周周一日期
        Date monday = getThisWeekMonday(new Date());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(monday);
        for (int i = 0; i < 7; i++) {
            if((num-1) == i){
                calendar.add(calendar.DATE,i);
            }
        }

        return calendar.getTime();
    }

    /**
     * 根据所传数  返回本周周几日期(yyyy-MM-dd格式)   1开始（周一）
     * @param num
     * @param date
     * @return
     */
    public  static  Date  getWeekDate(int num,Date date)  {
        //获取当前周周一日期
        Date monday = getThisWeekMonday(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(monday);
        for (int i = 0; i < 7; i++) {
            if((num-1) == i){
                calendar.add(calendar.DATE,i);
            }
        }

        return calendar.getTime();
    }

    /**
     * 获取当前周周一日期
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 获取下周一日期
     * @param date
     * @return
     */
    public static Date getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }

    /**
     * 判断返回本周起止日期还是下周截止日期
     * @param
     * @return
     */
    public static Map<String, Date> dateForWeek(String todayDate){
        Date date = DateTimeUtils.stringToDate(todayDate);
        int dayForWeek = 0;
        Date fromDate;
        Date toDate;
        //判断接收日期或今天是周几
        if (todayDate ==null || "".equals(todayDate)){
            dayForWeek = DateUtils.dayForWeek(new Date());
        }else {
            dayForWeek = DateUtils.dayForWeek(date);
        }
        if (dayForWeek >1 && dayForWeek <7){
            //本周
            fromDate = DateUtils.getWeekDate(1,date);
            toDate = DateUtils.getWeekDate(6,date);
        }else {
            //下周
            fromDate = DateUtils.getNextWeekMonday(date);
            toDate = DateUtils.getAfterDate(5, fromDate);
        }

        Map map = new HashMap();
        map.put("fromDate",fromDate);
        map.put("toDate",toDate);

        return map;
    }

    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isInDates(String strDate,String strDateBegin,String strDateEnd){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date myDate = null;
        Date dateBegin = null;
        Date dateEnd = null;
        try {
            myDate = sd.parse(strDate);
            dateBegin = sd.parse(strDateBegin);
            dateEnd = sd.parse(strDateEnd);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        strDate = String.valueOf(myDate);
        strDateBegin = String.valueOf(dateBegin);
        strDateEnd = String.valueOf(dateEnd);

        int strDateH = Integer.parseInt(strDate.substring(11,13));
        int strDateM = Integer.parseInt(strDate.substring(14,16));
//        int strDateS = Integer.parseInt(strDate.substring(17,19));

        int strDateBeginH = Integer.parseInt(strDateBegin.substring(11,13));
        int strDateBeginM = Integer.parseInt(strDateBegin.substring(14,16));
//        int strDateBeginS = Integer.parseInt(strDateBegin.substring(17,19));

        int strDateEndH = Integer.parseInt(strDateEnd.substring(11,13));
        int strDateEndM = Integer.parseInt(strDateEnd.substring(14,16));
//        int strDateEndS = Integer.parseInt(strDateEnd.substring(17,19));

        if((strDateH>=strDateBeginH && strDateH<=strDateEndH)){
            if(strDateH>strDateBeginH && strDateH<strDateEndH){
                return true;
            }else if(strDateH==strDateBeginH && strDateM>strDateBeginM && strDateH<strDateEndH){
                return true;
//            }else if(strDateH==strDateBeginH && strDateM==strDateBeginM && strDateS>strDateBeginS && strDateH<strDateEndH){
//                return true;
//            }else if(strDateH==strDateBeginH && strDateM==strDateBeginM && strDateS==strDateBeginS && strDateH<strDateEndH){
//                return true;
            }else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM<strDateEndM){
                return true;
//            }else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM==strDateEndM && strDateS<strDateEndS){
//                return true;
//            }else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM==strDateEndM && strDateS==strDateEndS){
//                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
