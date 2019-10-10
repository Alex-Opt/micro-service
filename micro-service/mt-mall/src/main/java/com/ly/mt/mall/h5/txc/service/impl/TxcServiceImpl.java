package com.ly.mt.mall.h5.txc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.txc.service.TxcService;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.ly.mt.core.feign.DataCenterMethod.TXC_ADD1;
import static com.ly.mt.core.feign.DataCenterMethod.TXC_ADD2;

@Service
public class TxcServiceImpl extends BaseServiceImpl implements TxcService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TxcServiceImpl.class);

    @TxcTransaction(appName = "moti")
    @Override
    public void test() throws Exception {
        String xid = TxcContext.getCurrentXid();
        LOGGER.info("测试事务回滚开始:xid={}", xid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("xid", xid);
        // add1返回成功
        callDataCenter(TXC_ADD1, jsonObject);
        // add2返回失败并抛出异常，预期回滚add1的插入动作
        callDataCenter(TXC_ADD2, jsonObject);
    }
}