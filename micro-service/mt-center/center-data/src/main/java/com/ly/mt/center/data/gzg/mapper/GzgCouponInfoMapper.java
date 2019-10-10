package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.coupon.entity.CouponInfo;
import com.ly.mt.center.data.gzg.entity.GzgCouponInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgCouponInfoMapper {
    /**
     * @Description 保存GzgCouponInfo
     * @Author taoye
     */
    void insertGzgCouponInfo(CouponInfo gzgCouponInfo);

    /**
     * @Description 删除GzgCouponInfo
     * @Author taoye
     */
    void deleteGzgCouponInfo(CouponInfo gzgCouponInfo);

    /**
     * @Description 更新GzgCouponInfo
     * @Author taoye
     */
    int updateGzgCouponInfo(CouponInfo gzgCouponInfo);

    /**
     * @Description 查询GzgCouponInfo
     * @Author taoye
     */
    CouponInfo getGzgCouponInfo(CouponInfo gzgCouponInfo);
}