package com.ly.mt.purchase.server.commodity.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.common.entity.purchase.CommodityDetail;
import com.ly.mt.core.common.entity.purchase.CommodityInfo;
import com.ly.mt.core.common.entity.purchase.ShopCouponInfo;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.purchase.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.purchase.server.commodity.mapper.PurchaseCommodityServiceMapper;
import com.ly.mt.purchase.server.commodity.service.PurchaseCommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR;

@Service
public class PurchaseCommodityServiceImpl extends BaseServiceImpl implements PurchaseCommodityService {

    private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(PurchaseCommodityServiceImpl.class);

    @Resource
    private PurchaseCommodityServiceMapper purchaseCommodityServiceMapper;
    @Override
    public JSONObject getCommodityInfo(String json) throws Exception {
        Map result = new HashMap();
        JSONObject jsonObject = JSONObject.parseObject(json);
        Long cId = jsonObject.getLong("cId");
        int page = jsonObject.getIntValue("page");
        int rows = jsonObject.getIntValue("rows");
        PageHelper.startPage(page, rows);
        try {
            List<CommodityInfo> vo = purchaseCommodityServiceMapper.getCommodityInfo(cId);
            PageInfo pageInfo = new PageInfo(vo);
            result.put("total", pageInfo.getTotal());
            result.put("rows", vo);
            return JsonUtil.getSuccessJson(result);
        } catch (Exception e) {
            Logger.error("根据cId查询商品信息数据，异常={}", e.getMessage(), e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

    @Override
    public JSONObject getCommodityDetail(String json) throws Exception {
        Long skuId = JSONObject.parseObject(json).getLong("skuId");
        try {
            List<CommodityDetail> vo = purchaseCommodityServiceMapper.getCommodityDetail(skuId);
            return JsonUtil.getSuccessJson(vo);
        } catch (Exception e) {
            Logger.error("根据skuId查询商品详情数据，异常={}", e.getMessage(), e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

    @Override
    public JSONObject getCommodityBySpuId(String json) throws Exception {
        Long spuId = JSONObject.parseObject(json).getLong("spuId");
        try {
            List<CommodityDetail> commodityDetailList = purchaseCommodityServiceMapper.getCommodityBySpuId(spuId);
            return JsonUtil.getSuccessJson(commodityDetailList);
        } catch (Exception e) {
            Logger.error("根据spuId获取商品信息数据，异常={}", e.getMessage(), e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

    @Override
    public JSONObject getCommodityLadderPrice(String json) throws Exception {
        Long skuId = JSONObject.parseObject(json).getLong("skuId");
        try {
            List<CommodityDetail> priceList = purchaseCommodityServiceMapper.getCommodityLadderPrice(skuId);
            return JsonUtil.getSuccessJson(priceList);
        } catch (Exception e) {
            Logger.error("根据skuId获取商品阶梯价信息数据，异常={}", e.getMessage(), e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

    @Override
    public JSONObject getShopCouponInfo(String json) throws Exception {
        Long shopId = JSONObject.parseObject(json).getLong("shopId");
        Long userId = JSONObject.parseObject(json).getLong("userId");
        try {
            ShopCouponInfo shopCouponInfo = purchaseCommodityServiceMapper.getShopCouponInfo(shopId,userId);
            return JsonUtil.getSuccessJson(shopCouponInfo);
        } catch (Exception e) {
            Logger.error("根据shopId和userId获取店铺优惠劵信息数据，异常={}", e.getMessage(), e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }
}
