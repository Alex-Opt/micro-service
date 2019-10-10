package com.ly.mt.cabinet.b.controller;
import	java.sql.Ref;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.annotation.AccessRequired;
import com.ly.mt.cabinet.b.common.constant.Constant;
import com.ly.mt.cabinet.b.common.dict.CabinetStoreStatusEnum;
import com.ly.mt.cabinet.b.common.dict.IsSignContractEnum;
import com.ly.mt.cabinet.b.common.dict.StoreCreateStatusEnum;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.*;
import com.ly.mt.cabinet.b.common.response.CreateCooperationResVO;
import com.ly.mt.cabinet.b.entity.CabinetStore;
import com.ly.mt.cabinet.b.service.CabinetBussinessCoorperationService;
import com.ly.mt.cabinet.b.service.CabinetOptionsService;
import com.ly.mt.cabinet.b.util.DateUtil;
import com.ly.mt.cabinet.b.util.ParameterValid;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.cabinet.b.util.RespCode;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;

@Api(tags = "cooperation")
@RestController
@RequestMapping("/cabinet/b/coorperation")
public class CooperationController extends BaseMessageController{

    private static final Logger log = LoggerFactory.getLogger(CooperationController.class);

    private static final Integer CONTRACT_PIC_TYPE = 0;
    private static final Integer COORPERATION_PIC_TYPE = 1;
    @Resource
    private CabinetOptionsService cabinetOptionsService;

    @Resource
    private CabinetBussinessCoorperationService cabinetBussinessCoorperationService;

    @ApiOperation("合作信息选项")
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功"),
            @ApiResponse(code = 1,message = "操作失败")
    })
    @GetMapping("/cooperationOptions")
    @AccessRequired
    public Resp cooperationOptions(HttpServletRequest request){
        //String phoneNo = getTokenUserMessage(request).getPhoneNo();
        log.info("call method cooperationOptions at {}", DateUtil.currentDateTime());
        long start = System.currentTimeMillis();
        Resp options = cabinetOptionsService.options();
        long end = System.currentTimeMillis();
        log.info("call method cooperationOptions cost {} mills",(end - start));
        return options;
    }

    @ApiOperation("格子柜保存合作")
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功"),
            @ApiResponse(code = 1,message = "系统异常")
    })
    @PostMapping("/cabCoorperationSave")
    @AccessRequired
    public Resp coorperationSave(HttpServletRequest request, @RequestBody CoorpReqVo coorpReqVo){
        Resp result = cabinetBussinessCoorperationService.cabCoorperationSave(coorpReqVo);
        return result;
    }
    @ApiOperation("格子柜创建合作")
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功"),
            @ApiResponse(code = 1,message = "系统异常")
    })
    @PostMapping("/cabCoorperationCreate")
    @AccessRequired
    public Resp coorperationCreate(HttpServletRequest request, @RequestBody CoorpReqVo coorpReqVo){
        if (StringUtils.isEmpty(coorpReqVo.getBd_phone()) || StringUtils.isEmpty(coorpReqVo.getOwner_phone())){
            return Resp.createMessage(RespCode.BD_PHONE_OR_OWNER_PHONE_IS_NULL);
        }
        if (coorpReqVo.getIs_sign_contract() == IsSignContractEnum.YES.getKey() && (StringUtils.isEmpty(coorpReqVo.getLose_effect_datetime()) || StringUtils.isEmpty(coorpReqVo.getTake_effect_datetime()))){
            return Resp.createMessage(RespCode.SIGN_CONTRACT_DATETIME_IS_NULL);
        }
        Resp result = cabinetBussinessCoorperationService.cabCoorperationCreate(coorpReqVo);
        return result;
    }

    @ApiOperation("展柜保存合作")
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功"),
            @ApiResponse(code = 1,message = "系统异常")
    })
    @PostMapping("/caseCoorperationSave")
    @AccessRequired
    public Resp caseCoorperationSave(HttpServletRequest request,@RequestBody CoorpReqVo coorpReqVo){
        return cabinetBussinessCoorperationService.caseCoorperationSave(coorpReqVo);
    }

    @ApiOperation("展柜创建合作")
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功"),
            @ApiResponse(code = 1,message = "系统异常")
    })
    @PostMapping("/caseCoorperationCreate")
    @AccessRequired
    public Resp caseCoorperationCreate(HttpServletRequest request,@RequestBody CoorpReqVo coorpReqVo){
        if (StringUtils.isEmpty(coorpReqVo.getBd_phone()) || StringUtils.isEmpty(coorpReqVo.getOwner_phone())){
            return Resp.createMessage(RespCode.BD_PHONE_OR_OWNER_PHONE_IS_NULL);
        }
        if (coorpReqVo.getIs_sign_contract() == IsSignContractEnum.YES.getKey() && (StringUtils.isEmpty(coorpReqVo.getLose_effect_datetime()) || StringUtils.isEmpty(coorpReqVo.getTake_effect_datetime()))){
            return Resp.createMessage(RespCode.SIGN_CONTRACT_DATETIME_IS_NULL);
        }
        return cabinetBussinessCoorperationService.caseCoorperationCreate(coorpReqVo);
    }

    @ApiOperation("上线展柜合作")
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功"),
            @ApiResponse(code = 1,message = "系统异常")
    })
    @PostMapping("/caseCoorperationOnline")
    @AccessRequired
    public Resp caseCoorperationOnline(HttpServletRequest request,@RequestBody CoorpReqVo coorpReqVo){
        if (StringUtils.isEmpty(coorpReqVo.getBd_phone()) || StringUtils.isEmpty(coorpReqVo.getOwner_phone())){
            return Resp.createMessage(RespCode.BD_PHONE_OR_OWNER_PHONE_IS_NULL);
        }
        if (coorpReqVo.getIs_sign_contract() == IsSignContractEnum.YES.getKey() && (StringUtils.isEmpty(coorpReqVo.getLose_effect_datetime()) || StringUtils.isEmpty(coorpReqVo.getTake_effect_datetime()))){
            return Resp.createMessage(RespCode.SIGN_CONTRACT_DATETIME_IS_NULL);
        }
        return cabinetBussinessCoorperationService.caseCoorperationOnline(coorpReqVo);
    }


    @ApiOperation("合作信息")
    @ApiResponses({
            @ApiResponse(code = 0 ,message = "操作成功"),
            @ApiResponse(code = 1 , message = "系统异常")
    })
    @PostMapping("/coorperationInfo")
    public Resp coorperationInfo(HttpServletRequest request, @RequestBody @Valid CoorperationMessageReqVO coorperationMessageReqVO, BindingResult bindingResult ){
        log.info("web-coorperationInfo jsonParameter={}",JSON.toJSONString(coorperationMessageReqVO));
        long start = System.currentTimeMillis();
        try {
            if (ParameterValid.valid(bindingResult)){
                return Resp.createMessage(RespCode.PARAMETER_VALID_FAIL);
            }
            String phoneNo = getTokenUserMessage(request).getPhoneNo();
            JSONObject data = JSON.parseObject(JSON.toJSONString(coorperationMessageReqVO));
            Resp resp = cabinetBussinessCoorperationService.coorperationInfo(data);
            long end = System.currentTimeMillis();
            log.info("call method web-coorperationInfo cost {} mills",( end - start));
            return resp;
        }catch (Exception e){
            log.error("call method web-coorperationInfo has some errors , errors message is {}",e);
            return Resp.createMessage(RespCode.ERROR);
        }
    }
    @ApiOperation(value = "上传校验图片", notes = "上传校验图片")
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功"),
            @ApiResponse(code = 1,message = "系统异常")
    })
    @PostMapping("/uploadPicture")
    @AccessRequired
    public Resp upload(HttpServletRequest request, @ApiParam(value = "图片文件",name = "file",required = true) @Param("file") MultipartFile file,@ApiParam(value = "照片类型(合同照片/合作照片)",name = "type",required = true)  @Param("type") Integer type){
        if (file == null || type == null){
            return Resp.createMessage(RespCode.PARAMETER_ERROR);
        }
        String path = null;
        if (type == CONTRACT_PIC_TYPE){
            path = Constant.CONTRACT_PIC_PATH;
        }else if (type == COORPERATION_PIC_TYPE){
            path = Constant.COORPERATION_PIC_PATH;
        }else {
            return Resp.createMessage(RespCode.PARAMETER_ERROR);
        }
        return cabinetBussinessCoorperationService.upload(file, path);
    }

    @ApiOperation("待创建列表")
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功"),
            @ApiResponse(code = 1,message = "系统异常")
    })
    @AccessRequired
    @PostMapping("/coorperationTreatCreat")
    public Resp coorperationTreatCreat(HttpServletRequest request,@Valid @RequestBody CoorperationListReqVO coorperationListReqVO,BindingResult bindingResult){
        if (ParameterValid.valid(bindingResult)){
            return Resp.createMessage(RespCode.PARAMETER_ERROR);
        }
        String phoneNo = getTokenUserMessage(request).getPhoneNo();
        coorperationListReqVO.setPhone(phoneNo);
        return cabinetBussinessCoorperationService.coorperationTreatCreate(coorperationListReqVO);
    }
    @ApiOperation("已创建列表")
    @ApiResponses({
            @ApiResponse(code = 0 , message = "操作成功"),
            @ApiResponse(code = 1 , message = "操作失败")
    })
    @AccessRequired
    @PostMapping("/coorperationCreated")
    public Resp coorperationCreated(HttpServletRequest request,@Valid @RequestBody CoorperationListReqVO coorperationListReqVO,BindingResult bindingResult){
        if (ParameterValid.valid(bindingResult)){
            return Resp.createMessage(RespCode.PARAMETER_ERROR);
        }
        String phoneNo = getTokenUserMessage(request).getPhoneNo();
        coorperationListReqVO.setPhone(phoneNo);
        return cabinetBussinessCoorperationService.coorperationCreated(coorperationListReqVO);
    }
    @ApiOperation("已使用列表")
    @ApiResponses({
            @ApiResponse(code = 0 , message = "操作成功"),
            @ApiResponse(code = 1 , message = "操作失败")
    })
    @AccessRequired
    @PostMapping("/coorperationAlreadyUsed")
    public Resp coorperationAlreadyUsed(HttpServletRequest request,@Valid @RequestBody CoorperationListReqVO coorperationListReqVO,BindingResult bindingResult){
        if (ParameterValid.valid(bindingResult)){
            return Resp.createMessage(RespCode.PARAMETER_ERROR);
        }
        String phoneNo = getTokenUserMessage(request).getPhoneNo();
        coorperationListReqVO.setPhone(phoneNo);
        return cabinetBussinessCoorperationService.coorperationAlreadyUsed(coorperationListReqVO);
    }
    @ApiOperation("全部列表")
    @ApiResponses({
            @ApiResponse(code = 0 , message = "操作成功"),
            @ApiResponse(code = 1 , message = "操作失败")
    })
    @AccessRequired
    @PostMapping("/coorperationAll")
    public Resp coorperationAll(HttpServletRequest request,@Valid @RequestBody CoorperationListReqVO coorperationListReqVO,BindingResult bindingResult){
        if (ParameterValid.valid(bindingResult)){
            return Resp.createMessage(RespCode.PARAMETER_ERROR);
        }
        String phoneNo = getTokenUserMessage(request).getPhoneNo();
        coorperationListReqVO.setPhone(phoneNo);
        return cabinetBussinessCoorperationService.coorperationAll(coorperationListReqVO);
    }
    @ApiOperation("搜索")
    @ApiResponses({
            @ApiResponse(code = 0 , message = "操作成功"),
            @ApiResponse(code = 1 , message = "操作失败")
    })
    @AccessRequired
    @PostMapping("/coorperationSearch")
    public Resp coorperationSearch(HttpServletRequest request,@RequestBody CoorperationSearchReqVO coorperationSearchReqVO){
        String phoneNo = getTokenUserMessage(request).getPhoneNo();
        coorperationSearchReqVO.setBd_phone(phoneNo);
        return cabinetBussinessCoorperationService.search(coorperationSearchReqVO);
    }

    @ApiOperation("店铺地址")
    @ApiResponses({
            @ApiResponse(code = 0 , message = "操作成功"),
            @ApiResponse(code = 1 , message = "操作失败")
    })
    @AccessRequired
    @PostMapping("/basicArea")
    public Resp basicArea(HttpServletRequest request,@RequestBody BasicAreaReqVO basicAreaReqVO){
        return cabinetBussinessCoorperationService.basicArea(basicAreaReqVO);
    }

    @ApiOperation("用户查询名字")
    @PostMapping("/findBdName")
    public Resp findBdName(){
        TokenUserMessage tokenUserMessage = getTokenUserMessage();
        String bdName = cabinetBussinessCoorperationService.findBdName(tokenUserMessage.getUserId());
        JSONObject jsonObject = new JSONObject(1);
        jsonObject.put("user_name",bdName);
        return Resp.createBySuccess(jsonObject);
    }
}
