package com.ly.mt.activity.server.wechat.server.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.server.wechat.mapper.ActivityTaskMasterMapper;
import com.ly.mt.activity.server.wechat.mapper.ActivityTaskSubMapper;
import com.ly.mt.activity.server.wechat.server.ActivityTaskService;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.entity.hd.ActivityTaskSub;
import com.ly.mt.core.common.entity.hd.dto.ActivityTaskMasterDto;
import com.ly.mt.core.common.entity.hd.dto.ActivityTaskSubDto;
import com.ly.mt.core.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 活动任务操作
 *
 * @author pjt
 */
@Slf4j
@Service
public class ActivityTaskServiceImpl implements ActivityTaskService {

    @Resource
    ActivityTaskMasterMapper taskMasterMapper;
    @Resource
    ActivityTaskSubMapper taskSubMapper;


    @Override
    public JSONObject tasks(JSONObject jsonObject) {

        try {
            Long activityId = Long.valueOf(jsonObject.getString("activityId"));
            List<ActivityTaskMasterDto> list = taskMasterMapper.findTasks(activityId);
            return JsonUtil.getSuccessJson(list);
        } catch (Exception e) {
            log.error("tasks,查询全部异常,e={}", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_FIND_ERROR);
        }

    }

    @Override
    public JSONObject taskSubs(JSONObject jsonObject) {
        try {
            Long taskMasterId = Long.valueOf(jsonObject.getString("taskId"));
            List<ActivityTaskSubDto> dtos = findSubs(taskMasterId);
            return JsonUtil.getSuccessJson(dtos);
        } catch (Exception e) {
            log.error("taskSubs，查询失败,e={}", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_FIND_ERROR);
        }
    }

    @Override
    public JSONObject task(JSONObject jsonObject) {

        try {
            Long taskId = Long.valueOf(jsonObject.getString("taskId"));
            ActivityTaskMasterDto dto = taskMasterMapper.findById(taskId);
            dto.setSubs(findSubs(taskId));
            return JsonUtil.getSuccessJson(dto);
        } catch (Exception e) {
            log.error("task,查询失败，e={}",e);
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_FIND_ERROR);
        }

    }
    

    private List<ActivityTaskSubDto> findSubs(Long taskMasterId) {
        List<ActivityTaskSub> list = taskSubMapper.findByTaskMasterId(taskMasterId);
        List<ActivityTaskSubDto> dtos = new ArrayList<>(list.size());
        list.forEach(l -> {
            ActivityTaskSubDto dto = new ActivityTaskSubDto();
            BeanUtils.copyProperties(l, dto);
            dtos.add(dto);
        });
        return dtos;
    }

}
