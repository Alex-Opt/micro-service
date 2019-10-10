package com.ly.mt.core.common.util;

import java.math.BigDecimal;

public class GzgRewardRuleUtil {
    public static BigDecimal reward(long minutes){
        BigDecimal reward = null;
        if (minutes >= 0 && minutes <= 10){
            reward = new BigDecimal(7);
        }else if (minutes > 10 && minutes <= 30){
            reward = new BigDecimal(5);
        }else if (minutes > 30 && minutes <=60){
            reward = new BigDecimal(3);
        }else {
            reward = new BigDecimal(1);
        }
        return reward;
    }
}
