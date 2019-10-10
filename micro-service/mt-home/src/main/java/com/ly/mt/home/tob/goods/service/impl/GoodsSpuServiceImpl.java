package com.ly.mt.home.tob.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.buycar.service.BuyCarService;
import com.ly.mt.home.tob.buycar.vo.BuyCar;
import com.ly.mt.home.tob.goods.service.GoodsSpuService;
import com.ly.mt.home.tob.goods.vo.GoodsPictureVo;
import com.ly.mt.home.tob.goods.vo.GoodsSpuAttrVo;
import com.ly.mt.home.tob.goods.vo.GoodsSpuVo;
import com.ly.mt.home.tob.purchases.service.PurchasesItemsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;


/**
 * 商品spu业务层
 *
 * @author: linan
 * @date: 2019/9/19
 **/
@Service
public class GoodsSpuServiceImpl extends BaseServiceImpl implements GoodsSpuService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BigDecimal rate;

    public GoodsSpuServiceImpl() {
        rate = new BigDecimal(0.6);
    }

    @Resource
    BuyCarService buyCarService;

    @Resource
    PurchasesItemsService purchasesItemsService;

    @Override
    public GoodsSpuVo queryGoodsSpuInfoById(String id) {
        Assert.hasLength(id, "查询条件不能为空");
        String spuStr = redisService.get(RedisKey.REDIS_ENTITY_GOODS_SPU_INFO_ID, id);

        if (StringUtils.isEmpty(spuStr)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            spuStr = callDataCenter(DataCenterMethod.GOODS_SPU_QUERY, jsonObject);
            redisService.set(RedisKey.REDIS_ENTITY_GOODS_SPU_INFO_ID, id, spuStr);
        }

        return JSONObject.parseObject(spuStr, GoodsSpuVo.class);
    }

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @Override
    public ResponseJson queryGoodsSpuById(String id) {
        GoodsSpuVo spuVo = queryGoodsSpuInfoById(id);
        if (null != spuVo) {
            JSONObject json = (JSONObject) JSONObject.toJSON(new GoodsPictureVo.Builder().spuId(id).build());
            // query spu pic list
            String picResult = callDataCenter(DataCenterMethod.GOODS_SPU_PICTURE_BY_SPUID, json);
            List<GoodsPictureVo> picList = JSONObject.parseArray(picResult, GoodsPictureVo.class);
            spuVo.setPictureList(picList);

            // spu attr
            spuVo.setAttr(JSONObject.parseArray(callDataCenter(DataCenterMethod.GOODS_SPU_ATTR_BY_SPUID, json), GoodsSpuAttrVo.class));

            // buycar sku size
            BuyCar buyCar = buyCarService.getBuyCarInfo();
            Integer buyCarNum = 0;
            if (null != buyCar) {
                buyCarNum = buyCar.getItems().size();
            }
            spuVo.setBuyCarSkuCategoryNum(String.valueOf(buyCarNum));

            // spu saleNum
            spuVo.setSaleNum(String.valueOf(purchasesItemsService.getSaleNum(id) + getBaseSaleNum(id)));

            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, spuVo);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, new GoodsSpuVo());
    }

    /**
     * 商品页
     *
     * @param cid
     * @param page
     * @param rows
     * @param sort
     * @return
     */
    @Override
    public ResponseJson queryGoodsSpuByCategroy(String cid, int page, int rows, String sort) {
        JSONObject json = new JSONObject();
        json.put("cid", cid);
        json.put("page", page);
        json.put("rows", rows);
        String result = callDataCenter(DataCenterMethod.GOODS_SPU_LIST_FOR_SHOP, json);

        // spu list
        JSONObject resultJson = JSONObject.parseObject(result);
        List<GoodsSpuVo> list = JSONObject.parseArray(resultJson.getString("rows"), GoodsSpuVo.class);

        // spu赋值
        for (GoodsSpuVo spu : list) {
            // spu pricture list
            spu.setPictureList((JSONObject.parseArray(callDataCenter(DataCenterMethod.GOODS_SPU_PICTURE_BY_SPUID,
                    (JSONObject) JSON.toJSON(new GoodsPictureVo.Builder().spuId(spu.getId()).build())), GoodsPictureVo.class)));
            // 折后价
            BigDecimal ratePrice = new BigDecimal(spu.getMarketPrice()).multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
            spu.setWholesalePrice(String.valueOf(ratePrice));
            spu.setSaleNum(String.valueOf(purchasesItemsService.getSaleNum(spu.getId()) + getBaseSaleNum(spu.getId())));
        }

        if (StringUtils.isNotEmpty(sort)) {
            if ("sale".equals(sort)) {
                Collections.sort(list);
            }
        }
        Map resultMap = new HashMap(2);
        resultMap.put("total", resultJson.getString("total"));
        resultMap.put("rows", list);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultMap);
    }

    /**
     * 雾化弹查询
     *
     * @param page
     * @param rows
     * @return
     */
    @Override
    public ResponseJson queryGoodsSpuByAerosol(int page, int rows) {
        JSONObject json = new JSONObject();
        json.put("page", page);
        json.put("rows", rows);
        String result = callDataCenter(DataCenterMethod.GOODS_SPU_QUERY_BY_AEROSOL, json);
        JSONObject resultJson = JSONObject.parseObject(result);

        List<GoodsSpuVo> list = JSONObject.parseArray(resultJson.getString("rows"), GoodsSpuVo.class);

        for (GoodsSpuVo spuVo : list) {
            spuVo.setPictureList((JSONObject.parseArray(callDataCenter(DataCenterMethod.GOODS_SPU_PICTURE_BY_SPUID,
                    (JSONObject) JSON.toJSON(new GoodsPictureVo.Builder().spuId(spuVo.getId()).build())), GoodsPictureVo.class)));
        }

        Function<String, List<GoodsSpuAttrVo>> attrList = (String spuId) ->
        {
            String attrStr = callDataCenter(DataCenterMethod.GOODS_SPU_PICTURE_BY_SPUID, (JSONObject) JSON.toJSON(new GoodsSpuAttrVo.Builder().spuId(spuId).build()));
            return JSONObject.parseArray(attrStr, GoodsSpuAttrVo.class);
        };
        for (GoodsSpuVo spu : list) {
            spu.setAttr(attrList.apply(spu.getId()));
        }
        Map resultMap = new HashMap(2);
        resultMap.put("total", resultJson.getString("total"));
        resultMap.put("rows", list);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultMap);
    }

    /**
     * 分类查询
     *
     * @param parentId
     * @return
     */
    @Override
    public ResponseJson queryCategroyList(String parentId) {
        JSONObject json = new JSONObject();
        json.put("parent_id", parentId);
        String result = callDataCenter(DataCenterMethod.GOODS_CATEGROY_INFO_GET_BY_PARENT_ID, json);
        JSONArray array = JSONObject.parseArray(result);

        List list = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            JSONObject node = array.getJSONObject(i);
            Map map = new HashMap(2);
            map.put("id", node.getString("id"));
            map.put("name", node.getString("name"));
            list.add(map);
        }
        list.sort((Comparator<Map<String, String>>) (o1, o2) -> {
            int diff = Integer.parseInt(o1.get("id")) - Integer.parseInt(o2.get("id"));
            if (diff > 0) {
                return 1;
            } else if (diff < 0) {
                return -1;
            }
            return 0;
        });
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
    }

    /**
     * 热销榜
     *
     * @param cId
     * @return
     */
    @Override
    public ResponseJson getListTop5ByCid(String cId) {
        //JSONObject json = new JSONObject();
        //json.put("c_id", cId);
        //String result = callDataCenter(DataCenterMethod.GOODS_SPU_INFO_TOP_FIVE_LIST, json);
        //List<GoodsSpuVo> list = JSONObject.parseArray(result, GoodsSpuVo.class);
        //List<Map> map = JSONObject.parseArray(JSONObject.parseArray(,String.class),new HashMap<String,String>);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, getTopList());
    }

    /**
     * 排行榜假数据
     *
     * @return
     */
    private JSONObject getTopList() {
        String motiStr = "燕尾黑1杆3弹,1786;" +
                "极光渐变1杆3弹,1490;" +
                "深海蓝1杆3弹,1257;" +
                "八重樱1杆3弹,1190;" +
                "暖棕灰1杆1弹,1043;";

        String aerosolStr = "经典烟草4颗装,2376;" +
                "激爽薄荷4颗装,2198;" +
                "风情芒果4颗装,1966;" +
                "绿豆冰沙4颗装,1630;" +
                "清甜西瓜4颗装,1208;";

        String mojoStr =
                "经典烟草1支装,2445;" +
                        "清爽薄荷1支装,2061;" +
                        "酷爽蜜瓜1支装,1834;" +
                        "香甜蜜桃1支装,1499;" +
                        "心甜草莓1支装,699;";


        Function<String, List<JSONObject>> attrList = attr -> {
            List<JSONObject> list = new ArrayList<>();
            Arrays.stream(attr.split(";")).forEach(str -> {
                String[] strings = str.split(",");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", strings[0]);
                jsonObject.put("num", strings[1]);
                list.add(jsonObject);
            });
            return list;
        };

        JSONObject resultJson = new JSONObject();
        resultJson.put("1", attrList.apply(motiStr));
        resultJson.put("2", attrList.apply(aerosolStr));
        resultJson.put("3", attrList.apply(mojoStr));

        return resultJson;
    }

    private Integer getBaseSaleNum(String spuId) {
        String saleStr = "{" +
                "\"11249257432\":2988," +
                "\"112492575959\":1745," +
                "\"112492575960\":1306," +
                "\"112492575961\":989," +
                "\"112492576695\":4098," +
                "\"112492576696\":3854," +
                "\"112492576865\":1903," +
                "\"112492576866\":1845," +
                "\"112492577792\":1043," +
                "\"112492577793\":865," +
                "\"112492577794\":798," +
                "\"112492578540\":3845," +
                "\"112492578541\":1908," +
                "\"112492578577\":9012," +
                "\"112492578693\":6207," +
                "\"112492578694\":4142," +
                "\"112492579774\":459," +
                "\"112492579775\":433," +
                "\"112492581503\":4500," +
                "\"112492581504\":3475," +
                "\"112492581505\":2405," +
                "\"112492612695\":1598," +
                "\"116042929408\":234" +
                "}";
        JSONObject saleJson = JSONObject.parseObject(saleStr);
        String num = saleJson.getString(spuId);
        return StringUtils.isEmpty(num) ? 0 : Integer.parseInt(num);
    }
}
