package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.bo.CabinetReplenishListBo;
import com.ly.mt.center.data.cabinet.bo.GoodsInfoNameBo;
import com.ly.mt.center.data.cabinet.entity.CabinetReplenish;
import com.ly.mt.center.data.cabinet.mapper.CabinetReplenishMapper;
import com.ly.mt.center.data.cabinet.service.CabinetReplenishService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 补货数据中心层
 * @author zhanglifeng
 * @date 2019-08-26
 */
@Service
public class CabinetReplenishServiceImpl  implements CabinetReplenishService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CabinetReplenishServiceImpl.class);
    @Resource
    private CabinetReplenishMapper cabinetReplenishMapper;
    @Override
    public ResponseJson insertCabinetReplenish(JSONObject jsonObject) {
        try {
            CabinetReplenish cabinetReplenish = JSONObject.toJavaObject(jsonObject, CabinetReplenish.class);
            if (StringUtil.isEmpty(cabinetReplenish.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            cabinetReplenishMapper.insertOne(cabinetReplenish);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CabinetReplenishServiceImpl.insertCabinetReplenish:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 查询补货列表
     * @param
     * @return
     */
    @Override
    public ResponseJson getCabinetReplenishList(JSONObject jsonObject){
        try {
            String userId = String.valueOf(jsonObject.get("userId"));
            String status = String.valueOf(jsonObject.get("status"));
            String orderType = String.valueOf(jsonObject.get("orderType"));
            if (StringUtil.isEmpty(userId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户id不能为空");
            }
            List<CabinetReplenishListBo> cabinetReplenishList = cabinetReplenishMapper.getReplenishList(userId,status,orderType);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,cabinetReplenishList);
        } catch (Exception e) {
            LOGGER.error("CabinetReplenishServiceImpl.getCabinetReplenishList:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 通过补货订单id查询补货详情
     * @param
     * @return
     */
    @Override
    public ResponseJson getCabinetReplenishById(JSONObject jsonObject){
        try {
            String id = String.valueOf(jsonObject.get("id"));
            if (StringUtil.isEmpty(id)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "id不能为空");
            }
            CabinetReplenish cabinetReplenish = cabinetReplenishMapper.getCabinetReplenishById(id);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,cabinetReplenish);
        } catch (Exception e) {
            LOGGER.error("CabinetReplenishServiceImpl.getCabinetReplenishById:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 通过skuid查询spu+sku名称
     * @param
     * @return
     */
    @Override
    public ResponseJson getGoodsInfoNameBySkuId(JSONObject jsonObject){
        try {
            String id = String.valueOf(jsonObject.get("id"));
            if (StringUtil.isEmpty(id)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "id不能为空");
            }
            GoodsInfoNameBo goodsNameInfoBo = cabinetReplenishMapper.getGoodsInfoNameBySkuId(id);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,goodsNameInfoBo);
        } catch (Exception e) {
            LOGGER.error("CabinetReplenishServiceImpl.getGoodsNameInfo:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 通过补货订单id更新
     * @param
     * @return
     */
    public ResponseJson updateCabinetReplenishById(JSONObject jsonObject){
        try {
            CabinetReplenish cabinetReplenish = JSONObject.toJavaObject(jsonObject,CabinetReplenish.class);
            int  i = cabinetReplenishMapper.updateCabinetReplenishById(cabinetReplenish);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("CabinetReplenishServiceImpl.getGoodsNameInfo:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
