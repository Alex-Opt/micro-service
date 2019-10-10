package com.ly.mt.blue.tooth.base.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static com.ly.mt.blue.tooth.base.dict.PropertyEnum.*;

/**
 * @Description 计算工具类
 * @Author whl
 * 分享页面
 * 加时时间= 口数*1.1，取整数，不足1取1
 *本周打分规则
 *   a.当周总口数=0时，健康指数=100分。
 *   b.当1≤口数≤599时，健康指数=|300-口数| / 16 +80，再四舍五入取整，最后结果在80~99分之间
 *   c.当口数大于等于600时，健康指数=79分
 * 对比上周增加/减少=（A-B）/B
 *
 * 本月健康指数规则
 *      * X=月总口数/4.5
 *      * a.X=0时，健康指数=100分。
 *      * b.当1≤X≤599时，健康指数=|300-X| / 16 +80，再四舍五入取整，最后结果在80~99分之间
 *      * c.当X大于等于600时，健康指数=79分
 *
 * 日均抽烟口数=总口数/累计抽烟天数
 * 烟弹剩余=(1-抽烟总口数/600)*100
 * 烟弹还可抽=600-抽烟总口数
 * 有害物质少吸入量 = 抽烟口数*4.615mg
 * 累计少抽香烟根数=(抽烟总口数/600f)*60元
 * 抽烟次数 = 抽烟总口数/10
 * 累计节省多少钱 =60*(总口数/600f))
 *
 * 电量详情-可抽天数：
 * A=（400mAH*z%）/（xG+y）
 * 1、日待机功耗y
 * 2、当没有同步数据时，x值为100口；当有数据同步时，x为每日抽烟口数平均值
 * 3、A为可抽天数，保留一位小数，最小值0
 * 4、硬件电量接口返回值为剩余电量百分比z%，剩余电量=400mAH*z%
 * 5、G为每口抽烟的功耗
 *
 * 电量详情-可抽口数=（400mAH*z%）/G
 * 1、待机功耗与抽烟相比可忽略不计
 * 2、可抽口数取整数，最小值0
 * 3、G为每口抽烟的功耗
 *
 * 电量详情-电量充满时间充满时间=（1-z%）*400/8 取整数
 * 1、z为剩余电量百分比
 * 两个值待工厂测试环境给出：烟杆日待机功耗y，每口抽烟功耗G
 */
public class CalcUntils {
    private final static Logger logger = LoggerFactory.getLogger(CalcUntils.class);


    /**
     * a 本周数据 b上周数据
     * 对比上周/上月增加/减少=（A-B）/B
     */
    public static int calcContrastLast(int a,int b){
        try {
            //本周数据不为0,上周数据为0
            if(a>0&&b==0)return 100;
            int c =a-b;
            int d = Math.abs(c);
            double f1 = new BigDecimal((float)d/b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留两位小数
            double kpi = f1*100;
            int r = Integer.parseInt(new java.text.DecimalFormat("0").format(kpi));
            if(c<0){
                return -r;
            }else{
                return r;
            }
        } catch (Exception e) {
            logger.error("计算-对比上次数据出错",e);
        }
        return 0;
    }


    /**
     * 本周健康指数规则
     *   a.当周总口数=0时，健康指数=100分。
     *   b.当1≤口数≤599时，健康指数=|300-口数| / 16 +80，(绝对值)再四舍五入取整，最后结果在80~99分之间
     *   c.当口数大于等于600时，健康指数=79分
     */
    public static int calcWeekHealthIndex(int smokingNumberMounthWeek){
        try {
            if(smokingNumberMounthWeek==0)return 100;
            if(smokingNumberMounthWeek>=1&&smokingNumberMounthWeek<=599){
                int healthIndex = (Math.abs(300-smokingNumberMounthWeek))/16+80;
                return healthIndex;
            }
            return 79;
        } catch (Exception e) {
            logger.error("计算-本周健康指数规则出错",e);
        }
        return 0;
    }

    /**
     * 本月健康指数规则
     * X=月总口数/4.5
     * a.X=0时，健康指数=100分。
     * b.当1≤X≤599时，健康指数=|300-X| / 16 +80，再四舍五入取整，最后结果在80~99分之间
     * c.当X大于等于600时，健康指数=79分
     */
    public static int calcMonthHealthIndex(int smokingNumberMounthMonth){
        try {
            if(smokingNumberMounthMonth<5)return 100;
            int x = (int) (smokingNumberMounthMonth/4.5);
            if(x>=1&&x<=599){
                int healthIndex = (Math.abs(300-x))/16+80;
                return healthIndex;
            }
            return 79;
        } catch (Exception e) {
            logger.error("计算-本月健康指数规则出错",e);
        }
        return 0;
    }

    /**
     * 加时时间(续命时间)= 口数*1.1，取整数，不足1取1
     *
     */
    public static int calcContinueOneSLifeMin(int smokingNumberMounthTotal){
        try {
            if(smokingNumberMounthTotal==0)return 0;
            int continueOneSLifeMin=(int)Math.floor(smokingNumberMounthTotal*CONTINUE_ONE_S_LIFE.getValue());
            return continueOneSLifeMin;
        } catch (Exception e) {
            logger.error("计算-加时时间(续命时间)出错",e);
        }
        return 0;
    }


    /**
     * 累计节省多少钱 30*(总口数/300f))
     *
     */
    public static int calcTotalSaveMoney(int smokingNumberMounthTotal){
        try {
            if(smokingNumberMounthTotal==0)return 0;
            int totalSaveMoney=(int)Math.floor(TOTAL_MONEY.getValue()*(smokingNumberMounthTotal/(float)TASTE_SMOKING_MOUTH_NUMBER.getValue()));
            if(totalSaveMoney<0|totalSaveMoney==0)totalSaveMoney=1;
            return totalSaveMoney;
        } catch (Exception e) {
            logger.error("计算-累计节省多少钱出错",e);
        }
        return 0;
    }


    /**
     * 烟弹剩余 (1-抽烟总口数/600)*100
     *
     */
    public static int calcTasteLastPercent(int smokingNumberMounthTotal){
        try {
            if(smokingNumberMounthTotal==0)return 0;
            int tasteLastPercent = (int) Math.floor((1-smokingNumberMounthTotal/(float)(TASTE_SMOKING_MOUTH_NUMBER.getValue()))*100);
            if(tasteLastPercent<0)tasteLastPercent=0;
            return tasteLastPercent;
        } catch (Exception e) {
            logger.error("计算烟弹剩余出错",e);
        }
        return 0;
    }

    /**
     * 烟弹还可抽口数 600-抽烟总口数
     *
     */
    public static int calcTasteLastMouthNumber(int smokingNumberMounthTotal){
        try {
            int tasteLastMouthNumber =(int) TASTE_SMOKING_MOUTH_NUMBER.getValue()-smokingNumberMounthTotal;
            if(tasteLastMouthNumber<0)tasteLastMouthNumber=0;
            return tasteLastMouthNumber;
        } catch (Exception e) {
            logger.error("计算烟弹还可抽口数出错",e);
        }
        return 0;
    }

    /**
     * 累计少抽香烟根数=(抽烟总口数/300f)*30
     *
     */
    public static int calcLessCigarettesr(int smokingNumberMounthTotal){
        try {
            if(smokingNumberMounthTotal==0)return 0;
            int lessCigarettes =(int)Math.floor((smokingNumberMounthTotal/TASTE_SMOKING_MOUTH_NUMBER.getValue())*TOTAL_MONEY.getValue());//累计少抽香烟根数
            if(lessCigarettes<0|lessCigarettes==0)lessCigarettes=1;
            return lessCigarettes;
        } catch (Exception e) {
            logger.error("计算累计少抽香烟根数出错",e);
        }
        return 0;
    }

    /**
     * 抽烟次数 = 抽烟总口数/10
     *
     */
    public static int calcSmokingTimes(int smokingNumberMounthTotal){
        try {
            int smokingTimes =(int)Math.floor(smokingNumberMounthTotal/(float)SMOKING_TEN_ONCE.getValue());//抽烟次数f
            if(smokingTimes<0)smokingTimes=0;
            return smokingTimes;
        } catch (Exception e) {
            logger.error("计算抽烟次数出错",e);
        }
        return 0;
    }


    /**
     * 有害物质少吸入量 = 抽烟口数*4.615mg
     *
     */
    public static int calcHarmFul(int smokingNumberMounthTotal){
        try {
            int harmFul = (int)Math.floor(smokingNumberMounthTotal*HARMFUL.getValue());
            if(harmFul<0)harmFul=0;
            return harmFul;
        } catch (Exception e) {
            logger.error("计算有害物质少吸入量出错",e);
        }
        return 0;
    }

    /**
     * 计算用户日均抽烟口数
     * @param smokingNumberMounthTotal
     * @param dayCount
     * @return
     */
    public static int calcAvgSmokingMouth(int smokingNumberMounthTotal,int dayCount) {
        try {
            if(dayCount==0)return 0;
            int avg=(int)Math.floor(smokingNumberMounthTotal/dayCount);
            if(avg<0)avg=0;
            return avg;
        } catch (Exception e) {
            logger.error("计算-用户日均抽烟口数出错",e);
        }
        return 0;
    }



    /**
     * 电量详情-计算电量充满时间
     * z为剩余电量百分比
     * （1-z%）*400/8
     * @param electiclQuantity
     * @return
     * @throws JsonProcessingException
     */
    public static int calcFullOfTime(int electiclQuantity) {
        try {
            int fullOfTime = (int) (((TOBACCO_ELECTRIC_QUANTITY_PERCENT.getValue()-electiclQuantity)*ELECTRIC_QUANTITY.getValue()/100)/MINUTE_FULL_ELECTRIC_QUANTITY.getValue());
            if(fullOfTime<0)fullOfTime=0;
            return fullOfTime;
        } catch (Exception e) {
            logger.error("电量详情-计算-电量充满时间出错",e);
        }
        return 0;
    }

    /**
     * 电量详情-当前电量可抽口数=（400mAH*z%）/G
     * 1、待机功耗与抽烟相比可忽略不计
     * 2、可抽口数取整数，最小值0
     * 3、G为每口抽烟的功耗
     * 4. z为剩余电量百分比
     */
    public static int calcSmokingMouthNumber(int electiclQuantity){
        try {
            float mouthNumber = ELECTRIC_QUANTITY.getValue()*electiclQuantity/100/ EVERY_MOUNTH_SMOKING_POWER.getValue();
            if(mouthNumber<0)mouthNumber=0;
            return (int)mouthNumber;
        } catch (Exception e) {
            logger.error("电量详情-计算当前电量可抽口数出错",e);
        }
        return 0;
    }

    /**
     * 电量详情-当前电量可抽天数 =（400mAH*z%）/（xG+y）
     * 1、日待机功耗y
     * 2、当没有同步数据时，x值为100口；当有数据同步时，x为每日抽烟口数平均值
     * 3、A为可抽天数，保留一位小数，最小值0
     * 4、硬件电量接口返回值为剩余电量百分比z%，剩余电量=400mAH*z%
     * 5、G为每口抽烟的功耗
     */
    public static int calcOnStandby(int electiclQuantity,int avgMouthNumber){
        try {
            int onStandby = (int) (ELECTRIC_QUANTITY.getValue()*electiclQuantity/100/ (avgMouthNumber*EVERY_MOUNTH_SMOKING_POWER.getValue()+TOBACCO_ON_STANDBY_POWER.getValue()));
            if(onStandby<0)onStandby=0;
            return onStandby;
        } catch (Exception e) {
            logger.error("电量详情-计算当前可抽天数出错",e);
        }
        return 0;
    }

    public static void main(String[] args) {
         // int a =  calcSmokingMouthNumber(33);
//       //int b =calcSmokingMouthNumber(100);
//        int c =calcOnStandby(40,100);
       // int d =calcContrastLast(200,150);//计算比上次数据增加或减少
        //int e = calcWeekHealthIndex(601);//计算周健康指数
        int f = calcMonthHealthIndex(5000);//计算月健康指数

        System.out.print(f);
    }
}
