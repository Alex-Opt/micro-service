package com.ly.mt.task.order.task;

import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.task.order.service.TradeOrderService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhanglifeng
 */
@Component
public class TradeOrderNotPaySendWxMsgTask extends QuartzJobBean {

    /** 日志*/
    private static final Logger LOGGER = LoggerFactory.getLogger(TradeOrderNotPaySendWxMsgTask.class);


    @Resource
    TradeOrderService tradeOrderService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException   {
        try {
            LOGGER.info("-------------到家C-------------------未支付订单倒计时15分钟时发送未支付提醒给微信小程序用户任务开始-----当前时间："+ DateUtil.getNowTimeStr());
            tradeOrderService.notPayUserSendWxMsg();
            LOGGER.info("-------------到家C-------------------未支付订单倒计时15分钟时发送未支付提醒给微信小程序用户任务结束---------");
        } catch (Exception e) {
            LOGGER.error("-------------到家C-------------------未支付订单倒计时15分钟时发送未支付提醒给微信小程序用户出错:{}", e);
        }
    }
}
