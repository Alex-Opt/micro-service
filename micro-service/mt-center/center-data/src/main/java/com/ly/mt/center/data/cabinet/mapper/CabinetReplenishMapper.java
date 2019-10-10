package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.bo.CabinetReplenishListBo;
import com.ly.mt.center.data.cabinet.bo.GoodsInfoNameBo;
import com.ly.mt.center.data.cabinet.entity.CabinetReplenish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 格子柜B-补货单-持久层
 * @author zhanglifeng
 * @date 2019-08-27
 */
@Mapper
public interface CabinetReplenishMapper {
    /**
     * 新增一条数据
     * @param cabinetReplenish
     * @return
     */
    int insertOne(CabinetReplenish cabinetReplenish);

    /**
     * 更新补货订单
     * @param cabinetReplenish
     * @return
     */
    int updateCabinetReplenishById(CabinetReplenish cabinetReplenish);

    /**
     * 通过sku_id 查询spuName-skuName
     * @param id
     * @return
     */
    GoodsInfoNameBo getGoodsInfoNameBySkuId(@Param("id")String id);
    /**
     * 通过id查询补货订单详情
     * @param id
     * @return
     */
    CabinetReplenish getCabinetReplenishById(@Param("id")String id);

    /**
     * 查询补货列表
     * @param
     * @return
     */
    List<CabinetReplenishListBo> getReplenishList(@Param("userId")String userId,@Param("status")String status,@Param("orderType")String orderType);
}
