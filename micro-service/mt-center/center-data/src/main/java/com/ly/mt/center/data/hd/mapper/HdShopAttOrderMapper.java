package com.ly.mt.center.data.hd.mapper;

import com.ly.mt.center.data.hd.entity.HdShopAttOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HdShopAttOrderMapper {
    /**
     * @Description 保存HdShopAttOrder
     * @Author taoye
     */
    void insertHdShopAttOrder(HdShopAttOrder hdShopAttOrder);

    /**
     * @Description 删除HdShopAttOrder
     * @Author taoye
     */
    void deleteHdShopAttOrder(HdShopAttOrder hdShopAttOrder);

    /**
     * @Description 更新HdShopAttOrder
     * @Author taoye
     */
    int updateHdShopAttOrder(HdShopAttOrder hdShopAttOrder);

    /**
     * @Description 查询HdShopAttOrder
     * @Author taoye
     */
    HdShopAttOrder getHdShopAttOrder(HdShopAttOrder hdShopAttOrder);
}