package com.ly.mt.core.common.constant.gzg;

public interface Constant {
    String USER_LOGIN_TOKEN_CACHE_KEY_PRE = "user_login_token_key_";
    String USER_REGISTER_DYNAMIC_CODE_PRE = "user_register_dynamic_code_pre_";
    String USER_LOGIN_DYNAMIC_CODE_PRE = "user_register_dynamic_code_pre_";
    String BASE64KEY = "ly_gzg_token_key";
    String ORDER_GRAB_LOCK_KEY = "ly_gzg_order_grab_lock_";
    String REPLENISH_PROCESS_DATA="ly_gzg_b_replenish_process_data_";

    //正则表达式规则
    String emailRule = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    String phoneRule = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
}
