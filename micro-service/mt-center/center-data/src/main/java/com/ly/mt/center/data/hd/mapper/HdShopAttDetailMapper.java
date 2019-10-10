package com.ly.mt.center.data.hd.mapper;

import com.ly.mt.center.data.hd.entity.HdShopAttDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HdShopAttDetailMapper {
    /**
     * @Description 保存HdShopAttDetail
     * @Author taoye
     */
    void insertHdShopAttDetail(HdShopAttDetail hdShopAttDetail);

    /**
     * @Description 删除HdShopAttDetail
     * @Author taoye
     */
    void deleteHdShopAttDetail(HdShopAttDetail hdShopAttDetail);

    /**
     * @Description 更新HdShopAttDetail
     * @Author taoye
     */
    int updateHdShopAttDetail(HdShopAttDetail hdShopAttDetail);

    /**
     * @Description 查询HdShopAttDetail
     * @Author taoye
     */
    HdShopAttDetail getHdShopAttDetail(HdShopAttDetail hdShopAttDetail);
}