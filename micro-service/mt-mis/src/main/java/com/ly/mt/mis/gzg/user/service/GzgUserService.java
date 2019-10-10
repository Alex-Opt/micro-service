package com.ly.mt.mis.gzg.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * MOTI售卖柜-用户管理-用户列表
 *
 * @author taoye
 */
public interface GzgUserService {
    /**
     * 用户信息分页表格
     *
     * @author taoye
     */
    ResponseJson loadUserDatagrid(JSONObject jsonObject) throws Exception;
}
