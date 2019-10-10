package com.ly.mt.cabinet.b.coorperation;
import	java.util.jar.Attributes.Name;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.annotation.AccessRequired;
import com.ly.mt.cabinet.b.common.constant.Constant;
import com.ly.mt.cabinet.b.common.request.*;
import com.ly.mt.cabinet.b.entity.CabinetStore;
import com.ly.mt.cabinet.b.service.CabinetBussinessCoorperationService;
import com.ly.mt.cabinet.b.service.CabinetOptionsService;
import com.ly.mt.cabinet.b.util.DateUtil;
import com.ly.mt.cabinet.b.util.ParameterValid;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.cabinet.b.util.RespCode;
import io.swagger.annotations.*;
import net.bytebuddy.implementation.bytecode.Multiplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(tags = "cooperation")
//@RestController
//@RequestMapping("/cabinet/b")
public class CooperationController{

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
    @GetMapping("/cooperation/cooperationOptions")
    //@AccessRequired
    public Resp cooperationOptions(HttpServletRequest request){
        log.info("call method cooperationOptions at {}", DateUtil.currentDateTime());
        long start = System.currentTimeMillis();
        Resp options = cabinetOptionsService.options();
        long end = System.currentTimeMillis();
        log.info("call method cooperationOptions cost {} mills",(end - start));
        return options;
    }

    @ApiOperation("创建合作")
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功"),
            @ApiResponse(code = 1,message = "系统异常")
    })
    @PostMapping("/cooperation/createCooperation")
    @AccessRequired
    public Resp createCooperation(HttpServletRequest request/*, @Valid @RequestBody CreateCooperationReqVO createCooperationReqVO, BindingResult bindingResult*/){
       /* if (ParameterValid.valid(bindingResult)){
            return Resp.createMessage(RespCode.PARAMETER_VALID_FAIL);
        }*/
        String userId = (String) request.getAttribute("userId");
        //店铺信息
        CabinetStore cabinetStore = new CabinetStore();
     //   cabinetStore.setName(createCooperationReqVO.getStore_name());
     //   cabinetStore.setAddress(createCooperationReqVO.getStore_address());
     //   cabinetStore.setBelong_circle(createCooperationReqVO.getBelong_circle());
     //   cabinetStore.setCreate_status(createCooperationReqVO.getCreate_status());
     //   cabinetStore.setStatus("1");
      //  cabinetStore.setM_id(createCooperationReqVO.getM_id());
      //  cabinetStore.setId(createCooperationReqVO.getStore_id());
      //  cabinetStore.setDetail_address(createCooperationReqVO.getDetail_address());
        cabinetStore.setGmt_create(com.ly.mt.core.base.util.DateUtil.getNowTimeStr());
        cabinetStore.setGmt_modify(com.ly.mt.core.base.util.DateUtil.getNowTimeStr());
        //店铺属性信息
       // CabinetStorePropertyReqVo cabinetStorePropertyReqVo = new CabinetStorePropertyReqVo();
      //  cabinetStorePropertyReqVo.setDecorate_type(createCooperationReqVO.getDescate_type());
      //  cabinetStorePropertyReqVo.setForcast_flow(createCooperationReqVO.getForecast_flow());
      //  cabinetStorePropertyReqVo.setIs_chain(createCooperationReqVO.getIs_chain());
       // cabinetStorePropertyReqVo.setPer_capita_expense(createCooperationReqVO.getPer_capita_expense());
       // cabinetStorePropertyReqVo.setStore_type(createCooperationReqVO.getStore_type());
       // cabinetStorePropertyReqVo.setDecorate_type(createCooperationReqVO.getDescate_type());
        //合同信息
        CabinetContractReqVo cabinetContractReqVo = new CabinetContractReqVo();
       // cabinetContractReqVo.setContract_pics(createCooperationReqVO.getContract_pic());
       // cabinetContractReqVo.setId(createCooperationReqVO.getContract_id());
      //  cabinetContractReqVo.setLose_efficacy_datetime(createCooperationReqVO.getLose_effect_datetime());
      //  cabinetContractReqVo.setTake_effect_datetime(createCooperationReqVO.getTake_effect_datetime());
        //商务合作信息
      //  CabinetBussinessCoorperationReqVO cabinetBussinessCoorperationReqVO = new CabinetBussinessCoorperationReqVO();
      //  cabinetBussinessCoorperationReqVO.setBd_name(createCooperationReqVO.getBd_name());
      //  cabinetBussinessCoorperationReqVO.setBd_phone(createCooperationReqVO.getBd_phone());
      //  cabinetBussinessCoorperationReqVO.setIs_deposit(createCooperationReqVO.getIs_desposit());
      //  cabinetBussinessCoorperationReqVO.setDeposit_amount(createCooperationReqVO.getDesposit_amount());
       // cabinetBussinessCoorperationReqVO.setOwner_name(createCooperationReqVO.getOwner_name());
      //  cabinetBussinessCoorperationReqVO.setOwner_phone(createCooperationReqVO.getOwner_phone());
        JSONObject data = new JSONObject();
        data.put("cabinet_store",cabinetStore);
     //   data.put("cabinet_store_property",cabinetStorePropertyReqVo);
        data.put("cabinet_contract",cabinetContractReqVo);
       // data.put("cabinet_bussiness_coorperation",cabinetBussinessCoorperationReqVO);
      //  data.put("owner_phone",createCooperationReqVO.getOwner_phone());
      //  data.put("showcase_imei",createCooperationReqVO.getShowcase_imei());
        Resp result = cabinetBussinessCoorperationService.createCoorperation(data);
        return result;
    }



    @ApiOperation("合作信息")
    @ApiResponses({
            @ApiResponse(code = 0 ,message = "操作成功"),
            @ApiResponse(code = 1 , message = "系统异常")
    })
    @PostMapping("/coorperation/coorperationMessage")
    public Resp coorperationMessage(HttpServletRequest request, @RequestBody @Valid CoorperationMessageReqVO coorperationMessageReqVO, BindingResult bindingResult ){
        if (ParameterValid.valid(bindingResult)){
            return Resp.createMessage(RespCode.PARAMETER_VALID_FAIL);
        }
        return null;
    }
    @ApiOperation(value = "上传校验图片", notes = "上传校验图片")
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功"),
            @ApiResponse(code = 1,message = "系统异常")
    })
    @PostMapping("/uploadPicture")
    @AccessRequired
    public Resp upload(HttpServletRequest request, @ApiParam(value = "图片文件",name = "file",required = true) MultipartFile file,@ApiParam(value = "照片类型(合同照片/合作照片)",name = "type",required = true) @Param("type") Integer type){
        if (file == null || type == null){
            return Resp.createMessage(RespCode.PARAMETER_ERROR);
        }
        String path = null;
        /*if (type == CONTRACT_PIC_TYPE){
            path = Constant.CONTRACT_PIC_PATH;
        }else if (type == COORPERATION_PIC_TYPE){
            path = Constant.COORPERATION_PIC_PATH;
        }else {
            return Resp.createMessage(RespCode.PARAMETER_ERROR);
        }
        return cabinetBussinessCoorperationService.upload(file, path);*/
        return null;
    }

    @ApiOperation("待创建列表")
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功"),
            @ApiResponse(code = 1,message = "系统异常")
    })
    @AccessRequired
    @PostMapping("/coorperation/coorperationTreatCreat")
    public Resp coorperationTreatCreat(HttpServletRequest request){
        String userId = (String) request.getAttribute("user_id");
       // cabinetBussinessCoorperationService.coorperationTreatCreate(userId);
        return null;
    }
    @ApiOperation("已创建列表")
    @ApiResponses({
            @ApiResponse(code = 0 , message = "操作成功"),
            @ApiResponse(code = 1 , message = "操作失败")
    })
    @AccessRequired
    @PostMapping("/coorperation/coorperationCreated")
    public Resp coorperationCreated(HttpServletRequest request){
        String userId = (String) request.getAttribute("user_id");
     //   cabinetBussinessCoorperationService.coorperationCreated(userId);
        return null;
    }
    @ApiOperation("已使用列表")
    @ApiResponses({
            @ApiResponse(code = 0 , message = "操作成功"),
            @ApiResponse(code = 1 , message = "操作失败")
    })
    @AccessRequired
    @PostMapping("/coorperation/coorperationAlreadyUsed")
    public Resp coorperationAlreadyUsed(HttpServletRequest request){
        String userId = (String) request.getAttribute("user_id");
       // cabinetBussinessCoorperationService.coorperationAlreadyUsed(userId);
        return null;
    }

    public Resp coorperationAll(HttpServletRequest request){
        String userId = (String) request.getAttribute("user_id");
        //cabinetBussinessCoorperationService.coorperationAll(userId);
        return null;
    }
}
