package com.ly.mt.gzg.b.server.gzgb.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gexin.fastjson.JSON;
import com.ly.mt.core.common.entity.gzg.GzgHotelStock;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.gzg.b.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgHotelStockMapper;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgPlanMapper;
import com.ly.mt.gzg.b.server.gzgb.service.GzgHotelStockService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GzgHotelStockServiceImpl extends BaseServiceImpl implements GzgHotelStockService {
    private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(GzgHotelStockServiceImpl.class);
    @Resource
    private GzgPlanMapper gzgPlanMapper;
    @Resource
    private GzgHotelStockMapper gzgHotelStockMapper;

    @Override
    public JSONObject getSkus(String jsonParam) {
        log.info("getSkus function jsonParam:{}",jsonParam);
        JSONObject jsonObject = JSONObject.parseObject(jsonParam);
        String hotelId = jsonObject.getString("hotelId");
        List<Map<String,String>> res = gzgPlanMapper.findSkuIdAndName(Long.parseLong(hotelId));
        log.info("获取当前酒店添加库存返回的数据是={}",res);
        return JsonUtil.getSuccessJson(res);
    }

    @Override
    public JSONObject saveHotelStock(String jsonParam) {
        log.info("添加库存参数 jsonParam={}",jsonParam);
        JSONObject jsonObject = JSONObject.parseObject(jsonParam);
        JSONArray data = jsonObject.getJSONArray("data");
        String hotelId = jsonObject.getString("hotelId");
        List<GzgHotelStock> gzgHotelStocks = data.toJavaList(GzgHotelStock.class);
        List<Long> skuIds = gzgHotelStockMapper.findByHotelId(Long.parseLong(hotelId));
        log.info("添加库存酒店对应的skuids=", JSON.toJSONString(skuIds));
        for (GzgHotelStock stock : gzgHotelStocks){
            long skuId = Long.valueOf(stock.getSkuId());
            GzgHotelStock hotelStock = gzgHotelStockMapper.findByHotelIdAndSkuId(Long.valueOf(hotelId), skuId);
            if (hotelStock != null){
                Integer totalNum = hotelStock.getTotalNum();
                totalNum += stock.getTotalNum();
                hotelStock.setTotalNum(totalNum);
                hotelStock.setSurplusNum(totalNum);
                gzgHotelStockMapper.updateByPrimaryKeySelective(hotelStock);
            }else {
                stock.setHotelId(hotelId);
                stock.setSurplusNum(stock.getTotalNum());
                gzgHotelStockMapper.insertSelective(stock);
            }
        }
        return JsonUtil.getSuccessJson();
    }

    @Override
    public JSONObject findHotelSocks(String jsonParam) {
        JSONObject jsonObject = JSONObject.parseObject(jsonParam);
        String hotelId = jsonObject.getString("hotelId");
        List<GzgHotelStock> allByHotelId = gzgHotelStockMapper.findAllByHotelId(Long.parseLong(hotelId));
        return JsonUtil.getSuccessJson(allByHotelId);
    }


}
