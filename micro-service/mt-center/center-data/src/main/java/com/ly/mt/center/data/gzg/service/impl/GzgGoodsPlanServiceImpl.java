package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.gzg.entity.GzgGoodsPlan;
import com.ly.mt.center.data.gzg.entity.GzgPlan;
import com.ly.mt.center.data.gzg.mapper.GzgGoodsPlanMapper;
import com.ly.mt.center.data.gzg.service.GzgGoodsPlanService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;


@Service
public class GzgGoodsPlanServiceImpl extends BaseServiceImpl implements GzgGoodsPlanService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgGoodsPlanServiceImpl.class);
    @Resource
    GzgGoodsPlanMapper mapper;
    /**
     * @Description 根据条件查询GzgPlan
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgGoodsPlan(JSONObject jsonObject) {
        try {
            GzgGoodsPlan gzgGoodsPlan = JSONObject.toJavaObject(jsonObject, GzgGoodsPlan.class);
            List<GzgGoodsPlan> gzgGoodsPlanList = mapper.getGzgGoodsPlan(gzgGoodsPlan);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,gzgGoodsPlanList);
        } catch (Exception e) {
            LOGGER.error("GzgGoodsPlanServiceImpl.getGzgGoodsPlan出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson insertGzgGoodsPlan(JSONObject jsonObject) {
        try{
            GzgGoodsPlan gzgGoodsPlan = JSONObject.toJavaObject(jsonObject, GzgGoodsPlan.class);
            mapper.insertGzgGoodsPlan(gzgGoodsPlan);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        }catch (Exception e){
            LOGGER.error("GzgGoodsPlanServiceImpl.insertGzgGoodsPlan出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }

    }

    @Override
    public ResponseJson updateGzgGoodsPlan(JSONObject jsonObject) {
        try {
            GzgGoodsPlan gzgGoodsPlan = new GzgGoodsPlan();
            gzgGoodsPlan.setImei(jsonObject.getString("imei"));
            gzgGoodsPlan.setCabinet_no(jsonObject.getString("cabinet_no"));
            LOGGER.info("GzgGoodsPlanServiceImpl.updateGzgGoodsPlan:参数:", gzgGoodsPlan);
            int i = mapper.updateGzgGoodsPlanNumByCondation(gzgGoodsPlan);

            LOGGER.info("GzgGoodsPlanServiceImpl.updateGzgGoodsPlan:扣减库存结果", i);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("GzgGoodsPlanServiceImpl.updateGzgGoodsPlan:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson updateGzgZgGoodsPlanNum(JSONObject jsonObject) {
        try {
            GzgGoodsPlan gzgGoodsPlan = new GzgGoodsPlan();
            gzgGoodsPlan.setImei(jsonObject.getString("imei"));
            gzgGoodsPlan.setSku_id(jsonObject.getString("sku_id"));
            LOGGER.info("GzgGoodsPlanServiceImpl.updateGzgZgGoodsPlanNum:参数:", JSONObject.toJSONString(gzgGoodsPlan));
            int i = mapper.updateGzgGoodsPlanNumByCondation(gzgGoodsPlan);

            LOGGER.info("GzgGoodsPlanServiceImpl.updateGzgZgGoodsPlanNum:扣减库存结果", i);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("GzgGoodsPlanServiceImpl.updateGzgGoodsPlan:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson insertBatchGzgGoodsPlan(JSONObject jsonObject) {
        try {
            String alist = jsonObject.getString("alist");
            List<GzgGoodsPlan> gzgGoodsPlanList = JSONArray.parseArray(alist, GzgGoodsPlan.class);
            int i = mapper.insertBatchGzgGoodsPlan(gzgGoodsPlanList);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("GzgGoodsPlanServiceImpl.insertBatchGzgGoodsPlan:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @Override
    public ResponseJson updateBatchGzgGoodsPlan(JSONObject jsonObject) {
        try {
            String alist = jsonObject.getString("alist");
            List<GzgGoodsPlan> gzgGoodsPlanList = JSONArray.parseArray(alist, GzgGoodsPlan.class);
            int j  = 0;
            for (int i = 0; i < gzgGoodsPlanList.size(); i++) {
               j = mapper.updateGzgGoodsPlan(gzgGoodsPlanList.get(i));
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,j);
        } catch (Exception e) {
            LOGGER.error("GzgGoodsPlanServiceImpl.updateBatchGzgGoodsPlan:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     *  展柜补货 在原库存基础上加上本次添加的库存
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson updateGzgGoodsPlanStock(JSONObject jsonObject){
        try {
            GzgGoodsPlan gzgGoodsPlan = JSONObject.toJavaObject(jsonObject,GzgGoodsPlan.class);
            LOGGER.info("GzgGoodsPlanServiceImpl.updateGzgGoodsPlanStock:参数:", gzgGoodsPlan);
            int i = mapper.updateGzgGoodsPlanStock(gzgGoodsPlan);
            LOGGER.info("GzgGoodsPlanServiceImpl.updateGzgGoodsPlanStock:补货增加库存结果", i);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("GzgGoodsPlanServiceImpl.updateGzgGoodsPlanStock:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     *  展柜补货 在原库存基础上加上本次添加的库存
     * @return
     */
    @Override
    public ResponseJson insertBatchGzgGoodsPlans(JSONObject jsonObject){
        try {
             List<GzgGoodsPlan> list =(List<GzgGoodsPlan>)jsonObject.get("goodsPlanList");
            int i = mapper.insertBatchGzgGoodsPlans(list);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("GzgGoodsPlanServiceImpl.insertBatchGzgGoodsPlans:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     *  查询货柜中是否包含套装商品
     * @return
     */
   public ResponseJson getGzgGoodsPlanSuitNum(JSONObject jsonObject){
       try {
           String imei = String.valueOf(jsonObject.get("imei"));
           int i = mapper.getGzgGoodsPlanSuitNum(imei);
           return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
       } catch (Exception e) {
           LOGGER.error("GzgGoodsPlanServiceImpl.getGzgGoodsPlanSuitNum:", e);
           return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
       }
   }

    /**
     * 查询展柜top3是否都已经卖完
     * @param jsonObject
     * @return
     */
    public ResponseJson getTop3GzgGoodsPlan(JSONObject jsonObject){
        try {
            List<String> skuIdList =(List<String>)(jsonObject.get("skuIdList"));
            String imei = String.valueOf(jsonObject.get("imei"));
            List<GzgGoodsPlan> list = mapper.getTop3GzgGoodsPlanStock(skuIdList,imei);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,list);
        } catch (Exception e) {
            LOGGER.error("GzgGoodsPlanServiceImpl.getTop3GzgGoodsPlan:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson updateGzgGoodsPlanNumByCondation(JSONObject jsonObject) {
        String imei =jsonObject.getString("imei");
        String cabinet_no = jsonObject.getString("cabinet_no");

        GzgGoodsPlan plan = new GzgGoodsPlan();
        plan.setImei(imei);
        plan.setCabinet_no(cabinet_no);
        int i = mapper.updateGzgGoodsPlanNumByCondation(plan);
        if(i > 0){
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        }else{
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

}