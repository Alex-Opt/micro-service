package com.ly.mt.blue.tooth.subsidary.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.subsidary.service.SubsidaryService;
import com.ly.mt.blue.tooth.subsidary.vo.FirmwareVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;


@Service
public class SubsidaryServiceImpl extends BaseServiceImpl implements SubsidaryService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SubsidaryServiceImpl.class);

    /**
     * 获取用户目标设定
     * @return
     * @throws Exception
     */
//    @Override
//    public ResponseJson getSubsidary() throws Exception {
//        String userId = getLoginUserId();
//        LOGGER.info("获取用户目标:user_id"+userId);
//        if(StringUtil.isEmpty(getLoginUserId())){
//            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("user_id",userId);
//        //获取用户目标设定
//        String result = callDataCenter(BLUETOOTH_SUBSIDARY_GET, jsonObject);
//        result = JsonUtils.underlineToCamel(result);//转换驼峰
//        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, result);
//    }

    /**
     * 保存用户戒烟目标
     * @param smokingTarget
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson saveOrModifySmokingTarget(String smokingTarget) throws Exception {
        String userId = getLoginUserId();
        LOGGER.info("保存用户抽烟目标:user_id"+userId);
        if(StringUtil.isEmpty(getLoginUserId())){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id",userId);
        String countResult = callDataCenter(BLUETOOTH_SUBSIDARY_COUNT, jsonObject);
        int c = Integer.parseInt(countResult);
        if(c>0){
            //更新
            jsonObject.put("smoking_target",smokingTarget);
            //保存戒烟目标
            String result = callDataCenter(BLUETOOTH_SUBSIDARY_UPDATE, jsonObject);
        }else{
            //插入
            jsonObject.put("smoking_target",smokingTarget);
            //保存戒烟目标
            String result = callDataCenter(BLUETOOTH_SUBSIDARY_INSERT, jsonObject);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    /**
     * 保存活更新用户达标天数
     * @param complianceDaysTarget
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson saveOrModifyComplianceDaysTarget(String complianceDaysTarget) throws Exception {
        String userId = getLoginUserId();
        LOGGER.info("保存用户达标天数:user_id"+userId);
        if(StringUtil.isEmpty(getLoginUserId())){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id",userId);
        String countResult = callDataCenter(BLUETOOTH_SUBSIDARY_COUNT, jsonObject);
        int c = Integer.parseInt(countResult);
        if(c>0){
            //更新
            jsonObject.put("compliance_days_target",complianceDaysTarget);
            //保存戒烟目标
            String result = callDataCenter(BLUETOOTH_SUBSIDARY_UPDATE, jsonObject);
        }else if(c==0){
            //插入
            jsonObject.put("compliance_days_target",complianceDaysTarget);
            //保存戒烟目标
            String result = callDataCenter(BLUETOOTH_SUBSIDARY_INSERT, jsonObject);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    /**
     * 保存用户投诉建议
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson saveUserSuggest(String name,String mobile,String remark) throws Exception {
        String userId = getLoginUserId();
        if(StringUtil.isEmpty(getLoginUserId())){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id",userId);
        jsonObject.put("name",name);
        jsonObject.put("mobile",mobile);
        jsonObject.put("remark",remark);
        //保存用户投诉建议
        String result = callDataCenter(BLUETOOTH_USER_SUGGESTIONS_INSERT, jsonObject);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    /**
     * 获取最新固件版本
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson getFirmwareUpgrade(String type,String terminalType) throws Exception {
//        String userId = getLoginUserId();
//        LOGGER.info("获取最新固件版本:user_id"+userId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type",type);
        if(!StringUtil.isEmpty(terminalType)){//不为空时查询终端类型
            jsonObject.put("terminal_type",terminalType);
        }
        //获取用户目标设定
        String result = callDataCenter(BLUETOOTH_FIRMWARE_GET, jsonObject);
        FirmwareVo firmwareVo = JSONObject.toJavaObject(JSONObject.parseObject(result), FirmwareVo.class);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, firmwareVo);
    }
}
