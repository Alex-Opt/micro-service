package com.ly.mt.center.data.notice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.notice.mapper.NoticeLogsMapper;
import com.ly.mt.center.data.notice.service.NoticeLogsService;
import com.ly.mt.center.data.notice.entity.NoticeLogs;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class NoticeLogsServiceImpl extends BaseServiceImpl implements NoticeLogsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(NoticeLogsServiceImpl.class);
    @Resource
    NoticeLogsMapper mapper;

    /**
     * @Description 保存NoticeLogs
     * @Author taoye
     */
    @Override
    public ResponseJson insertNoticeLogs(JSONObject jsonObject) {
        try {
            NoticeLogs noticeLogs = JSONObject.toJavaObject(jsonObject, NoticeLogs.class);
            if (StringUtil.isEmpty(noticeLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertNoticeLogs(noticeLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("NoticeLogsServiceImpl.insertNoticeLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除NoticeLogs
     * @Author taoye
     */
    @Override
    public ResponseJson deleteNoticeLogs(JSONObject jsonObject) {
        try {
            NoticeLogs noticeLogs = JSONObject.toJavaObject(jsonObject, NoticeLogs.class);
            if (StringUtil.isEmpty(noticeLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteNoticeLogs(noticeLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("NoticeLogsServiceImpl.deleteNoticeLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新NoticeLogs
     * @Author taoye
     */
    @Override
    public ResponseJson updateNoticeLogs(JSONObject jsonObject) {
        try {
            NoticeLogs noticeLogs = JSONObject.toJavaObject(jsonObject, NoticeLogs.class);
            if (StringUtil.isEmpty(noticeLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateNoticeLogs(noticeLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("NoticeLogsServiceImpl.updateNoticeLogsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询NoticeLogs
     * @Author taoye
     */
    @Override
    public ResponseJson getNoticeLogs(JSONObject jsonObject) {
        try {
            NoticeLogs noticeLogs = JSONObject.toJavaObject(jsonObject, NoticeLogs.class);
            if (StringUtil.isEmpty(noticeLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            noticeLogs = mapper.getNoticeLogs(noticeLogs);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, noticeLogs);
        } catch (Exception e) {
            LOGGER.error("NoticeLogsServiceImpl.getNoticeLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}