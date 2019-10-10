package com.ly.mt.core.data.shop.dao.impl;

import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.shop.dao.ShopPurchasesDao;
import com.ly.mt.core.data.shop.entity.ShopPurchases;
import com.ly.mt.core.data.shop.mapper.ShopPurchasesMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * ShopPurchases操作接口
 *
 * @author taoye
 */
@Service
public class ShopPurchasesDaoImpl extends BaseDaoServiceImpl implements ShopPurchasesDao {
    @Resource
    private ShopPurchasesMapper mapper;

    @Override
    public void updateShopPurchases(ShopPurchases shopPurchases) {
        Assert.notNull(shopPurchases, "ShopPurchasesDaoImpl.updateShopPurchases shopPurchases must not be null");
        Assert.notNull(shopPurchases.getId(), "ShopPurchasesDaoImpl.updateShopPurchases shopPurchases.id must not be null");
        mapper.updateShopPurchases(shopPurchases);
    }

    @Override
    public void updateFinishStatus(String time) {
        mapper.updateFinishStatus(time);
    }

    @Override
    public void updateCancelStatus(List<String> ids) {
        mapper.updateCancelStatus(ids);
    }

    @Override
    public List<ShopPurchases> getPurchasesList() {
        return mapper.getPurchasesList();
    }

    @Override
    public List<ShopPurchases> getShopPurchasesForTask(String time) {
        return mapper.getShopPurchasesForTask(time);
    }

}