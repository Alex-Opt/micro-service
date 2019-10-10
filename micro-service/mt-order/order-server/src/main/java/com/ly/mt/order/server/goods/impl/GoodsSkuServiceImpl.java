package com.ly.mt.order.server.goods.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.entity.goods.GoodsSkuInfo;
import com.ly.mt.core.base.entity.goods.GoodsSkuInfoVo;
import com.ly.mt.core.base.entity.goods.GoodsSpuInfoForSkuVo;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.goods.GoodsSkuService;
import com.ly.mt.order.server.goods.mapper.GoodsSkuInfoServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_SYSTEM_ERROR;

/**
 * 商品sku业务层
 */
@Service
public class GoodsSkuServiceImpl extends BaseServiceImpl implements GoodsSkuService {

    private final static Logger Logger = LoggerFactory.getLogger(GoodsSkuServiceImpl.class);

    @Resource
    private GoodsSkuInfoServiceMapper goodsSkuInfoMapper;

    @Override
    public JSONObject queryGoodsSku(String json) throws Exception {
        Long spuId = JSONObject.parseObject(json).getLong("spuId");
        String attributes = JSONObject.parseObject(json).getString("attributes");
        try {
            GoodsSkuInfoVo vo = goodsSkuInfoMapper.queryGoodsSku(spuId, attributes);
            if (null != vo) {
               // vo.setPrice(goodsSkuInfoMapper.queryGoodsSkuPriceBySkuId(Long.valueOf(vo.getId())));
                vo.setPictureList(goodsSkuInfoMapper.queryGoodsSkuPictureBySkuId(Long.valueOf(vo.getId())));
            }
            return JsonUtil.getSuccessJson(vo);
        } catch (Exception e) {
            Logger.error("根据spuId和属性查询商品sku数据，异常={}", e.getMessage(), e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }


    @Override
    public GoodsSkuInfo getGoodsSkuInfoBySkuId(Long skuId) {
        return goodsSkuInfoMapper.selectByPrimaryKey(skuId);
    }

    @Override
    public GoodsSpuInfoForSkuVo getGoodsSpuInfoBySpuId(Long spuId) {
        return goodsSkuInfoMapper.getGoodsSpuInfoBySpuId(spuId);
    }


    @Override
    public JSONObject getSkuInfoBySpuIdForOk(String json) {
        //从json中获取参数值
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        /*if (!checkParam(map)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }*/
        String spuId = map.get("spuId");

        List<GoodsSkuInfo> res = goodsSkuInfoMapper.selectBySpuId(Long.valueOf(spuId));
        return JsonUtil.getSuccessJson(res);
    }
}
