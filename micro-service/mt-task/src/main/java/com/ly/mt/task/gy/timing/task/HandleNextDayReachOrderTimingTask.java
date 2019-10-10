package com.ly.mt.task.gy.timing.task;

import com.ly.mt.core.data.trade.entity.TradeOrders;
import com.ly.mt.task.gy.timing.service.OrderTimingService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanglifeng
 * @description 针对快递方式为次日达和普通快的订单，要推送到管易系统。
 * 推送成功的更新订单的推送状态为成功。
 */
@Component
public class HandleNextDayReachOrderTimingTask extends QuartzJobBean {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HandleNextDayReachOrderTimingTask.class);

    @Resource
    private OrderTimingService orderTimingService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            LOGGER.info("-------------------------到家C-----------推送订单到管易系统job任务开始---------");
            List<TradeOrders> orders = orderTimingService.getOrderList();
            if(orders != null && orders.size()>0){
                LOGGER.info("本次定时任务捞取到要发送管易的订单数量为："+orders.size());
                orderTimingService.sendTradeOrdersToGY(orders);
            }
            LOGGER.info("-------------------------到家C-------------推送订单到管易系统job任务结束---------");
        } catch (Exception e) {
            LOGGER.error("到家C---推送订单到管易系统出错", e.getMessage(), e);
        }
    }
}
