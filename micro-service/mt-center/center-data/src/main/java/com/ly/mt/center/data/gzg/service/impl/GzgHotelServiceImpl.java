package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgHotelMapper;
import com.ly.mt.center.data.gzg.service.GzgHotelService;
import com.ly.mt.center.data.gzg.entity.GzgHotel;
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
public class GzgHotelServiceImpl extends BaseServiceImpl implements GzgHotelService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgHotelServiceImpl.class);
    @Resource
    GzgHotelMapper mapper;

    /**
     * @Description 保存GzgHotel
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgHotel(JSONObject jsonObject) {
        try {
            GzgHotel gzgHotel = JSONObject.toJavaObject(jsonObject, GzgHotel.class);
            if (StringUtil.isEmpty(gzgHotel.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgHotel(gzgHotel);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgHotelServiceImpl.insertGzgHotel出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgHotel
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgHotel(JSONObject jsonObject) {
        try {
            GzgHotel gzgHotel = JSONObject.toJavaObject(jsonObject, GzgHotel.class);
            if (StringUtil.isEmpty(gzgHotel.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgHotel(gzgHotel);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgHotelServiceImpl.deleteGzgHotel出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgHotel
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgHotel(JSONObject jsonObject) {
        try {
            GzgHotel gzgHotel = JSONObject.toJavaObject(jsonObject, GzgHotel.class);
            if (StringUtil.isEmpty(gzgHotel.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgHotel(gzgHotel);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgHotelServiceImpl.updateGzgHotelById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgHotel
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgHotel(JSONObject jsonObject) {
        try {
            GzgHotel gzgHotel = JSONObject.toJavaObject(jsonObject, GzgHotel.class);
            if (StringUtil.isEmpty(gzgHotel.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgHotel = mapper.getGzgHotel(gzgHotel);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgHotel);
        } catch (Exception e) {
            LOGGER.error("GzgHotelServiceImpl.getGzgHotel出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}