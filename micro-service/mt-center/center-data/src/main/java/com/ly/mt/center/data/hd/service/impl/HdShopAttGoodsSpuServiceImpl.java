package com.ly.mt.center.data.hd.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.hd.mapper.HdShopAttGoodsSpuMapper;
import com.ly.mt.center.data.hd.service.HdShopAttGoodsSpuService;
import com.ly.mt.center.data.hd.entity.HdShopAttGoodsSpu;
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
public class HdShopAttGoodsSpuServiceImpl extends BaseServiceImpl implements HdShopAttGoodsSpuService {
    private final static Logger LOGGER = LoggerFactory.getLogger(HdShopAttGoodsSpuServiceImpl.class);
    @Resource
    HdShopAttGoodsSpuMapper mapper;

    /**
     * @Description 保存HdShopAttGoodsSpu
     * @Author taoye
     */
    @Override
    public ResponseJson insertHdShopAttGoodsSpu(JSONObject jsonObject) {
        try {
            HdShopAttGoodsSpu hdShopAttGoodsSpu = JSONObject.toJavaObject(jsonObject, HdShopAttGoodsSpu.class);
            if (StringUtil.isEmpty(hdShopAttGoodsSpu.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertHdShopAttGoodsSpu(hdShopAttGoodsSpu);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdShopAttGoodsSpuServiceImpl.insertHdShopAttGoodsSpu出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除HdShopAttGoodsSpu
     * @Author taoye
     */
    @Override
    public ResponseJson deleteHdShopAttGoodsSpu(JSONObject jsonObject) {
        try {
            HdShopAttGoodsSpu hdShopAttGoodsSpu = JSONObject.toJavaObject(jsonObject, HdShopAttGoodsSpu.class);
            if (StringUtil.isEmpty(hdShopAttGoodsSpu.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteHdShopAttGoodsSpu(hdShopAttGoodsSpu);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdShopAttGoodsSpuServiceImpl.deleteHdShopAttGoodsSpu出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新HdShopAttGoodsSpu
     * @Author taoye
     */
    @Override
    public ResponseJson updateHdShopAttGoodsSpu(JSONObject jsonObject) {
        try {
            HdShopAttGoodsSpu hdShopAttGoodsSpu = JSONObject.toJavaObject(jsonObject, HdShopAttGoodsSpu.class);
            if (StringUtil.isEmpty(hdShopAttGoodsSpu.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateHdShopAttGoodsSpu(hdShopAttGoodsSpu);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdShopAttGoodsSpuServiceImpl.updateHdShopAttGoodsSpuById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询HdShopAttGoodsSpu
     * @Author taoye
     */
    @Override
    public ResponseJson getHdShopAttGoodsSpu(JSONObject jsonObject) {
        try {
            HdShopAttGoodsSpu hdShopAttGoodsSpu = JSONObject.toJavaObject(jsonObject, HdShopAttGoodsSpu.class);
            if (StringUtil.isEmpty(hdShopAttGoodsSpu.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            hdShopAttGoodsSpu = mapper.getHdShopAttGoodsSpu(hdShopAttGoodsSpu);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, hdShopAttGoodsSpu);
        } catch (Exception e) {
            LOGGER.error("HdShopAttGoodsSpuServiceImpl.getHdShopAttGoodsSpu出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}