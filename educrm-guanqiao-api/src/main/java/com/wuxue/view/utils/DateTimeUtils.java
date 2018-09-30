package com.wuxue.view.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

}
