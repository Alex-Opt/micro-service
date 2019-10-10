package com.ly.mt.example.txc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.example.txc.mapper.TxcMapper;
import com.ly.mt.example.txc.service.TxcService;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class TxcServiceImpl extends BaseServiceImpl implements TxcService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TxcServiceImpl.class);
    @Resource
    TxcMapper mapper;

    /**
     * @Description 返回操作成功
     * @Author taoye
     */
    @Override
    public ResponseJson add1(JSONObject jsonObject) {
        try {
            mapper.add1();
        } catch (Exception e) {
            LOGGER.error("", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    /**
     * @Description 返回操作失败(表名不存在)
     * @Author taoye
     */
    @Override
    public ResponseJson add2(JSONObject jsonObject) {
        try {
            mapper.add2();
        } catch (Exception e) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }
}