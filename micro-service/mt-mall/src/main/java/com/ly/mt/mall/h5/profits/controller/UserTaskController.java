package com.ly.mt.mall.h5.profits.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.mall.h5.profits.service.UserTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(description = "赚钱-新手任务接口")
@RestController
@RequestMapping("/mall/h5/user/task")
public class UserTaskController {

    @Autowired
    private UserTaskService taskService;

    @ApiOperation("查询新手任务")
    @PostMapping("/")
    public ResponseJson getNotFinshTask(HttpServletRequest request){
        return taskService.getNotFinshTask();
    }

}
