package com.ly.mt.cabinet.c.rujin.service;

import com.ly.mt.core.base.entity.ResponseJson;


public interface GzgRujinRelationService {
    /**
     * 绑定如金格子柜并更新数据库信息
     * @param
     * @return
     */
    ResponseJson bindGzgRujin() throws Exception;

    /**
     * 更新如金柜子绑定信息
     * @param skuid
     * @return
     */
    ResponseJson updateGzgRujinRelationById(String skuid);
}
