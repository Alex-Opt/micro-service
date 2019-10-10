package com.ly.mt.core.data.shop.dao.impl;

import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.shop.dao.ShopStocksDao;
import com.ly.mt.core.data.shop.entity.ShopStocks;
import com.ly.mt.core.data.shop.mapper.ShopStocksMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ShopStocks操作接口
 *
 * @author taoye
 */
@Service
public class ShopStocksDaoImpl extends BaseDaoServiceImpl implements ShopStocksDao {
    @Resource
    private ShopStocksMapper shopStocksMapper;

    @Override
    public int updateDeliveryNums(List<ShopStocks> list) {
        return shopStocksMapper.updateDeliveryNums(list);
    }

    @Override
    public int updateNums(List<ShopStocks> list) {
        return shopStocksMapper.updateNums(list);
    }
}