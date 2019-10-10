package com.ly.mt.cabinet.b.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.cabinet.b.login.vo.TokenInfo;
import com.ly.mt.cabinet.b.user.bo.CabinetMessageBo;
import com.ly.mt.cabinet.b.user.service.CabinetMessageService;
import com.ly.mt.cabinet.b.user.vo.CabinetMessageVo;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.dict.CabinetMessageType;
import com.ly.mt.core.base.dict.CabinetReadStatus;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * @description: 用户消息
 * @author: wanghongliang
 * @create: 2019-09-16 15:43
 **/
@Service
public class CabinetMessageServiceImpl extends BaseServiceImpl implements CabinetMessageService {
    private final static Logger logger = LoggerFactory.getLogger(CabinetMessageServiceImpl.class);

    /**
     * 获取未读消息
     * @throws Exception
     */
    @Override
    public ResponseJson getMessage() throws Exception {
        TokenInfo tokenInfo = getTokenInfo();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", tokenInfo.getUserId());
        //获取未读消息列表
        String result = callDataCenter(GZG_CABINET_MESSAGE_GET, jsonObject);
        List<CabinetMessageBo> list = JSONObject.parseObject(result,new TypeReference<List<CabinetMessageBo>>(){});
        CabinetMessageVo cabinetMessageVo = new CabinetMessageVo();
        String gzgReadStatus=CabinetReadStatus.READ_STATUS_YES.getId();//格子柜补货是否已读状态 默认已读
        String zgReadStatus=CabinetReadStatus.READ_STATUS_YES.getId();//展柜补货是否已读状态 默认已读
        cabinetMessageVo.setGzgReadStatus(gzgReadStatus);
        cabinetMessageVo.setZgReadStatus(zgReadStatus);
        if(list==null||list.size()==0){//都设置为已读
            cabinetMessageVo.setGzgReadStatus(gzgReadStatus);
            cabinetMessageVo.setZgReadStatus(zgReadStatus);
        }else{
            for(CabinetMessageBo cabinetMessageBo:list){
                if(cabinetMessageBo.getMessageType().equals(CabinetMessageType.MESSAGE_TYPE_GZG.getId())){
                    gzgReadStatus = cabinetMessageBo.getReadStatus();
                    cabinetMessageVo.setGzgReadStatus(gzgReadStatus);
                }else{
                    zgReadStatus = cabinetMessageBo.getReadStatus();
                    cabinetMessageVo.setZgReadStatus(zgReadStatus);
                }
            }
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, cabinetMessageVo);
    }

    /**
     * 更新消息状态
     * @throws Exception
     */
    @Override
    public ResponseJson updateMessageStatus(String messageType) throws Exception {
        TokenInfo tokenInfo = getTokenInfo();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", tokenInfo.getUserId());
        jsonObject.put("message_type", messageType);
        jsonObject.put("read_status", CabinetReadStatus.READ_STATUS_YES.getId());//更新为已读
        //获取未读消息列表
        String result = callDataCenter(GZG_CABINET_MESSAGE_UPDATE, jsonObject);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, result);
    }
}