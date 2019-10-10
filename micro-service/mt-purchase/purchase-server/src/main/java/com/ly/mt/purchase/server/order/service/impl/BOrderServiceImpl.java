package com.ly.mt.purchase.server.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.purchase.LadderPriceVO;
import com.ly.mt.core.common.entity.purchase.ShopAddressVO;
import com.ly.mt.core.common.entity.purchase.ShopCouponVO;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.purchase.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.purchase.server.order.mapper.BOrderMapper;
import com.ly.mt.purchase.server.order.service.BOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 进货订单页面
 *
 * @author xiaobei-ihmhny
 * @date 2019-6-26 6:4:52
 */
@Service
public class BOrderServiceImpl extends BaseServiceImpl implements BOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BOrderServiceImpl.class);

    @Resource
    private BOrderMapper bOrderMapper;

    /**
     * B端 订单确认页相关信息
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject getOrderInfo(String json) throws Exception {
        JSONObject jsonObject = new JSONObject();
        LOGGER.info("订单确认页相关信息，入参为：{}", json);
        Map<String, String> map = JSON.parseObject(json, Map.class);
        // 订单总金额
        String totalPriceStr = map.get("totalPrice");
        // 订单商品数量
        String totalNumStr = map.get("totalNum");
        String loginUserId = getLoginUserId();
        // 1. 查询地址信息
        List<ShopAddressVO> addressList = bOrderMapper.listShopAddress(Long.valueOf(loginUserId));
        jsonObject.put("addressList",addressList);
        // 2. 根据订单总金额，计算本次交易时，优惠券折扣率及可抵扣金额
        List<ShopCouponVO> shopCouponList = bOrderMapper.listShopCouponByUserId(Long.valueOf(loginUserId));
        ShopCouponVO shopCoupon = null;
        if(shopCouponList != null && shopCouponList.size() > 0){
            // 这里做容错处理，只取第一条记录
            shopCoupon = shopCouponList.get(0);
            BigDecimal disCountRate = new BigDecimal(Double.valueOf(shopCoupon.getDiscountRate()));
            BigDecimal totalPrice = new BigDecimal(Double.valueOf(totalPriceStr));
            double couponDiscountMoney = disCountRate.multiply(totalPrice).doubleValue();
            jsonObject.put("couponDiscountMoney",couponDiscountMoney);
        }
        // 3. 根据订单商品数量及订单金额计算阶梯价折扣情况
        List<LadderPriceVO> ladderPriceList = bOrderMapper.listShopLadderPriceByUserId(Long.valueOf(loginUserId));
        for(LadderPriceVO ladderPriceVO : ladderPriceList){
            // 若当前进货商品数量大于该条折扣率所需的最小进货量时，则采用该进货率
            if(Integer.valueOf(totalNumStr) >= Integer.valueOf(ladderPriceVO.getThisNum())) {
                BigDecimal disCountRate = new BigDecimal(Double.valueOf(ladderPriceVO.getThisPromotionRate()));
                BigDecimal totalPrice = new BigDecimal(Double.valueOf(totalPriceStr));
                double ladderDiscountMoney = disCountRate.multiply(totalPrice).doubleValue();
                jsonObject.put("ladderDiscountMoney", ladderDiscountMoney);
            }
        }
        return JsonUtil.getSuccessJson(jsonObject);
    }
}
