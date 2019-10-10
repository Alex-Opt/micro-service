package com.ly.mt.marketing.server.profits.mapper;

import com.ly.mt.core.common.entity.marketing.*;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *@Description 收益日志mapper
 *@Author  zhuyh
 */
@Mapper
public interface ShopProfitsLogsServiceMapper {

    /**
     *@Description 查询有奖励的订单
     *@Author  zhuyh
     */
    List<ShopProfitsLogsRewardOrderVo> getRewardOrder(ShopProfitsLogs vo);

    /**
     *@Description 查询奖励记录
     *@Author  zhuyh
     */
    List<ShopProfitsLogsVo> selectRewards(ShopProfitsLogsParamsVo vo);

    /**
     *@Description 查询奖励累计
     *@Author  zhuyh
     */
    ShopProfitsLogsVo selectSumRewards(ShopProfitsLogsParamsVo vo);
    /**
     *@Description 查询淘金记录
     *@Author  zhuyh
     */
    List<ShopProfitsLogsVo> selectLodes(ShopProfitsLogsParamsVo vo);

    /**
     *@Description 查询淘金累计
     *@Author  zhuyh
     */
    ShopProfitsLogsVo selectSumLodes(ShopProfitsLogsParamsVo vo);

    /**
     *@Description 查询周期统计
     *@Author  zhuyh
     */
    List<ShopProfitsLogsCycleVo> selectCycleList(ShopProfitsLogsParamsVo paramsVo);

    /**
     * 查询列表
     * @param paramsVo
     * @return
     */
    List<ShopProfitsLogsVo> selectList(ShopProfitsLogsParamsVo paramsVo);
}
