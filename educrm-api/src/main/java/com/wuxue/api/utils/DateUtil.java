package com.wuxue.api.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 時間工具類
 * @author  tly
 * @date 2018/08/03
 */
public class DateUtil {


	public static Calendar calendar = Calendar.getInstance();
	/**
	 * 获取当前天的开始时间
	 * @return
	 */
	public static Date getCurrentDayStartTime(){
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return  calendar.getTime();
	}

	/**
	 * 获取当前天的结束时间
	 * @return
	 */
	public static Date getCurrentDayEndTime(){
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND,59);
		return calendar.getTime();
	}

	/**
	 * 上午十一点59
	 */
	public static Date getForenoonTime() {
		calendar.set(Calendar.HOUR_OF_DAY, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	/**
	 * 下午十二点
	 */
	public static Date getAfternoonTime() {
		calendar.set(Calendar.HOUR_OF_DAY, 12);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		return calendar.getTime();
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

    /**
     * 將年月日设置为当前年月日
     *
     * @param time
     * @return
     */
    public static Date changeToCurrentTime(Date time) {
        Calendar currentDate = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.YEAR, currentDate.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, currentDate.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, currentDate.get(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

	/**
	 * 判断时间是否在时间段内
	 *
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

}