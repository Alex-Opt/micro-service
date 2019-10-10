package com.ly.mt.core.common.method;

/**
 *@Description
 *@Author  zhuyh
 */
public enum MarketingMethodEnum {

    // 收益
    MARKETING_PROFITS_SUM("shopProfitsServiceImpl", "getProfitsSumByLoginId", "查询总收益"),
    MARKETING_PROFITS_TOP("shopProfitsServiceImpl", "getProfitsTop", "查询收益排行"),
    MARKETING_PROFITS_USER_COUNT("shopProfitsServiceImpl", "getOrderUserCount", "正在抢单赚取的人数"),
    MARKETING_PROFITS_REWARD_DETAILS("shopProfitsServiceImpl", "getRewardProfitsDetails", "查询抢单奖励收益详情"),
    MARKETING_PROFITS_GRAB_DETAILS("shopProfitsServiceImpl", "getGrabProfitsDetails", "查询抢单收益详情详情"),
    MARKETING_PROFITS_ORDER_DETAILS("shopProfitsServiceImpl", "getOrderProfitsDetails", "查询专属订单收益详情"),
    MARKETING_PROFITS_LODE_DETAILS("shopProfitsServiceImpl", "getLodeProfitsDetails", "查询淘金收益详情"),

    // 收益日志
    MARKETING_PROFITS_LOGS_REWARD_ORDER("shopProfitsLogsServiceImpl", "getRewardOrders", "查询有奖励的订单"),
    MARKETING_PROFITS_LOGS_REWARD("shopProfitsLogsServiceImpl", "getRewards", "查询奖励的日志"),
    MARKETING_PROFITS_LOGS_REWARD_TOTAL("shopProfitsLogsServiceImpl", "getRewardsTotal", "查询奖励的累计"),
    MARKETING_PROFITS_LOGS_LODE("shopProfitsLogsServiceImpl", "getLodes", "查询淘金的日志"),
    MARKETING_PROFITS_LOGS_LODE_TOTAL("shopProfitsLogsServiceImpl", "getLodeTotal", "查询淘金的累计"),
    MARKETING_PROFITS_LOGS_GRABS_CYCLE_SUM("shopProfitsLogsServiceImpl", "getGrabCycleSum", "查询抢单周期结算的累计"),
    MARKETING_PROFITS_LOGS_GRABS("shopProfitsLogsServiceImpl", "getGrabs", "查询抢单的日志"),
    MARKETING_PROFITS_LOGS_ORDER_CYCLE_SUM("shopProfitsLogsServiceImpl", "getOrderCycleSum", "查询专属单周期结算的累计"),
    MARKETING_PROFITS_LOGS_ORDERS("shopProfitsLogsServiceImpl", "getOrders", "查询专属单的日志"),

    // 淘金
    MARKETING_LODE_DETAILS("lodeRunnerTreesServiceImpl", "getDetails", "分页获取明细详细"),
    MARKETING_LODE_MY("lodeRunnerTreesServiceImpl", "getMine", "获取我的淘金详情"),
    MARKETING_LODE_TEAM_HEAD_DETAILS("lodeRunnerTreesServiceImpl", "getTeamHeadDetails", "分页获取团队明细详细"),
    MARKETING_LODE_CODE("lodeRunnerCodesServiceImpl", "getCode", "获取淘金者邀请码"),

;



    /**
     * @Description 接口名字
     */
    private final String serviceName;
    /**
     * @Description 方法名字
     */
    private final String functionName;
    /**
     * @Description 方法描述
     */
    private final String describe;

    MarketingMethodEnum(String serviceName, String functionName, String describe) {
        this.serviceName = serviceName;
        this.functionName = functionName;
        this.describe = describe;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getFunctionName() {
        return functionName;
    }
}
