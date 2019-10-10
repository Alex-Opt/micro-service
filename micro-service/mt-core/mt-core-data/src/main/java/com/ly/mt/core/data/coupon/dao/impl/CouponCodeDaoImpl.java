package com.ly.mt.core.data.coupon.dao.impl;

import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.coupon.dao.CouponCodeDao;
import com.ly.mt.core.data.coupon.entity.CouponCode;
import com.ly.mt.core.data.coupon.mapper.CouponCodeMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * CouponCode操作接口
 *
 * @author taoye
 */
@Service
public class CouponCodeDaoImpl extends BaseDaoServiceImpl implements CouponCodeDao {
    @Resource
    private CouponCodeMapper mapper;

    @Override
    public void updateCouponCode(CouponCode couponCode) {
        Assert.notNull(couponCode, "CouponCodeDaoImpl.updateCouponCode couponCode must not be null");
        mapper.updateCouponCode(couponCode);
    }

    @Override
    public void updateCouponCodeBatch(List<String> ids) {
        mapper.updateCouponCodeBatch(ids);
    }


    @Override
    public void batchUpdateCouponCodeUnused(List<Map<String, String>> list) {
        for (Map<String, String> map: list) {
            CouponCode couponCode = new CouponCode();
            couponCode.setCouponId(map.get("couponId"));
            couponCode.setUserId(map.get("userId"));
            mapper.updateCouponCodeByUserIdAndCouponId(couponCode);
        }
    }
}