package com.ly.mt.home.tob.ladder.service;


import com.alibaba.fastjson.JSONObject;
import com.ly.mt.home.tob.ladder.vo.LadderVo;

import java.util.List;

/**
 * 阶梯价接口
 *
 * @author add by linan
 * @date 20190709
 */
public interface LadderService {

    /**
     * 阶梯价
     *
     * @param
     * @return
     */
    List<LadderVo> list();

    /**
     * 根据订单查询当前阶梯优惠
     *
     * @return
     */
    LadderVo getCurrentLadder(int num);

    /**
     * 获取商家累计阶梯优惠
     */
    JSONObject shopLadder();
}
