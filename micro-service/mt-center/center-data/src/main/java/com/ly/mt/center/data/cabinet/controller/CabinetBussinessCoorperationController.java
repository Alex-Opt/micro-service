package com.ly.mt.center.data.cabinet.controller;
import	java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.entity.CabinetStore;
import com.ly.mt.center.data.cabinet.request.CabinetBussinessCoorperationReqVO;
import com.ly.mt.center.data.cabinet.request.CabinetContractReqVo;
import com.ly.mt.center.data.cabinet.request.CabinetStorePropertyReqVo;
import com.ly.mt.center.data.cabinet.request.CreateCooperationReqVO;
import com.ly.mt.center.data.cabinet.service.CabinetBussinessCoorperationService;
import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/cabinet/b")
@EnableAutoConfiguration
public class CabinetBussinessCoorperationController {

    @Resource
    private CabinetBussinessCoorperationService cabinetBussinessCoorperationService;

    @ApiOperation("创建合作")
    @ApiResponses({
            @ApiResponse(code = 0,message = "操作成功"),
            @ApiResponse(code = 1,message = "系统异常")
    })
    @PostMapping("/cooperation/createCooperation")
    //@AccessRequired
    public ResponseJson createCooperation(HttpServletRequest request, @Valid @RequestBody CreateCooperationReqVO createCooperationReqVO, BindingResult bindingResult){
        /*if (ParameterValid.valid(bindingResult)){
            return Resp.createMessage(RespCode.PARAMETER_VALID_FAIL);
        }*/
        //店铺信息
        CabinetStore cabinetStore = new CabinetStore();
        cabinetStore.setName(createCooperationReqVO.getStore_name());
        cabinetStore.setAddress(createCooperationReqVO.getStore_address());
        cabinetStore.setBelong_circle(createCooperationReqVO.getBelong_circle());
        cabinetStore.setCreate_status(createCooperationReqVO.getCreate_status());
        cabinetStore.setStatus("1");
        cabinetStore.setM_id(String.valueOf(createCooperationReqVO.getM_id()));
        cabinetStore.setId(createCooperationReqVO.getStore_id());
        cabinetStore.setDetail_address(createCooperationReqVO.getDetail_address());
        cabinetStore.setGmt_create(com.ly.mt.core.base.util.DateUtil.getNowTimeStr());
        cabinetStore.setGmt_modify(com.ly.mt.core.base.util.DateUtil.getNowTimeStr());
        //店铺属性信息
        CabinetStorePropertyReqVo cabinetStorePropertyReqVo = new CabinetStorePropertyReqVo();
        cabinetStorePropertyReqVo.setDecorate_type(createCooperationReqVO.getDescate_type());
        cabinetStorePropertyReqVo.setForcast_flow(createCooperationReqVO.getForecast_flow());
        cabinetStorePropertyReqVo.setIs_chain(createCooperationReqVO.getIs_chain());
        cabinetStorePropertyReqVo.setPer_capita_expense(createCooperationReqVO.getPer_capita_expense());
        cabinetStorePropertyReqVo.setStore_type(createCooperationReqVO.getStore_type());
        cabinetStorePropertyReqVo.setDecorate_type(createCooperationReqVO.getDescate_type());
        //合同信息
        CabinetContractReqVo cabinetContractReqVo = new CabinetContractReqVo();
        cabinetContractReqVo.setContract_pic(createCooperationReqVO.getContract_pic());
        cabinetContractReqVo.setId(createCooperationReqVO.getContract_id());
        cabinetContractReqVo.setLose_efficacy_datetime(createCooperationReqVO.getLose_effect_datetime());
        cabinetContractReqVo.setTake_effect_datetime(createCooperationReqVO.getTake_effect_datetime());
        //商务合作信息
        CabinetBussinessCoorperationReqVO cabinetBussinessCoorperationReqVO = new CabinetBussinessCoorperationReqVO();
        cabinetBussinessCoorperationReqVO.setBd_name(createCooperationReqVO.getBd_name());
        cabinetBussinessCoorperationReqVO.setBd_phone(createCooperationReqVO.getBd_phone());
        cabinetBussinessCoorperationReqVO.setIs_deposit(createCooperationReqVO.getIs_desposit());
        cabinetBussinessCoorperationReqVO.setDeposit_amount(createCooperationReqVO.getDesposit_amount());
        cabinetBussinessCoorperationReqVO.setOwner_name(createCooperationReqVO.getOwner_name());
        cabinetBussinessCoorperationReqVO.setOwner_phone(createCooperationReqVO.getOwner_phone());
        cabinetBussinessCoorperationReqVO.setCoorperation_pic(createCooperationReqVO.getCoorperation_pic());
        JSONObject data = new JSONObject();
        data.put("cabinet_store",cabinetStore);
        data.put("cabinet_store_property",cabinetStorePropertyReqVo);
        data.put("cabinet_contract",cabinetContractReqVo);
        data.put("cabinet_bussiness_coorperation",cabinetBussinessCoorperationReqVO);
        //ResponseJson bussinessCoorperation = cabinetBussinessCoorperationService.createBussinessCoorperation(data);
        return null;
    }
}
