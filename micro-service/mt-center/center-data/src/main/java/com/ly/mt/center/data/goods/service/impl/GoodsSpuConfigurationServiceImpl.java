package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.goods.mapper.GoodsSpuConfigurationMapper;
import com.ly.mt.center.data.goods.service.GoodsSpuConfigurationService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @author zhanglifeng
 * @date 2019-09-16
 */
@Service
public class GoodsSpuConfigurationServiceImpl extends BaseServiceImpl implements GoodsSpuConfigurationService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsSpuInfoServiceImpl.class);

    @Resource
    GoodsSpuConfigurationMapper goodsSpuConfigurationMapper;

    @Override
    public ResponseJson querySpuConfigGoods(JSONObject jsonObject) {
        Integer system_user_type = jsonObject.getInteger("system_user_type");
        try {
            List<String> spuIds = goodsSpuConfigurationMapper.querySpuIdListBySystemUserType(system_user_type);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, spuIds);
        } catch (Exception e) {
            LOGGER.info("==========GoodsSpuConfigurationServiceImpl.querySpuConfigGoods查询方法异常==================：{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
