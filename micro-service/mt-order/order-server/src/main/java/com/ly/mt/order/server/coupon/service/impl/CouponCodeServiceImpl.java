package com.ly.mt.order.server.coupon.service.impl;

import com.ly.mt.core.base.entity.coupon.CouponCode;
import com.ly.mt.core.base.entity.coupon.CouponModel;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.coupon.mapper.CouponCodeServiceMapper;
import com.ly.mt.order.server.coupon.service.CouponCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.dict.CouponCodeUse.COUPON_CODE_USE_STATUS_NOT_USE;
import static com.ly.mt.core.base.dict.CouponCodeUse.COUPON_CODE_USE_STATUS_USED;
import static com.ly.mt.core.base.dict.PrimaryKey.COUPON_CODE;

/**
 * @author zhanglifeng
 * @description 优惠券码表服务层
 * @date 2019-05-24
 */
@Service
public class CouponCodeServiceImpl extends BaseServiceImpl implements CouponCodeService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CouponCodeServiceImpl.class);
    @Resource
    private CouponCodeServiceMapper couponCodeServiceMapper;

    @Override
    public List<String> selectNotUsedCouponIdByUserId(Long userId) {
        List<CouponCode> couponCodes = couponCodeServiceMapper.selectCouponIdByUserIdAndUseStatus(userId, Integer.valueOf(COUPON_CODE_USE_STATUS_NOT_USE.getCode()));
        List<String> couponIdList = new ArrayList<String>(3);
        if (couponCodes != null && couponCodes.size() > 0) {
            for (CouponCode couponCode : couponCodes) {
                if(couponCode != null){
                    couponIdList.add(couponCode.getCouponId());
                }
            }
        }
        return couponIdList;
    }

    @Override
    public List<String> selectUsedCouponIdByUserId(Long userId) {
        List<CouponCode> couponCodeList = couponCodeServiceMapper.selectCouponIdByUserIdAndUseStatus(userId, Integer.valueOf(COUPON_CODE_USE_STATUS_USED.getCode()));
        List<String> idList = new ArrayList<String>(3);
        if (couponCodeList != null && couponCodeList.size() > 0) {
            for (CouponCode couponCode : couponCodeList) {
                idList.add(couponCode.getCouponId());
            }
        }
        return idList;
    }

    /**
     * 异步更新用户优惠券的使用状态
     *
     * @param couponList
     * @return
     */
    @Override
    public void updateCouponCode(List<CouponModel> couponList,String userId) {
        if (couponList != null && couponList.size() > 0) {
            for (CouponModel couponModel : couponList) {
                int updateNum = couponCodeServiceMapper.updateByCouponIdAndUserId(Long.valueOf(userId), Long.valueOf(couponModel.getCouponId()), DateUtil.getNowTimeStr());
                //如果更新的条数为0，则证明目前不存在这条数据，要插入一条使用记录
                if(updateNum==0){
                    CouponCode couponCode = new CouponCode();
                    String couponId = SnowflakeUtil.getPrimaryKey(COUPON_CODE);
                    couponCode.setId(couponId);
                    couponCode.setCouponId(couponModel.getCouponId());
                    couponCode.setUserId(userId);
                    couponCodeServiceMapper.insertUsedCouponCodeHistory(couponCode);
                }
            }
        }
    }
}
