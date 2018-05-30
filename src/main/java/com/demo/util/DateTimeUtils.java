package com.demo.util;

import lombok.NonNull;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Chen
 */
public class DateTimeUtils {


    public static String checkDateTime(String dateTime) {
        if (dateTime.endsWith(".0")) {
            return dateTime.substring(0, dateTime.length() - 2);
        }
        return dateTime;
    }


    public static final String FULL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String SHORT_DATE_TIME_PATTERN = "y-M-d H:m:s";

    /**
     * @param formatPattern 要返回的日期格式
     * @return
     */
    public static String now(@NonNull String formatPattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public static String now(@NonNull DateTimeFormatter pattern) {
        return LocalDateTime.now().format(pattern);
    }

    /**
     * @param dateTime      要处理的日期
     * @param formatPattern 日期格式  同时也是返回格式
     * @param i             要加的天数，可以为负数
     * @return
     */
    public static String plusYears(@NonNull String dateTime, @NonNull String formatPattern, long i) {
        dateTime = checkDateTime(dateTime);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        return LocalDateTime.parse(dateTime, dateTimeFormatter).plusYears(i).format(dateTimeFormatter);
    }


    public static String plusMonths(@NonNull String dateTime, @NonNull String formatPattern, long i) {
        dateTime = checkDateTime(dateTime);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        return LocalDateTime.parse(dateTime, dateTimeFormatter).plusMonths(i).format(dateTimeFormatter);
    }

    public static String plusDays(@NonNull String dateTime, @NonNull String formatPattern, long i) {
        dateTime = checkDateTime(dateTime);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        return LocalDateTime.parse(dateTime, dateTimeFormatter).plusDays(i).format(dateTimeFormatter);
    }


    public static String plusHours(@NonNull String dateTime, @NonNull String formatPattern, long i) {
        dateTime = checkDateTime(dateTime);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        return LocalDateTime.parse(dateTime, dateTimeFormatter).plusHours(i).format(dateTimeFormatter);
    }

    public static String plusMinutes(@NonNull String dateTime, @NonNull String formatPattern, long i) {
        dateTime = checkDateTime(dateTime);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        return LocalDateTime.parse(dateTime, dateTimeFormatter).plusMinutes(i).format(dateTimeFormatter);
    }

    public static String plusSeconds(@NonNull String dateTime, @NonNull String formatPattern, long i) {
        dateTime = checkDateTime(dateTime);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
        return LocalDateTime.parse(dateTime, dateTimeFormatter).plusSeconds(i).format(dateTimeFormatter);
    }


    /**
     * 检查指定时间是否在另外两个时间之中
     *
     * @param startStr
     * @param endStr
     * @param compareDateStr
     * @return
     * @throws ParseException
     */
    public static boolean isBetween(@NonNull String startStr, @NonNull String endStr, @NonNull String compareDateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FULL_DATE_TIME_PATTERN);
        LocalDateTime start = LocalDateTime.parse(startStr, dateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse(endStr, dateTimeFormatter);
        LocalDateTime compareDate = LocalDateTime.parse(compareDateStr, dateTimeFormatter);

        if (compareDate.isAfter(start) && compareDate.isBefore(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较两个dateTime大小，自己指定两个解析格式，比较两个dateTime大小格式
     *
     * @param dateTime1
     * @param formatPattern1
     * @param dateTime2
     * @param formatPattern2
     * @return 1 0 -1    1 表示第一个大于第二个 0表示两个相等 -1表示第一个小于第二个
     */
    public static int compareDateTime(@NonNull String dateTime1, @NonNull String formatPattern1, @NonNull String dateTime2, @NonNull String formatPattern2) {
        dateTime1 = checkDateTime(dateTime1);
        dateTime2 = checkDateTime(dateTime2);
        LocalDateTime localDateTime1 = LocalDateTime
                .parse(dateTime1, DateTimeFormatter.ofPattern(formatPattern1));
        LocalDateTime localDateTime2 = LocalDateTime
                .parse(dateTime2, DateTimeFormatter.ofPattern(formatPattern2));
        int result = localDateTime1.compareTo(localDateTime2);
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return result;
        }
    }

    /**
     * 比较两个dateTime大小，默认两个格式都是yyyy-MM-dd HH:mm:ss，比较两个dateTime大小格式
     *
     * @param dateTime1
     * @param dateTime2
     * @return 1 0 -1    1 表示第一个大于第二个 0表示两个相等 -1表示第一个小于第二个
     */
    public static int compareDateTime(@NonNull String dateTime1, @NonNull String dateTime2) {
        return compareDateTime(dateTime1, SHORT_DATE_TIME_PATTERN, dateTime2, SHORT_DATE_TIME_PATTERN);
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
        srcString = checkDateTime(srcString);
        return LocalDateTime
                .parse(srcString, DateTimeFormatter.ofPattern(srcFormat))
                .format(DateTimeFormatter.ofPattern(orderFormat));
    }

    /**
     * 获取本周的最早日期
     *
     * @param resultFormatPattern 结果格式 如 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String atStartOfWeek(String resultFormatPattern) {
        return atStartOfWeek(LocalDate.now(), resultFormatPattern);
    }

    /**
     * 获取传入的date所在周的最早日期
     *
     * @param date                传入要获取的结果日期
     * @param resultFormatPattern 结果格式 如 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String atStartOfWeek(LocalDate date, String resultFormatPattern) {
        return LocalDateTime.of(date, LocalTime.MIN).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).format(DateTimeFormatter.ofPattern(resultFormatPattern));
    }

    /**
     * 获取本周的最晚时间
     *
     * @param resultFormatPattern 结果格式 如 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String atEndOfWeek(String resultFormatPattern) {
        return atEndOfWeek(LocalDate.now(), resultFormatPattern);
    }


    /**
     * 获取传入日期所在的最晚时间
     *
     * @param resultFormatPattern 结果格式 如 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String atEndOfWeek(LocalDate date, String resultFormatPattern) {
        return LocalDateTime.of(date, LocalTime.MAX).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).format(DateTimeFormatter.ofPattern(resultFormatPattern));
    }

    /**
     * 获取本月的最晚时间
     *
     * @param resultFormatPattern 结果格式 如 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String atEndOfMonth(String resultFormatPattern) {
        return atEndOfMonth(LocalDate.now(), resultFormatPattern);
    }

    /**
     * 获取传入的日期所在月的最晚时间
     *
     * @param date                传入要获取的结果
     * @param resultFormatPattern 结果格式 如 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String atEndOfMonth(LocalDate date, String resultFormatPattern) {
        return LocalDateTime.of(date, LocalTime.MAX).with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ofPattern(resultFormatPattern));
    }

    /**
     * 获取本月的最早时间
     *
     * @param resultFormatPattern 结果格式 如 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String atStartOfMonth(String resultFormatPattern) {
        return atStartOfMonth(LocalDate.now(), resultFormatPattern);
    }

    /**
     * 获取传入的date所在月的最早日期
     *
     * @param resultFormatPattern 结果格式 如 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String atStartOfMonth(LocalDate date, String resultFormatPattern) {
        return LocalDateTime.of(date, LocalTime.MIN).with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern(resultFormatPattern));
    }


    /**
     * @param date
     * @param retsultFormatPattern
     * @return 返回传入的date当天最早的时间   如传入2017-7-1 12:12:12  返回 2017-7-1 0:0:0
     */
    public static String atStartOfDay(@NonNull LocalDate date, @NonNull String retsultFormatPattern) {
        return LocalDateTime.of(date, LocalTime.MIN).format(DateTimeFormatter.ofPattern(retsultFormatPattern));
    }

    /**
     * 今天最早的时间
     *
     * @param retsultFormatPattern
     * @return 今天最早的时间
     */
    public static String atStartOfDay(@NonNull String retsultFormatPattern) {
        return atStartOfDay(LocalDate.now(), retsultFormatPattern);
    }

    /**
     * @param date
     * @param retsultFormatPattern
     * @return 返回传入的datetime当天最晚的时间   如传入2017-7-1 12:12:12  返回 2017-7-2 23:59:59.999999999 （和指定的格式有关）
     */
    public static String atEndOfDay(@NonNull LocalDate date, @NonNull String retsultFormatPattern) {
        return LocalDateTime.of(date, LocalTime.MAX).format(DateTimeFormatter.ofPattern(retsultFormatPattern));
    }


    /**
     * 今天最晚的时间
     *
     * @param retsultFormatPattern
     * @return 今天最早的时间
     */
    public static String atEndOfDay(@NonNull String retsultFormatPattern) {
        return atEndOfDay(LocalDate.now(), retsultFormatPattern);
    }

    public static void main(String[] args) {

        final String pattern = "yyyy-MM-dd HH:mm:ss";

        System.out.println(
                "今天开始时间：" +
                        DateTimeUtils.atStartOfDay(pattern)
        );
        System.out.println(
                "今天结束时间：" +
                        DateTimeUtils.atEndOfDay(pattern)
        );
        System.out.println(
                "上周开始时间：" +
                        DateTimeUtils.atStartOfWeek(LocalDate.now().plusWeeks(-1), pattern)
        );
        System.out.println(
                "本月开始时间：" +
                        DateTimeUtils.atStartOfMonth(pattern)
        );
        System.out.println(
                "本月最后时间：" +
                        DateTimeUtils.atEndOfMonth(pattern)
        );
        System.out.println(
                "上月最后时间：" +
                        DateTimeUtils.atEndOfMonth(LocalDate.now().plusMonths(-1), pattern)
        );
        System.out.println(
                "下月最后时间：" +
                        DateTimeUtils.atEndOfMonth(LocalDate.now().plusMonths(1), pattern)
        );
        System.out.println(
                "检查：" +
                        DateTimeUtils.checkDateTime("2017-1-1 1:1:1.0")
        );
    }

}
