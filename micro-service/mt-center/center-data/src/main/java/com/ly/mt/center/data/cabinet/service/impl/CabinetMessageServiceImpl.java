package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.entity.CabinetMessage;
import com.ly.mt.center.data.cabinet.mapper.CabinetMessageMapper;
import com.ly.mt.center.data.cabinet.service.CabinetMessageService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 消息数据中心层
 * @author wanghongliang
 * @date 2019-09-16
 *ice
 * */
@Service
public class CabinetMessageServiceImpl implements CabinetMessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CabinetMessageServiceImpl.class);
    @Resource
    private CabinetMessageMapper cabinetMessageMapper;


    /**
     * 新增一条消息
     * @return
     */
    public ResponseJson insertCabinetMessage(JSONObject jsonObject){
        try {
            CabinetMessage cabinetMessage = JSONObject.toJavaObject(jsonObject, CabinetMessage.class);
            if (StringUtil.isEmpty(cabinetMessage.getMessage_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            int i = cabinetMessageMapper.insertCabinetMessage(cabinetMessage);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("CabinetMessageServiceImpl.insertCabinetMessage:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 更新消息
     *
     * @return
     */
    public ResponseJson updateCabinetMessageById(JSONObject jsonObject){
        try {
            CabinetMessage cabinetMessage = JSONObject.toJavaObject(jsonObject, CabinetMessage.class);
            if (StringUtil.isEmpty(cabinetMessage.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户id不能为空");
            }
            int i = cabinetMessageMapper.updateCabinetMessageById(cabinetMessage);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("CabinetMessageServiceImpl.updateCabinetMessageById:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 获取根据用户消息
     *
     * @return
     */
    public ResponseJson getCabinetMessage(JSONObject jsonObject){
        try {
            String userId =String.valueOf(jsonObject.get("userId"));
            if (StringUtil.isEmpty(userId)){
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户id不能为空");
            }
            List<CabinetMessage> list =  cabinetMessageMapper.getCabinetMessage(userId);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,list);
        } catch (Exception e) {
            LOGGER.error("CabinetMessageServiceImpl.getCabinetMessage:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
