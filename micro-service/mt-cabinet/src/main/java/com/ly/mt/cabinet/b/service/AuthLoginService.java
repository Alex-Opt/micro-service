package com.ly.mt.cabinet.b.service;

import com.ly.mt.cabinet.b.common.request.LoginRequestBody;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.core.base.entity.ResponseJson;

public interface AuthLoginService {


    /**
     * 手机号登陆
     * @param body
     * @return
     */
    ResponseJson loginByPhone(LoginRequestBody body);

    /**
     * 登出
     * @return
     */
    Resp loginOut(String phone);
}
