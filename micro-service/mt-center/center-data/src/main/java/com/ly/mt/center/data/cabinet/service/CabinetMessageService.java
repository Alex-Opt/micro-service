package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.entity.CabinetReplenish;
import com.ly.mt.core.base.entity.ResponseJson;
import org.apache.ibatis.annotations.Param;

/**
 * 消息数据中心层
 * @author wanghongliang
 * @date 2019-09-16
 */
public interface CabinetMessageService {
    /**
     * 新增一条消息
     * @return
     */
    ResponseJson insertCabinetMessage(JSONObject jsonObject);

    /**
     * 更新消息
     *
     * @return
     */
    ResponseJson updateCabinetMessageById(JSONObject jsonObject);

    /**
     * 获取根据用户消息
     *
     * @return
     */
    ResponseJson getCabinetMessage(JSONObject jsonObject);

}