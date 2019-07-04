package com.xqh.ad.dsp.platform.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by samson.huang on 2019/7/3
 */
public class CommonUtils {

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
     * 取得零点时间
     */
    public static String getZeroHourTimeDate(int day)
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
}
