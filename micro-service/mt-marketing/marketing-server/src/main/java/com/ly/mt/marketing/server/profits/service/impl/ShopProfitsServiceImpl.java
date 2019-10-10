package com.ly.mt.marketing.server.profits.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.dict.ShopProfitsTpEnum;
import com.ly.mt.core.common.entity.marketing.ShopProfits;
import com.ly.mt.core.common.entity.marketing.ShopProfitsVo;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.marketing.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.marketing.server.profits.mapper.ShopProfitsServiceMapper;
import com.ly.mt.marketing.server.profits.service.ShopProfitsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 zhuyh
 * @创建时间 2019/6/14
 * @描述
 */
@Service
public class ShopProfitsServiceImpl extends BaseServiceImpl implements ShopProfitsService {

    /**
     * 收益
     */
    private static final String PROFITS = "profits";
    /**
     * 收益排行
     */
    private static final String PROFITS_TOP = "profits_top";

    @Resource
    private ShopProfitsServiceMapper shopProfitsServiceMapper;

    @Override
    public JSONObject getProfitsSumByLoginId(String json) throws Exception {
        Map<String, Object> result = new HashMap<>(16);
        ShopProfits profits = new ShopProfits();
        profits.setUserId(getUserId());
        result.put(PROFITS, shopProfitsServiceMapper.selectSumProfitsByUId(profits));
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject getProfitsTop(String json) throws Exception {
        ShopProfitsVo profits = JSONObject.parseObject(json, ShopProfitsVo.class);
        Map<String, Object> result = new HashMap<>();
        // 抢单结算
        if (ShopProfitsTpEnum.GRAB.getId().equals(profits.getTp())){
                result.put(PROFITS_TOP, shopProfitsServiceMapper.selectGrabTop(profits.getTopValue()));
        } else if (ShopProfitsTpEnum.REWARD.getId().equals(profits.getTp())){
            // 抢单奖励
            result.put(PROFITS_TOP, shopProfitsServiceMapper.selectRewardTop(profits.getTopValue()));
        } else if (ShopProfitsTpEnum.LODE.getId().equals(profits.getTp())){
            // 淘金奖励
            result.put(PROFITS_TOP, shopProfitsServiceMapper.selectRewardTop(profits.getTopValue()));
        }
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject getRewardProfitsDetails(String json) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put(PROFITS, shopProfitsServiceMapper.getRewardProfitsDetails(getUserId()));
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject getGrabProfitsDetails(String json) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put(PROFITS, shopProfitsServiceMapper.getGrabProfitsDetails(getUserId()));
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject getOrderProfitsDetails(String json) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put(PROFITS, shopProfitsServiceMapper.getOrderProfitsDetails(getUserId()));
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject getLodeProfitsDetails(String json) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put(PROFITS, shopProfitsServiceMapper.getLodeProfitsDetails(getUserId()));
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject getOrderUserCount(String json) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Integer total = shopProfitsServiceMapper.getShopOrderUserTotal();
        result.put(TOTAL, total == null ? 0 : total);
        return JsonUtil.getSuccessJson(result);
    }
}
