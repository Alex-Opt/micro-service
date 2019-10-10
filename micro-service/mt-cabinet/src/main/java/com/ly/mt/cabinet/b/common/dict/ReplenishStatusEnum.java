package com.ly.mt.cabinet.b.common.dict;

/**
 * 格子柜B补货状态:1:待抢补货单 3:锁定补货单 4：商品校验失败 5:商品校验成功 6：货柜校验失败 7:货柜校验成功 9:提交审核成功 11:审核通过 12:审核失败  13:奖励提现成功 14：补货超时 15:放弃补货
 * @author zhanglifeng
 * @date 2019-08-26
 */
public enum ReplenishStatusEnum {
    REPLENISH_STATUS_WAITING_GRAB("1","待抢补货单") ,
    REPLENISH_STATUS_LOCKED("3","锁定补货单") ,
    REPLENISH_STATUS_GOODS_CHECK_FALSE("4","商品校验失败") ,
    REPLENISH_STATUS_GOODS_CHECK_SUCCESS("5","商品校验成功") ,
    REPLENISH_STATUS_CABINET_CHECK_FALSE("6","货柜校验失败") ,
    REPLENISH_STATUS_CABINET_CHECK_SUCCESS("7","货柜校验成功") ,
    REPLENISH_STATUS_SUBMIT_CHECK_SUCCESS("9","提交审核成功") ,
    REPLENISH_STATUS_PASSED("11","审核通过") ,
    REPLENISH_STATUS_FAILURE("12","审核失败") ,
    REPLENISH_STATUS_WITHDRAW_SUCCESS("13","奖励提现成功") ,
    REPLENISH_STATUS_TIME_OUT("14","补货超时"),
    REPLENISH_STATUS_INVALID("15","放弃补货") ,;

    private String key;
    private String value;

    ReplenishStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
