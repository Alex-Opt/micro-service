package com.ly.mt.center.data.goods.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsSpuConfigurationMapper {

    /**
     * 根据使用的类型查询配置的商品spuId集合
     *
     * @param system_user_type
     * @return
     */
    List<String> querySpuIdListBySystemUserType(Integer system_user_type);

}
