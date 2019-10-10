package com.ly.mt.center.data.hd.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.hd.mapper.HdShopAttOrderDetailMapper;
import com.ly.mt.center.data.hd.service.HdShopAttOrderDetailService;
import com.ly.mt.center.data.hd.entity.HdShopAttOrderDetail;
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
public class HdShopAttOrderDetailServiceImpl extends BaseServiceImpl implements HdShopAttOrderDetailService {
    private final static Logger LOGGER = LoggerFactory.getLogger(HdShopAttOrderDetailServiceImpl.class);
    @Resource
    HdShopAttOrderDetailMapper mapper;

    /**
     * @Description 保存HdShopAttOrderDetail
     * @Author taoye
     */
    @Override
    public ResponseJson insertHdShopAttOrderDetail(JSONObject jsonObject) {
        try {
            HdShopAttOrderDetail hdShopAttOrderDetail = JSONObject.toJavaObject(jsonObject, HdShopAttOrderDetail.class);
            if (StringUtil.isEmpty(hdShopAttOrderDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertHdShopAttOrderDetail(hdShopAttOrderDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdShopAttOrderDetailServiceImpl.insertHdShopAttOrderDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除HdShopAttOrderDetail
     * @Author taoye
     */
    @Override
    public ResponseJson deleteHdShopAttOrderDetail(JSONObject jsonObject) {
        try {
            HdShopAttOrderDetail hdShopAttOrderDetail = JSONObject.toJavaObject(jsonObject, HdShopAttOrderDetail.class);
            if (StringUtil.isEmpty(hdShopAttOrderDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteHdShopAttOrderDetail(hdShopAttOrderDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdShopAttOrderDetailServiceImpl.deleteHdShopAttOrderDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新HdShopAttOrderDetail
     * @Author taoye
     */
    @Override
    public ResponseJson updateHdShopAttOrderDetail(JSONObject jsonObject) {
        try {
            HdShopAttOrderDetail hdShopAttOrderDetail = JSONObject.toJavaObject(jsonObject, HdShopAttOrderDetail.class);
            if (StringUtil.isEmpty(hdShopAttOrderDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateHdShopAttOrderDetail(hdShopAttOrderDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdShopAttOrderDetailServiceImpl.updateHdShopAttOrderDetailById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询HdShopAttOrderDetail
     * @Author taoye
     */
    @Override
    public ResponseJson getHdShopAttOrderDetail(JSONObject jsonObject) {
        try {
            HdShopAttOrderDetail hdShopAttOrderDetail = JSONObject.toJavaObject(jsonObject, HdShopAttOrderDetail.class);
            if (StringUtil.isEmpty(hdShopAttOrderDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            hdShopAttOrderDetail = mapper.getHdShopAttOrderDetail(hdShopAttOrderDetail);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, hdShopAttOrderDetail);
        } catch (Exception e) {
            LOGGER.error("HdShopAttOrderDetailServiceImpl.getHdShopAttOrderDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}