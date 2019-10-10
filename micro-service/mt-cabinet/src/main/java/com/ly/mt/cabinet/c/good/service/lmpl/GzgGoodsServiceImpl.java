package com.ly.mt.cabinet.c.good.service.lmpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.entity.CabinetInfo;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.coupon.entity.GzgCouponTypeEnum;
import com.ly.mt.cabinet.c.coupon.service.GzgCouponCodeService;
import com.ly.mt.cabinet.c.enumEntity.GzgStatusEnum;
import com.ly.mt.cabinet.c.good.entity.GoodsSkuInfo;
import com.ly.mt.cabinet.c.good.entity.GzgSkuPicture;
import com.ly.mt.cabinet.c.good.entity.GoodsSpuInfo;
import com.ly.mt.cabinet.c.good.entity.GzgGoodsPlan;
import com.ly.mt.cabinet.c.good.service.GzgGoodsService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ly.mt.core.feign.DataCenterMethod.*;

@Service
public class GzgGoodsServiceImpl extends BaseServiceImpl implements GzgGoodsService {
    private final static Logger logger = LoggerFactory.getLogger(GzgGoodsServiceImpl.class);

    @Autowired
    GzgCouponCodeService gzgCouponCodeService;

    @Override
    public ResponseJson gzgGoodsList(String gzgId, String userId,String tname) throws Exception {
        JSONObject resu = new JSONObject();
        JSONArray result = new JSONArray();
        //判断是否为如今格子柜
        if(gzgId.startsWith("RJ")){
            JSONObject param = new JSONObject();
            param.put("name",gzgId);
            param.put("tname",tname);
           String resultJson=  callDataCenter(DataCenterMethod.GZG_RUJIN_RELATION_GET,param);
           if(StringUtil.isEmpty(resultJson)){
               logger.info("查询表  gzg_rujin_relation 格子柜{}格子对应的货柜时，查询结果为空", gzgId);
               return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "柜子未绑定");
           }
        }
           /* JSONObject statusJson = new JSONObject();
            statusJson.put("imei",gzgId);
            String resultGzgInfo = callDataCenter(CABINET_INFO_GET_BY_IMEI, statusJson);
            if(StringUtil.isEmpty(resultGzgInfo)){
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "查询柜子信息为空");
            }else{
                CabinetInfo gzgInfoVo = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgInfo), CabinetInfo.class);
                if(GzgStatusEnum.DOWNSTALE.getKey().equals(gzgInfoVo.getStatus())){
                    return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "该柜子已下架");
                }
            }*/
        JSONObject jsonGzgGoodsPlan = new JSONObject();
        jsonGzgGoodsPlan.put("imei", gzgId);
        String resultGzgGoodsPlan = callDataCenter(GZG_GOODS_PLAN_GET, jsonGzgGoodsPlan);

        if (StringUtil.isEmpty(resultGzgGoodsPlan)) {
            logger.info("查询表gzg_goods_plan格子柜{}格子对应的货物时，查询结果为空", gzgId);
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "柜子未注册");
           // logger.info("查询表 gzg_goods_plan 格子柜{}格子对应的货物时，查询结果为空", gzgId);
           // ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "柜子未注册");
        }
        List<GzgGoodsPlan> gzgGoodsPlanList = JSONArray.parseArray(resultGzgGoodsPlan, GzgGoodsPlan.class);

        List<Integer> sellNumList = new ArrayList<Integer>();
        for (int i = 0; i < gzgGoodsPlanList.size(); i++) {
            sellNumList.add(Integer.parseInt(gzgGoodsPlanList.get(i).getSell_no()));
        }
        Collections.sort(sellNumList);

        for (int i = 0; i < gzgGoodsPlanList.size(); i++) {
            JSONObject jsonGood = new JSONObject();
            String sku_id = gzgGoodsPlanList.get(i).getSku_id();
            jsonGood.put("id", sku_id);
            String resultGoods = callDataCenter(GOODS_SKU_QUERY, jsonGood);
            //GOODS_SPU_QUERY
            if (StringUtil.isEmpty(resultGoods)) {
                logger.error("查询表goods_sku_info，落地页商品信息时，skuid={}时查询结果为空", sku_id);
               return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数异常");
            }
            GoodsSkuInfo goodsSkuInfo = JSONObject.toJavaObject(JSONObject.parseObject(resultGoods), GoodsSkuInfo.class);
            jsonGood.put("id", goodsSkuInfo.getSpu_id());
           String resultGoodsSpu =  callDataCenter(GOODS_SPU_QUERY, jsonGood);
            if (StringUtil.isEmpty(resultGoods)) {

                logger.error("查询表goods_spu_info，落地页商品信息时，spuid={}时查询结果为空", goodsSkuInfo.getSpu_id());
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数异常");
            }
            GoodsSpuInfo goodsSpuInfo = JSONObject.toJavaObject(JSONObject.parseObject(resultGoodsSpu), GoodsSpuInfo.class);
            JSONObject jsonGoodPic = new JSONObject();
            jsonGoodPic.put("sku_id", gzgGoodsPlanList.get(i).getSku_id());
            String resultGzgGoodsPic = callDataCenter(GZG_SKU_PICTURE_GET, jsonGoodPic);

            JSONObject jsonObject = new JSONObject();
            if (StringUtil.isEmpty(resultGzgGoodsPic)) {
                logger.error("查询落地页图片时，skuid={}时查询结果为空");
                jsonObject.put("picture", "");
                //return  ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数异常");
            }else{
                GzgSkuPicture gzgSkuPicture = JSONObject.toJavaObject(JSONObject.parseObject(resultGzgGoodsPic), GzgSkuPicture.class);
                jsonObject.put("picture", gzgSkuPicture.getBig_pic_url());
            }
            jsonObject.put("skuId", sku_id);
            jsonObject.put("name", goodsSpuInfo.getName()+goodsSkuInfo.getName());
            jsonObject.put("price", goodsSkuInfo.getMarket_price());
            //内阁编号
            jsonObject.put("cabinetNo", (i + 1));
            if("0".equals(gzgGoodsPlanList.get(i).getStock())){
                jsonObject.put("isStockout",true);
            }else {
                jsonObject.put("isStockout",false);
            }
            //判断是否是火爆产品
            if ("0".equals(gzgGoodsPlanList.get(i).getSell_no())) {
                jsonObject.put("isFire", false);
            } else {
                if(sellNumList.size()<=2){
                    jsonObject.put("isFire", true);
                }else {
                    if((sellNumList.get(sellNumList.size()-1)+"").equals(gzgGoodsPlanList.get(i).getSell_no()) ||
                        (sellNumList.get(sellNumList.size()-2)+"").equals(gzgGoodsPlanList.get(i).getSell_no())){
                        jsonObject.put("isFire", true);
                    }else {
                        jsonObject.put("isFire", false);
                    }
                }
            }
            result.add(jsonObject);
        }
        JSONObject jsonBannerPic = new JSONObject();
        String allGzgBannerPicture = callDataCenter(GZG_BANNER_PICTURE_GET, jsonBannerPic);
        String usedCouponByUserIdAndCouponId = null;
        if (StringUtil.isNotEmpty(userId)) {
             usedCouponByUserIdAndCouponId = gzgCouponCodeService.isUsedCouponByUserIdAndCouponId(userId, GzgCouponTypeEnum.GZG_COUPON_NEW_PEOPLE_DISCOUNT.getId());
        }
        resu.put("isUsedCoupon", StringUtil.isEmpty(usedCouponByUserIdAndCouponId)?true:false);
        resu.put("goods", result);
        resu.put("swiperList", JSONArray.parseArray(allGzgBannerPicture));

        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, resu);
    }


    @Override
    public ResponseJson gzgGoodsDetail(String skuId) {
        JSONObject jsonResponse = new JSONObject();
        GoodsSkuInfo goodsSkuInfo = getGoodsSkuInfo(skuId);
        if (goodsSkuInfo == null) {
            logger.info("商品详情，查询表goods_sku_info数据时，skuid={}时查询结果为空", skuId);
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数异常");
        }

        GoodsSpuInfo goodsSpuInfo = getGoodsSpuInfo(goodsSkuInfo.getSpu_id());
        if (goodsSpuInfo == null) {
            logger.info("商品详情，查询表goods_spu_info数据时，skuid={}时查询结果为空", skuId);
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR, "参数异常");
        }
        jsonResponse.put("sku_name", goodsSkuInfo.getName());
        jsonResponse.put("sku_price", goodsSkuInfo.getMarket_price());
        jsonResponse.put("spu_name", goodsSpuInfo.getName());
        jsonResponse.put("describe_url", goodsSpuInfo.getDescribe_url());
        jsonResponse.put("sell_num", getSellNumBySkuId(skuId));
        jsonResponse.put("sku_pic",getGoodsSkuPictureURL(skuId));
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, jsonResponse);
    }

    /**
     * 通过skuId查找商品sku详情
     *
     * @param skuId
     * @return
     */
    public GoodsSkuInfo getGoodsSkuInfo(String skuId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", skuId);
        String result = callDataCenter(GOODS_SKU_QUERY, jsonObject);
        GoodsSkuInfo goodsSkuInfoVo = JSONObject.toJavaObject(JSONObject.parseObject(result), GoodsSkuInfo.class);
        return goodsSkuInfoVo;
    }


    /**
     * 通过skuId查找商品spuinfo
     *
     * @param spuId
     * @return
     */
    public GoodsSpuInfo getGoodsSpuInfo(String spuId) {
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("id", spuId);
        String result2 = callDataCenter(GOODS_SPU_QUERY, jsonObject2);
        GoodsSpuInfo goodsSpuInfo = JSONObject.toJavaObject(JSONObject.parseObject(result2), GoodsSpuInfo.class);
        return goodsSpuInfo;
    }


    /**
     * 通过skuId查找某个商品卖出数量
     *
     * @param skuId
     * @return
     */
    public int getSellNumBySkuId(String skuId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sku_id", skuId);
        String result = callDataCenter(GZG_ORDER_ITEMS_SELL_NUM_GET, jsonObject);
        if (StringUtils.isBlank(result)) {
            logger.info("通过skuid查询表gzg_order_item获取货物卖出数量数据为空，skuid = {}", skuId);
            return 0;
        }
        int num = Integer.parseInt(result);
        return num;
    }


    /**
     * 通过skuId查找某个商品图片URL
     *
     * @param skuId
     * @return
     */
    public String getGoodsSkuPictureURL(String skuId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sku_id", skuId);
        String result = callDataCenter(GOODS_SKU_PICTURE_GET, jsonObject);
        if(StringUtil.isEmpty(result)){
            return "";
        }
        JSONArray objects = JSONArray.parseArray(result);
        String picture_url = objects.getJSONObject(0).getString("picture_url");
        return picture_url;
    }

}
