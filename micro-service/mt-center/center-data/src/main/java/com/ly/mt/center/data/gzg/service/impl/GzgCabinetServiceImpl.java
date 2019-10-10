package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgCabinetMapper;
import com.ly.mt.center.data.gzg.service.GzgCabinetService;
import com.ly.mt.center.data.gzg.entity.GzgCabinet;
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
public class GzgCabinetServiceImpl extends BaseServiceImpl implements GzgCabinetService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgCabinetServiceImpl.class);
    @Resource
    GzgCabinetMapper mapper;

    /**
     * @Description 保存GzgCabinet
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgCabinet(JSONObject jsonObject) {
        try {
            GzgCabinet gzgCabinet = JSONObject.toJavaObject(jsonObject, GzgCabinet.class);
            if (StringUtil.isEmpty(gzgCabinet.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgCabinet(gzgCabinet);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCabinetServiceImpl.insertGzgCabinet出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgCabinet
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgCabinet(JSONObject jsonObject) {
        try {
            GzgCabinet gzgCabinet = JSONObject.toJavaObject(jsonObject, GzgCabinet.class);
            if (StringUtil.isEmpty(gzgCabinet.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgCabinet(gzgCabinet);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCabinetServiceImpl.deleteGzgCabinet出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgCabinet
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgCabinet(JSONObject jsonObject) {
        try {
            GzgCabinet gzgCabinet = JSONObject.toJavaObject(jsonObject, GzgCabinet.class);
            if (StringUtil.isEmpty(gzgCabinet.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgCabinet(gzgCabinet);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCabinetServiceImpl.updateGzgCabinetById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgCabinet
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgCabinet(JSONObject jsonObject) {
        try {
            GzgCabinet gzgCabinet = JSONObject.toJavaObject(jsonObject, GzgCabinet.class);
//            if (StringUtil.isEmpty(gzgCabinet.getId())) {
//                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
//            }
            gzgCabinet = mapper.getGzgCabinet(gzgCabinet);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgCabinet);
        } catch (Exception e) {
            LOGGER.error("GzgCabinetServiceImpl.getGzgCabinet出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgCabinet
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgCabinetBySellNo(JSONObject jsonObject) {
        try {
            GzgCabinet gzgCabinet = JSONObject.toJavaObject(jsonObject, GzgCabinet.class);
            List<GzgCabinet> gzgCabinetList = mapper.getGzgCabinetBySellNo(gzgCabinet);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgCabinetList);
        } catch (Exception e) {
            LOGGER.error("GzgCabinetServiceImpl.getGzgCabinet出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}