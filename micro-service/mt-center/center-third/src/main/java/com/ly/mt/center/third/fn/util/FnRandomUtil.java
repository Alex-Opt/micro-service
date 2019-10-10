package com.ly.mt.center.third.fn.util;

import java.util.Random;

/**
 * 随机数生成器工具类
 */
public class FnRandomUtil {
    private static FnRandomUtil instance = new FnRandomUtil();

    private FnRandomUtil() {
    }

    public static FnRandomUtil getInstance() {
        return instance;
    }

    private Random random = new Random();

    /**
     * 随机生成min和max之间一个数，包括min不包括max
     *
     * @param min
     * @param max
     * @return [min, max)
     */
    public int generateValue(int min, int max) {
        return (int) (random.nextDouble() * (max - min) + min);
    }
}