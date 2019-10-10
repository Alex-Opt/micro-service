package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.coupon.entity.CouponCode;
import com.ly.mt.center.data.gzg.entity.GzgCouponCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GzgCouponCodeMapper {
    /**
     * @Description 插入CouponCode
     * @Author taoye
     */
    void insertGzgCouponCode(CouponCode gzgCouponCode);

    /**
     * @Description 根据id删除CouponCode
     * @Author taoye
     */
    void deleteGzgCouponCodeById(CouponCode gzgCouponCode);

    /**
     * @Description 根据id更新CouponCode
     * @Author taoye
     */
    int updateGzgCouponCodeById(CouponCode gzgCouponCode);

    /**
     * @Description 根据条件查询CouponCode
     * @Author taoye
     */
    CouponCode getGzgCouponCode(CouponCode gzgCouponCode);

    /**
     * @Description 根据条件查询GzgCouponCode
     * @Author taoye
     */
    List<CouponCode> getGzgCouponCodeNotUsed(CouponCode gzgCouponCode);

    /**
     * @Description 根据id查询GzgCouponCode
     * @Author taoye
     */
    CouponCode getGzgCouponCodeById(CouponCode gzgCouponCode);


    /**
     * @Description 查询格子柜用户领取的某个优惠券目前是否可用，为空：不可用，不为空：可用
     * @Author gongjy
     */
    CouponCode getGzgCouponCodeUsedInfo(CouponCode gzgCouponCode);


    /**
     * @Description 通过用户id获取用户所有优惠券信息
     * @Author gongjy
     */
    List<CouponCode> selectAllCouponByUserId(CouponCode gzgCouponCode);

    int updateGzgCouponUseStatus(CouponCode gzgCouponCode);


}