package com.ly.mt.gzg.b.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.entity.gzg.request.StayReplenishReqVO;
import com.ly.mt.core.common.entity.gzg.request.WyReplenishReqVO;
import com.ly.mt.core.common.method.GzgBMethodEnum;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.gzg.b.web.base.controller.BaseController;
import com.ly.mt.gzg.b.web.config.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ly.mt.core.common.constant.FilePathEnum.GZG_B_CABINET_CHECK_PIC;
import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_PARAM_ERROR;
import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR;

@Api(tags = {"补货接口"})
@RestController
@RequestMapping("/gzgb/replenish")
@Slf4j
public class ReplenishController extends BaseController {

    @Autowired
    private HttpServletResponse response;

    @ApiOperation("补货列表")
    @GetMapping("/replenishList")
    @Permission
    public JSONObject replenishList(HttpServletRequest request, HttpServletResponse response) {
        long userId = (Long) request.getAttribute("userId");
        JSONObject json = new JSONObject();
        json.put("userId", userId);
        JSONObject data = callGzgBServer(GzgBMethodEnum.GZG_B_REPLENISH, json);
        log.info("call {} 接口 {} 方法 返回结果是:{}", GzgBMethodEnum.GZG_B_REPLENISH.getServiceName(), GzgBMethodEnum.GZG_B_REPLENISH.getFunctionName(), data);
        response.setCharacterEncoding("UTF-8");
        return data;
    }

    @ApiOperation("放弃补货")
    @PostMapping("/giveUpReplenish")
    @Permission
    public JSONObject giveUpReplenish(HttpServletRequest request, @RequestBody WyReplenishReqVO wyReplenishReqVO) {
        long userId = (Long) request.getAttribute("userId");
        JSONObject json = new JSONObject();
        json.put("userId", userId);
        json.put("gzgCode", wyReplenishReqVO.getGzgCode());
        json.put("cabinetNo", wyReplenishReqVO.getCabinetNo());
        json.put("replenishOrderId", wyReplenishReqVO.getReplenishOrderId());
        response.setCharacterEncoding("UTF-8");
        return callGzgBServer(GzgBMethodEnum.GIVE_UP_REPLENISH, json);
    }

    @ApiOperation("我要补货")
    @PostMapping("/wyReplenish")
    @Permission
    public JSONObject wyReplenish(HttpServletRequest request, @RequestBody WyReplenishReqVO wyReplenishReqVO) {

        long userId = (Long) request.getAttribute("userId");
        JSONObject json = new JSONObject();
        json.put("userId", userId);
        json.put("replenishOrderId", wyReplenishReqVO.getReplenishOrderId());
        json.put("gzgCode", wyReplenishReqVO.getGzgCode());
        json.put("cabinetNo", wyReplenishReqVO.getCabinetNo());
        response.setCharacterEncoding("UTF-8");
        return callGzgBServer(GzgBMethodEnum.WY_REPLENISH, json);
    }

    @ApiOperation("待补货")
    @GetMapping("/stayReplenish")
    @Permission
    public JSONObject stayReplenish(HttpServletRequest request, @RequestBody StayReplenishReqVO stayReplenishReqVO) {
        long userId = (Long) request.getAttribute("userId");
        JSONObject json = new JSONObject();
        json.put("userId", userId);
        json.put("gzgCode", stayReplenishReqVO.getGzgCode());
        json.put("cabinetNo", stayReplenishReqVO.getGzgCode());
        response.setCharacterEncoding("UTF-8");
        return callGzgBServer(GzgBMethodEnum.GZG_B_STAY_REPLENISH, json);
    }

    @ApiOperation("商品校验")
    @GetMapping("/goodsCheck")
    @Permission
    public JSONObject goodsCheck(HttpServletRequest request, @ApiParam(value = "商品条形码", name = "goodsBarCode", required = true) @RequestParam String goodsBarCode,
                                 @ApiParam(value = "补货订单Id", name = "replenishOrderId", required = true) @RequestParam String replenishOrderId) {
        long userId = (Long) request.getAttribute("userId");
        if (goodsBarCode == null || StringUtils.isBlank(goodsBarCode)) {
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_PARAM_ERROR);
        }
        if (StringUtils.length(goodsBarCode) > 11 || StringUtils.length(goodsBarCode) < 11){
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_PARAM_ERROR);
        }
        JSONObject json = new JSONObject();
        json.put("goodsBarCode", goodsBarCode);
        json.put("userId", userId);
        json.put("replenishOrderId", replenishOrderId);
        response.setCharacterEncoding("UTF-8");
        return callGzgBServer(GzgBMethodEnum.GZG_B_GOODS_CHECK, json);
    }

    @ApiOperation("格子柜校验")
    @GetMapping("/cabinetCheck")
    @Permission
    public JSONObject cabinetCheck(HttpServletRequest request, @ApiParam(value = "格子柜编号", name = "gzgCode", required = true) @RequestParam String gzgCode,
                                   @ApiParam(value = "格子号", name = "cabinetNo", required = true) @RequestParam String cabinetNo) {
        long userId = (Long) request.getAttribute("userId");
        JSONObject json = new JSONObject();
        json.put("userId", userId);
        json.put("gzgCode", gzgCode);
        json.put("cabinetNo", cabinetNo);
        response.setCharacterEncoding("UTF-8");
        return callGzgBServer(GzgBMethodEnum.GZG_B_CABINET_CHECK, json);
    }

    @ApiOperation(value = "上传校验图片", notes = "上传校验图片")
    @PostMapping("/uploadPicture")
    @Permission
    public JSONObject uploadPicture(HttpServletRequest request, @ApiParam(value = "校验图片", name = "file", required = true) MultipartFile file,
                                    @ApiParam(value = "格子柜编号", name = "gzgCode", required = true) String gzgCode,
                                    @ApiParam(value = "格子柜格子号", name = "cabinetNo", required = true) String cabinetNo) {
        long userId = (Long) request.getAttribute("userId");
        // 校验参数
        if (null == file) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        String uploadJson;
        JSONObject result;
        try {
            uploadJson = ossServer.upload(file, GZG_B_CABINET_CHECK_PIC, null);
            JSONObject json = new JSONObject();
            json.put("userId", userId);
            json.put("gzgCode", gzgCode);
            json.put("cabinetNo", cabinetNo);
            result = callGzgBServer(GzgBMethodEnum.GZG_UPLOAD_FILE, json);
            result.put("uploadJson", uploadJson);
        } catch (Exception e) {
            log.error("文件上传阿里云出错：", e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        return JsonUtil.getSuccessJson(result);
    }

    @ApiOperation("提交审核")
    @GetMapping("/commitAudit")
    @Permission
    public JSONObject commitAudit(HttpServletRequest request, @ApiParam(value = "校验图片", name = "checkPicture", required = true) @RequestParam String checkPicture,
                                  @ApiParam(value = "补货订单Id", name = "replenishOrderId", required = true) @RequestParam String replenishOrderId) {
        long userId = (Long) request.getAttribute("userId");
        JSONObject json = new JSONObject();
        json.put("userId", userId);
        json.put("replenishOrderId", replenishOrderId);
        json.put("checkPicture", checkPicture);
        return callGzgBServer(GzgBMethodEnum.GZG_B_COMMIT_AUDIT, json);

    }

    @ApiOperation("补货详情")
    @GetMapping("/replenishDetail")
    @Permission
    public JSONObject replenishDetail(HttpServletRequest request,@RequestParam String replenishOrderId) {
        long userId = (Long) request.getAttribute("userId");
        JSONObject json = new JSONObject();
        json.put("userId", userId);
        if (StringUtils.isBlank(replenishOrderId)){
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_PARAM_ERROR);
        }
        json.put("replenishOrderId",replenishOrderId);
        JSONObject result = callGzgBServer(GzgBMethodEnum.GZG_REPLENISH_DETAIL, json);
        log.info("replenishDetail result data={}",result);
        return result;
    }

    @ApiOperation("check")
    @GetMapping("/check")
    @Permission
    public JSONObject check(HttpServletRequest request) {
        return null;
    }

    @ApiOperation("规则页面")
    @GetMapping("/replenishRuleInfo")
    @Permission
    public JSONObject replenishRuleInfo(HttpServletRequest request, @ApiParam(value = "补货订单id", name = "replenishOrderId", required = true) @RequestParam String replenishOrderId) {
        log.info("replenishRuleInfo jsonParams={}",replenishOrderId);
        long userId = (Long) request.getAttribute("userId");
        JSONObject json = new JSONObject();
        json.put("userId", userId);
        if (StringUtils.isBlank(replenishOrderId)){
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_PARAM_ERROR);
        }
        json.put("replenishOrderId", replenishOrderId);
        JSONObject jsonObject = callGzgBServer(GzgBMethodEnum.GZG_B_RULE_INFO, json);
        log.info("replenishRuleInfo resultData={}",jsonObject);
        return jsonObject;
    }
}
