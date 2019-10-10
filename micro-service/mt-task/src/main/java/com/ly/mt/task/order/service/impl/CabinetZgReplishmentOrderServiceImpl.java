package com.ly.mt.task.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.data.cabinet.dao.CabinetZgReplenishOrderDao;
import com.ly.mt.core.data.cabinet.entity.CabinetZgReplenishOrder;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.order.service.CabinetZgReplishmentOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.aliyun.sms.dict.SmsTemplate.SMS_TEMPLATE_NOTIFICATION_CABAINET_B_ZG_BD;


/**
 * @description:
 * @author: wanghongliang
 * @create: 2019-09-23 19:02
 **/
@Service
public class CabinetZgReplishmentOrderServiceImpl extends BaseServiceImpl implements CabinetZgReplishmentOrderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private CabinetZgReplenishOrderDao cabinetZgReplenishOrderDao;

    /**
     * 开始处理展柜补货订单 并发送短信
     * @throws Exception
     */
    @Override
    public void processCabinetZgReplishmentOrder() throws Exception {
        List<CabinetZgReplenishOrder> list = cabinetZgReplenishOrderDao.getCabinetZgReplenish();
        logger.info("*********************开始处理展柜补货订单,本次查询到数据条数为:"+list.size()+"*********************");
        //下面告知BD 发送短信
        for(CabinetZgReplenishOrder cabinetZgReplenishOrder:list){
            //发送短信对象
//            JSONObject smsObject = new JSONObject();
//            smsObject.put("phone", cabinetZgReplenishOrder.getPhone());
//            ///展柜BD补货通知
//            smsObject.put("templateCode", SMS_TEMPLATE_NOTIFICATION_CABAINET_B_ZG_BD.getCode());
//            smsObject.put("signName", SMS_TEMPLATE_NOTIFICATION_CABAINET_B_ZG_BD.getSign());
            logger.info("CABINET-B-APP-发送展柜补货短信通知：" + cabinetZgReplenishOrder.getPhone());
            Map<String,Object> templateMap  = new HashMap<String,Object>();
            templateMap.put("show_num",cabinetZgReplenishOrder.getNum());
            String templateParms = JSONObject.toJSONString(templateMap);
            //smsObject.put("templateParam", templateParms);//通知类短信
            //调用后台发送短信
            logger.info("*********************开始发送展柜补货通知短信,发送给:*********************"+cabinetZgReplenishOrder.getPhone()+"|数量为:"+cabinetZgReplenishOrder.getNum());
            smsService.sendSms(SMS_TEMPLATE_NOTIFICATION_CABAINET_B_ZG_BD,templateParms,cabinetZgReplenishOrder.getPhone());
            logger.info("*********************结束发送展柜补货通知短信,发送给:*********************"+cabinetZgReplenishOrder.getPhone());
        }
        logger.info("*********************结束处理展柜补货订单,本次查询到数据条数为:*********************"+list.size());

    }
}