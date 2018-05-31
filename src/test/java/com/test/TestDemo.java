package java.com.test;

/**
 * Tests
 */
public class TestDemo {
    public static void main(String[] args) {
        for (int i = 16; i <= 30; i++) {
            System.out.println("INSERT INTO `t_trans_file` VALUES (null, 'DJJD201805" + i + ".xls', null, null, '2018-05-" + i + "', null, 'DJJD', false);");
            System.out.println("INSERT INTO `t_trans_file` VALUES (null, 'HB201805" + i + ".xls', null, null, '2018-05-" + i + "', null, 'HB', false);");
            System.out.println("INSERT INTO `t_trans_file` VALUES (null, 'JZMX201805" + i + ".txt', null, null, '2018-05-" + i + "', null, 'JZMX', false);");
            System.out.println("INSERT INTO `t_trans_file` VALUES (null, 'WTCZ201805" + i + ".xls', null, null, '2018-05-" + i + "', null, 'WTCZ', false);");
            System.out.println("INSERT INTO `t_trans_file` VALUES (null, 'WTTX201805" + i + ".xls', null, null, '2018-05-" + i + "', null, 'WTTX', false);");
            System.out.println("INSERT INTO `t_trans_file` VALUES (null, 'YE201805" + i + ".xls', null, null, '2018-05-" + i + "', null, 'YE', false);");
        }
    }
}
