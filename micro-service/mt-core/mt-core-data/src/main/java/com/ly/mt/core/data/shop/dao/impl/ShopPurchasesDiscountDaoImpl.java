package com.ly.mt.core.data.shop.dao.impl;

import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.shop.dao.ShopPurchasesDiscountDao;
import com.ly.mt.core.data.shop.entity.ShopPurchasesDiscount;
import com.ly.mt.core.data.shop.mapper.ShopPurchasesDiscountMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ShopPurchasesDiscount操作接口
 *
 * @author taoye
 */
@Service
public class ShopPurchasesDiscountDaoImpl extends BaseDaoServiceImpl implements ShopPurchasesDiscountDao {
    @Resource
    private ShopPurchasesDiscountMapper mapper;

    @Override
    public List<ShopPurchasesDiscount> listShopPurchasesDiscount(ShopPurchasesDiscount shopPurchasesDiscount) {
        Assert.notNull(shopPurchasesDiscount, "ShopPurchasesDiscountDaoImpl.listShopPurchasesDiscount shopPurchasesDiscount must not be null");
        List<ShopPurchasesDiscount> list = mapper.listShopPurchasesDiscount(shopPurchasesDiscount);
        if (null != list) {
            return list;
        } else {
            return new ArrayList<>();
        }
    }
}