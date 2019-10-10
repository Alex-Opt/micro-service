package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgBUserMapper;
import com.ly.mt.center.data.gzg.service.GzgBUserService;
import com.ly.mt.center.data.gzg.entity.GzgBUser;
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
public class GzgBUserServiceImpl extends BaseServiceImpl implements GzgBUserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgBUserServiceImpl.class);
    @Resource
    GzgBUserMapper mapper;

    /**
     * @Description 保存GzgBUser
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgBUser(JSONObject jsonObject) {
        try {
            GzgBUser gzgBUser = JSONObject.toJavaObject(jsonObject, GzgBUser.class);
            if (StringUtil.isEmpty(gzgBUser.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgBUser(gzgBUser);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgBUserServiceImpl.insertGzgBUser出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgBUser
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgBUser(JSONObject jsonObject) {
        try {
            GzgBUser gzgBUser = JSONObject.toJavaObject(jsonObject, GzgBUser.class);
            if (StringUtil.isEmpty(gzgBUser.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgBUser(gzgBUser);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgBUserServiceImpl.deleteGzgBUser出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgBUser
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgBUser(JSONObject jsonObject) {
        try {
            GzgBUser gzgBUser = JSONObject.toJavaObject(jsonObject, GzgBUser.class);
            if (StringUtil.isEmpty(gzgBUser.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgBUser(gzgBUser);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgBUserServiceImpl.updateGzgBUserById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgBUser
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgBUser(JSONObject jsonObject) {
        try {
            GzgBUser gzgBUser = JSONObject.toJavaObject(jsonObject, GzgBUser.class);
            if (StringUtil.isEmpty(gzgBUser.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgBUser = mapper.getGzgBUser(gzgBUser);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgBUser);
        } catch (Exception e) {
            LOGGER.error("GzgBUserServiceImpl.getGzgBUser出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}