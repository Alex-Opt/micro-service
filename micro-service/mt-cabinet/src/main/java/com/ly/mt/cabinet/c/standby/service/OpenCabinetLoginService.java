package com.ly.mt.cabinet.c.standby.service;

import com.ly.mt.cabinet.c.standby.entity.OpenCabinetLoginRequestVo;
import com.ly.mt.core.base.entity.ResponseJson;

public interface OpenCabinetLoginService {


    /**
     * H5运维人员打开设备--手机号登陆
     *
     * @param body
     * @return
     */
    ResponseJson loginByPhone(OpenCabinetLoginRequestVo body);

    /**
     * 登出
     *
     * @return
     */
    ResponseJson loginOut(String phone);
}
