package com.ly.mt.mall.h5.profits.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.profits.service.UserTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserTaskServiceImpl extends BaseServiceImpl implements UserTaskService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserTaskServiceImpl.class);


    @Override
    public ResponseJson getNotFinshTask() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", getLoginUserId());
        try {
            String taskJson = callDataCenter(DataCenterMethod.PROFITS_USER_TASK_NOT_FINISH, jsonObject);
            if (StringUtil.isEmpty(taskJson)) {
                LOGGER.error("数据中心返回新手任务数据为空,userId={}", getLoginUserId());
                return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
            }
            JSONObject resultJson = JSONObject.parseObject(taskJson);
            JSONArray array = resultJson.getJSONArray("rows");
            Map resultMap = new HashMap();
            List list = new ArrayList();
            if (!CollectionUtils.isEmpty(array)) {
                for (int i = 0; i < array.size(); i++) {
                    JSONObject node = array.getJSONObject(i);
                    Map map = new HashMap();
                    map.put("id", node.getString("id"));
                    map.put("taskName", node.getString("taskName"));
                    map.put("taskDesc", node.getString("taskDesc"));
                    map.put("taskWork", node.getString("taskWork"));
                    map.put("taskWorkStr", getTaskWorkStr(node.getString("taskWork")));
                    list.add(map);
                }
            }
            resultMap.put("rows", list);
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, resultMap);
        } catch (Exception e) {
            LOGGER.error("调用数据中心报错！");
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description: 获取某类型的描述
     * @Author: zhuyh
     */
    private Object getTaskWorkStr(String taskWork) {
        return "类型描述";
    }
}
