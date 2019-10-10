package com.ly.mt.center.data.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.shop.mapper.ShopGradeMapper;
import com.ly.mt.center.data.shop.service.ShopGradeService;
import com.ly.mt.center.data.shop.entity.ShopGrade;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class ShopGradeServiceImpl extends BaseServiceImpl implements ShopGradeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopGradeServiceImpl.class);
    @Resource
    ShopGradeMapper mapper;

    /**
     * @Description 保存ShopGrade
     * @Author taoye
     */
    @Override
    public ResponseJson insertShopGrade(JSONObject jsonObject) {
        try {
            ShopGrade shopGrade = JSONObject.toJavaObject(jsonObject, ShopGrade.class);
            if (StringUtil.isEmpty(shopGrade.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShopGrade(shopGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopGradeServiceImpl.insertShopGrade出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShopGrade
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShopGrade(JSONObject jsonObject) {
        try {
            ShopGrade shopGrade = JSONObject.toJavaObject(jsonObject, ShopGrade.class);
            if (StringUtil.isEmpty(shopGrade.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShopGrade(shopGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopGradeServiceImpl.deleteShopGrade出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShopGrade
     * @Author taoye
     */
    @Override
    public ResponseJson updateShopGrade(JSONObject jsonObject) {
        try {
            ShopGrade shopGrade = JSONObject.toJavaObject(jsonObject, ShopGrade.class);
            if (StringUtil.isEmpty(shopGrade.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShopGrade(shopGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShopGradeServiceImpl.updateShopGradeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShopGrade
     * @Author taoye
     */
    @Override
    public ResponseJson getShopGrade(JSONObject jsonObject) {
        try {
            ShopGrade shopGrade = JSONObject.toJavaObject(jsonObject, ShopGrade.class);
            if (StringUtil.isEmpty(shopGrade.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shopGrade = mapper.getShopGrade(shopGrade);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shopGrade);
        } catch (Exception e) {
            LOGGER.error("ShopGradeServiceImpl.getShopGrade出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}