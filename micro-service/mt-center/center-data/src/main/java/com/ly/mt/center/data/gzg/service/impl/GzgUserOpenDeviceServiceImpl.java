package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.gzg.entity.GzgInfo;
import com.ly.mt.center.data.gzg.mapper.GzgInfoMapper;
import com.ly.mt.center.data.gzg.mapper.GzgUserOpenDeviceMapper;
import com.ly.mt.center.data.gzg.service.GzgInfoService;
import com.ly.mt.center.data.gzg.service.GzgUserOpenDeviceService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class GzgUserOpenDeviceServiceImpl extends BaseServiceImpl implements GzgUserOpenDeviceService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgUserOpenDeviceServiceImpl.class);
    @Resource
    GzgUserOpenDeviceMapper mapper;

    /**
     * @Description 根据 phone 查询 GzgUserOpenDevice
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgUserOpenDeviceByPhone(JSONObject jsonObject) {
        try {
            String phone  =  jsonObject.getString("phone");
            if (StringUtil.isEmpty(phone)){
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数 phone 不能为空");
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,mapper.getGzgUserOpenDeviceByPhone(phone));
        } catch (Exception e) {
            LOGGER.error("GzgUserOpenDeviceServiceImpl.getGzgUserOpenDeviceByPhone  出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


}