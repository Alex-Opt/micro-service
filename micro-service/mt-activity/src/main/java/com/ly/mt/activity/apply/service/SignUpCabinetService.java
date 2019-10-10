package com.ly.mt.activity.apply.service;

import com.ly.mt.activity.apply.vo.SignUpCabinet;
import com.ly.mt.core.base.entity.ResponseJson;

public interface SignUpCabinetService {
    /**
     * @Description 发送动态验证码
     * @Author taoye
     */
    ResponseJson sendDynamicCode(String mobile);

    /**
     * @Description 保存报名信息
     * @Author taoye
     */
    ResponseJson saveSignUpInfo(SignUpCabinet cabinet);
}