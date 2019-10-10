package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.bluetooth.entity.BluetoothUserBind;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothUserBindMapper;
import com.ly.mt.center.data.bluetooth.service.BluetoothUserBindService;
import com.ly.mt.core.base.dict.BluetoothBindStatus;
import com.ly.mt.core.base.dict.BluetoothChildLock;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_BLUETOOTH_USER_BIND;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class BluetoothUserBindServiceImpl extends BaseServiceImpl implements BluetoothUserBindService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothUserBindServiceImpl.class);
    @Resource
    BluetoothUserBindMapper mapper;

    /**
     * @Description 插入BluetoothUserBind
     * @Author wanghongliang
     */
    @Override
    public ResponseJson insertBluetoothUserBind(JSONObject jsonObject) {
        try {
            BluetoothUserBind bluetoothUserBind = JSONObject.toJavaObject(jsonObject, BluetoothUserBind.class);
            String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_BLUETOOTH_USER_BIND);
            bluetoothUserBind.setId(id);
            bluetoothUserBind.setCreate_time(DateUtil.getNowTimeStr());
            bluetoothUserBind.setModify_time(DateUtil.getNowTimeStr());
            bluetoothUserBind.setBind_status(BluetoothBindStatus.BIND_STATUS.getId());//已绑定
            bluetoothUserBind.setChild_lock(BluetoothChildLock.UNCHILD_LOCK.getId());//儿童锁关闭
            mapper.insertBluetoothUserBind(bluetoothUserBind);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserBindServiceImpl.insertBluetoothUserBind出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**
     * @Description 根据id更新BluetoothUserBind
     * @Author wanghongliang
     */
    @Override
    public ResponseJson updateBluetoothUserBindById(JSONObject jsonObject) {
        try {
            BluetoothUserBind bluetoothUserBind = JSONObject.toJavaObject(jsonObject, BluetoothUserBind.class);
            bluetoothUserBind.setModify_time(DateUtil.getNowTimeStr());
            mapper.updateBluetoothUserBindById(bluetoothUserBind);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserBindServiceImpl.updateBluetoothUserBindById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件更新BluetoothUserBind
     * @Author wanghongliang
     */
    @Override
    public ResponseJson updateBluetoothUserBindByCondtion(JSONObject jsonObject) {
        try {
            BluetoothUserBind bluetoothUserBind = JSONObject.toJavaObject(jsonObject, BluetoothUserBind.class);
            bluetoothUserBind.setModify_time(DateUtil.getNowTimeStr());
            mapper.updateBluetoothUserBindByCondtion(bluetoothUserBind);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserBindServiceImpl.updateBluetoothUserBindByCondtion:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 根据条件查询烟杆集合
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothUserBindByCondtions(JSONObject jsonObject){
        try {
            BluetoothUserBind bluetoothUserBind = JSONObject.toJavaObject(jsonObject, BluetoothUserBind.class);
            List<BluetoothUserBind> bindList = mapper.getBluetoothUserBindByCondtions(bluetoothUserBind);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, bindList);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserBindServiceImpl.getBluetoothUserBindByCondtions出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**
     * @Description  根据id查询单个烟杆
     * @Author wanghongliang
     */
    @Override
   public ResponseJson getBluetoothUserBindById(JSONObject jsonObject){
        try {
            BluetoothUserBind bluetoothUserBind = JSONObject.toJavaObject(jsonObject, BluetoothUserBind.class);
            BluetoothUserBind bluetoothUserBindR = mapper.getBluetoothUserBindById(bluetoothUserBind.getId());
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, bluetoothUserBindR);
        } catch (Exception e) {
            LOGGER.error("BluetoothUserBindServiceImpl.getBluetoothUserBindByCondtions出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
   }
}