package com.ly.mt.cabinet.b.util;

/**
 * @author evan
 */
public enum RespCode {
  SUCCESS(0, "操作成功"),
  ERROR(10, "操作失败"),
  PARAMETER_ERROR(13,"参数录入错误或者参数为空"),
  BD_PHONE_OR_OWNER_PHONE_IS_NULL(13,"bd手机号或店铺管理员手机号为空"),
  SIGN_CONTRACT_DATETIME_IS_NULL(14,"已签合同,生效或者失效时间为空"),
  PARAMETER_VALID_FAIL(11,"参数校验失败"),
  PHONENO_FORMAT_ERROR(12,"手机号格式不正确"),
  EXIST_PHONENO_ERROR(1, "该手机号已被注册"),
  USER_NOT_EXIST_ERROR(2, "用户不存在"),
  SMS_ERROR_CODE(3, "短信验证码错误"),
  SMS_SEND_ERROR(4, "短信验证码错误!"),
  PHONE_HAS_BIND(5, "手机号已经被绑定"),
  SEQUEEUSER_HAS_EXISTS(6,"用户已存在"),
  USER_TOKEN_HAS_OVERDUE(7,"用户token已过期,请重新登陆"),
  RESULT_TOKEN_ERROR(8, "您未登录,请先登录!"),
  TOKEN_INVALID(9,"无效token"),
  ERRCODE_USER_LOGIN_OTHER_DEVICE(1001, "您的账号在另一台设备上登录,您被迫下线!");

  private final int code;
  private final String desc;

  RespCode(int code,String desc){
    this.code = code;
    this.desc = desc;
  }

  public int getCode(){
    return code;
  }
  public String getDesc(){
    return desc;
  }


}
