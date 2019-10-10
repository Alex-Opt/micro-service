package com.ly.mt.gzg.b.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.method.GzgBMethodEnum;
import com.ly.mt.gzg.b.web.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * gzgb info控制层
 */
@Api("gzgb info控制层")
@RestController
@RequestMapping("/gzgb/info")
@Slf4j
public class GzgInfoController extends BaseController {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(HotelController.class);

    @ApiOperation(value = "保存格子柜", notes = "保存格子柜")
    @PostMapping("/save")
    public JSONObject save(@RequestBody JSONObject req){
        Logger.info("GzgInfoController=>save:param{}",req);
        return callGzgBServer(GzgBMethodEnum.GZG_INFO_SAVE, req);
    }

    @ApiOperation(value = "获取格子柜列表", notes = "获取格子柜列表")
    @GetMapping("/getInfos")
    public JSONObject getInfos(@ApiParam(value = "酒店ID", name = "hotelId", required = true) @RequestParam Long hotelId){
        Logger.info("GzgInfoController=>getInfos:hotelId{}",hotelId);
        JSONObject param = new JSONObject();
        param.put("hotelId",hotelId);
        return callGzgBServer(GzgBMethodEnum.GZG_GET_INFOS,param);
    }

    @ApiOperation(value = "保存用户格子柜关系", notes = "保存用户格子柜关系")
    @PostMapping("/saveGzgUserRelation")
    public JSONObject saveGzgUserRelation(@RequestBody JSONObject req){
        Logger.info("GzgInfoController=>saveGzgUserRelation:param{}",req);
        return callGzgBServer(GzgBMethodEnum.GZG_BUSER_RELATION_SAVE,req);
    }

    @ApiOperation(value = "根据手机号模糊查询用户列表", notes = "根据手机号模糊查询用户列表")
    @GetMapping("/getUserByGzgIdLikeMobile")
    public JSONObject getUserByGzgIdLikeMobile(@ApiParam(value = "格子柜ID", name = "gzgId", required = true) @RequestParam Long gzgId,
                                             @ApiParam(value = "手机号", name = "mobile", required = true) @RequestParam String mobile){
        log.info("getUserByGzgIdLikeMobile gzgId={} mobile={}",gzgId,mobile);
        //Logger.info("GzgInfoController=>getUserByGzgIdLikeMobile:gzgId="+gzgId + " mobile=" + mobile);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gzgId",gzgId);
        jsonObject.put("mobile",mobile);
        return callGzgBServer(GzgBMethodEnum.GZG_GET_BUSER_BY_NICKNAME,jsonObject);
    }

    @ApiOperation(value = "删除用户格子柜关系", notes = "删除用户格子柜关系")
    @GetMapping("/delGzgUserRelation")
    public JSONObject delGzgUserRelation(@ApiParam(value = "格子柜ID", name = "gzgId", required = true) @RequestParam Long gzgId,
                                         @ApiParam(value = "用户ID", name = "userId", required = true) @RequestParam String userId){
        Logger.info("GzgInfoController=>saveGzgUserRelation:gzgId="+gzgId + " userId=" + userId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gzgId",gzgId);
        jsonObject.put("userId",userId);
        return callGzgBServer(GzgBMethodEnum.GZG_DEL_GZG_USER_RELATION,jsonObject);
    }

    @GetMapping("/getGzgUsers")
    public JSONObject getGzgUsers(@ApiParam(value = "格子柜ID", name = "gzgId", required = true) @RequestParam Long gzgId){
        Logger.info("GzgInfoController=>getGzgUsers:gzgId="+gzgId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gzgId",gzgId);
        return callGzgBServer(GzgBMethodEnum.GZG_GET_USER_GZG_RE,jsonObject);
    }
    @GetMapping("/getGzgPlans")
    public JSONObject getGzgPlans(HttpServletRequest request){
        log.info("call getGzgPlans");
        JSONObject json = new JSONObject();
        json.put("userId","userId");
        return callGzgBServer(GzgBMethodEnum.GZG_PLAN,json);
    }
}
