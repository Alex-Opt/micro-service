package com.ly.mt.center.data.wxshop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.wxshop.entity.WxShop;
import com.ly.mt.center.data.wxshop.mapper.WxShopMapper;
import com.ly.mt.center.data.wxshop.service.WxShopService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_WX_SHOP;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class WxShopServiceImpl extends BaseServiceImpl implements WxShopService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WxShopServiceImpl.class);
    @Resource
    WxShopMapper mapper;

    /**
     * @Description 保存WxShop
     * @Author wanghongliang
     */
    @Override
    public ResponseJson insertWxShop(JSONObject jsonObject) {
        try {
            WxShop wxShop = JSONObject.toJavaObject(jsonObject, WxShop.class);
            wxShop.setShop_id(SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_WX_SHOP));
            wxShop.setCreate_time(DateUtil.getNowTimeStr());
            wxShop.setUpdate_time(DateUtil.getNowTimeStr());
            mapper.insertWxShop(wxShop);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("WxShopServiceImpl.insertWxShop出错:", e);
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
}