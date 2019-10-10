package com.ly.mt.blue.tooth.setting.service;

import com.ly.mt.blue.tooth.taste.vo.BluetoothIndexVo;
import com.ly.mt.core.base.entity.ResponseJson;

import java.util.List;

/**
 * 用户设置service操作接口
 */
public interface IndexSettingService {
    /**
     * 保存用户设置
     * @throws Exception
     */
    ResponseJson saveUserSettings(List<BluetoothIndexVo> bluetoothIndexVoSetting, String indexType, String macAddress) throws Exception;

    /**
     * 获取用户设置
     * @throws Exception
     */
    ResponseJson getUserSettings(String indexType, String macAddress) throws Exception;
}