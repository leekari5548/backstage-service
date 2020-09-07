package com.leekari.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author litao
 * @date 2020/8/25 21:16
 * @description
 */
public class TimeUtils {
    final static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";

    public static String parseDateTime(Date date){
        LocalDateTime time = LocalDateTime.
                of(date.getYear() + 1900,date.getMonth() + 1,date.getDate(),date.getHours(),date.getMinutes(),date.getSeconds());
        DateTimeFormatter dateTimeFormatter =   DateTimeFormatter.ofPattern(dateTimePattern);

        return time.format(dateTimeFormatter);
    }
}
