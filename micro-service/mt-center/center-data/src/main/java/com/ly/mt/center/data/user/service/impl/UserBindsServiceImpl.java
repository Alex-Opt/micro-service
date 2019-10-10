package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserBindsMapper;
import com.ly.mt.center.data.user.service.UserBindsService;
import com.ly.mt.center.data.user.entity.UserBinds;
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
public class UserBindsServiceImpl extends BaseServiceImpl implements UserBindsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserBindsServiceImpl.class);
    @Resource
    UserBindsMapper mapper;

    /**
     * @Description 保存UserBinds
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserBinds(JSONObject jsonObject) {
        try {
            UserBinds userBinds = JSONObject.toJavaObject(jsonObject, UserBinds.class);
            if (StringUtil.isEmpty(userBinds.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertUserBinds(userBinds);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserBindsServiceImpl.insertUserBinds出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除UserBinds
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserBinds(JSONObject jsonObject) {
        try {
            UserBinds userBinds = JSONObject.toJavaObject(jsonObject, UserBinds.class);
            if (StringUtil.isEmpty(userBinds.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteUserBinds(userBinds);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserBindsServiceImpl.deleteUserBinds出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新UserBinds
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserBinds(JSONObject jsonObject) {
        try {
            UserBinds userBinds = JSONObject.toJavaObject(jsonObject, UserBinds.class);
            if (StringUtil.isEmpty(userBinds.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateUserBinds(userBinds);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserBindsServiceImpl.updateUserBindsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询UserBinds
     * @Author taoye
     */
    @Override
    public ResponseJson getUserBinds(JSONObject jsonObject) {
        try {
            UserBinds userBinds = JSONObject.toJavaObject(jsonObject, UserBinds.class);
            if (StringUtil.isEmpty(userBinds.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            userBinds = mapper.getUserBinds(userBinds);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userBinds);
        } catch (Exception e) {
            LOGGER.error("UserBindsServiceImpl.getUserBinds出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}