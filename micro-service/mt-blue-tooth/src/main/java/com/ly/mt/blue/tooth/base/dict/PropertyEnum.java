package com.ly.mt.blue.tooth.base.dict;

/**
 * @Description 属性配置
 * @Author whl
 * 可抽天数：
 * A=（400mAH*z%）/（xG+y）
 * 1、日待机功耗y
 * 2、当没有同步数据时，x值为100口；当有数据同步时，x为每日抽烟口数平均值
 * 3、A为可抽天数，保留一位小数，最小值0
 * 4、硬件电量接口返回值为剩余电量百分比z%，剩余电量=400mAH*z%
 * 5、G为每口抽烟的功耗
 *
 * 可抽口数=（400mAH*z%）/G
 * 1、待机功耗与抽烟相比可忽略不计
 * 2、可抽口数取整数，最小值0
 * 3、G为每口抽烟的功耗
 *
 * 电量充满时间充满时间=（1-z%）*400/8 取整数
 * 1、z为剩余电量百分比
 * 两个值待工厂测试环境给出：烟杆日待机功耗y，每口抽烟功耗G
 */
public enum PropertyEnum {
    TASTE_SMOKING_MOUTH_NUMBER(300, "烟弹可抽总口数300"),
    TASTE_LAST_PERCENT(100, "烟弹默认剩余百分比100"),
    TOBACCO_ELECTRIC_QUANTITY_PERCENT(100, "烟杆总电量百分比"),
    ELECTRIC_QUANTITY(400, "电量总毫安400"),
    MINUTE_FULL_ELECTRIC_QUANTITY(8, "每分钟充电8MH"),
    EVERY_MOUNTH_SMOKING_POWER(1.143f, "每口抽烟功耗"),//工厂给出最新数值
    TOBACCO_ON_STANDBY_POWER(1.2f, "烟杆日待机功耗"),//工厂给出最新数值
    TOTAL_MONEY(30, "一个烟弹1.5包烟钱30根/30元"),
    SMOKING_TEN_ONCE(10, "默认10口烟为抽一次"),
    AVG_SMOKING_MOUTH_NUMBER(100, "日均抽烟口数默认值/当没有抽烟数据时"),
    HARMFUL(5, "每口少抽有害物质5mg"),
    CONTINUE_ONE_S_LIFE(1.1f, "每口续命min");


    private final float value;
    private final String describe;

    PropertyEnum(float value, String describe) {
        this.value = value;
        this.describe = describe;
    }

    public float getValue() {
        return value;
    }

    public String getDescribe() {
        return describe;
    }}