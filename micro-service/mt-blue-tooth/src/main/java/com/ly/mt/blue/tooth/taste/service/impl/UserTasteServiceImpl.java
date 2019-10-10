package com.ly.mt.blue.tooth.taste.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.taste.service.UserTasteService;
import com.ly.mt.blue.tooth.taste.vo.BluetoothIndexVo;
import com.ly.mt.blue.tooth.taste.vo.BluetoothTasteInfoVo;
import com.ly.mt.blue.tooth.taste.vo.BluetoothUserTasteVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * 用户烟弹service
 */
@Service
public class UserTasteServiceImpl extends BaseServiceImpl implements UserTasteService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserTasteServiceImpl.class);

    /**
     * 获取烟弹列表
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson getTasteList() throws Exception {
        JSONObject jsonObject = new JSONObject();
        String id = getLoginUserId();
        LOGGER.info("用户获取烟弹列表BlueToothUserid="+id);
        jsonObject.put("user_id",id);//放入当前登录用户id
        String result = callDataCenter(BLUETOOTH_TASTE_INFO_GET, jsonObject);
        List<BluetoothTasteInfoVo> list = JSONObject.parseObject(result, new TypeReference<List<BluetoothTasteInfoVo>>(){});
        if (list.isEmpty()) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "烟弹列表暂未初始化!");
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
    }

    /**
     * 保存用户烟弹
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson saveUserTaste(String tasteKey,String addTime,String macAddress) throws Exception {
        String userId = getLoginUserId();
        LOGGER.info("用户保存烟弹==user_id"+userId);
        if(StringUtil.isEmpty(getLoginUserId())){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("taste_key", tasteKey);
        jsonObject.put("add_time", addTime);
        jsonObject.put("user_id", userId);
        jsonObject.put("mac_address", macAddress);
        String result = callDataCenter(BLUETOOTH_USER_TASTE_INERT, jsonObject);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "保存成功!");
    }

    /**
     * 获取口味最佳匹配
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson getBestTaste(String tasteKey) throws Exception {
        String userId = getLoginUserId();
        LOGGER.info("用户获取最佳口味匹配==user_id"+userId);
        if(StringUtil.isEmpty(getLoginUserId())){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
        }
        JSONObject jsonObject = new JSONObject();//查询最佳匹配jsonObject
        jsonObject.put("taste_key",tasteKey);
        /**
         *  如果没有口味key,则认为用户手动点击最佳匹配.则自动匹配用户最新添加的烟弹口味
         */
        if(StringUtil.isEmpty(String.valueOf(tasteKey))){
            JSONObject tasteJson = new JSONObject();
            tasteJson.put("user_id", userId);
            String result = callDataCenter(BLUETOOTH_USER_TASTE_GET, tasteJson); //查询最新添加烟弹
            //result = JsonUtils.underlineToCamel(result);//转换驼峰
            BluetoothUserTasteVo bluetoothUserTasteVo = JSONObject.parseObject(result, BluetoothUserTasteVo.class);
            if(bluetoothUserTasteVo ==null){
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "暂未添加烟弹,无法进行最佳匹配");
            }
            jsonObject.put("taste_key", bluetoothUserTasteVo.getTasteKey());
        }
        //查询最佳匹配
        String result = callDataCenter(BLUETOOTH_TASTE_BEST_GET, jsonObject);
        List<BluetoothIndexVo> list = JSONObject.parseObject(result, new TypeReference<List<BluetoothIndexVo>>(){});
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
    }
}
