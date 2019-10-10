package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.gzg.mapper.GzgSuitBarcodeInfoMapper;
import com.ly.mt.center.data.gzg.service.GzgSuitBarcodeInfoService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 格子柜六号格子的商品套装对应的商品明细操作
 *
 * @author zhanglifeng
 * @date 2019-08-27
 */
@Service
public class GzgSuitBarcodeInfoServiceImpl extends BaseServiceImpl implements GzgSuitBarcodeInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgSuitBarcodeInfoServiceImpl.class);

    @Resource
    private GzgSuitBarcodeInfoMapper gzgPlaidSetBarcodeInfoMapper;

    /**
     * 根据套装sku_id查询套装下的商品信息
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson queryBySuitSpuId(JSONObject jsonObject) {
        Long sku_id = jsonObject.getLong("sku_id");
        try {
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgPlaidSetBarcodeInfoMapper.queryBySuitSpuId(sku_id));
        } catch (Exception e) {
            LOGGER.error("GzgPlaidSetBarcodeInfoServiceImpl.queryBySuitSkuId:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
