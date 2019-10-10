package com.ly.mt.home.tob.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.goods.service.GoodsAttrValueService;
import com.ly.mt.home.tob.goods.vo.GoodsAttrValueVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品sku业务层
 */
@Service
public class GoodsAttrValueServiceImpl extends BaseServiceImpl implements GoodsAttrValueService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 查询商品规格
     *
     * @param
     * @return
     */
    @Override
    public List<GoodsAttrValueVo> getGoodsSpuAttrValueBySpuId(String spuId) {
        JSONObject json = new JSONObject();
        json.put("spu_id", spuId);
        return JSONObject.parseArray(callDataCenter(DataCenterMethod.GOODS_SPU_ATTR_VALUE_BY_SPUID, json), GoodsAttrValueVo.class);
    }
}
