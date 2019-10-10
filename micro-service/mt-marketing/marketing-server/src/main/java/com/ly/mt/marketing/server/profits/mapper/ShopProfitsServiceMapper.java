package com.ly.mt.marketing.server.profits.mapper;

import com.ly.mt.core.common.entity.marketing.ShopProfits;
import com.ly.mt.core.common.entity.marketing.ShopProfitsDetailsVO;
import com.ly.mt.core.common.entity.marketing.ShopProfitsSumVo;
import com.ly.mt.core.common.entity.marketing.ShopProfitsTopVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopProfitsServiceMapper {

    /**
     *@Description 根据用户id查询总收益
     *@Author  zhuyh
     */
    public ShopProfitsSumVo selectSumProfitsByUId(ShopProfits vo);

    /**
     *@Description 查询抢单成交额排行
     *@Author  zhuyh
     */
    List<ShopProfitsTopVo> selectGrabTop(Integer topValue);

    /**
     *@Description 查询抢单奖励金额排行
     *@Author  zhuyh
     */
    List<ShopProfitsTopVo> selectRewardTop(Integer topValue);


    /**
     *@Description   查询抢单奖励收益详情
     *@Author  zhuyh
     */
    ShopProfitsDetailsVO getRewardProfitsDetails(Long userId);

    /**
     *@Description 查询抢单收益详情详情
     *@Author  zhuyh
     */
    ShopProfitsDetailsVO getGrabProfitsDetails(Long userId);

    /**
     *@Description 查询专属订单收益详情
     *@Author  zhuyh
     */
    ShopProfitsDetailsVO getOrderProfitsDetails(Long userId);

    /**
     *@Description 查询淘金收益详情
     *@Author  zhuyh
     */
    ShopProfitsDetailsVO getLodeProfitsDetails(Long userId);

    /**
     *@Description 查询正在抢单赚取的人数
     *@Author  zhuyh
     */
    Integer getShopOrderUserTotal();


    /**
     *@Description 查询抢单奖励金额排行
     *@Author  zhuyh
     */
    List<ShopProfitsTopVo> selectLodeTop(Integer topValue);
}
