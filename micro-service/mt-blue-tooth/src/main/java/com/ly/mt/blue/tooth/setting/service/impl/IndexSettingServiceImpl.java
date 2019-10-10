package com.ly.mt.blue.tooth.setting.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.setting.service.IndexSettingService;
import com.ly.mt.blue.tooth.setting.vo.BluetoothIndexDB;
import com.ly.mt.blue.tooth.taste.vo.BluetoothIndexVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_BLUETOOTH_INDEX;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * 用户设置service
 */
@Service
public class IndexSettingServiceImpl extends BaseServiceImpl implements IndexSettingService {

    private final static Logger LOGGER = LoggerFactory.getLogger(IndexSettingServiceImpl.class);
    /**
     * 保存用户设置
     * @param bluetoothIndexVoList
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson saveUserSettings(List<BluetoothIndexVo> bluetoothIndexVoList, String indexType, String macAddress) throws Exception {
        String userId = getLoginUserId();
        LOGGER.info("用户保存设置==user_id"+userId);
        if(StringUtil.isEmpty(getLoginUserId())){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
        }
        //删除用户之前设置
        JSONObject oldSetting = new JSONObject();
        oldSetting.put("index_type", indexType);
        oldSetting.put("mac_address", macAddress);
        oldSetting.put("user_id", userId);
        String delResult = callDataCenter(BLUETOOTH_INDEX_DEL, oldSetting);

        List<BluetoothIndexDB> list = new ArrayList<>();
        //循环变成数据库对象
        bluetoothIndexVoList.forEach(bluetoothIndexVo -> {
            BluetoothIndexDB  bluetoothIndexDB = new BluetoothIndexDB();
            bluetoothIndexDB.setId(SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_BLUETOOTH_INDEX));
            bluetoothIndexDB.setUser_id(userId);
            bluetoothIndexDB.setMac_address(macAddress);
            bluetoothIndexDB.setIndex_key(bluetoothIndexVo.getIndexKey());
            bluetoothIndexDB.setIndex_value(bluetoothIndexVo.getIndexValue());
            bluetoothIndexDB.setIndex_type(indexType);
            bluetoothIndexDB.setIndex_name(bluetoothIndexVo.getIndexKey());
            bluetoothIndexDB.setCreate_time(DateUtil.getNowTimeStr());
            bluetoothIndexDB.setModify_time(DateUtil.getNowTimeStr());
            list.add(bluetoothIndexDB);
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bluetoothIndexVoList", list);
        String result = callDataCenter(BLUETOOTH_INDEX_BATCH_INSERT, jsonObject);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "保存成功!");
    }

    /**
     * 获取用户设置
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson getUserSettings(String indexType, String macAddress) throws Exception {
        String userId = getLoginUserId();
        LOGGER.info("用户获取设置:user_id"+userId+"|设置类型:"+indexType+"|mac地址为:"+macAddress);
        if(StringUtil.isEmpty(getLoginUserId())){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户已过期,请您重新登录！");
        }
        JSONObject jsonObject = new JSONObject();//查询最佳匹配jsonObject
        jsonObject.put("user_id",userId);
        jsonObject.put("index_type",indexType);
        jsonObject.put("mac_address",macAddress);
        //查询用户设置
        String result = callDataCenter(BLUETOOTH_INDEX_GET, jsonObject); //查询最新添加烟弹
        //result = JsonUtils.underlineToCamel(result);//转换驼峰
        List<BluetoothIndexVo> list = JSONObject.parseObject(result, new TypeReference<List<BluetoothIndexVo>>() {});
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
    }
}