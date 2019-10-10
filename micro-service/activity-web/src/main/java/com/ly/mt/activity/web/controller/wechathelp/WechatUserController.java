package com.ly.mt.activity.web.controller.wechathelp;

import com.alibaba.fastjson.JSONObject;
import com.gexin.fastjson.JSON;
import com.google.gson.JsonObject;
import com.ly.mt.activity.web.annotations.TimingMethodDown;
import com.ly.mt.activity.web.controller.BaseController;
import com.ly.mt.activity.web.request.MotiPicCommit;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.entity.hd.dto.UserSendCOdeRequestDto;
import com.ly.mt.core.common.entity.hd.request.*;
import com.ly.mt.core.common.method.ActivityMethodEnum;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.ly.mt.core.common.constant.FilePathEnum.FILE_PATH_ACTIVITY_ZHULI;
import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_PARAM_ERROR;
import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR;


/**
 * @author
 * @description 微信用户操作
 * 保存及其拉去微信用户信息
 * @date 2019/8/14 11:08 AM
 */

@Api("蓝牙助力，拉去及其保存微信用户信息使用")
@RestController
@RequestMapping("/activity/activity/wechat")
@Slf4j
public class WechatUserController extends BaseController {

    @ApiOperation(value = "保存微信用户个人信息")
    //@TimingMethodDown(downtime = "{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 25,\"hour\": 23,\"minute\": 59,\"second\": 59}")
    @PostMapping("/addWechatUser")
    public JSONObject addWechatUser(@RequestBody WechatUserRequestBody body, HttpServletRequest request) {
        if(null == body){
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        JSONObject resultJson = callActivityServer(ActivityMethodEnum.WECHAT_USER_ADD, jsonObject);
        if("0".equals(resultJson.getString("code"))){
            HttpSession session = request.getSession();
            session.setAttribute("openId",body.getOpenId());
            JSONObject user = resultJson.getJSONObject("result");
            session.setAttribute("user", resultJson.getString("result"));
            session.setAttribute("userId", user.getString("id"));
        }
        return resultJson;
    }


    @ApiOperation(value = "查询用户是否参与活动数据", notes = "查询数据接口")
    //@TimingMethodDown(downtime = "{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 25,\"hour\": 23,\"minute\": 59,\"second\": 59}")
    @PostMapping("/queryHelpMasterByUserId")
    public JSONObject queryHelpMasterByUserId(@RequestBody BaseWechatHelpReauesyBody body, HttpServletRequest request) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.HELP_MASTER_BY_USERID, jsonObject);
    }

    @ApiOperation(value = "根据活动id，查询活动任务主表数据", notes = "查询数据接口")
    //@TimingMethodDown(downtime = "{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 25,\"hour\": 23,\"minute\": 59,\"second\": 59}")
    @PostMapping("/queryTaskMasterByActiId")
    public JSONObject queryTaskMasterByActiId(@RequestBody BaseWechatHelpReauesyBody body) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.TASK_MASTER_BY_ACTIID, jsonObject);
    }

    @ApiOperation(value = "保存用户选择的活动类型", notes = "保存数据接口")
    //@TimingMethodDown(downtime = "{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 25,\"hour\": 23,\"minute\": 59,\"second\": 59}")
    @PostMapping("/saveHelpMaster")
    public JSONObject saveHelpMaster(@RequestBody WechatHelpUserRequestBody body, HttpServletRequest request) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.HELP_MASTER_SAVE, jsonObject);
    }

    @ApiOperation(value = "根据任务id，查询子任务数据", notes = "查询数据接口")
    //@TimingMethodDown(downtime = "{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 25,\"hour\": 23,\"minute\": 59,\"second\": 59}")
    @PostMapping("/queryTaskSubByTaskId")
    public JSONObject queryTaskSubByTaskId(@RequestBody WechatHelpUserRequestBody body) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.TASK_SUB_BY_TASKID, jsonObject);
    }

    @ApiOperation(value = "校验手机号和验证码", notes = "保存手机号、验证码接口")
    //@TimingMethodDown(downtime = "{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 25,\"hour\": 23,\"minute\": 59,\"second\": 59}")
    @PostMapping("/checkMobileAndCode")
    public JSONObject checkMobileAndCode(@RequestBody PhoneCodeRequestBody body) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.HELP_UPDATE_PHONE, jsonObject);
    }

    @ApiOperation(value = "根据活动id和userId查询助力数据", notes = "根据活动id和userId查询助力数据")
    //@TimingMethodDown(downtime = "{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 25,\"hour\": 23,\"minute\": 59,\"second\": 59}")
    @PostMapping("/queryHelpSubByUserId")
    public JSONObject queryHelpSubByUserId(@RequestBody BaseWechatHelpReauesyBody body) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.HELP_SUB_BY_OPENID, jsonObject);
    }

    @ApiOperation(value = "保存助力用户的数据", notes = "保存助力用户的数据")
    //@TimingMethodDown(downtime = "{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 25,\"hour\": 23,\"minute\": 59,\"second\": 59}")
    @PostMapping("/saveHelpSub")
    public JSONObject saveHelpSub(@RequestBody DoHelpRequestBody body) {
        log.error("saveHelpSub of WechatUserController jsonParam={}", JSON.toJSONString(body));
        log.info("saveHelpSub of WechatUserController jsonParam={}", JSON.toJSONString(body));
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.HELP_SUB_SAVE, jsonObject);
    }
    @ApiOperation(value = "上传moti图片", notes = "上传moti图片")
    //@TimingMethodDown(downtime = "{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 25,\"hour\": 23,\"minute\": 59,\"second\": 59}")
    @PostMapping("/upload")
    public JSONObject upload(@ApiParam(value = "图片", name = "file", required = true) MultipartFile file){
        // 校验参数
        if (null == file) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        String uploadJson;
        JSONObject result = new JSONObject();
        try {
            uploadJson = ossServer.upload(file, FILE_PATH_ACTIVITY_ZHULI, null);
            result.put("uploadJson", uploadJson);
        } catch (Exception e) {
            log.error("文件上传阿里云出错：", e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        return JsonUtil.getSuccessJson(result);
    }

    @ApiOperation("提交moti照片")
    //@TimingMethodDown(downtime = "{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 25,\"hour\": 23,\"minute\": 59,\"second\": 59}")
    @PostMapping("/motiPicCommit")
    public JSONObject motiPicCommit(@RequestBody @Valid MotiPicCommit motiPicCommit, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_PARAM_ERROR);
        }
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(motiPicCommit));
        return callActivityServer(ActivityMethodEnum.HELP_MASTER_PICTURE_SAVE,jsonObject);
    }

    @ApiOperation(value = "兑换优惠码", notes = "兑换优惠码")
    //@TimingMethodDown(downtime = "{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 25,\"hour\": 23,\"minute\": 59,\"second\": 59}")
    @PostMapping("/queryUserCouponCode")
    public JSONObject queryUserCouponCode(@RequestBody BaseWechatHelpReauesyBody body) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.USER_COUPON_CODE_GET, jsonObject);
    }


    @ApiOperation("查询参加人数")
    //@TimingMethodDown(downtime = "{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 25,\"hour\": 23,\"minute\": 59,\"second\": 59}")
    @PostMapping("/userCount")
    public JSONObject userCount(){
        return callActivityServer(ActivityMethodEnum.USER_FIND_USER_COUNT,new JSONObject(1));

    }

    @ApiOperation("发送验证码")
    //@TimingMethodDown(downtime = "{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 25,\"hour\": 23,\"minute\": 59,\"second\": 59}")
    @PostMapping("/sendCode")
    public JSONObject sendCode(@RequestBody UserSendCOdeRequestDto body){
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.HELP_SEND_CODE, jsonObject);
    }


    @ApiOperation("根据openid和活动后id查询兑换码")
    @PostMapping("/openId/code")
    public JSONObject queryCode(@RequestBody QueryCodeRequestBody body){
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(body));
        return callActivityServer(ActivityMethodEnum.USER_FIND_CODE_BY_OPENID,jsonObject);
    }
}
