package com.ly.mt.center.data.punch.mapper;

import com.ly.mt.center.data.punch.entity.PunchCardCoupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PunchCardCouponMapper {
    /**
     * @Description 插入PunchCardCoupon
     * @Author taoye
     */
    void insertPunchCardCoupon(PunchCardCoupon punchCardCoupon);

    /**
     * @Description 根据id删除PunchCardCoupon
     * @Author taoye
     */
    void deletePunchCardCouponById(PunchCardCoupon punchCardCoupon);

    /**
     * @Description 根据id更新PunchCardCoupon
     * @Author taoye
     */
    int updatePunchCardCouponById(PunchCardCoupon punchCardCoupon);

    /**
     * @Description 根据条件查询PunchCardCoupon
     * @Author taoye
     */
    PunchCardCoupon getPunchCardCoupon(PunchCardCoupon punchCardCoupon);

    /**
     * @Description 根据id查询PunchCardCoupon
     * @Author taoye
     */
    PunchCardCoupon getPunchCardCouponById(PunchCardCoupon punchCardCoupon);

    /**
     * 根据状态status查询PunchCardCoupon
     * @param punchCardCoupon
     * @return
     */
    List<PunchCardCoupon> getPunchCardCouponByStatus(PunchCardCoupon punchCardCoupon);
}