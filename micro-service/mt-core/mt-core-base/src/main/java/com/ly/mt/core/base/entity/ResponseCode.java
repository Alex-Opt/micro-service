package com.ly.mt.core.base.entity;

/**
 * 响应编码
 * 除了特殊状态值(没有独立的值前端就无法处理逻辑)以外，所有的异常信息统一使用RESPONSE_CODE_ERROR枚举，并通过ResponseUtil.getResponseMsg方法返回提示信息
 *
 * @author taoye
 */
public enum ResponseCode {
    RESPONSE_CODE_SUCCESS("0", "操作成功"),
    RESPONSE_CODE_ERROR("1", "系统异常"),
    RESPONSE_CODE_NOT_LOGIN("3", "未登录"),
    RESPONSE_CODE_NOT_REGIST("4", "未注册"),
    RESPONSE_CODE_TOKEN_INVALID_DESC("5", "token无效"),
    RESPONSE_CODE_OPERATOR_TOO_FASTER("6", "操作过快，停几秒在试试吧"),
    RESPONSE_CODE_LOGIN_INVALID("7", "微信小程序登陆信息过期！"),
    RESPONSE_CODE_NOT_SET_PASSWD("8", "未设置登陆密码"),
    RESPONSE_CODE_WECHAT_AUTH_ERROR("12", "微信认证登录失败"),
    RESPONSE_CODE_WECHAT_JSSDK_ERROR("13", "微信JSSDK调用失败"),
    RESPONSE_CODE_COUPON_CODE_PULL_SUCCESS("14", "兑换码兑换成功!"),
    RESPONSE_CODE_COUPON_CODE_PULL_FAILURE("15", "该兑换码已经兑换过了!"),
    RESPONSE_CODE_COUPON_PULL_ALREADY("16", "领取已达上限，感谢您的参与！"),
    RESPONSE_CODE_REPEAT_SUBMIT("17", "该手机号已提交过申请，无需重复提交！"),
    RESPONSE_CODE_VERIFY_CODE_INVALID("18", "图片验证码失效!"),
    RESPONSE_CODE_VERIFY_CODE_ERROR("19", "图片验证码错误!"),
    RESPONSE_CODE_GET_MOBILE_EXCEPTION("20", "获取微信绑定手机号异常，请重试!"),
    /**
     * @deprecated
     */
    RESULT_CODE_OPERATION_SUCCESS("0", "操作成功!"),
    /**
     * @deprecated
     */
    RESULT_CODE_SYSTEM_ERROR("1", "系统异常!"),
    /**
     * @deprecated
     */
    RESULT_CODE_PARAM_ERROR("2", "参数错误!"),
    /**
     * @deprecated
     */
    RESULT_CODE_NOT_LOGIN_ERROR("3", "未登录!"),
    /**
     * @deprecated
     */
    RESULT_CODE_LOGIN_ERROR("4", "账号或密码错误!"),
    /**
     * @deprecated
     */
    RESULT_CODE_OPERATION_FAIL("5", "操作失败!"),
    /**
     * @deprecated
     */
    RESULT_CODE_PARAM_NEED("6", "参数缺失!"),
    /**
     * @deprecated
     */
    RESULT_CODE_OUT_TIME("7", "不在配送时间范围！"),
    /**
     * @deprecated
     */
    RESULT_CODE_OUT_DISTRICT("8", "收货地址暂不支持一小时达！"),
    /**
     * @deprecated
     */
    RESULT_CODE_DYNAMIC_CODE_INVALID("10", "动态验证码失效!"),
    /**
     * @deprecated
     */
    RESULT_CODE_DYNAMIC_CODE_ERROR("11", "动态验证码错误!"),
    /**
     * @deprecated
     */
    RESULT_CODE_DYNAMIC_CODE_REPEAT_ERROR("12", "90秒内重复获取!"),
    /**
     * @deprecated
     */
    RESULT_CODE_VERIFY_CODE_INVALID("13", "图片验证码失效!"),
    /**
     * @deprecated
     */
    RESULT_CODE_VERIFY_CODE_ERROR("14", "图片验证码错误!"),
    /**
     * @deprecated
     */
    RESULT_CODE_INVITE_CODE_INVALID("15", "注册邀请码无效!"),
    /**
     * @deprecated
     */
    RESULT_CODE_MOBILE_SAME_BIND("16", "该手机号与当前绑定的手机号相同!"),
    /**
     * @deprecated
     */
    RESULT_CODE_MOBILE_OTHER_BIND("17", "该手机号已绑定过其他手机号!"),
    /**
     * @deprecated
     */
    RESULT_CODE_IDCARD_FRONT_UPLOAD("18", "上图请上传身份证正面!"),
    /**
     * @deprecated
     */
    RESULT_CODE_IDCARD_BACK_UPLOAD("19", "下图请上传身份证背面!"),
    /**
     * @deprecated
     */
    RESULT_CODE_REGIST_LOGIN_NAME_ERROR("20", "账号已注册!"),
    /**
     * @deprecated
     */
    RESULT_CODE_REGIST_MOBILE_ERROR("21", "手机号已注册!"),
    /**
     * @deprecated
     */
    RESULT_CODE_REGIST_NOT_REGIST("22", "手机号未入住MOTO到家!"),
    /**
     * @deprecated
     */
    RESULT_CODE_REGIST_NOT_AUTHENTICATION("23", "该b端未认证!"),
    /**
     * @deprecated
     */
    RESULT_CODE_REGIST_NOTSET_PAWWSORD("24", "未设置登陆密码!"),
    /**
     * @deprecated
     */
    RESULT_CODE_ALREADY_BIND_WECHAT("25", "已绑定微信!"),
    /**
     * @deprecated
     */
    RESULT_CODE_BUYCAR_NULL_ERROR("30", "购物车为空!"),
    /**
     * @deprecated
     */
    RESULT_CODE_COUPON_RECEIVED("40", "您已领取过该优惠券!"),
    /**
     * @deprecated
     */
    RESULT_CODE_COUPON_INVALID("41", "优惠券已过期!"),
    /**
     * @deprecated
     */
    RESULT_ACTIVITY_INVALID("42", "促销活动已过期!"),
    /**
     * @deprecated
     */
    RESULT_FULL_REDUCTION_ERROR("50", "没有有效期内的满减活动!"),
    /**
     * @deprecated
     */
    RESULT_CODE_GOODS_NULL_ERROR("60", "购买商品为空!"),
    /**
     * @deprecated
     */
    RESULT_PUNCH_CARD_ERROR("65", "今天已打卡!"),
    /**
     * @deprecated
     */
    TOKEN_OVER_DUE("70", "token已过期!"),
    /**
     * @deprecated
     */
    HAS_LOGIN_ON_OTHER_MACHINE("80", "已在其它机器登录!"),
    /**
     * @deprecated
     */
    USER_NOT_EXIST("90", "用户不存在!"),
    /**
     * @deprecated
     */
    PHONE_NO_FORMAT_ERROR("100", "手机号格式不争取!"),
    /**
     * @deprecated
     */
    STAY_REPLENISH("101", "未完成待补货,无法抢单!"),
    /**
     * @deprecated
     */
    ORDER_GRAB_FAIL("102", "抢单失败"),
    /**
     * @deprecated
     */
    GZG_REPLENISH_GOODS_NOT_EXISTS("103", "补货商品不存在!"),
    /**
     * @deprecated
     */
    GZG_CHANNEL_CODE_HAS_SELLED("104", "该商品已出售!"),
    /**
     * @deprecated
     */
    GZG_CHECK_FAILURE("105", "格子柜校验失败!"),
    /**
     * @deprecated
     */
    GZG_HAS_EXISTS("106", "该用户已添加关联!"),
    /**
     * @deprecated
     */
    GZG_GOODS_CHECK_BARCODE_TO_SKUID_ERROR("107", "追踪码错误!"),
    /**
     * @deprecated
     */
    GZG_PHONE_NOT_REGISTER("600", "手机号未注册或被停用!"),
    /**
     * @deprecated
     */
    RESULT_CODE_MYSQL_UPDATE_FAIL("70", "执行数据库更新失败。更新0条!"),
    /**
     * @deprecated
     */
    ACTIVITY_JOIN_REPETITION("111", "活动不能重复参加!"),
    /**
     * @deprecated
     */
    ACTIVITY_ORDER_NULL("112", "订单为空!"),
    /**
     * @deprecated
     */
    ACTIVITY_USER_UNREGIST("113", "用户未注册!"),
    /**
     * @deprecated
     */
    ACTIVITY_SHOP_REGIST_REPETITION("114", "门店已经注册，不可重复注册!"),
    /**
     * @deprecated
     */
    ACTIVITY_SHOP_LOGIN_ERROR("115", "登录失败，不是管理员或验证码错误!"),
    /**
     * @deprecated
     */
    ACTIVITY_SHOP_PRODUCTS_ERROR("116", "无商品信息!"),
    /**
     * @deprecated
     */
    ACTIVITY_SHOP_SELECT_ERROR("117", "信息查询失败!"),
    /**
     * @deprecated
     */
    ACTIVITY_USER_DYNAMIC_CODE_ERROR("118", "验证码失效或错误，请检查!"),
    /**
     * @deprecated
     */
    ACTIVITY_USER_ORDER_DOWN_ERROR("119", "用户下单失败!"),
    /**
     * @deprecated
     */
    ACTIVITY_USER_GETPRODUCT_CODE_ERROR("120", "取货码错误!"),
    /**
     * @deprecated
     */
    ACTIVITY_ORDER_STATUS_UPDATE_ERROR("121", "订单状态修改失败!"),
    /**
     * @deprecated
     */
    TRADE_ORDER_REFUND_STATUS_ERROR("150", "订单状态禁止售后/退款!"),
    /**
     * @deprecated
     */
    TRADE_ORDER_REFUND_COUNT_ERROR("151", "退货数量大于订单数量!"),
    /**
     * @deprecated
     */
    TRADE_ORDER_REFUND_SKU_ERROR("152", "SKU码不存在!"),
    /**
     * @deprecated
     */
    TRADE_ORDER_REFUND_CANCEL_ERROR("153", "当前退货单已取消!"),
    /**
     * @deprecated
     */
    TRADE_ORDER_REFUND_CANCEL_STATUS_ERROR("154", "当前退货单状态拒绝取消申请!"),
    /**
     * @deprecated
     */
    TRADE_ORDER_REFUND_NULL("155", "退货单为空!"),
    /**
     * @deprecated
     */
    RESULT_CODE_IDCARD_UNKNOWN("200", "身份信息未知!"),
    /**
     * @deprecated
     */
    RESULT_CODE_IDCARD_SAME("201", "身份信息一致!"),
    /**
     * @deprecated
     */
    RESULT_CODE_IDCARD_UNSAME("202", "证件不一致，姓名不一致!"),
    /**
     * @deprecated
     */
    RESULT_CODE_IDCARD_SAME_ID_DIFFERENT_NAME("203", "证件一致,姓名不一致!"),
    /**
     * @deprecated
     */
    RESULT_CODE_IDCARD_DIFFERENT_ID_SAME_NAME("204", "证件不一致,姓名一致!"),
    /**
     * @deprecated
     */
    RESULT_CODE_IDCARD_UNSAME_ALL("205", "不一致但不匹配项未知!"),
    /**
     * @deprecated
     */
    RESULT_CODE_PHOTO_NAME_DIFFERENT("206", "不一致，身份核验未通过!"),
    /**
     * @deprecated
     */
    RESULT_CODE_PHOTO_NAME_SAME("207", "人脸，身份证信息一致!"),
    /**
     * @deprecated
     */
    RESULT_CODE_NOT_FOUND_STOCK("206", "人脸，身份信息库无!"),
    /**
     * @deprecated
     */
    CHECK_GOODS_NO_GOODS_CODE_DATA("207", "校验商品需要的商品码不存在数据!"),
    /**
     * @deprecated
     */
    RESULT_CODE_COUPON_NUMBER_EMPTY("208", "来晚啦 大礼包已经发完了 明天再过来看看吧"),
    /**
     * @deprecated
     */
    RESULT_CODE_COUPON_IS_TOKED("209", "新人礼包您已经领过啦"),

    RESULT_CODE_ID_CARD_EXT_ERROR("210", "上传身份证图片格式不正确"),
    RESULT_CODE_GZG_ORDER_TIMEOUT("211", "格子柜订单超时"),
    RESULT_CODE_GZG_ORDER_COMPLETE("212", "格子柜订单支付完成"),
    RESULT_CODE_GZG_ORDER_NOTPAY("213", "格子柜订单未支付"),
    RESULT_CODE_GZG_ORDER_PAY_FAIL("214", "格子柜订单支付失败"),
    RESULT_CODE_GZG_ORDER_OPEN_FAIL("215", "格子柜订单舱门失败"),;


    private String code;
    private String msg;

    ResponseCode(String code, String msg) {
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