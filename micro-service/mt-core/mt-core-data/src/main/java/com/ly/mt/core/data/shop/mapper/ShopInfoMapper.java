package com.ly.mt.core.data.shop.mapper;

import com.ly.mt.core.data.shop.entity.ShopInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ShopInfo操作接口
 *
 * @author taoye
 */
@Mapper
public interface ShopInfoMapper {
    /**
     * 查询ShopInfo
     *
     * @param shopInfo 查询条件
     * @return ShopInfo
     * @author taoye
     */
    ShopInfo getShopInfo(ShopInfo shopInfo);

    /**
     * 查询List<ShopInfo>
     *
     * @param shopInfo 查询条件
     * @return List<ShopInfo>
     * @author taoye
     */
    List<ShopInfo> listShopInfo(ShopInfo shopInfo);
}