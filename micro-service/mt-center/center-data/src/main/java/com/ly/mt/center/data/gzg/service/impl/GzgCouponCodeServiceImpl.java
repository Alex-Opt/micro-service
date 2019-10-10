package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.coupon.entity.CouponCode;
import com.ly.mt.center.data.gzg.mapper.GzgCouponCodeMapper;
import com.ly.mt.center.data.gzg.service.GzgCouponCodeService;
import com.ly.mt.center.data.gzg.entity.GzgCouponCode;
import com.ly.mt.base.service.impl.BaseServiceImpl;
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

@Service
public class GzgCouponCodeServiceImpl extends BaseServiceImpl implements GzgCouponCodeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgCouponCodeServiceImpl.class);
    @Resource
    GzgCouponCodeMapper mapper;

    /**
     * @Description 保存GzgCouponCode
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgCouponCode(JSONObject jsonObject) {
        try {
            CouponCode gzgCouponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            if (StringUtil.isEmpty(gzgCouponCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgCouponCode(gzgCouponCode);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCouponCodeServiceImpl.insertGzgCouponCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgCouponCode
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgCouponCodeById(JSONObject jsonObject) {
        try {
            CouponCode gzgCouponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            if (StringUtil.isEmpty(gzgCouponCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgCouponCodeById(gzgCouponCode);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCouponCodeServiceImpl.deleteGzgCouponCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgCouponCode
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgCouponCodeById(JSONObject jsonObject) {
        try {
            CouponCode gzgCouponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            if (StringUtil.isEmpty(gzgCouponCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgCouponCodeById(gzgCouponCode);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCouponCodeServiceImpl.updateGzgCouponCodeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgCouponCode
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgCouponCode(JSONObject jsonObject) {
        try {
            CouponCode CouponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            CouponCode gzgCouponCode = mapper.getGzgCouponCode(CouponCode);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgCouponCode);
        } catch (Exception e) {
            LOGGER.error("GzgCouponCodeServiceImpl.getGzgCouponCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgCouponCode
     * @Author taoye
     */
    @Override
    public ResponseJson selectAllCouponByUserId(JSONObject jsonObject) {
        try {
            CouponCode CouponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            List<CouponCode> couponCodeList = mapper.selectAllCouponByUserId(CouponCode);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, couponCodeList);
        } catch (Exception e) {
            LOGGER.error("GzgCouponCodeServiceImpl.getGzgCouponCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson updateGzgCouponUseStatus(JSONObject jsonObject) {
        CouponCode gzgCouponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
        try{
            if(StringUtil.isEmpty(gzgCouponCode.getCoupon_id()) || StringUtil.isEmpty(gzgCouponCode.getUser_id())){
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }

            int i = mapper.updateGzgCouponUseStatus(gzgCouponCode);
            LOGGER.info("更新优惠卷使用状态 {}",i);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        }catch (Exception e){
            LOGGER.error("GzgCouponCodeServiceImpl.updateGzgCouponUseStatus:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }

    }

    @Override
    public ResponseJson getGzgCouponCodeNotUsed(JSONObject jsonObject) {
        try {
            CouponCode gzgCouponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            List<CouponCode> gzgCouponCodeNotUsed = mapper.getGzgCouponCodeNotUsed(gzgCouponCode);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,gzgCouponCodeNotUsed);
        } catch (Exception e) {
            LOGGER.error("GzgCouponCodeServiceImpl.getGzgCouponCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }




    /**
     * @Description 根据id查询GzgCouponCode
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgCouponCodeById(JSONObject jsonObject) {
        try {
            CouponCode gzgCouponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            mapper.getGzgCouponCodeById(gzgCouponCode);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCouponCodeServiceImpl.getGzgCouponCodeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description #查询格子柜用户领取的某个优惠券目前是否可用，为空：不可用，不为空：可用
     * @Author gongjy
     */
    @Override
    public ResponseJson getGzgCouponCodeUsedInfo(JSONObject jsonObject) {
        try {
            CouponCode gzgCouponCode = JSONObject.toJavaObject(jsonObject, CouponCode.class);
            CouponCode gzgCouponCodeUsedInfo = mapper.getGzgCouponCodeUsedInfo(gzgCouponCode);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,gzgCouponCodeUsedInfo);
        } catch (Exception e) {
            LOGGER.error("GzgCouponCodeServiceImpl.getGzgCouponCodeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }



}