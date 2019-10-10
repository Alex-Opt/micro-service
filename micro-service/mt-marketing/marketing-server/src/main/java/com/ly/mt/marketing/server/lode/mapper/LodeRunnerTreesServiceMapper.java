package com.ly.mt.marketing.server.lode.mapper;

import com.ly.mt.core.common.entity.marketing.*;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface LodeRunnerTreesServiceMapper {

    /**
     *@Description   查询下级人员
     *@Author  zhuyh
     */
    List<LodeRunnerVo> getNextRunnerList(LodeRunnerTreesParamsVo vo);

    /**
     *@Description 查询商家进货数量
     *@Author  zhuyh
     */
    List<ShopPurchasesVo> selectShopPurchasesCount(List<Long> liveList);

    /**
     *@Description 查询商店的利润
     *@Author  zhuyh
     */
    List<ShopProfitsLogs> selectShopProfits(LodeRunnerProfitsParamsVo vo);

    /**
     *@Description  查询团队详情
     *@Author  zhuyh
     */
    LodeRunnerVo selectTeamDetails(LodeRunnerVo vo);

    /**
     *@Description 查询我的淘金收益
     *@Author  zhuyh
     */
    LodeRunnerProfitsParamsVo getMyLodeProfits(LodeRunnerProfitsParamsVo paramsVo);

    /**
     *@Description  查询我的淘金收益排名
     *@Author  zhuyh
     */
    Integer getLodeRankByMe(LodeRunnerProfitsParamsVo paramsVo);

    /**
     *@Description 查询淘金人数
     *@Author  zhuyh
     */
    Integer getLodeUserCount();
}