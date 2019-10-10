package com.ly.mt.center.data.notice.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface NoticeLogsService {
    /**
     * @Description 保存NoticeLogs
     * @Author taoye
     */
    ResponseJson insertNoticeLogs(JSONObject jsonObject);

    /**
     * @Description 删除NoticeLogs
     * @Author taoye
     */
    ResponseJson deleteNoticeLogs(JSONObject jsonObject);

    /**
     * @Description 更新NoticeLogs
     * @Author taoye
     */
    ResponseJson updateNoticeLogs(JSONObject jsonObject);

    /**
     * @Description 查询NoticeLogs
     * @Author taoye
     */
    ResponseJson getNoticeLogs(JSONObject jsonObject);
}