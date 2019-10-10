package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgOrderPay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgOrderPayMapper {
    /**
     * @Description 保存GzgOrderPay
     * @Author taoye
     */
    void insertGzgOrderPay(GzgOrderPay gzgOrderPay);

    /**
     * @Description 删除GzgOrderPay
     * @Author taoye
     */
    void deleteGzgOrderPay(GzgOrderPay gzgOrderPay);

    /**
     * @Description 更新GzgOrderPay
     * @Author taoye
     */
    int updateGzgOrderPay(GzgOrderPay gzgOrderPay);

    /**
     * @Description 查询GzgOrderPay
     * @Author taoye
     */
    GzgOrderPay getGzgOrderPay(GzgOrderPay gzgOrderPay);
}