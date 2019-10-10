package com.ly.mt.center.data.wxshop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.wxshop.entity.WxShop;
import com.ly.mt.center.data.wxshop.mapper.WxShopWebMapper;
import com.ly.mt.center.data.wxshop.service.WxShopWebService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class WxShopWebServiceImpl extends BaseServiceImpl implements WxShopWebService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WxShopWebServiceImpl.class);
    @Resource
    WxShopWebMapper mapper;

    /**
     * @Description 删除WxShop
     * @Author wanghongliang
     */
    @Override
    public ResponseJson deleteWxShop(JSONObject jsonObject) {
        try {
            WxShop wxShop = JSONObject.toJavaObject(jsonObject, WxShop.class);
            if (StringUtil.isEmpty(wxShop.getShop_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            int i = mapper.deleteWxShop(wxShop);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("WxShopServiceImpl.deleteWxShop出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新WxShop
     * @Author wanghongliang
     */
    @Override
    public ResponseJson updateWxShop(JSONObject jsonObject) {
        try {
            WxShop wxShop = JSONObject.toJavaObject(jsonObject, WxShop.class);
            if (StringUtil.isEmpty(wxShop.getShop_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            int i = mapper.updateWxShop(wxShop);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("WxShopServiceImpl.updateWxShopById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询WxShop
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getWxShopList(JSONObject jsonObject) {
        try {
            WxShop wxShop = JSONObject.toJavaObject(jsonObject, WxShop.class);
            List<WxShop> wxShopList = mapper.getWxShopList(wxShop);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, wxShopList);
        } catch (Exception e) {
            LOGGER.error("WxShopServiceImpl.getWxShop出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 获取单个WxShop对象
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getWxShop(JSONObject jsonObject) {
        try {
            String shopId = String.valueOf(jsonObject.get("shop_id"));//获取shopId
            WxShop wxShop = mapper.getWxShop(shopId);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, wxShop);
        } catch (Exception e) {
            LOGGER.error("WxShopServiceImpl.getWxShop出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}