package com.ly.mt.center.data.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.share.mapper.ShareGoodsMapper;
import com.ly.mt.center.data.share.service.ShareGoodsService;
import com.ly.mt.center.data.share.entity.ShareGoods;
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
public class ShareGoodsServiceImpl extends BaseServiceImpl implements ShareGoodsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShareGoodsServiceImpl.class);
    @Resource
    ShareGoodsMapper mapper;

    /**
     * @Description 保存ShareGoods
     * @Author taoye
     */
    @Override
    public ResponseJson insertShareGoods(JSONObject jsonObject) {
        try {
            ShareGoods shareGoods = JSONObject.toJavaObject(jsonObject, ShareGoods.class);
            if (StringUtil.isEmpty(shareGoods.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShareGoods(shareGoods);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShareGoodsServiceImpl.insertShareGoods出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShareGoods
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShareGoods(JSONObject jsonObject) {
        try {
            ShareGoods shareGoods = JSONObject.toJavaObject(jsonObject, ShareGoods.class);
            if (StringUtil.isEmpty(shareGoods.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShareGoods(shareGoods);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShareGoodsServiceImpl.deleteShareGoods出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShareGoods
     * @Author taoye
     */
    @Override
    public ResponseJson updateShareGoods(JSONObject jsonObject) {
        try {
            ShareGoods shareGoods = JSONObject.toJavaObject(jsonObject, ShareGoods.class);
            if (StringUtil.isEmpty(shareGoods.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShareGoods(shareGoods);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShareGoodsServiceImpl.updateShareGoodsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShareGoods
     * @Author taoye
     */
    @Override
    public ResponseJson getShareGoods(JSONObject jsonObject) {
        try {
            ShareGoods shareGoods = JSONObject.toJavaObject(jsonObject, ShareGoods.class);
            if (StringUtil.isEmpty(shareGoods.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shareGoods = mapper.getShareGoods(shareGoods);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shareGoods);
        } catch (Exception e) {
            LOGGER.error("ShareGoodsServiceImpl.getShareGoods出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}