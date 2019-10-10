package com.ly.mt.core.data.shop.mapper;

import com.ly.mt.core.data.shop.entity.ShopStocks;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ShopStocks操作接口
 *
 * @author taoye
 */
@Mapper
public interface ShopStocksMapper {
    /**
     * 更新List<ShopStocks>
     *
     * @param list 更新条件和数据
     * @return 更新结果
     * @author taoye
     */
    int updateDeliveryNums(List<ShopStocks> list);

    /**
     * 更新List<ShopStocks>
     *
     * @param list 更新条件和数据
     * @return 更新结果
     * @author taoye
     */
    int updateNums(List<ShopStocks> list);
}