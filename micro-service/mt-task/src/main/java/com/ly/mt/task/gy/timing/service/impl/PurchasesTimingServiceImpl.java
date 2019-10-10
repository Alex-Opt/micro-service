package com.ly.mt.task.gy.timing.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.data.basic.dao.BasicAreaDao;
import com.ly.mt.core.data.basic.entity.BasicArea;
import com.ly.mt.core.data.goods.dao.GoodsSkuInfoDao;
import com.ly.mt.core.data.goods.entity.GoodsSkuInfo;
import com.ly.mt.core.data.shop.dao.ShopPurchasesDao;
import com.ly.mt.core.data.shop.dao.ShopPurchasesItemsDao;
import com.ly.mt.core.data.shop.entity.ShopPurchases;
import com.ly.mt.core.data.shop.entity.ShopPurchasesItems;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.gy.entity.GYResult;
import com.ly.mt.task.gy.service.impl.GyServiceImpl;
import com.ly.mt.task.gy.timing.service.PurchasesTimingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.dict.OrderStatus.ORDER_STATUS_PENDING_RECEIPT;
import static com.ly.mt.core.base.dict.PushStatus.PUSH_STATUS_PUSH_SUCCESS;
import static com.ly.mt.core.logistics.dict.Kd100Com.KD_COM_ZT;

@Service
public class PurchasesTimingServiceImpl extends GyServiceImpl implements PurchasesTimingService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);
    @Resource
    private ShopPurchasesDao shopPurchasesDao;
    @Resource
    private ShopPurchasesItemsDao shopPurchasesItemsDao;
    @Resource
    private BasicAreaDao basicAreaDao;
    @Resource
    private GoodsSkuInfoDao goodsSkuInfoDao;

    /**
     * 批量推送订单到管易系统
     *
     * @param purchasesList
     * @return
     * @throws Exception
     */
    @Override
    public void sendTradeOrdersToGY(List<ShopPurchases> purchasesList) throws Exception {
        for (ShopPurchases purchases : purchasesList) {
            //查询订单明细数据
            List<ShopPurchasesItems> purchasesListItemList = shopPurchasesItemsDao.listShopPurchasesItemsByShopPurchasesIdFromRedis(purchases.getId());
            //封装推送订单的参数//getReqJSONObject();
            JSONObject paramJson = processParam(getReqJSONObject(), purchases, purchasesListItemList);
            //调用管易接口，推送订单
            paramJson.put("sign", getSign(paramJson.toString()));

            String returnJson = postGY(paramJson);
            GYResult result = JSONObject.parseObject(returnJson, GYResult.class);
            if (result.getSuccess()) {
                //更新订单推送状态
                ShopPurchases shopPurchases = new ShopPurchases();
                shopPurchases.setId(purchases.getId());
                shopPurchases.setPushStatus(PUSH_STATUS_PUSH_SUCCESS.getId());
                shopPurchases.setStatus(ORDER_STATUS_PENDING_RECEIPT.getId());
                shopPurchasesDao.updateShopPurchases(shopPurchases);
                LOGGER.info("推送订单数据到管易，更新订单表状态成功！");
            }
        }
    }

    @Override
    public List<ShopPurchases> getPurchasesList() {
        return shopPurchasesDao.getPurchasesList();
    }

    /**
     * 组装调用管易接口参数
     *
     * @param json
     * @param order
     * @param itemList
     * @return
     */
    public JSONObject processParam(JSONObject json, ShopPurchases order, List<ShopPurchasesItems> itemList) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        json.put("method", "gy.erp.trade.add");
        //店铺代码
        json.put("shop_code", yml.getShopIdMerchant());
        String wareHouseId = null;
        if (yml.getWarehouseId().contains("1")) {
            wareHouseId = "001";
        } else {
            wareHouseId = yml.getWarehouseId();
        }
        //仓库代码
        json.put("warehouse_code", wareHouseId);
        //物流公司
        json.put("express_code", KD_COM_ZT.getGyCom());
        //会员代码
        json.put("vip_code", order.getUserId());
        //平台单号
        json.put("platform_code", order.getId());
        //拍单时间
        json.put("deal_datetime", format.format(format.parse(order.getCreateTime())));
        //订单类型
        json.put("order_type_code", "Sales");
        //买家留言
        json.put("buyer_memo", order.getRemark());
        //收货人
        json.put("receiver_name", order.getConsignee());
        //电话
        json.put("receiver_phone", order.getMobile());
        //手机号
        json.put("receiver_mobile", order.getMobile());
        //收货地址
        json.put("receiver_address", order.getFullAddress());
        //省名称
        BasicArea province = basicAreaDao.getBasicAredByMIdFromRedis(order.getProvinceCode());
        json.put("receiver_province", province.getName());
        //市名称
        BasicArea city = basicAreaDao.getBasicAredByMIdFromRedis(order.getCityCode());
        json.put("receiver_city", city.getName());
        //区名称
        BasicArea district = basicAreaDao.getBasicAredByMIdFromRedis(order.getDistrictCode());
        json.put("receiver_district", district.getName());
        //商品明细
        List<Map<String, Object>> details = new ArrayList<>();
        for (ShopPurchasesItems item : itemList) {
            Map<String, Object> detail = new LinkedHashMap<>();
            detail.put("item_code", item.getSkuId());
            GoodsSkuInfo goodsSkuInfo = goodsSkuInfoDao.getGoodsSkuInfoByIdFromRedis(item.getSkuId());
            // 不能传空值
            detail.put("sku_code", goodsSkuInfo.getCode());
            detail.put("price", item.getSkuPrice());
            detail.put("qty", item.getSkuNum());
            detail.put("oid", item.getId());
            details.add(detail);
        }
        json.put("details", details);
        return json;
    }

}
