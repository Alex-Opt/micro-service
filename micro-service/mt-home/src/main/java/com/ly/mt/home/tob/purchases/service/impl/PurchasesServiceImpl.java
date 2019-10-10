package com.ly.mt.home.tob.purchases.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.feign.ThirdCenterMethod;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.home.base.constant.ShopConstant;
import com.ly.mt.home.base.constant.ShopPurchasesEnum;
import com.ly.mt.home.base.exception.MTException;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.buycar.vo.BuyCar;
import com.ly.mt.home.tob.buycar.vo.CarSku;
import com.ly.mt.home.tob.coupon.service.ShopCouponService;
import com.ly.mt.home.tob.coupon.vo.CouponInfoVo;
import com.ly.mt.home.tob.discount.service.DiscountService;
import com.ly.mt.home.tob.discount.vo.ShopPurchasesDiscountVo;
import com.ly.mt.home.tob.goods.service.GoodsSpuService;
import com.ly.mt.home.tob.goods.vo.GoodsPictureVo;
import com.ly.mt.home.tob.goods.vo.GoodsSpuVo;
import com.ly.mt.home.tob.purchases.factory.DiscountFactory;
import com.ly.mt.home.tob.purchases.factory.decorator.AbstractPriceComponent;
import com.ly.mt.home.tob.purchases.service.PurchasesItemsService;
import com.ly.mt.home.tob.purchases.service.PurchasesService;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesItemsVo;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.ly.mt.home.base.constant.ShopPurchasesEnum.SHOP_PURCHASES_TO_PAY;

/**
 * 进货单实现类
 *
 * @author: linan
 * @date: 2019/9/10
 **/
@Service
public class PurchasesServiceImpl extends BaseServiceImpl implements PurchasesService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    PurchasesItemsService purchasesItemsService;

    @Resource
    ShopCouponService shopCouponService;

    @Resource
    GoodsSpuService goodsSpuService;

    @Resource
    DiscountService discountService;

    /**
     * 进货单分页
     *
     * @param rows 行数
     * @param page 页码
     * @return List<ShopPurchasesVo>
     */
    @Override
    public List<ShopPurchasesVo> queryPurchasesList(String rows, String page) {
        String shopId = getLoginShopId();

        // purchasesList
        JSONObject purchasesJson = new JSONObject();
        purchasesJson.put("rows", rows);
        purchasesJson.put("page", page);
        purchasesJson.put("shop_id", shopId);
        String purchasesListStr = callDataCenter(DataCenterMethod.SHOP_PURCHASES_LIST_GET, purchasesJson);
        List<ShopPurchasesVo> purchasesVoList = JSONObject.parseArray(purchasesListStr, ShopPurchasesVo.class);

        // itemList
        for (ShopPurchasesVo purchasesVo : purchasesVoList) {
            appendShopPurchasesItem(purchasesVo);
        }

        return purchasesVoList;
    }

    /**
     * 进货单详情
     *
     * @param id 进货单id
     * @return ShopPurchasesVo
     */
    @Override
    public ShopPurchasesVo getPurchasesAndItem(String id) {
        ShopPurchasesVo shopPurchasesVo = getPurchases(id, getLoginShopId());
        return appendShopPurchasesItem(shopPurchasesVo);
    }

    /**
     * 取消进货单
     *
     * @param id 进货单id
     */
    @Override
    public void cancelPurchases(String id) {
        ShopPurchasesVo vo = getPurchases(id, getLoginShopId());
        Assert.isTrue(vo.getStatus().equals(SHOP_PURCHASES_TO_PAY.getCode()), "该订单不能取消");

        // update purchases
        vo.setStatus(ShopPurchasesEnum.SHOP_PURCHASES_CANCEL.getCode());
        this.updatePurchase(vo);

        // update coupon
        if (StringUtils.isNotEmpty(vo.getCouponId())) {
            shopCouponService.updateCouponStatus(vo.getCouponId(), ShopConstant.ShopCouponStatus.NO_USE.getValue());
        }
    }

    /**
     * 确认收货
     *
     * @param id 进货单id
     */
    @Override
    public void completePurchases(String id) {
        ShopPurchasesVo vo = getPurchases(id, getLoginShopId());
        Assert.isTrue(vo.getStatus().equals(ShopPurchasesEnum.SHOP_PURCHASES_TO_RECEIVE.getCode()), "该订单不能确认收货");

        // update purchases
        vo.setStatus(ShopPurchasesEnum.SHOP_PURCHASES_FINISH.getCode());
        this.updatePurchase(vo);
    }

    /**
     * 获取运费
     *
     * @return JSONObject
     */
    @Override
    public JSONObject getPurchasesFreight() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("freight", ShopConstant.FREIGHT);
        return jsonObject;
    }

    /**
     * 获取物流信息
     *
     * @param id 进货单id
     * @return ResponseJson
     */
    @Override
    public ResponseJson getPurchasesDeliveryInfo(String id) {
        ShopPurchasesVo purchasesVo = this.getPurchases(id, getLoginShopId());
        // 快递单号
        String num = purchasesVo.getGyLogisticsCode();
        // 物流公司编码
        String com = purchasesVo.getLogisticsCode();

        try {
            // 如果不存在则去gy查询
            if (StringUtils.isEmpty(com)) {
                JSONObject gyJson = new JSONObject();
                gyJson.put("orderNo", id);
                String result = callThirdCenter(ThirdCenterMethod.GY_GET_DELIVER_INFO, gyJson);

                if (StringUtils.isNotEmpty(result)) {
                    JSONObject gyResult = JSONObject.parseObject(result);
                    num = gyResult.getString("express_no");
                    com = gyResult.getString("express_code");

                    purchasesVo.setStatus(ShopPurchasesEnum.SHOP_PURCHASES_TO_RECEIVE.getCode());
                    purchasesVo.setGyLogisticsCode(num);
                    purchasesVo.setLogisticsCode(com);
                    this.updatePurchase(purchasesVo);
                }
            }

            // 如果gy物流为空则返回
            if (StringUtils.isEmpty(com)) {
                return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, "暂无物流信息");
            }
            // kd100
            JSONObject kd100Json = new JSONObject();
            kd100Json.put("com", com);
            kd100Json.put("num", num);
            kd100Json.put("phone", purchasesVo.getMobile());

            JSONObject kd100Result = new JSONObject();
            String result = callThirdCenter(ThirdCenterMethod.KD100_GET_EXPRESS_INFO, kd100Json);
            kd100Result.put("expressNo", com);
            kd100Result.put("expressCompanyCode", num);
            kd100Result.put("deliverys", result);
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, kd100Result);
        } catch (Exception e) {
            logger.error("物流查询异常", e);
            throw new MTException("物流查询异常");
        }
    }

    // TODO: 2019/9/9 optimize async or
    /**
     * 下单
     *
     * @param vo 进货单
     * @return JSONObject
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addPurchases(ShopPurchasesVo vo) {
        String shopId = getLoginShopId();
        String discountType = vo.getDiscountType();

        vo.setShopId(shopId);
        vo.setUserId(getLoginUserId());

        // 优惠计算
        AbstractPriceComponent priceDecorator = DiscountFactory.createDecorator(vo.getDiscountType());
        vo = priceDecorator.calc(vo);
        logger.info("add shop_purchases log: {}", vo.toString());

        Assert.isTrue(new BigDecimal(vo.getActualAmount()).compareTo(new BigDecimal(vo.getPreAmount())) == 0,
                "价格计算错误");
        /*Assert.isTrue(discountAmount.compareTo(
                amount.subtract(new BigDecimal(shopPurchasesVo.getPreAmount()))) == 0, "价格计算错误");*/

        // add purchases
        callDataCenter(DataCenterMethod.SHOP_PURCHASES_INSERT, (JSONObject) JSON.toJSON((vo)));

        // add item
        List<ShopPurchasesItemsVo> itemsVoList = vo.getItemList();
        for (ShopPurchasesItemsVo itemsVo : itemsVoList) {
            itemsVo.setId(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_SHOP_PURCHASES_ITEMS));
            itemsVo.setShopPurchasesId(vo.getId());
            itemsVo.setCreateTime(DateUtil.getNowTimeStr());
            purchasesItemsService.addPurchasesItems(itemsVo);
        }

        // update coupon
        if (StringUtils.isNotEmpty(vo.getCouponId()) && discountType.equals(ShopConstant.DiscountType.COUPON.getValue())) {
            shopCouponService.updateCouponStatus(vo.getCouponId(), ShopConstant.ShopCouponStatus.USE.getValue());
        }

        // add shop_purchases_discount
        List<ShopPurchasesDiscountVo> discountList = vo.getDiscountList();
        if (!CollectionUtils.isEmpty(discountList)) {
            for (ShopPurchasesDiscountVo discountVo: discountList) {
                discountService.addDiscount(discountVo);
            }
        }

        // remove buyCar
        Integer buyType = vo.getBuyType();
        if (buyType.equals(1)) {
            removeBuyCar(vo);
        }

        // TODO: 2019/7/24 淘金收益

        JSONObject jsonObject = new JSONObject();
        // 订单号
        jsonObject.put("orderNo", vo.getId());
        // 金额
        jsonObject.put("amount", vo.getActualAmount());
        // 创建时间
        jsonObject.put("createTime", vo.getCreateTime());
        // 超时时间
        jsonObject.put("countDown", "30");
        return jsonObject;
    }

    /**
     * 检查订单
     *
     * @param id 进货单id
     * @return boolean
     */
    @Override
    public boolean checkPurchases(String  id) {
        ShopPurchasesVo shopPurchasesVo = getPurchases(id, getLoginShopId());

        if(null == shopPurchasesVo){
            throw new MTException("订单不存在");
        }

        if(!shopPurchasesVo.getStatus().equals(SHOP_PURCHASES_TO_PAY.getCode())){
            throw new MTException("订单状态异常");
        }

        try {
            String endTime = DateUtil.getAfterMinutesDateFromPointTime(30, shopPurchasesVo.getCreateTime());
            boolean flag = (-1 == DateUtil.compareDateTime(DateUtil.date2TimeStr(new Date()), endTime));
            if(!flag){
                throw new MTException("订单已过期");
            }
        } catch (Exception e) {
            logger.error("订单异常：{}", e.getMessage());
            throw new MTException("订单异常");
        }

        if(StringUtil.isNotEmpty(shopPurchasesVo.getCouponId())){
            CouponInfoVo couponInfoVo = shopCouponService.getCoupon(shopPurchasesVo.getCouponId());
            if(ShopConstant.ShopCouponStatus.USE.getValue().equals(couponInfoVo.getUseStatus())) {
                throw new MTException("优惠券已使用");
            }
            String invalidTime = couponInfoVo.getInvalidTime();
            if(StringUtil.isNotEmpty(invalidTime)){
                boolean flag;
            try {
                flag = (1 == DateUtil.compareDateTime(invalidTime, DateUtil.date2TimeStr(new Date())));
            } catch (Exception e) {
                throw new MTException("优惠券已过期");
            }
            if(!flag){
                throw new MTException("优惠券已过期");
            }
        }
        }
        return true;
    }

    /**
     * 检查订单状态
     *
     * @param id 进货单id
     * @return boolean
     */
    @Override
    public boolean checkPurchasesStatus(String id) {
        ShopPurchasesVo shopPurchasesVo = getPurchases(id, getLoginShopId());
        if(null == shopPurchasesVo){
            return false;
        }

        if(!shopPurchasesVo.getStatus().equals(SHOP_PURCHASES_TO_PAY.getCode())){
            return false;
        }

        if (StringUtil.isNotEmpty(shopPurchasesVo.getCouponId())) {
            CouponInfoVo couponInfoVo = shopCouponService.getCoupon(shopPurchasesVo.getCouponId());
            if(couponInfoVo.getUseStatus().equals(ShopConstant.ShopCouponStatus.USE.getValue())){
                return false;
            }
        }

        try {
            String endTime = DateUtil.getAfterMinutesDateFromPointTime(30, shopPurchasesVo.getCreateTime());
            return (-1 == DateUtil.compareDateTime(DateUtil.date2TimeStr(new Date()), endTime));
        } catch (Exception e) {
            logger.error("订单状态检查异常：{}", e.getMessage());
            return false;
        }
    }

    /**
     * 修改商家订单状态
     *
     * @param vo 进货单
     */
    @Override
    public void updatePurchase(ShopPurchasesVo vo) {
        vo.setModifyTime(DateUtil.getNowTimeStr());
        callDataCenter(DataCenterMethod.SHOP_PURCHASES_UPDATE, (JSONObject) JSON.toJSON((vo)));
    }

    /**
     * 商家订单详情
     *
     * @param id 进货单id
     * @param shopId 商家id
     * @return ShopPurchasesVo
     */
    @Override
    public ShopPurchasesVo getPurchases(String id, String shopId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        if (StringUtils.isNotEmpty(shopId)) {
            jsonObject.put("shop_id", shopId);
        }

        // purchases
        ShopPurchasesVo shopPurchasesVo = JSONObject.parseObject(callDataCenter(DataCenterMethod.SHOP_PURCHASES_GET, jsonObject), ShopPurchasesVo.class);

        if(null == shopPurchasesVo) {
            throw new MTException("订单不存在");
        }
        // discountList
        List<ShopPurchasesDiscountVo> discountList = discountService.getDiscountByPurchasesId(id);

        if(!CollectionUtils.isEmpty(discountList)) {
            shopPurchasesVo.setDiscountList(discountList);
        }
        return shopPurchasesVo;
    }

    /**
     * 拼装purchases 和 item
     *
     * @param purchasesVo 进货单
     * @return ShopPurchasesVo
     */

    private ShopPurchasesVo appendShopPurchasesItem(ShopPurchasesVo purchasesVo) {
        JSONObject itemJson = new JSONObject();
        itemJson.put("shopPurchaseId", purchasesVo.getId());
        String itemListStr = callDataCenter(DataCenterMethod.SHOP_PURCHASES_ITEMS_LIST_GET, itemJson);
        List<ShopPurchasesItemsVo> itemList = JSONObject.parseArray(itemListStr, ShopPurchasesItemsVo.class);

        // goodsInfo
        for (ShopPurchasesItemsVo itemVo : itemList) {
            GoodsSpuVo goodsSpuVo = goodsSpuService.queryGoodsSpuInfoById(itemVo.getSpuId());
            itemVo.setSpuName(goodsSpuVo.getName());
            itemVo.setSpuPicUrl(goodsSpuVo.getPictureUrl());

            // GoodsSkuPicture list
            String result = callDataCenter(DataCenterMethod.GOODS_SKU_PICTURE_QUERY, (JSONObject) JSON.toJSON(
                    new GoodsPictureVo.Builder().skuId(itemVo.getSkuId()).build()));
            GoodsPictureVo goodsPictureVo = JSONObject.parseArray(result, GoodsPictureVo.class).get(0);

            itemVo.setSkuPicUrl(goodsPictureVo.getPictureUrl());
        }
        purchasesVo.setItemList(itemList);
        return purchasesVo;
    }

    /**
     * 购物车进货时，删除购物车
     *
     * @param purchasesVo 进货单
     */
    private void removeBuyCar(ShopPurchasesVo purchasesVo) {
        List<ShopPurchasesItemsVo> itemList = purchasesVo.getItemList();

        // buyCar
        String jsonObject = redisService.get(RedisKey.REDIS_ENTITY_SHOP_CAR_ID, getLoginShopId());
        if (StringUtils.isNotEmpty(jsonObject)) {
            BuyCar buyCar = JSONObject.parseObject(jsonObject, BuyCar.class);
            for (ShopPurchasesItemsVo itemsVo : itemList) {
                CarSku carSku = buyCar.getItems().get(itemsVo.getSkuId());
                if (null != carSku) {
                    buyCar.subCar(itemsVo.getSkuId());
                }
            }
            redisService.set(RedisKey.REDIS_ENTITY_SHOP_CAR_ID, buyCar.getShopId(), JSON.toJSONString(buyCar));
        }
    }
}
