package com.ly.mt.center.data.hd.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.hd.mapper.HdActivityUserMapper;
import com.ly.mt.center.data.hd.service.HdActivityUserService;
import com.ly.mt.center.data.hd.entity.HdActivityUser;
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
public class HdActivityUserServiceImpl extends BaseServiceImpl implements HdActivityUserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(HdActivityUserServiceImpl.class);
    @Resource
    HdActivityUserMapper mapper;

    /**
     * @Description 保存HdActivityUser
     * @Author taoye
     */
    @Override
    public ResponseJson insertHdActivityUser(JSONObject jsonObject) {
        try {
            HdActivityUser hdActivityUser = JSONObject.toJavaObject(jsonObject, HdActivityUser.class);
            if (StringUtil.isEmpty(hdActivityUser.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertHdActivityUser(hdActivityUser);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdActivityUserServiceImpl.insertHdActivityUser出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除HdActivityUser
     * @Author taoye
     */
    @Override
    public ResponseJson deleteHdActivityUser(JSONObject jsonObject) {
        try {
            HdActivityUser hdActivityUser = JSONObject.toJavaObject(jsonObject, HdActivityUser.class);
            if (StringUtil.isEmpty(hdActivityUser.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteHdActivityUser(hdActivityUser);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdActivityUserServiceImpl.deleteHdActivityUser出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新HdActivityUser
     * @Author taoye
     */
    @Override
    public ResponseJson updateHdActivityUser(JSONObject jsonObject) {
        try {
            HdActivityUser hdActivityUser = JSONObject.toJavaObject(jsonObject, HdActivityUser.class);
            if (StringUtil.isEmpty(hdActivityUser.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateHdActivityUser(hdActivityUser);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdActivityUserServiceImpl.updateHdActivityUserById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询HdActivityUser
     * @Author taoye
     */
    @Override
    public ResponseJson getHdActivityUser(JSONObject jsonObject) {
        try {
            HdActivityUser hdActivityUser = JSONObject.toJavaObject(jsonObject, HdActivityUser.class);
            if (StringUtil.isEmpty(hdActivityUser.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            hdActivityUser = mapper.getHdActivityUser(hdActivityUser);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, hdActivityUser);
        } catch (Exception e) {
            LOGGER.error("HdActivityUserServiceImpl.getHdActivityUser出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}