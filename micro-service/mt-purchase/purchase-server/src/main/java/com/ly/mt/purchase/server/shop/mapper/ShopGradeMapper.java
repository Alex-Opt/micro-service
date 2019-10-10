package com.ly.mt.purchase.server.shop.mapper;


import com.ly.mt.core.common.entity.purchase.ShopGradeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 店铺会员等级配置
 * @author xiaobei
 * @date 2019-06-21 08:01:01
 */
@Mapper
public interface ShopGradeMapper {

    /**
     * 查询所有的等级优惠情况
     * @return
     */
    List<ShopGradeVO> listAll();
}
