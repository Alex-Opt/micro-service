package com.ly.mt.center.data.share.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.share.mapper.ShareGoodsPictureDelMapper;
import com.ly.mt.center.data.share.service.ShareGoodsPictureDelService;
import com.ly.mt.center.data.share.entity.ShareGoodsPictureDel;
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
public class ShareGoodsPictureDelServiceImpl extends BaseServiceImpl implements ShareGoodsPictureDelService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShareGoodsPictureDelServiceImpl.class);
    @Resource
    ShareGoodsPictureDelMapper mapper;

    /**
     * @Description 保存ShareGoodsPictureDel
     * @Author taoye
     */
    @Override
    public ResponseJson insertShareGoodsPictureDel(JSONObject jsonObject) {
        try {
            ShareGoodsPictureDel shareGoodsPictureDel = JSONObject.toJavaObject(jsonObject, ShareGoodsPictureDel.class);
            if (StringUtil.isEmpty(shareGoodsPictureDel.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertShareGoodsPictureDel(shareGoodsPictureDel);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShareGoodsPictureDelServiceImpl.insertShareGoodsPictureDel出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ShareGoodsPictureDel
     * @Author taoye
     */
    @Override
    public ResponseJson deleteShareGoodsPictureDel(JSONObject jsonObject) {
        try {
            ShareGoodsPictureDel shareGoodsPictureDel = JSONObject.toJavaObject(jsonObject, ShareGoodsPictureDel.class);
            if (StringUtil.isEmpty(shareGoodsPictureDel.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteShareGoodsPictureDel(shareGoodsPictureDel);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShareGoodsPictureDelServiceImpl.deleteShareGoodsPictureDel出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ShareGoodsPictureDel
     * @Author taoye
     */
    @Override
    public ResponseJson updateShareGoodsPictureDel(JSONObject jsonObject) {
        try {
            ShareGoodsPictureDel shareGoodsPictureDel = JSONObject.toJavaObject(jsonObject, ShareGoodsPictureDel.class);
            if (StringUtil.isEmpty(shareGoodsPictureDel.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateShareGoodsPictureDel(shareGoodsPictureDel);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ShareGoodsPictureDelServiceImpl.updateShareGoodsPictureDelById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ShareGoodsPictureDel
     * @Author taoye
     */
    @Override
    public ResponseJson getShareGoodsPictureDel(JSONObject jsonObject) {
        try {
            ShareGoodsPictureDel shareGoodsPictureDel = JSONObject.toJavaObject(jsonObject, ShareGoodsPictureDel.class);
            if (StringUtil.isEmpty(shareGoodsPictureDel.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            shareGoodsPictureDel = mapper.getShareGoodsPictureDel(shareGoodsPictureDel);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, shareGoodsPictureDel);
        } catch (Exception e) {
            LOGGER.error("ShareGoodsPictureDelServiceImpl.getShareGoodsPictureDel出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}