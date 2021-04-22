package com.lao4j.drivetools;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
  * @description 日期时间操作工具类
  * @author lao4j
  * @date 2021/4/21 15:32
  */
public class DateUtils {
    private final static Logger log = LoggerFactory.getLogger(DateUtils.class);

    public final static String[] DEFAULT_PATTEN = {"yyyy-MM-dd_HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss.0", "yyyyMMddHHmm", "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"};
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String FORMAT_YYYYMM = "yyyyMM";
    public static final String FORMAT_YYYY_MM_DD_CN = "yyyy年MM月dd日";
    public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static int SECONDS_OF_HOUR = 60 * 60;
    public final static int SECONDS_OF_DAY = 24 * 60 * 60;
    public final static int SECONDS_OF_MIN = 60;
    public final static int MILLIS_OF_MIN = 60 * 1000;
    public final static int MILLIS_OF_DAY = 24 * 60 * 60 * 1000;
    public final static int MILLIS_OF_SECOND = 1000;
    public final static int MILLIS_OF_TWENTY_SECOND = 20000;
    public final static int ONE_MILLIS = 1000;

    /***
     * parseDate
     * @param str
     */
    public static Date parseDate(String str) {
        try {
            return org.apache.commons.lang.time.DateUtils.parseDate(str, DEFAULT_PATTEN);
        } catch (Exception e) {
            log.error("parse date error," + e.getMessage(), e);
        }
        return null;
    }

    /***
     * parseDate
     * @param str
     * @param parsePattern
     */
    public static Date parseDate(String str, String parsePattern) {
        try {
            return org.apache.commons.lang.time.DateUtils.parseDate(str, new String[]{parsePattern});
        } catch (ParseException e) {
            log.error("parse date error," + e.getMessage(), e);
        }
        return null;
    }

    /***
     * dateToSecs
     * @param dateTime
     * @param patten
     * @throws ParseException
     */
    public static int dateToSecs(String dateTime, String patten) throws ParseException {
        Date time = new SimpleDateFormat(patten).parse(dateTime);
        //  DateUtils.parseDate(date, new String[]{patten});
        return (int) (time.getTime() / 1000);
    }

    /***
     * secsToDate
     * @param secs
     */
    public static Date secsToDate(long secs) {
        Date date = new Date(1000 * secs);
        return date;
    }

    /***
     * dateToSec
     * @param date
     */
    public static long dateToSec(Date date) {
        return (date.getTime() / 1000);
    }

    /***
     * dateToDay
     * @param date
     */
    public static int dateToDay(Date date) {
        String day = DateFormatUtils.format(date, "yyyyMMdd");
        return Integer.valueOf(day);
    }

    /**
     * 时间戳转换成时间 格式:yyyy/MM/dd-HH:mm:ss 2017/03/30-14:41:00
     * @param pTime
     */
    public static String timeDeal(String pTime) {
        String dealTime = "";
        String[] arry = pTime.split(":");
        dealTime = arry[0].replaceAll("-", "/");
        dealTime = dealTime.replace(" ", "-") + ":" + arry[1] + ":" + arry[2];
        return dealTime;
    }

    /**
     * 时间戳转换成时间 格式:yyyy-MM-dd HH:mm:ss
     * @param sTime
     */
    public static String stampToDate(long sTime) {
        Date date = new Date(sTime);
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 时间戳转换成时间 格式:yyyy-MM-dd HH:mm
     * @param sTime
     */
    public static String stampToDateMin(long sTime) {
        Date date = new Date(sTime);
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm");
    }

    /**
     * 时间戳转换成时间 格式:yyyy-MM-dd_HH:mm:ss
     * 2018-01-01_16:10:00
     * @param sTime
     */
    public static String timestampToDate(long sTime) {
        Date date = new Date(sTime);
        return DateFormatUtils.format(date, "yyyy-MM-dd_HH:mm:ss");
    }

    /**
     * 时间戳转换成时间
     * @param sTime
     */
    public static String timestampToDate(long sTime, String format) {
        Date date = new Date(sTime);
        return DateFormatUtils.format(date, format);
    }

    /**
     * 根据时间获取时间戳单位(ms)
     * @param time
     */
    public static long dateToStamp(String time) {
        Date date = parseDate(time);
        assert date != null;
        return date.getTime();
    }


    /**
     * 日期格式字符串转换成时间戳
     * @param date_str 字符串日期
     */
    public static String date2TimeStamp(String date_str) {
        try {
            Date date = parseDate(date_str);
            assert date != null;
            return String.valueOf(date.getTime() / 1000);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    /**
     * 日期格式字符串转换成时间戳
     * @param date_str 字符串日期
     */
    public static String date2TimeStampLong(String date_str) {
        if (StringUtils.isNotBlank(date_str)) {
            try {
                Date date = parseDate(date_str);
                assert date != null;
                return String.valueOf(date.getTime());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return "";
    }

    /**
     * 日期转换成时间戳
     * @param date 字符串日期
     */
    public static String date2TimeStamp(Date date) {
        try {
            return String.valueOf(date.getTime() / 1000);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    /***
     * calculateByDate
     * @param d
     * @param amount
     */
    public static Date calculateByDate(Date d, int amount) {
        return calculate(d, Calendar.DATE, amount);
    }

    /***
     * calculateByHour
     * @param d
     * @param amount
     */
    public static Date calculateByHour(Date d, int amount) {
        return calculate(d, Calendar.HOUR_OF_DAY, amount);
    }

    /***
     * calculateByMinute
     * @param d
     * @param amount
     */
    public static Date calculateByMinute(Date d, int amount) {
        return calculate(d, Calendar.MINUTE, amount);
    }

    /***
     * calculateBySecond
     * @param d
     * @param amount
     */
    public static Date calculateBySecond(Date d, int amount) {
        return calculate(d, Calendar.SECOND, amount);
    }

    /***
     * calculateByMonth
     * @param d
     * @param amount
     */
    public static Date calculateByMonth(Date d, int amount) {
        return calculate(d, Calendar.MONTH, amount);
    }

    /***
     * calculate
     * @param d
     * @param field
     * @param amount
     */
    public static Date calculate(Date d, int field, int amount) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * 获取精度到天的日期
     * @param d
     */
    public static Date getEndDate(Date d) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取精度到分钟的日期
     * @param d
     */
    public static Date getEndMin(Date d) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取精度到小时的日期
     * @param d
     */
    public static Date getEndHour(Date d) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取时间所在小时的最后一个时间，精度到秒
     * @param timeStamp
     */
    public static Long getHourEndTimeStamp(Long timeStamp) {
        Calendar c = Calendar.getInstance();
        Date endHour = getEndHour(new Date(timeStamp));
        c.setTime(endHour);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTimeInMillis();
    }

    /**
     * 获取时间所在小时的第一个时间，精度到秒
     * @param timeStamp
     */
    public static Long getHourStartTimeStamp(Long timeStamp) {
        Calendar c = Calendar.getInstance();
        Date endHour = getEndHour(new Date(timeStamp));
        c.setTime(endHour);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTimeInMillis();
    }

    /***
     * strToDate
     * @param date
     * @param format
     */
    public static Date strToDate(String date, String format) {
        format = StringUtils.isBlank(format) ? "yyyy-MM-dd HH:mm:ss" : format;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 判断两个日期是否是同一天
     * @param sDate
     * @param eDate
     */
    public static Boolean isSameDay(String sDate, String eDate) {
        Date date1 = parseDate(sDate);
        Date date2 = parseDate(eDate);
        return isSameDay(date1, date2);
    }

    /**
     * 判断两个日期是否是同一天
     * @param sDate
     * @param eDate
     */
    public static Boolean isSameDay(Date sDate, Date eDate) {
        String s = date2Str(sDate, "yyyy-MM-dd");
        String e = date2Str(eDate, "yyyy-MM-dd");
        return s.equals(e);
    }

    /**
     * 日期转字符串
     * @param date
     * @param format
     */
    public static String date2Str(Date date, String format) {
        format = StringUtils.isBlank(format) ? "yyyy-MM-dd HH:mm:ss" : format;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /***
     * date2Str
     * @param date
     * @param format
     */
    public static String date2Str(String date, String format) {
        format = StringUtils.isBlank(format) ? "yyyy-MM-dd HH:mm:ss" : format;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(parseDate(date));
    }

    /**
     * 获取指定日期当天的第一时刻
     * @param date
     */
    public static Date dayFirst(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(date);
        dateStr += " 00:00:00";
        return strToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 转换
     * @param time
     * @param fromFormat
     * @param toFormat
     */
    public static String convertFormat(String time, String fromFormat, String toFormat) {
        SimpleDateFormat fromSdf = new SimpleDateFormat(fromFormat);
        try {
            Date date = fromSdf.parse(time);
            SimpleDateFormat toSdf = new SimpleDateFormat(toFormat);
            return toSdf.format(date);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * ms 将时间转化成分钟，粒度到分钟  向后归并
     * @param timestamp
     */
    public static long timeStampToMinuteBackward(long timestamp) {
        long mod = (timestamp / 1000) % 60;
        long num = timestamp / 1000 / 60;
        if (mod > 0) {
            num++;
        }
        return num * 60 * 1000;
    }

    /**
     * ms 将时间转化成分钟，粒度到分钟  向前归并
     * @param timestamp
     */
    public static long timeStampToMinute(long timestamp) {
        long num = timestamp / 1000 / 60;
        return num * 60 * 1000;
    }

    /**
     * 时间戳转换成时间 格式:yyyy/MM/dd-HH:mm:ss 2017/03/30-14:41:00
     * @param sTime
     */
    public static String stampToDateType(long sTime) {
        String resTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
        Date date = new Date(sTime);
        resTime = simpleDateFormat.format(date);
        return resTime;
    }

    /***
     * stampToDateTime
     * @param sTime
     */
    public static String stampToDateTime(long sTime) {
        String resTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(sTime);
        resTime = simpleDateFormat.format(date);
        return resTime;
    }

    /**
     * 时间戳转换成时间 格式:yyyyMMddHHmmss 20170330144100
     * @param sTime java时间戳
     */
    public static String stampToNoSplitDateDayMinute(long sTime) {
        String resTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date(sTime);
        resTime = simpleDateFormat.format(date);
        return resTime;
    }

    /**
     * 从任务ID提取时间，精确到月份
     * @param taskId
     */
    public static String getMonthFromId(String taskId) {
        long timestamp;
        try {
            timestamp = Long.valueOf(taskId) / 1000; //任务id去掉后三位随机数就是时间戳
        } catch (Exception e) {
            log.error("从任务ID[{}]解析时间戳失败！", taskId);
            timestamp = System.currentTimeMillis();
        }
        return DateUtils.timestampToDate(timestamp, DateUtils.FORMAT_YYYYMM);
    }

    /**
     * 从任务ID提取时间，精确到月份
     * @param taskId
     */
    public static String getMonthFromConfId(String taskId) {
        long timestamp;
        try {
            timestamp = Long.valueOf(taskId) / 1000; //任务id去掉后三位随机数就是时间戳
            if (timestamp < 1541001600000L) {  //11月之前的查询11月份表
                timestamp = 1541001600000L;
            }
        } catch (Exception e) {
            log.error("从任务ID[{}]解析时间戳失败！", taskId);
            timestamp = System.currentTimeMillis();
        }
        return DateUtils.timestampToDate(timestamp, DateUtils.FORMAT_YYYYMM);
    }

    /***
     * truncateDate
     * @param date
     * @param minutes
     */
    public static Date truncateDate(Date date, int minutes) {
        long timestamp = date.getTime() / 1000;

        timestamp = timestamp / (minutes * 60);

        return new Date(1000 * (timestamp * minutes * 60));
    }

    /***
     * 获取两个时间相差的分钟数
     * @param endDate
     * @param nowDate
     */
    public static int getDiffMins(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        Long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return min.intValue();
    }

    /**
     * 返回对应时间的小时
     * @param d
     */
    public static int getHour(Date d) {
        if (d == null) {
            return -1;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.HOUR_OF_DAY);
    }

}
