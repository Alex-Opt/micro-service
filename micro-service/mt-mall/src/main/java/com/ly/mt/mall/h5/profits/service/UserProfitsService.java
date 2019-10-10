package com.ly.mt.mall.h5.profits.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @Description: 用户收益接口
 * @Author: zhuyh
 */
public interface UserProfitsService {

    /**
     * @Description: 获取滚屏
     * @Author: zhuyh
     */
    ResponseJson getCashScorolling();

    /**
     * @Description: 获取赚取排行
     * @Author: zhuyh
     */
    ResponseJson getRank();


    /**
     * @Description: 查询某人的赚钱详情
     * @Author: zhuyh
     */
    ResponseJson getDetails();

    /**
     * @Description: 查询赚钱日志记录
     * @Author: zhuyh
     */
    ResponseJson getLogs(Integer page, Integer rows, Integer type);
}
