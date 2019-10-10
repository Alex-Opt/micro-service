package com.ly.mt.core.common.constant;

/**
 * @Description 返回代码枚举
 * @Author taoye
 */
public enum ResultCodeEnum {
    RESULT_CODE_OPERATION_SUCCESS("0", "操作成功!"),
    RESULT_CODE_SYSTEM_ERROR("1", "系统异常!"),
    RESULT_CODE_PARAM_ERROR("2", "参数错误!"),
    RESULT_CODE_NOT_LOGIN_ERROR("3", "未登录!"),
    RESULT_CODE_LOGIN_ERROR("4", "账号或密码错误!"),
    RESULT_CODE_OPERATION_FAIL("5", "操作失败!"),
    RESULT_CODE_PARAM_NEED("6", "参数缺失!"),
    RESULT_CODE_OUT_TIME("7", "不在配送时间范围！"),

    RESULT_CODE_DYNAMIC_CODE_INVALID("10", "动态验证码失效!"),
    RESULT_CODE_DYNAMIC_CODE_ERROR("11", "动态验证码错误!"),
    RESULT_CODE_DYNAMIC_CODE_REPEAT_ERROR("12", "90秒内重复获取!"),
    RESULT_CODE_VERIFY_CODE_INVALID("13", "图片验证码失效!"),
    RESULT_CODE_VERIFY_CODE_ERROR("14", "图片验证码错误!"),
    RESULT_CODE_INVITE_CODE_INVALID("15", "注册邀请码无效!"),
    RESULT_CODE_MOBILE_SAME_BIND("16", "该手机号与当前绑定的手机号相同!"),
    RESULT_CODE_MOBILE_OTHER_BIND("17", "该手机号已绑定过其他手机号!"),
    RESULT_CODE_IDCARD_FRONT_UPLOAD("18", "左图请上传身份证正面!"),
    RESULT_CODE_IDCARD_BACK_UPLOAD("19", "右图请上传身份证背面!"),


    RESULT_CODE_REGIST_LOGIN_NAME_ERROR("20", "账号已注册!"),
    RESULT_CODE_REGIST_MOBILE_ERROR("21", "手机号已注册!"),
    RESULT_CODE_REGIST_NOT_REGIST("22", "手机号未入住MOTO到家!"),
    RESULT_CODE_REGIST_NOT_AUTHENTICATION("23", "该b端未认证!"),
    RESULT_CODE_REGIST_NOTSET_PAWWSORD("24", "未设置登陆密码!"),
    RESULT_CODE_ALREADY_BIND_WECHAT("25", "已绑定微信!"),

    RESULT_CODE_BUYCAR_NULL_ERROR("30", "购物车为空!"),
    RESULT_CODE_COUPON_RECEIVED("40", "您已领取过该优惠券!"),
    RESULT_CODE_COUPON_INVALID("41","优惠券已过期"),
    RESULT_ACTIVITY_INVALID("42","促销活动已过期"),
    RESULT_FULL_REDUCTION_ERROR("50","没有有效期内的满减活动！"),
    RESULT_CODE_GOODS_NULL_ERROR("60", "购买商品为空!"),
    RESULT_PUNCH_CARD_ERROR("65", "今天已打卡!"),
    TOKEN_OVER_DUE("70","token已过期"),
    HAS_LOGIN_ON_OTHER_MACHINE("80","已在其它机器登录"),
    USER_NOT_EXIST("90","用户不存在"),
    PHONE_NO_FORMAT_ERROR("100","手机号格式不争取"),
    STAY_REPLENISH("101","未完成待补货,无法抢单"),
    ORDER_GRAB_FAIL("102","抢单失败"),
    GZG_REPLENISH_GOODS_NOT_EXISTS("103","补货商品不存在"),
    GZG_CHANNEL_CODE_HAS_SELLED("104","该商品已出售"),
    GZG_CHECK_FAILURE("105","格子柜校验失败"),
    GZG_HAS_EXISTS("106","该用户已添加关联"),
    GZG_GOODS_CHECK_BARCODE_TO_SKUID_ERROR("107","追踪码错误"),

    /**
     * 格子柜
     */
    GZG_PHONE_NOT_REGISTER("600","手机号未注册或被停用"),

    RESULT_CODE_MYSQL_UPDATE_FAIL("70","执行数据库更新失败。更新0条"),

    /**
     * 活动
     */
    ACTIVITY_JOIN_REPETITION("111","活动不能重复参加"),
    ACTIVITY_ORDER_NULL("112","订单为空"),
    ACTIVITY_USER_UNREGIST("113","用户未注册"),
    ACTIVITY_SHOP_REGIST_REPETITION("114","门店已经注册，不可重复注册"),
    ACTIVITY_SHOP_LOGIN_ERROR("115","登录失败，不是管理员或验证码错误"),
    ACTIVITY_SHOP_PRODUCTS_ERROR("116","无商品信息"),
    ACTIVITY_SHOP_SELECT_ERROR("117","信息查询失败"),
    ACTIVITY_USER_DYNAMIC_CODE_ERROR("118","验证码失效或错误，请检查"),
    ACTIVITY_USER_ORDER_DOWN_ERROR("119","用户下单失败"),
    ACTIVITY_USER_GETPRODUCT_CODE_ERROR("120","取货码错误"),
    ACTIVITY_ORDER_STATUS_UPDATE_ERROR("121","订单状态修改失败"),
    ACTIVITY_WECHAT_ADD_USER_ERROR("131","微信用户保存失败"),
    ACTIVITY_FIND_ERROR("132","信息查询失败"),
    ACTIVITY_ADD_ERROR("133","添加失败"),
    ACTIVITY_UPDATE_ERROR("134","信息更新失败"),
    ACTIVITY_REGIST_EXIST("135","已经注册"),
    ACTIVITY_DATA_NOT_EXIST("136","数据不存在"),
    ACTIVITY_WECHAT_HELP_OK("137","助力已经完成"),
    ACTIVITY_WECHAT_HELP_NO("138","助力未完成"),
    ACTIVITY_COUPON_CODE_NO_EXIST("139","兑换码不存在"),
    ACTIVITY_PHONE_CODE_NO_EXIST("141","验证码不存在"),
    ACTIVITY_HELP_EXIST("142","已经为别人助力过了哦"),
    ACTIVITY_SELECT_HELP_EXIST("143","还没选择过任务"),

    /**
     * 退款模块状态码
     */
    TRADE_ORDER_REFUND_STATUS_ERROR("150","订单状态禁止售后/退款"),
    TRADE_ORDER_REFUND_COUNT_ERROR("151","退货数量大于订单数量"),
    TRADE_ORDER_REFUND_SKU_ERROR("152","SKU码不存在"),
    TRADE_ORDER_REFUND_CANCEL_ERROR("153","当前退货单已取消"),
    TRADE_ORDER_REFUND_CANCEL_STATUS_ERROR("154","当前退货单状态拒绝取消申请"),
    TRADE_ORDER_REFUND_NULL("155","退货单为空"),

    /**
     * 身份证ocr 人像比对
     */
    RESULT_CODE_IDCARD_UNKNOWN("200","身份信息未知"),
    RESULT_CODE_IDCARD_SAME("201","身份信息一致"),
    RESULT_CODE_IDCARD_UNSAME("202","证件不一致，姓名不一致"),
    RESULT_CODE_IDCARD_SAME_ID_DIFFERENT_NAME("203","证件一致,姓名不一致"),
    RESULT_CODE_IDCARD_DIFFERENT_ID_SAME_NAME("204","证件不一致,姓名一致"),
    RESULT_CODE_IDCARD_UNSAME_ALL("205","不一致但不匹配项未知"),
    RESULT_CODE_PHOTO_NAME_DIFFERENT("206","不一致，身份核验未通过"),
    RESULT_CODE_PHOTO_NAME_SAME("207","人脸，身份证信息一致"),
    RESULT_CODE_NOT_FOUND_STOCK("206","人脸，身份信息库无"),

    CHECK_GOODS_NO_GOODS_CODE_DATA("207","校验商品需要的商品码不存在数据!");

    private final String code;
    private final String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}