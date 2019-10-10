package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.goods.entity.GoodsSpuInfo;
import com.ly.mt.center.data.goods.mapper.GoodsSpuInfoMapper;
import com.ly.mt.center.data.goods.service.GoodsSpuInfoService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class GoodsSpuInfoServiceImpl extends BaseServiceImpl implements GoodsSpuInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsSpuInfoServiceImpl.class);
    @Resource
    GoodsSpuInfoMapper mapper;

    /**
     * @Description 插入GoodsSpuInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertGoodsSpuInfo(JSONObject jsonObject) {
        try {
            GoodsSpuInfo goodsSpuInfo = JSONObject.toJavaObject(jsonObject, GoodsSpuInfo.class);
            mapper.insertGoodsSpuInfo(goodsSpuInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuInfoServiceImpl.insertGoodsSpuInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除GoodsSpuInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGoodsSpuInfoById(JSONObject jsonObject) {
        try {
            GoodsSpuInfo goodsSpuInfo = JSONObject.toJavaObject(jsonObject, GoodsSpuInfo.class);
            mapper.deleteGoodsSpuInfoById(goodsSpuInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuInfoServiceImpl.deleteGoodsSpuInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新GoodsSpuInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateGoodsSpuInfoById(JSONObject jsonObject) {
        try {
            GoodsSpuInfo goodsSpuInfo = JSONObject.toJavaObject(jsonObject, GoodsSpuInfo.class);
            mapper.updateGoodsSpuInfoById(goodsSpuInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuInfoServiceImpl.updateGoodsSpuInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询GoodsSpuInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSpuInfo(JSONObject jsonObject) {
        try {
            GoodsSpuInfo goodsSpuInfo = JSONObject.toJavaObject(jsonObject, GoodsSpuInfo.class);
            mapper.getGoodsSpuInfo(goodsSpuInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuInfoServiceImpl.getGoodsSpuInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询GoodsSpuInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSpuInfoById(JSONObject jsonObject) {
        try {
            GoodsSpuInfo goodsSpuInfo = JSONObject.toJavaObject(jsonObject, GoodsSpuInfo.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,mapper.getGoodsSpuInfoById(goodsSpuInfo));
        } catch (Exception e) {
            LOGGER.error("GoodsSpuInfoServiceImpl.getGoodsSpuInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGoodsSpuInfoByCategroy(JSONObject jsonObject) {
        try {
            Map result = new HashMap();
            PageHelper.startPage(jsonObject.getInteger("page"), jsonObject.getInteger("rows"));
            GoodsSpuInfo info = new GoodsSpuInfo();
            info.setCid(jsonObject.getString("cid"));
            List<GoodsSpuInfo> list = mapper.getGoodsSpuInfoByCategroy(info);
            PageInfo pageInfo = new PageInfo(list);
            result.put("total", pageInfo.getTotal());
            result.put("rows", list);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,result);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuInfoServiceImpl.getGoodsSpuInfoByCategroy出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGoodsSpuInfoByAerosol(JSONObject jsonObject) {
        try {
            Map result = new HashMap();
            PageHelper.startPage(jsonObject.getInteger("page"), jsonObject.getInteger("rows"));
            GoodsSpuInfo info = new GoodsSpuInfo();
            info.setCid("2");
            List<GoodsSpuInfo> list = mapper.getGoodsSpuInfoByCategroy(info);
            PageInfo pageInfo = new PageInfo(list);
            result.put("total", pageInfo.getTotal());
            result.put("rows", list);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,result);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuInfoServiceImpl.getGoodsSpuInfoByCategroy出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson queryHourSpu(JSONObject jsonObject) {
        try {
            Map result = new HashMap();
            PageHelper.startPage(jsonObject.getInteger("page"), jsonObject.getInteger("rows"));
            List<GoodsSpuInfo> list = mapper.queryHourSpu();
            if(list == null || list.size() == 0){
                result.put("total", 0);
                result.put("rows", new ArrayList<>());
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,result);
            }
            PageInfo pageInfo = new PageInfo(list);
            result.put("total", pageInfo.getTotal());
            result.put("rows", list);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,result);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuInfoServiceImpl.queryHourSpu出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getSpuGoodsSellerNumberEachMonth(JSONObject jsonObject) {
        Long spuId = jsonObject.getLong("spuId");
        String earlyMonthStr = jsonObject.getString("earlyMonthStr");
        String nowTimeStr = jsonObject.getString("nowTimeStr");
        String sellerNumber = mapper.getGoodsSellerNumberByMonth(spuId, earlyMonthStr, nowTimeStr);
        JSONObject resultJson = new JSONObject();
        resultJson.put("sellerNumber",sellerNumber);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,resultJson);
    }

    @Override
    public ResponseJson getListTop5ByCid(JSONObject jsonObject) {
        GoodsSpuInfo goodsSpuInfo = JSONObject.toJavaObject(jsonObject, GoodsSpuInfo.class);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getListTop5ByCid(goodsSpuInfo));
    }

    @Override
    public  ResponseJson queryGoodsSpuInfo(JSONObject jsonObject){
        try {
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,mapper.queryGoodsSpuInfo());
        } catch (Exception e) {
            LOGGER.error("GoodsSpuInfoServiceImpl.queryGoodsSpuInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getSpuListForShop(JSONObject jsonObject) {
        try {
            Map result = new HashMap();
            PageHelper.startPage(jsonObject.getInteger("page"), jsonObject.getInteger("rows"));
            GoodsSpuInfo info = new GoodsSpuInfo();
            info.setCid(jsonObject.getString("cid"));
            List<GoodsSpuInfo> list = mapper.getSpuListForShop(info);
            PageInfo pageInfo = new PageInfo(list);
            result.put("total", pageInfo.getTotal());
            result.put("rows", list);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,result);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuInfoServiceImpl.getSpuListForShop出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGoodsSpuInfoBySpuIdList(JSONObject jsonObject) {
        List<Long> spuIds = JSONObject.parseArray(jsonObject.getString("spuIdList"), Long.class);
        List<GoodsSpuInfo> goodsSpuInfoBySpuIdList = mapper.getGoodsSpuInfoBySpuIdList(spuIds);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,goodsSpuInfoBySpuIdList);
    }
}