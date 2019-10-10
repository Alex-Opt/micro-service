package com.ly.mt.core.base.dict;

/**
 *@Description 收益日志类别枚举
 *@Author  zhuyh
 */
public enum ShopProfitsLogType {

    INVITE(1, "邀请", "分享淘金"),
    ADDRESS_BOOK(2, "通讯录", "通讯录"),
    TRAFFIC(3, "流量", "流量"),
    REWARD(4, "抢单奖励", "抢单奖励"),
    GRAB(5, "抢单金额", "抢单金额"),
    ORDER(6, "专属订单", "专属订单"),
    BUY(8, "进货", "进货"),
    GRAB_CASH(10, "抢单提现", "提现"),
    LODE_CASH(11, "淘金提现", "提现"),
    ORDER_CASH(12, "专属订单提现", "提现"),
    REWARD_CASH(13, "抢单奖励提现", "提现"),
    BUY_REWAD_CASH(14, "进货奖励提现", "提现"),
    ;
    private Integer id;
    private String name;
    /**
     * 前端显示
     */
    private String txt;

    ShopProfitsLogType(Integer id, String name, String txt) {
        this.id = id;
        this.name = name;
        this.txt = txt;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTxt() {
        return txt;
    }

    /**
     *@Description 获得前端显示文字
     *@Author  zhuyh
     */
    public static String getTxtById(Integer id){
        if (id == null){
            return "";
        }
        for (ShopProfitsLogType typeEnum : ShopProfitsLogType.values()){
            if (typeEnum.getId().equals(id)){
                return typeEnum.getTxt();
            }
        }
        return "";
    }

    /**
     *@Description 查询符号
     *@Author  zhuyh
     */
    public static String getSymbol(Integer id){
        if (ShopProfitsLogType.REWARD_CASH.getId().equals(id)
            || ShopProfitsLogType.GRAB_CASH.getId().equals(id)
            || ShopProfitsLogType.LODE_CASH.getId().equals(id)
            || ShopProfitsLogType.ORDER_CASH.getId().equals(id)
            || ShopProfitsLogType.BUY_REWAD_CASH.getId().equals(id)
        ){
            return "-";
        } else {
            return "+";
        }
    }
}
