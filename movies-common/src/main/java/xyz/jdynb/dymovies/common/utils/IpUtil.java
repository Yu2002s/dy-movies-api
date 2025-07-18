package xyz.jdynb.dymovies.common.utils;

import java.util.Random;

public class IpUtil {

    private static final int[][] CHINA_IP_RANGE = {
            {607649792, 608174079},// 36.56.0.0-36.63.255.255
            {1038614528, 1039007743},// 61.232.0.0-61.237.255.255
            {1783627776, 1784676351},// 106.80.0.0-106.95.255.255
            {2035023872, 2035154943},// 121.76.0.0-121.77.255.255
            {2078801920, 2079064063},// 123.232.0.0-123.235.255.255
            {-1950089216, -1948778497},// 139.196.0.0-139.215.255.255
            {-1425539072, -1425014785},// 171.8.0.0-171.15.255.255
            {-1236271104, -1235419137},// 182.80.0.0-182.92.255.255
            {-770113536, -768606209},// 210.25.0.0-210.47.255.255
            {-569376768, -564133889}, // 222.16.0.0-222.95.255.255
    };

    private static final Random random = new Random();

    public static String getRandomChinaIP() {
        int index = random.nextInt(CHINA_IP_RANGE.length);
        return num2ip(CHINA_IP_RANGE[index][0] + random.nextInt(CHINA_IP_RANGE[index][1] - CHINA_IP_RANGE[index][0]));
    }

    /*
     * 将十进制转换成ip地址
     */
    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x;

        b[0] = (ip >> 24) & 0xff;
        b[1] = (ip >> 16) & 0xff;
        b[2] = (ip >> 8) & 0xff;
        b[3] = ip & 0xff;
        x = b[0] + "." + b[1] + "." + b[2] + "." + b[3];
        return x;
    }
}