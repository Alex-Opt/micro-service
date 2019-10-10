package com.ly.mt.center.data.hd.mapper;

import com.ly.mt.center.data.hd.entity.HdShopAttOrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HdShopAttOrderDetailMapper {
    /**
     * @Description 保存HdShopAttOrderDetail
     * @Author taoye
     */
    void insertHdShopAttOrderDetail(HdShopAttOrderDetail hdShopAttOrderDetail);

    /**
     * @Description 删除HdShopAttOrderDetail
     * @Author taoye
     */
    void deleteHdShopAttOrderDetail(HdShopAttOrderDetail hdShopAttOrderDetail);

    /**
     * @Description 更新HdShopAttOrderDetail
     * @Author taoye
     */
    int updateHdShopAttOrderDetail(HdShopAttOrderDetail hdShopAttOrderDetail);

    /**
     * @Description 查询HdShopAttOrderDetail
     * @Author taoye
     */
    HdShopAttOrderDetail getHdShopAttOrderDetail(HdShopAttOrderDetail hdShopAttOrderDetail);
}