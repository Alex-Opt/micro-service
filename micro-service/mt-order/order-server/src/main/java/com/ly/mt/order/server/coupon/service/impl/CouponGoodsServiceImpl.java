package com.ly.mt.order.server.coupon.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.coupon.CouponGoods;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.coupon.mapper.CouponGoodsServiceMapper;
import com.ly.mt.order.server.coupon.service.CouponGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品优惠券服务层
 * @author zhanglifeng
 * @date 2019-05-25
 */
@Service
public class CouponGoodsServiceImpl extends BaseServiceImpl implements CouponGoodsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CouponGoodsServiceImpl.class);
    @Resource
    private CouponGoodsServiceMapper couponGoodsServiceMapper;

    @Override
    public CouponGoods selectByPrimaryKey(Long id) {
        return couponGoodsServiceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CouponGoods> selectByCouponIdAndSpuIds(List spuIdList, String couponId) {
        LOGGER.info("查询优惠券信息的入参spuIdList："+ JSONObject.toJSONString(spuIdList)+",couponId:"+couponId);
        if(spuIdList==null ||spuIdList.size()<1){
            return null;
        }
        return couponGoodsServiceMapper.selectByCouponIdAndSpuIds(spuIdList,couponId);
    }
}
