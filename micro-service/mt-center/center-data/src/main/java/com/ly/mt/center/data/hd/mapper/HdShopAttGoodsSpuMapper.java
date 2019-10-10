package com.ly.mt.center.data.hd.mapper;

import com.ly.mt.center.data.hd.entity.HdShopAttGoodsSpu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HdShopAttGoodsSpuMapper {
    /**
     * @Description 保存HdShopAttGoodsSpu
     * @Author taoye
     */
    void insertHdShopAttGoodsSpu(HdShopAttGoodsSpu hdShopAttGoodsSpu);

    /**
     * @Description 删除HdShopAttGoodsSpu
     * @Author taoye
     */
    void deleteHdShopAttGoodsSpu(HdShopAttGoodsSpu hdShopAttGoodsSpu);

    /**
     * @Description 更新HdShopAttGoodsSpu
     * @Author taoye
     */
    int updateHdShopAttGoodsSpu(HdShopAttGoodsSpu hdShopAttGoodsSpu);

    /**
     * @Description 查询HdShopAttGoodsSpu
     * @Author taoye
     */
    HdShopAttGoodsSpu getHdShopAttGoodsSpu(HdShopAttGoodsSpu hdShopAttGoodsSpu);
}