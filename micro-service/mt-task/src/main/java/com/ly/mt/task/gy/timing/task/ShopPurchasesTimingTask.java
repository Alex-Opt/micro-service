package com.ly.mt.task.gy.timing.task;

import com.ly.mt.core.data.shop.entity.ShopPurchases;
import com.ly.mt.task.gy.timing.service.PurchasesTimingService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 商家订单往GY推送
 * @author: linan
 * @date: 2019/7/22
 */
@Component
public class ShopPurchasesTimingTask extends QuartzJobBean {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopPurchasesTimingTask.class);

    @Resource
    PurchasesTimingService purchasesTimingService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            LOGGER.info("---------------------到家B------------推送商家订单到管易系统job任务开始---------");
            List<ShopPurchases> purchasesList = purchasesTimingService.getPurchasesList();
            LOGGER.info("商家订单推送数量：{}", purchasesList.size());
            if(purchasesList != null && purchasesList.size()>0){
                purchasesTimingService.sendTradeOrdersToGY(purchasesList);
            }
            LOGGER.info("----------------------到家B------------推送商家订单到管易系统job任务结束---------");
        } catch (Exception e) {
            LOGGER.error("------------------------到家B----推送商家订单到管易系统出错", e.getMessage(), e);
        }
    }
}
