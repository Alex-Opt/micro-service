package com.ly.mt.cabinet.b.user.service;

import com.ly.mt.core.base.entity.ResponseJson;

public interface CabinetMessageService {
    /**
     * 获取未读消息
     * @throws Exception
     */
    ResponseJson getMessage() throws Exception;

    /**
     * 更新消息状态
     * @throws Exception
     */
    ResponseJson updateMessageStatus(String messageType) throws Exception;
}