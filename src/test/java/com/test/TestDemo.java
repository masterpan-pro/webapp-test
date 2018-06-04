package com.test;

/**
 * Tests
 */
public class TestDemo {
    public static void main(String[] args) {
        for (int i = 16; i <= 30; i++) {
            System.out.println("INSERT INTO `t_trans_file` VALUES (null, 'DJJD201806" + i + ".xls', null, null, '2018-06-" + i + "', null, 'DJJD', false);");
            System.out.println("INSERT INTO `t_trans_file` VALUES (null, 'HB201806" + i + ".xls', null, null, '2018-06-" + i + "', null, 'HB', false);");
            System.out.println("INSERT INTO `t_trans_file` VALUES (null, 'JZMX201806" + i + ".txt', null, null, '2018-06-" + i + "', null, 'JZMX', false);");
            System.out.println("INSERT INTO `t_trans_file` VALUES (null, 'WTCZ201806" + i + ".xls', null, null, '2018-06-" + i + "', null, 'WTCZ', false);");
            System.out.println("INSERT INTO `t_trans_file` VALUES (null, 'WTTX201806" + i + ".xls', null, null, '2018-06-" + i + "', null, 'WTTX', false);");
            System.out.println("INSERT INTO `t_trans_file` VALUES (null, 'YE201806" + (i - 1) + ".xls', null, null, '2018-06-" + i + "', null, 'YE', false);");
        }
    }
}
