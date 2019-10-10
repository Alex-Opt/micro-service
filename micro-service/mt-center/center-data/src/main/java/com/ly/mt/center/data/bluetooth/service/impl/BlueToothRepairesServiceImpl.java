package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.bluetooth.entity.BluetoothRepairs;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothRepairsMapper;
import com.ly.mt.center.data.bluetooth.service.BlueToothRepairesService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.RandomUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class BlueToothRepairesServiceImpl extends BaseServiceImpl implements BlueToothRepairesService {


    private final static Logger LOGGER = LoggerFactory.getLogger(BlueToothRepairesServiceImpl.class);
    @Resource
    BluetoothRepairsMapper mapper;

    /**
     * 生成用户报修单
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson insertBlueToothRepairs(JSONObject jsonObject) {
        try{
            BluetoothRepairs bluetoothRepairs = JSONObject.toJavaObject(jsonObject, BluetoothRepairs.class);
            if(StringUtil.isEmpty(bluetoothRepairs.getId())){
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"主键不能为空");
            }
            int i =  mapper.insertBluetoothRepairs(bluetoothRepairs);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        }catch (Exception e){
            LOGGER.error("BlueToothRepairesServiceImpl.insertBlueToothRepairs:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 查询报修信息
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson getBluetoothRepair(JSONObject jsonObject) {
     try{
         BluetoothRepairs bluetoothRepairs = JSONObject.toJavaObject(jsonObject, BluetoothRepairs.class);
         if(StringUtil.isEmpty(bluetoothRepairs.getUser_id())){
             return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"用户编号不能为空");
         }
         BluetoothRepairs bluetoothRepairss = mapper.getRepairByUserId(Long.parseLong(bluetoothRepairs.getUser_id()));
         return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, bluetoothRepairss);
     }catch (Exception e){
         LOGGER.error("BlueToothRepairesServiceImpl.getBluetoothRepair:", e);
         return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
     }

    }

    /**
     * 更新报修信息
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson updateBluetoothRepair(JSONObject jsonObject) {
        try{
            BluetoothRepairs bluetoothRepairs = JSONObject.toJavaObject(jsonObject, BluetoothRepairs.class);
            if(StringUtil.isEmpty(bluetoothRepairs.getId())){
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"报修id不能为空");
            }
            int i = mapper.updateBluetoothRepairsByPrimaryKey(bluetoothRepairs);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        }catch (Exception e){
            LOGGER.error("BlueToothRepairesServiceImpl.updateBluetoothRepair:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
