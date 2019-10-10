package com.ly.mt.mall.h5.punchcard.service;


import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 用户打卡操作
 * @author  ypmu
 * @date 20190530
 */
public interface PunchCardService {

    /**
     * 用户打卡
     * @return
     * @throws Exception
     */
    ResponseJson punchCard() throws Exception;

    /**
     * 查询用户打卡记录
     * @return
     * @throws Exception
     */
    ResponseJson queryPointDataByUserId() throws Exception;
}
