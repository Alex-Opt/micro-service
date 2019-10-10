package com.ly.mt.center.data.hd.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.hd.mapper.HdShopAttDetailMapper;
import com.ly.mt.center.data.hd.service.HdShopAttDetailService;
import com.ly.mt.center.data.hd.entity.HdShopAttDetail;
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
public class HdShopAttDetailServiceImpl extends BaseServiceImpl implements HdShopAttDetailService {
    private final static Logger LOGGER = LoggerFactory.getLogger(HdShopAttDetailServiceImpl.class);
    @Resource
    HdShopAttDetailMapper mapper;

    /**
     * @Description 保存HdShopAttDetail
     * @Author taoye
     */
    @Override
    public ResponseJson insertHdShopAttDetail(JSONObject jsonObject) {
        try {
            HdShopAttDetail hdShopAttDetail = JSONObject.toJavaObject(jsonObject, HdShopAttDetail.class);
            if (StringUtil.isEmpty(hdShopAttDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertHdShopAttDetail(hdShopAttDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdShopAttDetailServiceImpl.insertHdShopAttDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除HdShopAttDetail
     * @Author taoye
     */
    @Override
    public ResponseJson deleteHdShopAttDetail(JSONObject jsonObject) {
        try {
            HdShopAttDetail hdShopAttDetail = JSONObject.toJavaObject(jsonObject, HdShopAttDetail.class);
            if (StringUtil.isEmpty(hdShopAttDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteHdShopAttDetail(hdShopAttDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdShopAttDetailServiceImpl.deleteHdShopAttDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新HdShopAttDetail
     * @Author taoye
     */
    @Override
    public ResponseJson updateHdShopAttDetail(JSONObject jsonObject) {
        try {
            HdShopAttDetail hdShopAttDetail = JSONObject.toJavaObject(jsonObject, HdShopAttDetail.class);
            if (StringUtil.isEmpty(hdShopAttDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateHdShopAttDetail(hdShopAttDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdShopAttDetailServiceImpl.updateHdShopAttDetailById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询HdShopAttDetail
     * @Author taoye
     */
    @Override
    public ResponseJson getHdShopAttDetail(JSONObject jsonObject) {
        try {
            HdShopAttDetail hdShopAttDetail = JSONObject.toJavaObject(jsonObject, HdShopAttDetail.class);
            if (StringUtil.isEmpty(hdShopAttDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            hdShopAttDetail = mapper.getHdShopAttDetail(hdShopAttDetail);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, hdShopAttDetail);
        } catch (Exception e) {
            LOGGER.error("HdShopAttDetailServiceImpl.getHdShopAttDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}