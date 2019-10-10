package com.ly.mt.task.order.service.impl;

import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.data.coupon.dao.CouponCodeDao;
import com.ly.mt.core.data.shop.dao.ShopPurchasesDao;
import com.ly.mt.core.data.shop.entity.ShopPurchases;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.order.service.ShopPurchasesService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShopPurchasesServiceImpl extends BaseServiceImpl implements ShopPurchasesService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ShopPurchasesDao shopPurchasesDao;

    @Resource
    private CouponCodeDao couponCodeDao;

    @Override
    public void updCompleteStatus() {
        String time = DateUtil.date2TimeStr(DateUtil.offsetDate(new Date(), -15));
        shopPurchasesDao.updateFinishStatus(time);
    }

    @Override
    public void updCancelStatus() {
        try {
            String time = DateUtil.getBeforeMinutesDateFromPointTime(30,DateUtil.getNowTimeStr());
            List<ShopPurchases> list = shopPurchasesDao.getShopPurchasesForTask(time);
            List<String> idList = new ArrayList<>();
            List<String> couponIdList = new ArrayList<>();
            list.stream().forEach(purchasess -> {
                idList.add(purchasess.getId());
                if(StringUtils.isNotEmpty(purchasess.getCouponId())){
                    couponIdList.add(purchasess.getCouponId());
                }
            });

            if(couponIdList.size() > 0){
                couponCodeDao.updateCouponCodeBatch(couponIdList);
            }
            if(idList.size() > 0){
                shopPurchasesDao.updateCancelStatus(idList);
            }
        } catch (Exception e) {
            logger.error("shop purchases cancel task failed");
            e.printStackTrace();
        }
    }
}