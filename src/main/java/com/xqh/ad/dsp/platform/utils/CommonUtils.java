package com.xqh.ad.dsp.platform.utils;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by samson.huang on 2019/7/3
 */
public class CommonUtils {

    /**
     * 启动状态
     */
    public static final String ENABLE_STATUS = "1";
    public static final String DISABLE_STATUS = "2";
    public static final String ENABLE_NAME = "启用";
    public static final String DISABLE_NAME = "暂停";

    /**
     * 联网方式
     */
    public static final int NETWORK_NONE = 0;
    public static final int NETWORK_WIFI = 1;
    public static final int NETWORK_MOBILE = 2;

    public static void main(String[] args) {
        System.out.println(getHour());
    }

    /**
     * 获取当前小时
     * @return
     */
    public static int getHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 取得零点时间
     */
    public static LocalDateTime getZeroHourTime(int day)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();

        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }


    /**
     * 时间戳转LocalDateTime
     * @param timestamp
     * @return
     */
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 取得零点时间
     */
    public static String getZeroHourTimeStr(int day)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(calendar.getTime());
    }


    /**
     * localDateTime to String
     * @param localDateTime
     * @return
     */
    public static String localDateToStr(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDateTime.format(formatter);
    }

    /**
     * localDateTime to String
     * @param localDateTime
     * @return
     */
    public static String localDateTimeToStr(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    /**
     * str to Obj
     * @param ext
     * @return
     */
    public static Object strToObj(String ext) {
        try {
            return JSONObject.parseObject(ext);
        } catch (Exception e) {
            return ext;
        }
    }
}
