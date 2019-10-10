package com.ly.mt.cabinet.b.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.cabinet.b.common.dict.*;
import com.ly.mt.cabinet.b.common.request.*;
import com.ly.mt.cabinet.b.common.response.CreateCooperationResVO;
import com.ly.mt.cabinet.b.entity.CabinetStore;
import com.ly.mt.cabinet.b.service.CabinetBussinessCoorperationService;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.cabinet.b.util.RespCode;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.feign.DataCenterMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;

@Service
public class CabinetBussinessCoorperationServiceImpl extends BaseServiceImpl implements CabinetBussinessCoorperationService {

    private static final Logger log = LoggerFactory.getLogger(CabinetBussinessCoorperationServiceImpl.class);
    private static final String UNREGISTER_CODE = "4";
    private static final String SUCCESS_CODE = "0";


    @Override
    public Resp createCoorperation(JSONObject data) {
        log.info("调用 web-createCoorperation jsonParam={}",data.toJSONString());
        long start = System.currentTimeMillis();
        try {
            String s = callDataCenter(DataCenterMethod.CABINET_BUSSINESS_COORPERATIONS, data);
            log.info("调用 web-createCoorperation 返回结果={}",s);
            long end = System.currentTimeMillis();
            log.info("调用 web-createCoorperation 花费 {} 毫秒", (end - start));
            return Resp.createMessage(RespCode.SUCCESS);
        }catch (Exception e){
            log.error("调用 web-createCoorperation 出错，错误信息={}",e);
            return Resp.createMessage(RespCode.ERROR);
        }
    }

    @Override
    public Resp cabCoorperationSave(CoorpReqVo coorpReqVo) {
        log.info("调用 save jsonParam={}",JSON.toJSONString(coorpReqVo));
        long start = System.currentTimeMillis();
        try {
            JSONObject data = coorpHandle(coorpReqVo, StoreCreateStatusEnum.A.getKey());
            String s = callDataCenter(DataCenterMethod.CABINET_BUSSINESS_COORPERATIONS_SAVE, data);
            log.info("格子柜保存调用数据中心返回数据={}",s);
            JSONObject callBackData = JSON.parseObject(s);
            long end = System.currentTimeMillis();
            log.info("call method createCoorperation cost {} mills", (end - start));
            return Resp.createBySuccess(RespCode.SUCCESS,callBackData);
        }catch (Exception e){
            log.error("call method createCoorperation has some errors,the error message is {}",e);
            return Resp.createMessage(RespCode.ERROR);
        }
    }

    @Override
    public Resp cabCoorperationCreate(CoorpReqVo coorpReqVo) {
        log.info("调用 create jsonParam={}",JSON.toJSONString(coorpReqVo));
        long start = System.currentTimeMillis();
        try {
            JSONObject data = coorpHandle(coorpReqVo, StoreCreateStatusEnum.B.getKey());
            String s = callDataCenter(DataCenterMethod.CABINET_BUSSINESS_COORPERATIONS_CREATE, data);
            log.info("格子柜创建调用数据中心返回数据={}",s);
            JSONObject callBackData = JSON.parseObject(s);
            if (StringUtils.equals(callBackData.getString("code"),UNREGISTER_CODE)){
                return Resp.createMessage(RespCode.USER_NOT_EXIST_ERROR);
            }
            log.info("createCoorperation result={}",s);
            long end = System.currentTimeMillis();
            log.info("call method createCoorperation cost {} mills", (end - start));
            return Resp.createBySuccess(RespCode.SUCCESS,callBackData);
        }catch (Exception e){
            log.error("call method createCoorperation has some errors,the error message is {}",e);
            return Resp.createMessage(RespCode.USER_NOT_EXIST_ERROR);
        }
    }

    @Override
    public Resp caseCoorperationSave(CoorpReqVo coorpReqVo) {
        log.info("调用 create jsonParam={}",JSON.toJSONString(coorpReqVo));
        long start = System.currentTimeMillis();
        try {
            JSONObject data = coorpHandle(coorpReqVo, StoreCreateStatusEnum.A.getKey());
            String s = callDataCenter(DataCenterMethod.SHOWCASE_BUSSINESS_COORPERATIONS_SAVE, data);
            log.info("展柜保存调用数据中心返回数据={}",s);
            JSONObject callBackData = JSON.parseObject(s);
            long end = System.currentTimeMillis();
            log.info("call method createCoorperation cost {} mills", (end - start));
            return Resp.createBySuccess(RespCode.SUCCESS,callBackData);
        }catch (Exception e){
            log.error("call method createCoorperation has some errors,the error message is {}",e);
            return Resp.createMessage(RespCode.ERROR);
        }
    }

    @Override
    public Resp caseCoorperationCreate(CoorpReqVo coorpReqVo) {
        log.info("调用 create jsonParam={}",JSON.toJSONString(coorpReqVo));
        long start = System.currentTimeMillis();
        try {
            JSONObject data = coorpHandle(coorpReqVo, StoreCreateStatusEnum.B.getKey());
            data.put("case_online_status",false);
            String s = callDataCenter(DataCenterMethod.SHOWCASE_BUSSINESS_COORPERATIONS_CREATE, data);
            log.info("展柜创建调用数据中心返回数据={}",s);
            JSONObject callBackData = JSON.parseObject(s);
            if (StringUtils.equals(callBackData.getString("code"),UNREGISTER_CODE)){
                return Resp.createMessage(RespCode.USER_NOT_EXIST_ERROR);
            }
            log.info("createCoorperation result={}",s);
            long end = System.currentTimeMillis();
            log.info("调用 web-createCoorperation 花费 {} 毫秒", (end - start));
            return Resp.createBySuccess(RespCode.SUCCESS,callBackData);
        }catch (Exception e){
            log.error("调用 web-createCoorperation 出错,错误信息是 {}",e);
            return Resp.createMessage(RespCode.USER_NOT_EXIST_ERROR);
        }
    }

    @Override
    public Resp caseCoorperationOnline(CoorpReqVo coorpReqVo) {
        log.info("调用 create jsonParam={}",JSON.toJSONString(coorpReqVo));
        long start = System.currentTimeMillis();
        try {
            JSONObject data = coorpHandle(coorpReqVo, StoreCreateStatusEnum.B.getKey());
            data.put("case_online_status",true);
            String s = callDataCenter(DataCenterMethod.SHOWCASE_BUSSINESS_COORPERATIONS_CREATE, data);
            log.info("展柜创建调用数据中心返回数据={}",s);
            JSONObject callBackData = JSON.parseObject(s);
            if (StringUtils.equals(callBackData.getString("code"),UNREGISTER_CODE)){
                return Resp.createMessage(RespCode.USER_NOT_EXIST_ERROR);
            }
            log.info("createCoorperation result={}",s);
            long end = System.currentTimeMillis();
            log.info("调用 web-createCoorperation 花费 {} 毫秒", (end - start));
            return Resp.createBySuccess(RespCode.SUCCESS,callBackData);
        }catch (Exception e){
            log.error("调用 web-createCoorperation 出错,错误信息是 {}",e);
            return Resp.createMessage(RespCode.USER_NOT_EXIST_ERROR);
        }
    }

    private JSONObject coorpHandle(CoorpReqVo coorpReqVo,int create_status) {
        JSONObject data = new JSONObject();
        CabStoreReqVo cs = new CabStoreReqVo();
        cs.setId(coorpReqVo.getStore_id());
        cs.setName(coorpReqVo.getStore_name());
        cs.setAddress(coorpReqVo.getStore_address());
        cs.setBelong_circle(coorpReqVo.getBelong_circle());
        cs.setCreate_status(String.valueOf(create_status));
        cs.setStatus(CabinetStoreStatusEnum.A.getKey());
        cs.setM_id(coorpReqVo.getM_id());
        cs.setDetail_address(coorpReqVo.getDetail_address());
        data.put("cabinet_store",cs);
        //店铺属性信息
        StorePropReqVo storePropReqVo = new StorePropReqVo();
        storePropReqVo.setDecorate_type(coorpReqVo.getDescate_type());
        storePropReqVo.setForcast_flow(coorpReqVo.getForecast_flow());
        storePropReqVo.setIs_chain(coorpReqVo.getIs_chain());
        storePropReqVo.setPer_capita_expense(coorpReqVo.getPer_capita_expense());
        storePropReqVo.setStore_type(coorpReqVo.getStore_type());
        storePropReqVo.setDecorate_type(coorpReqVo.getDescate_type());
        storePropReqVo.setUse_area(coorpReqVo.getUse_area());
        data.put("cabinet_store_property", storePropReqVo);
        //合同信息
        if (coorpReqVo.getIs_sign_contract() == IsSignContractEnum.YES.getKey()) {
            CabinetContractReqVo cabinetContractReqVo = new CabinetContractReqVo();
            if (coorpReqVo.getContract_pic() != null) {
                cabinetContractReqVo.setContract_pic(JSON.toJSONString(coorpReqVo.getContract_pic()));
            }
            cabinetContractReqVo.setId(coorpReqVo.getContract_id());
            cabinetContractReqVo.setLose_efficacy_datetime(coorpReqVo.getLose_effect_datetime());
            cabinetContractReqVo.setTake_effect_datetime(coorpReqVo.getTake_effect_datetime());
            data.put("cabinet_contract",cabinetContractReqVo);
        }
        //商务合作信息
        BussCoorpReqVo bussCoorpReqVo = new BussCoorpReqVo();
        bussCoorpReqVo.setBd_name(coorpReqVo.getBd_name());
        bussCoorpReqVo.setBd_phone(coorpReqVo.getBd_phone());
        bussCoorpReqVo.setIs_deposit(coorpReqVo.getIs_desposit());
        bussCoorpReqVo.setDeposit_amount(String.valueOf(coorpReqVo.getDesposit_amount()));
        bussCoorpReqVo.setOwner_name(coorpReqVo.getOwner_name());
        bussCoorpReqVo.setOwner_phone(coorpReqVo.getOwner_phone());
        bussCoorpReqVo.setDivide_scale(coorpReqVo.getDivide_scale());
        bussCoorpReqVo.setIs_sign_contract(coorpReqVo.getIs_sign_contract());
        bussCoorpReqVo.setDescription(coorpReqVo.getDescription());
        if (coorpReqVo.getCoorperation_pic() != null) {
            bussCoorpReqVo.setCoorperation_pic(JSON.toJSONString(coorpReqVo.getCoorperation_pic()));
        }
        data.put("cabinet_bussiness_coorperation", bussCoorpReqVo);
        //公共
        BasicReqVO basicReqVO = new BasicReqVO();
        basicReqVO.setOwner_name(coorpReqVo.getOwner_name());
        basicReqVO.setOwner_phone(coorpReqVo.getOwner_phone());
        basicReqVO.setBd_name(coorpReqVo.getBd_name());
        basicReqVO.setBd_phone(coorpReqVo.getBd_phone());
        basicReqVO.setShowcase_imei(String.valueOf(coorpReqVo.getShowcase_imei()));
        data.put("basic",basicReqVO);
        return data;
    }



    @Override
    public Resp coorperationInfo(JSONObject data) {
        log.info("调用 web-coorperationInfo jsonParameter={}",data.toJSONString());
        long start = System.currentTimeMillis();
        try {
            String s = callDataCenter(DataCenterMethod.CABINET_COORPERATION_INFO, data);
            log.info("调用 web-coorperationInfo 返回结果={}",s);
            CoorpReqVo cabReqVo = JSON.parseObject(s,new TypeReference<CoorpReqVo>(){});
            long end = System.currentTimeMillis();
            log.info("调用 web-coorperationInfo 花费 {} 毫秒",( end - start));
            return Resp.createBySuccess(RespCode.SUCCESS,cabReqVo);
        }catch (Exception e){
            log.error("调用 web-coorperationInfo 出错 , 错误信息是={}",e);
            return Resp.createMessage(RespCode.ERROR);
        }
    }

    @Override
    public Resp upload(MultipartFile file,String path) {
        log.info("call method upload parameter={}",path);
        long start = System.currentTimeMillis();
        try {
            String s = callThirdCenter(file, path);
            log.info("method upload callThirdCenter returnData={}",s);
            long end = System.currentTimeMillis();
            log.info("call method upload cost {} mills",(end - start));
            return Resp.createBySuccess(RespCode.SUCCESS,s);
        }catch (Exception e){
            log.error("call method upload has some errors , the error message is {}",e);
            return Resp.createMessage(RespCode.ERROR);
        }
    }

    @Override
    public Resp coorperationTreatCreate(CoorperationListReqVO coorperationListReqVO) {
        log.info("call method treatCreate parameter={}",JSON.toJSONString(coorperationListReqVO));
        long start = System.currentTimeMillis();
        try {
            //调用数据中心获取待创建的合作信息
            JSONObject data = JSON.parseObject(JSON.toJSONString(coorperationListReqVO));
            String s = callDataCenter(DataCenterMethod.CABINET_COORPERATION_TREAT_CREATE, data);
            log.info("调用待创建返回数据  s={}",s);
            JSONObject callBackData = JSON.parseObject(s);
            return Resp.createBySuccess(RespCode.SUCCESS, callBackData);
        }catch (Exception e){
            log.error("call method treatCreate has some errors , errors message is {}",e);
            return Resp.createMessage(RespCode.ERROR);
        }
    }

    @Override
    public Resp coorperationCreated(CoorperationListReqVO coorperationListReqVO) {
        log.info("call method created parameter={}",JSON.toJSONString(coorperationListReqVO));
        long start = System.currentTimeMillis();
        try{
            //调用数据中心获取已创建的合作信息列表
            JSONObject data = JSON.parseObject(JSON.toJSONString(coorperationListReqVO));
            String s = callDataCenter(DataCenterMethod.CABINET_COORPERATION_CREATED, data);
            log.info("调用已创建返回数据  s={}",s);
            JSONObject callBackData = JSON.parseObject(s);
            return Resp.createBySuccess(RespCode.SUCCESS,callBackData);
        }catch (Exception e){
            log.error("call method has some errors , errors message is {}",e);
            return Resp.createMessage(RespCode.ERROR);
        }
    }

    @Override
    public Resp coorperationAlreadyUsed(CoorperationListReqVO coorperationListReqVO) {
        log.info("call method alreadyUsed parameter={}",JSON.toJSONString(coorperationListReqVO));
        long start = System.currentTimeMillis();
        try {
            //调用数据中心获取已使用的合作信息列表
            JSONObject data = JSON.parseObject(JSON.toJSONString(coorperationListReqVO));
            String s = callDataCenter(DataCenterMethod.CABINET_COORPERATION_ALREADY_USED, data);
            log.info("调用已使用返回数据  s={}",s);
            JSONObject callBackData = JSON.parseObject(s);
            return Resp.createBySuccess(RespCode.SUCCESS,callBackData);
        }catch (Exception e){
            log.error("call method alreadyUsed has some errors , errors message is {}",e);
            return Resp.createMessage(RespCode.ERROR);
        }
    }

    @Override
    public Resp coorperationAll(CoorperationListReqVO coorperationListReqVO) {
        log.info("call method all parameter={}",coorperationListReqVO);
        long start = System.currentTimeMillis();
        try {
            //调用数据中心获取所有的合作列表信息
            JSONObject data = JSON.parseObject(JSON.toJSONString(coorperationListReqVO));
            String s = callDataCenter(DataCenterMethod.CABINET_COORPERATION_ALL, data);
            log.info("调用全部返回数据  s={}",s);
            JSONObject callBackData = JSON.parseObject(s);
            return Resp.createBySuccess(RespCode.SUCCESS,callBackData);
        }catch (Exception e){
            log.error("call method all has some errors , errors message is {}",e);
            return Resp.createMessage(RespCode.ERROR);
        }
    }

    @Override
    public Resp search(CoorperationSearchReqVO coorperationSearchReqVO) {
        log.info("call method search parameters condition={},ownerPhone={}",JSON.toJSONString(coorperationSearchReqVO));
        long start = System.currentTimeMillis();
        try {
            JSONObject param = JSON.parseObject(JSON.toJSONString(coorperationSearchReqVO));
            String s = callDataCenter(DataCenterMethod.CABINET_BD_STORE_SEARCH, param);
            JSONObject callBackData = JSON.parseObject(s);
            return Resp.createBySuccess(RespCode.SUCCESS,callBackData);
        }catch (Exception e){
            log.error("call method search has some errors , errors message are {}",e);
            return Resp.createMessage(RespCode.ERROR);
        }
    }

    @Override
    public Resp basicArea(BasicAreaReqVO basicAreaReqVO) {
        log.info("call method basicArea jsonParam={}",JSON.toJSONString(basicAreaReqVO));
        long start = System.currentTimeMillis();
        try {
            JSONObject param = JSON.parseObject(JSON.toJSONString(basicAreaReqVO));
            String s = callDataCenter(DataCenterMethod.CABINET_STORE_BASIC_AREA, param);
            log.info("basicArea result={}",s);
            JSONArray callBackData = JSON.parseArray(s);
            return Resp.createBySuccess(RespCode.SUCCESS,callBackData);
        }catch (Exception e){
            log.error("call method search has some errors , errors message are {}",e);
            return Resp.createMessage(RespCode.ERROR);
        }
    }

    @Override
    public String findBdName(Long userId) {
        try {
            log.info("findBdName开始");
            if (userId<0 || userId ==null)
                return "";
            JSONObject jsonObject = new JSONObject(1);
            jsonObject.put("userId",userId);
            return callDataCenter(DataCenterMethod.CABINET_BD_NAME,jsonObject);
        } catch (Exception e) {
            return "查询出错啦";
        }
    }


}
