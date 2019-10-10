package com.ly.mt.core.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author taoye
 */
public class DateUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
    private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final static String DATE_FORMAT = "yyyy-MM-dd";
    private final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private final static String[] WEEK_DAYS = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 获取当前时间，格式yyyy-MM-dd HH:mm:ss
     *
     * @author taoye
     */
    public static String getNowTimeStr() {
        SimpleDateFormat fmt = new SimpleDateFormat(TIME_FORMAT);
        return fmt.format(System.currentTimeMillis());
    }

    /**
     * 获取当前日期，格式yyyy-MM-dd
     *
     * @author taoye
     */
    public static String getNowDateStr() {
        SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
        return fmt.format(System.currentTimeMillis());
    }

    /**
     * 获取当前时间戳
     *
     * @author taoye
     */
    public static String getNowTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }


    /**
     * 获取当前日期，格式yyyy-MM-dd HH:mm:ss.SSS
     *
     * @author taoye
     */
    public static String getNowDateTimeMillStr() {
        SimpleDateFormat fmt = new SimpleDateFormat(DATE_TIME_FORMAT);
        return fmt.format(System.currentTimeMillis());
    }

    /**
     * 获取明天日期，格式yyyy-MM-dd
     *
     * @author taoye
     */
    public static String getTomorrowDateStr() throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
        Date date = fmt.parse(fmt.format(System.currentTimeMillis()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return fmt.format(calendar.getTime());
    }

    /**
     * 字符串时间格式化，格式yyyy-MM-dd HH:mm:ss
     *
     * @author taoye
     */
    public static String timeFormat(String date) {
        return dateTimeFormat(date, TIME_FORMAT);
    }

    /**
     * 字符串时间格式化，格式yyyy-MM-dd
     *
     * @author taoye
     */
    public static String dateFormat(String date) {
        return dateTimeFormat(date, DATE_FORMAT);
    }

    /**
     * 根据格式字符串时间格式化
     *
     * @author taoye
     */
    private static String dateTimeFormat(String dateTime, String format) {
        String result = "";
        try {
            if (StringUtil.isNotEmpty(dateTime)) {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                Date date = sdf.parse(dateTime);
                result = sdf.format(date);
            }
        } catch (Exception e) {
            LOGGER.error("时间格式化出错:", e);
        }
        return result;
    }

    /**
     * Date转String
     *
     * @author taoye
     */
    public static String date2TimeStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        return sdf.format(date);
    }

    /**
     * Date转String
     *
     * @author taoye
     */
    public static String date2DateStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    /**
     * String转TimeStamp
     *
     * @author taoye
     */
    public static String stringTime2TimeStamp(String time) throws Exception {
        return string2TimeStamp(time, TIME_FORMAT);
    }

    /**
     * String转TimeStamp
     *
     * @author taoye
     */
    public static String stringDate2TimeStamp(String date) throws Exception {
        return string2TimeStamp(date, DATE_FORMAT);
    }

    /**
     * 根据格式String转TimeStamp
     *
     * @author taoye
     */
    private static String string2TimeStamp(String time, String format) throws Exception {
        if (StringUtil.isEmpty(time)) {
            return null;
        }
        DateFormat fmt = new SimpleDateFormat(format);
        Date d = fmt.parse(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return String.valueOf(cal.getTimeInMillis() / 1000L);
    }

    /**
     * String转TimeStamp
     *
     * @author taoye
     */
    public static String timeStamp2TimeStr(String timeStamp) throws Exception {
        return timeStamp2String(timeStamp, TIME_FORMAT);
    }

    /**
     * String转TimeStamp
     *
     * @author taoye
     */
    public static String timeStamp2DateStr(String timeStamp) throws Exception {
        return timeStamp2String(timeStamp, DATE_FORMAT);
    }

    /**
     * 根据格式TimeStamp转String
     *
     * @author taoye
     */
    private static String timeStamp2String(String timeStamp, String format) throws Exception {
        if (StringUtil.isEmpty(timeStamp)) {
            return null;
        }
        Long time = Long.valueOf(timeStamp);
        Date date = new Date(time * 1000L);
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 日期相差天数
     *
     * @author taoye
     */
    public static long getDaySub(String begin, String end) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date beginDate = format.parse(begin);
        Date endDate = format.parse(end);
        return (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取上个月第一天
     *
     * @author taoye
     */
    public static String getFirstDayOfLastMonth(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取上个月最后一天
     *
     * @author whl
     */
    public static String getAfterDayOfLastMonth(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        //指定日期月份减去一后的 最大天数
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取上一年第一天
     *
     * @author taoye
     */
    public static String getFirstDayOfLastYear(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(calendar.getTime());
    }

    /**
     * 比较日期大小
     *
     * @author taoye
     */
    public static int compareDateTime(String dateTime1, String dateTime2) throws Exception {
        if (dateTime1.length() <= 10) {
            dateTime1 += " 00:00:00";
        }
        if (dateTime2.length() <= 10) {
            dateTime2 += " 00:00:00";
        }
        int i = 0;
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        Date date1 = format.parse(dateTime1);
        Date date2 = format.parse(dateTime2);
        if (date1.before(date2)) {
            i = -1;
        } else if (date1.after(date2)) {
            i = 1;
        }
        return i;
    }

    /**
     * 获取指定年月的最后一天
     *
     * @author taoye
     */
    public static String getLastDayByDate(String dateStr) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.valueOf(dateStr.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.valueOf(dateStr.substring(5, 7)) - 1);
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(cal.getTime());
    }

    /**
     * 获取指定年月的总天数
     *
     * @author taoye
     */
    public static String getDayCountByDate(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int count = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return String.valueOf(count);
    }


    /**
     * 获取指定日期
     *
     * @author taoye
     */
    public static String getDate(String year, String month, String date) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.valueOf(year));
        cal.set(Calendar.MONTH, Integer.valueOf(month) - 1);
        cal.set(Calendar.DATE, Integer.valueOf(date));
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(cal.getTime());
    }

    /**
     * 获取指定日期下个月的同一天
     *
     * @author taoye
     */
    public static String getDateNextMonth(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取指定日期下个月的前一天
     *
     * @author taoye
     */
    public static String getDateBeforeNextMonth(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        return sdf.format(calendar.getTime());
    }


    /**
     * 获取指定日期下个月第一天
     *
     * @author taoye
     */
    public static String getFirstDayOfNextMonth(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return sdf.format(calendar.getTime());
    }

    /**
     * @param hours 在当前时间上减去的小时数
     * @return 获取当前时间减去几个小时后的时间
     * @author zhanglifeng
     */
    public static String getBeforeTimeDate(Integer hours) {
        Long beforeHour = System.currentTimeMillis() - hours * 60 * 60 * 1000;
        Date date = new Date(beforeHour);
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        return sdf.format(date);
    }

    /**
     * @param hours
     * @return 获取当前时间后多少小时是什么时间
     * @author zhanglifeng
     */
    public static String getAfterTimeDate(Integer hours) {
        Long beforeHour = System.currentTimeMillis() + hours * 60 * 60 * 1000;
        Date date = new Date(beforeHour);
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        return sdf.format(date);
    }

    /**
     * 获得某天最大时间 2017-10-15 23:59:59
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        ;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获得某天最小时间 2017-10-15 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 时间偏移，获取前n天或后n天
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date offsetDate(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, offset);
        return calendar.getTime();
    }

    /**
     * 从指定时间算经x分钟后的时间
     *
     * @param minutes
     * @param pointTime
     * @return
     * @throws Exception
     */
    public static String getAfterMinutesDateFromPointTime(Integer minutes, String pointTime) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        Date date = sdf.parse(pointTime);
        Long afterTime = date.getTime() + minutes * 60 * 1000;
        Date date1 = new Date(afterTime);
        return sdf.format(date1);
    }

    /**
     * 从指定时间算x分钟前的时间
     *
     * @param minutes
     * @param pointTime
     * @return
     * @throws Exception
     */
    public static String getBeforeMinutesDateFromPointTime(Integer minutes, String pointTime) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        Date date = sdf.parse(pointTime);
        Long beforeTime = date.getTime() - minutes * 60 * 1000;
        Date date1 = new Date(beforeTime);
        return sdf.format(date1);
    }

    /**
     * 返回当前时间的月初时间  yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String earlyMonthStr() {
        Calendar localTime = Calendar.getInstance();
        String strY = null;
        int x = localTime.get(Calendar.YEAR);
        int y = localTime.get(Calendar.MONTH) + 1;
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + "-" + strY + "-01 00:00:00";
    }

    /**
     * 返回当前时间的本月时间  yyyyMM
     *
     * @return
     */
    public static String thisYearMonthStr() {
        Calendar localTime = Calendar.getInstance();
        String strY = null;
        int x = localTime.get(Calendar.YEAR);
        int y = localTime.get(Calendar.MONTH) + 1;
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + strY;
    }

    /**
     * 获取日期是星期几
     *
     * @param date 日期
     * @return 星期几
     * @author taoye
     */
    public static String getWeekOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return WEEK_DAYS[w];
    }

    /**
     * 返回指定日期上周第一天
     *
     * @return
     * @author wanghongliang
     */
    public static String getFirstWeekLastDay(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset1 = 1 - dayOfWeek;
        calendar.add(Calendar.DATE, offset1 - 7);
        return sdf.format(calendar.getTime());
    }

    /**
     * 返回指定日期上周最后一天
     *
     * @return
     * @author wanghongliang
     */
    public static String getAfterWeekLastDay(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int offset1 = 7 - dayOfWeek;
        calendar.add(Calendar.DATE, offset1 - 7);
        return sdf.format(calendar.getTime());
    }

    /**
     * 根据生日计算年龄
     *
     * @param birthDay
     * @return
     * @throws ParseException
     */
    public static int getAgeByBirth(Date birthDay) throws ParseException {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        //当前年份
        int yearNow = cal.get(Calendar.YEAR);
        //当前月份
        int monthNow = cal.get(Calendar.MONTH);
        //当前日期
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //计算整岁数
        age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    //当前日期在生日之前，年龄减一
                    age--;
                }
            } else {
                //当前月份在生日之前，年龄减一
                age--;
            }
        }
        return age;
    }
}