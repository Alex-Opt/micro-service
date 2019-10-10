package com.ly.mt.center.data.wxshop.mapper;

import com.ly.mt.center.data.wxshop.entity.WxShop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WxShopMapper {
    /**
     * @Description 保存WxShop
     * @Author wanghongliang
     */
    void insertWxShop(WxShop wxShop);
    /**
     * @Description 查询WxShop
     * @Author wanghongliang
     */
    List<WxShop> getWxShopList(WxShop wxShop);
}