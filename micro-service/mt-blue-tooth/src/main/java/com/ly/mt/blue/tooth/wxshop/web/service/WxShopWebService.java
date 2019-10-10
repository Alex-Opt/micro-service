package com.ly.mt.blue.tooth.wxshop.web.service;

import com.ly.mt.blue.tooth.wxshop.app.vo.WxShopH5Vo;
import com.ly.mt.blue.tooth.wxshop.web.vo.WxShopWebVo;
import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @program: mt-blue-tooth
* @description: 微信Web店铺操作service
* @author: wanghongliang
* @create: 2019/8/2 11:00
**/
public interface WxShopWebService {
    /**
     * 获取店铺列表
     * @throws Exception
     */
    List<WxShopWebVo> getWxShopList(String authStatus,String cityName,String shopName,String shopAddress) throws Exception;

    /**
     *
     * 授权店铺
     * @throws Exception
     */
    int authWxShop(String shopId) throws Exception;

    /**
     *
     * 拒绝店铺
     * @throws Exception
     */
    int noAuthWxShop(String shopId) throws Exception;

    /**
     *
     * 更新店铺信息
     * @throws Exception
     */
    int updateWxShop(WxShopWebVo wxShopWebVo) throws Exception;

    /**
     *
     * 删除店铺
     * @throws Exception
     */
    int deleteWxShop(String shopId) throws Exception;

    /**
     * 获取单个店铺信息
     * @throws Exception
     */
    WxShopWebVo getWxShop(String shopId) throws Exception;
}