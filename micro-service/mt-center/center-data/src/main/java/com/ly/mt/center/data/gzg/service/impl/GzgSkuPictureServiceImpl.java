package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgSkuPictureMapper;
import com.ly.mt.center.data.gzg.service.GzgSkuPictureService;
import com.ly.mt.center.data.gzg.entity.GzgSkuPicture;
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
public class GzgSkuPictureServiceImpl extends BaseServiceImpl implements GzgSkuPictureService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgSkuPictureServiceImpl.class);
    @Resource
    GzgSkuPictureMapper mapper;

    /**
     * @Description 保存GzgSkuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgSkuPicture(JSONObject jsonObject) {
        try {
            GzgSkuPicture gzgSkuPicture = JSONObject.toJavaObject(jsonObject, GzgSkuPicture.class);
            if (StringUtil.isEmpty(gzgSkuPicture.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgSkuPicture(gzgSkuPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgSkuPictureServiceImpl.insertGzgSkuPicture出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgSkuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgSkuPicture(JSONObject jsonObject) {
        try {
            GzgSkuPicture gzgSkuPicture = JSONObject.toJavaObject(jsonObject, GzgSkuPicture.class);
            if (StringUtil.isEmpty(gzgSkuPicture.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgSkuPicture(gzgSkuPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgSkuPictureServiceImpl.deleteGzgSkuPicture出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgSkuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgSkuPicture(JSONObject jsonObject) {
        try {
            GzgSkuPicture gzgSkuPicture = JSONObject.toJavaObject(jsonObject, GzgSkuPicture.class);
            if (StringUtil.isEmpty(gzgSkuPicture.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgSkuPicture(gzgSkuPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgSkuPictureServiceImpl.updateGzgSkuPictureById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgSkuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgSkuPicture(JSONObject jsonObject) {
        try {
            GzgSkuPicture gzgSkuPicture = JSONObject.toJavaObject(jsonObject, GzgSkuPicture.class);
            if (gzgSkuPicture == null) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgSkuPicture = mapper.getGzgSkuPicture(gzgSkuPicture);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgSkuPicture);
        } catch (Exception e) {
            LOGGER.error("GzgSkuPictureServiceImpl.getGzgSkuPicture出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


}