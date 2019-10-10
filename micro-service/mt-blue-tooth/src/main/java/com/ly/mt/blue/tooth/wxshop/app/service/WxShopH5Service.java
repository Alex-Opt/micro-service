package com.ly.mt.blue.tooth.wxshop.app.service;

import com.ly.mt.blue.tooth.wxshop.app.vo.WxShopH5Vo;
import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.web.multipart.MultipartFile;

/**
* @program: mt-blue-tooth
* @description: 微信小程序店铺操作service
* @author: wanghongliang
* @create: 2019/8/2 11:00
**/
public interface WxShopH5Service {

    /**
     * 获取微信公众号签名
     * @throws Exception
     */
    ResponseJson getWxSignature(String requestUrl) throws Exception;
    /**
     * 获取店铺列表
     * @throws Exception
     */
    ResponseJson getWxShopList(String shopAddress,String coordinate) throws Exception;
    /**
     * 新增店铺信息
     * @throws Exception
     */
    ResponseJson addWxShop(WxShopH5Vo wxShopH5Vo) throws Exception;

    /**
     * 上传门店图片logo
     * @throws Exception
     */
    ResponseJson uploadWxShopLogo(MultipartFile file) throws Exception;

}