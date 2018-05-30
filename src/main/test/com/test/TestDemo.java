package com.test;

import com.demo.util.DateUtils;

/**
 * Tests
 */
public class TestDemo {
    public static void main(String[] args) {

        String formatDate = DateUtils.formatTransfrom("2018-05-30", DateUtils.FULL_DATE_PATTERN, "yyyyMMdd");
        System.out.println(formatDate);

    }
}
