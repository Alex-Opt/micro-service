package com.ly.mt.cabinet.c.user.service;

import com.ly.mt.cabinet.c.user.entity.GzgUserLoginVo;
import com.ly.mt.cabinet.c.user.entity.GzgUserVo;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @Description 用户登录接口
 * @Author taoye
 */
public interface GzgLoginService {


    /**
     * @Description 手机号登录
     * @Author
     */
    ResponseJson mobileLogin(GzgUserLoginVo userLoginVo) throws Exception;


}