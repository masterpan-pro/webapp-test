package com.demo.util;

import lombok.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class DateUtils {

    public static final String FULL_DATE_PATTERN = "yyyy-MM-dd";
    public static final String SHORT_DATE_PATTERN = "y-M-d";

    /**
     * @param formatPattern 要返回的日期格式
     * @return
     */
    public static String now(@NonNull String formatPattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatPattern));
    }

    /**
     * 获取指定日期的星期
     *
     * @param
     * @return
     */
    public static int getDayOfWeek(@NonNull String date) {
        DayOfWeek dw = LocalDate.parse(date).getDayOfWeek();
        return dw.getValue();
    }

    /**
     * @param date          要处理的日期
     * @param formatPattern 日期格式  同时也是返回格式
     * @param i             要加的天数，可以为负数
     * @return
     */
    public static String plusYears(@NonNull String date, @NonNull String formatPattern, long i) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatPattern);
        return LocalDate.parse(date, dateFormatter).plusYears(i).format(dateFormatter);
    }

    public static String plusMonths(@NonNull String date, @NonNull String formatPattern, long i) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatPattern);
        return LocalDate.parse(date, dateFormatter).plusMonths(i).format(dateFormatter);
    }

    public static String plusDays(@NonNull String date, @NonNull String formatPattern, long i) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatPattern);
        return LocalDate.parse(date, dateFormatter).plusDays(i).format(dateFormatter);
    }


    /**
     * 返回指定日期月份内的第一天
     *
     * @param date
     * @param formatPattern 解析格式
     * @return 格式与传入的格式一致
     */
    public static String firstDayOfMonth(@NonNull String date, @NonNull String formatPattern) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatPattern);
        return LocalDate.parse(date, dateFormatter).with(TemporalAdjusters.firstDayOfMonth()).format(dateFormatter);
    }

    /**
     * 返回指定日期月份内的最后一天
     *
     * @param date
     * @param formatPattern 解析格式
     * @return 格式与传入的格式一致
     */
    public static String lastDayOfMonth(@NonNull String date, @NonNull String formatPattern) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formatPattern);
        return LocalDate.parse(date, dateFormatter).with(TemporalAdjusters.firstDayOfMonth()).format(dateFormatter);
    }

    /**
     * 返回当月的第一天
     * 格式 yyyy-MM-dd
     *
     * @return
     */
    public static String firstDayOfMonth() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).format(dateFormatter);
    }

    /**
     * 返回当月的最后一天
     * 格式 yyyy-MM-dd
     *
     * @return
     */
    public static String lastDayOfMonth() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).format(dateFormatter);
    }


    /**
     * 计算两个日期间隔天数
     *
     * @param date1
     * @param pattern1 解析格式
     * @param date2
     * @param pattern2 解析格式
     * @return 返回第一个日期和第二个日期相隔天数，第一个比第二个早即为负数，第一个比第二个晚即为正数
     */

    public static long daysOf2Date(@NonNull String date1, @NonNull String pattern1, @NonNull String date2, @NonNull String pattern2) {

        LocalDate localDate1 = LocalDate.parse(date1, DateTimeFormatter.ofPattern(pattern1));
        LocalDate localDate2 = LocalDate.parse(date2, DateTimeFormatter.ofPattern(pattern2));
        return localDate1.until(localDate2, ChronoUnit.DAYS);

    }

    /**
     * 计算两个日期间隔天数
     * 默认解析格式是 yyyy-MM-dd
     *
     * @param date1
     * @param date2
     * @return 返回第一个日期和第二个日期相隔天数，第一个比第二个早即为负数，第一个比第二个晚即为正数
     */

    public static long daysOf2Date(@NonNull String date1, @NonNull String date2) {
        return daysOf2Date(date1, SHORT_DATE_PATTERN, date2, SHORT_DATE_PATTERN);
    }


    /**
     * 计算两个日期间隔月数，自定义解析格式
     *
     * @param date1
     * @param pattern1 解析格式
     * @param date2
     * @param pattern2 解析格式
     * @return 返回第一个日期和第二个日期相隔月数，第一个比第二个早即为负数，第一个比第二个晚即为正数
     */
    public static long monthsOf2Date(@NonNull String date1, @NonNull String pattern1, @NonNull String date2, @NonNull String pattern2) {

        LocalDate localDate1 = LocalDate.parse(date1, DateTimeFormatter.ofPattern(pattern1));
        LocalDate localDate2 = LocalDate.parse(date2, DateTimeFormatter.ofPattern(pattern2));
        return localDate1.until(localDate2, ChronoUnit.MONTHS);

    }


    /**
     * 计算两个日期间隔月数
     * 默认解析格式是 yyyy-MM-dd
     *
     * @param date1
     * @param date2
     * @return 返回第一个日期和第二个日期相隔月数，第一个比第二个早即为负数，第一个比第二个晚即为正数
     */
    public static long monthsOf2Date(@NonNull String date1, @NonNull String date2) {
        return monthsOf2Date(date1, SHORT_DATE_PATTERN, date2, SHORT_DATE_PATTERN);

    }


    /**
     * 比较两个date大小，默认两个格式都是yyyy-MM-dd，比较两个date大小格式
     *
     * @return 1 0 -1    1 表示第一个大于第二个 0表示两个相等 -1表示第一个小于第二个
     */
    public static int compareDate(@NonNull String date1, @NonNull String date2) {
        int num = compareDate(date1, SHORT_DATE_PATTERN, date2, SHORT_DATE_PATTERN);
        if (num < 0) {
            return -1;
        } else if (num > 0) {
            return 1;
        } else {
            return 0;
        }

    }


    /**
     * 比较两个date大小
     *
     * @param @return        1 0 -1    1 表示第一个大于第二个 0表示两个相等 -1表示第一个小于第二个
     * @param formatPattern1
     * @param date2
     * @param formatPattern2
     * @return
     */
    public static int compareDate(@NonNull String date1, @NonNull String formatPattern1, @NonNull String date2, @NonNull String formatPattern2) {
        LocalDate localDate1 = LocalDate
                .parse(date1, DateTimeFormatter.ofPattern(formatPattern1));
        LocalDate localDate2 = LocalDate
                .parse(date2, DateTimeFormatter.ofPattern(formatPattern2));
        return localDate1.compareTo(localDate2);
    }


    /**
     * 日期格式转换
     *
     * @param srcString   日期字符串
     * @param srcFormat   原格式
     * @param orderFormat 目标格式
     * @return
     */
    public static String formatTransfrom(@NonNull String srcString, @NonNull String srcFormat, @NonNull String orderFormat) {


        return LocalDate
                .parse(srcString, DateTimeFormatter.ofPattern(srcFormat))
                .format(DateTimeFormatter.ofPattern(orderFormat));
    }


    public static Long getTime(@NonNull String timeStr, @NonNull String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Long startTime = 0L;
        try {
            startTime = sdf.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startTime;
    }
}
