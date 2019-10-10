package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgBannerPictureMapper;
import com.ly.mt.center.data.gzg.service.GzgBannerPictureService;
import com.ly.mt.center.data.gzg.entity.GzgBannerPicture;
import com.ly.mt.base.service.impl.BaseServiceImpl;
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
public class GzgBannerPictureServiceImpl extends BaseServiceImpl implements GzgBannerPictureService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgBannerPictureServiceImpl.class);
    @Resource
    GzgBannerPictureMapper mapper;

    /**
     * @Description 保存GzgBannerPicture
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgBannerPicture(JSONObject jsonObject) {
        try {
            GzgBannerPicture gzgBannerPicture = JSONObject.toJavaObject(jsonObject, GzgBannerPicture.class);
            if (StringUtil.isEmpty(gzgBannerPicture.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgBannerPicture(gzgBannerPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgBannerPictureServiceImpl.insertGzgBannerPicture出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgBannerPicture
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgBannerPicture(JSONObject jsonObject) {
        try {
            GzgBannerPicture gzgBannerPicture = JSONObject.toJavaObject(jsonObject, GzgBannerPicture.class);
            if (StringUtil.isEmpty(gzgBannerPicture.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgBannerPicture(gzgBannerPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgBannerPictureServiceImpl.deleteGzgBannerPicture出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgBannerPicture
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgBannerPicture(JSONObject jsonObject) {
        try {
            GzgBannerPicture gzgBannerPicture = JSONObject.toJavaObject(jsonObject, GzgBannerPicture.class);
            if (StringUtil.isEmpty(gzgBannerPicture.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgBannerPicture(gzgBannerPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgBannerPictureServiceImpl.updateGzgBannerPictureById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgBannerPicture
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgBannerPicture(JSONObject jsonObject) {
        try {
            GzgBannerPicture gzgBannerPicture = JSONObject.toJavaObject(jsonObject, GzgBannerPicture.class);
//            if (StringUtil.isEmpty(gzgBannerPicture.getId())) {
//                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
//            }
          List<GzgBannerPicture> gzgBannerPictureList = mapper.getGzgBannerPicture(gzgBannerPicture);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgBannerPictureList);
        } catch (Exception e) {
            LOGGER.error("GzgBannerPictureServiceImpl.getGzgBannerPicture出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}