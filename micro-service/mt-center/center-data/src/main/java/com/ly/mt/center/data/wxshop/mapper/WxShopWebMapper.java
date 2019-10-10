package com.ly.mt.center.data.wxshop.mapper;

import com.ly.mt.center.data.wxshop.entity.WxShop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WxShopWebMapper {

    /**
     * @Description 删除WxShop
     * @Author wanghongliang
     */
    int deleteWxShop(WxShop wxShop);

    /**
     * @Description 更新WxShop
     * @Author wanghongliang
     */
    int updateWxShop(WxShop wxShop);

    /**
     * @Description 查询WxShop
     * @Author wanghongliang
     */
    List<WxShop> getWxShopList(WxShop wxShop);

    /**
     * @Description 获取WxShop
     * @Author wanghongliang
     */
    WxShop getWxShop(@Param("shop_id") String shop_id);
}