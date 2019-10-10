package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ly.mt.center.data.basic.entity.BasicUserRole;
import com.ly.mt.center.data.basic.mapper.BasicRoleMapper;
import com.ly.mt.center.data.basic.mapper.BasicUserRoleMapper;
import com.ly.mt.center.data.cabinet.constant.Constant;
import com.ly.mt.center.data.cabinet.dto.CoorpListReqDto;
import com.ly.mt.center.data.cabinet.entity.*;
import com.ly.mt.center.data.cabinet.enums.CabinetStoreCreateStatusEnum;
import com.ly.mt.center.data.cabinet.enums.CabinetStoreCreateTypeEnum;
import com.ly.mt.center.data.cabinet.enums.CabinetTypeEnum;
import com.ly.mt.center.data.cabinet.mapper.*;
import com.ly.mt.center.data.cabinet.requestdto.BasicReqDto;
import com.ly.mt.center.data.cabinet.requestdto.CoorpOperationReqDto;
import com.ly.mt.center.data.cabinet.requestdto.PageUtil;
import com.ly.mt.center.data.cabinet.response.BdStoreMsgRespVO;
import com.ly.mt.center.data.cabinet.response.CreateCooperationResVO;
import com.ly.mt.center.data.cabinet.response.CreateCoorpResVo;
import com.ly.mt.center.data.cabinet.response.PageInfo;
import com.ly.mt.center.data.cabinet.service.CabinetBussinessCoorperationService;
import com.ly.mt.center.data.gzg.mapper.GzgGoodsPlanMapper;
import com.ly.mt.center.data.user.entity.User;
import com.ly.mt.center.data.user.mapper.UserMapper;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class CabinetBusinessCoorperationServiceImpl implements CabinetBussinessCoorperationService {

    private static final Logger log = LoggerFactory.getLogger(CabinetBusinessCoorperationServiceImpl.class);
    private static final Integer PAGE_SIZE = 10;
    private static final Integer PAGE_NUM = 1;
    private static final String NULL_ID = "null";
    @Resource
    private CabinetBussinessCoorperationMapper cabinetBussinessCoorperationMapper;
    @Resource
    private CabinetStoreMapper cabinetStoreMapper;
    @Resource
    private CabinetStorePropertyMapper cabinetStorePropertyMapper;
    @Resource
    private CabinetContractMapper cabinetContractMapper;
    @Resource
    private BasicUserRoleMapper basicUserRoleMapper;
    @Resource
    private BasicRoleMapper basicRoleMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CabinetInfoMapper cabinetInfoMapper;
    @Resource
    private CabinetAreaMapper cabinetAreaMapper;
    @Resource
    private CabinetCreateRecordMapper cabinetCreateRecordMapper;
    @Resource
    private GzgGoodsPlanMapper gzgGoodsPlanMapper;



    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseJson cabCoorperationSave(JSONObject data) {
        CoorpOperationReqDto cord = save(data,CabinetStoreCreateTypeEnum.A.getKey());
        CabinetStore cs = cord.getCabinetStore();
        if (StringUtils.isEmpty(cs.getId())) {
            cs.setId(StringUtil.getRandomIntByLength(20));
            cs.setGmt_create(DateUtil.getNowTimeStr());
            cs.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.A.getKey()));
            cabinetStoreMapper.insertSelective(cs);
        }else {
            cs.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.A.getKey()));
            cs.setGmt_modify(DateUtil.getNowTimeStr());
            cabinetStoreMapper.updateByPrimaryKeySelective(cs);
        }
        CabinetStoreProperty csp = cord.getCabinetStoreProperty();
        CabinetStoreProperty csProp = cabinetStorePropertyMapper.selectByStoreId(cs.getId());
        if (csProp == null ) {
            csp.setId(StringUtil.getRandomIntByLength(20));
            csp.setCabinet_store_id(cs.getId());
            csp.setGmt_create(DateUtil.getNowTimeStr());
            cabinetStorePropertyMapper.insertSelective(csp);
        }else {
            csp.setId(csProp.getId());
            cabinetStorePropertyMapper.updateByPrimaryKeySelective(csp);
        }
        CabinetBussinessCoorperation cbc = cord.getCabinetBussinessCoorperation();
        CabinetBussinessCoorperation cbCoorp = cabinetBussinessCoorperationMapper.selectByStoreId(cs.getId());
        CabinetContract cc = cord.getCabinetContract();
        if (cc != null) {
            if (StringUtils.isEmpty(cc.getId()) || StringUtils.equals(cc.getId(), NULL_ID)) {
                cc.setId(StringUtil.getRandomIntByLength(20));
                cabinetContractMapper.insertSelective(cc);
                cbc.setContract_id(cc.getId());
            } else {
                cc.setGmt_modify(DateUtil.getNowTimeStr());
                cabinetContractMapper.updateByPrimaryKeySelective(cc);
                cbc.setContract_id(cc.getId());
            }
        }
        if (cbCoorp == null) {
            cbc.setId(StringUtil.getRandomIntByLength(20));
            cbc.setStore_id(cord.getCabinetStore().getId());
            cbc.setGmt_create(DateUtil.getNowTimeStr());
            cbc.setGmt_modify(DateUtil.getNowTimeStr());
            cbc.setStatus("0");
            cabinetBussinessCoorperationMapper.insertSelective(cbc);
        }else {
            cbc.setGmt_modify(DateUtil.getNowTimeStr());
            cbc.setId(cbCoorp.getId());
            cbc.setStatus("0");
            cabinetBussinessCoorperationMapper.updateByPrimaryKeySelective(cbc);
        }
        CreateCoorpResVo createCoorpResVo = new CreateCoorpResVo();
        createCoorpResVo.setStore_id(cs.getId());
        if (cc == null){
            createCoorpResVo.setContract_id("");
        }else {
            createCoorpResVo.setContract_id(cc.getId());
        }
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,createCoorpResVo);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseJson cabCoorperationCreate(JSONObject data) {
        JSONObject basic = data.getJSONObject("basic");
        String bdPhone = basic.getString("bd_phone");
        String ownerPhone = basic.getString("owner_phone");
        User ownerUser = userMapper.getByPhoneNo(ownerPhone);
        User bdUser = userMapper.getByPhoneNo(bdPhone);
        CoorpOperationReqDto cord = create(data, CabinetStoreCreateTypeEnum.A.getKey());
        if (cord == null){
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_NOT_REGIST,"店铺管理员未注册");
        }
        CabinetStore cs = cord.getCabinetStore();
        if (StringUtils.isEmpty(cs.getId()) || StringUtils.equals(cs.getId(),NULL_ID)) {
            cs.setId(StringUtil.getRandomIntByLength(20));
            cs.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.A.getKey()));
            cabinetStoreMapper.insertSelective(cs);
        }else {
            cs.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.A.getKey()));
            cs.setGmt_modify(DateUtil.getNowTimeStr());
            cabinetStoreMapper.updateByPrimaryKeySelective(cs);
        }
        CabinetStoreProperty csp = cord.getCabinetStoreProperty();
        CabinetStoreProperty csProp = cabinetStorePropertyMapper.selectByStoreId(cs.getId());
        if (csProp == null) {
            csp.setCabinet_store_id(cs.getId());
            csp.setId(StringUtil.getRandomIntByLength(20));
            csp.setGmt_create(DateUtil.getNowTimeStr());
            cabinetStorePropertyMapper.insertSelective(csp);
        }else {
            csp.setCabinet_store_id(cs.getId());
            csp.setId(csProp.getId());
            csp.setGmt_modify(DateUtil.getNowTimeStr());
            cabinetStorePropertyMapper.updateByPrimaryKeySelective(csp);
        }
        CabinetBussinessCoorperation cbc = cord.getCabinetBussinessCoorperation();
        CabinetContract cc = cord.getCabinetContract();
        if (cc != null) {
            if (StringUtils.isEmpty(cc.getId()) || StringUtils.equals(cc.getId(), NULL_ID)) {
                cc.setId(StringUtil.getRandomIntByLength(20));
                cabinetContractMapper.insertSelective(cc);
                cbc.setContract_id(cc.getId());
            } else {
                cc.setGmt_modify(DateUtil.getNowTimeStr());
                cabinetContractMapper.updateByPrimaryKeySelective(cc);
                cbc.setContract_id(cc.getId());
            }
        }
        CabinetBussinessCoorperation cbcoor = cabinetBussinessCoorperationMapper.selectByStoreId(cs.getId());
        if (cbcoor == null) {
            cbc.setStore_id(cord.getCabinetStore().getId());
            cbc.setId(StringUtil.getRandomIntByLength(20));
            cbc.setGmt_create(DateUtil.getNowTimeStr());
            cbc.setOwner_user_id(String.valueOf(ownerUser.getId()));
            cbc.setBd_user_id(String.valueOf(bdUser.getId()));
            cbc.setStatus("0");
            cabinetBussinessCoorperationMapper.insertSelective(cbc);
        }else {
            cbc.setStore_id(cs.getId());
            cbc.setId(cbcoor.getId());
            cbc.setOwner_user_id(String.valueOf(ownerUser.getId()));
            cbc.setBd_user_id(String.valueOf(bdUser.getId()));
            cbc.setStatus("0");
            cabinetBussinessCoorperationMapper.updateByPrimaryKeySelective(cbc);
        }
        BasicUserRole bur = basicUserRoleMapper.selectByUserId(ownerUser.getId());
        if (bur == null) {
            basicUserRoleMapper.insert(cord.getBasicUserRole());
        }
        CreateCoorpResVo createCoorpResVo = new CreateCoorpResVo();
        createCoorpResVo.setStore_id(cs.getId());
        if (cc == null){
            createCoorpResVo.setContract_id("");
        }else {
            createCoorpResVo.setContract_id(cc.getId());
        }
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,createCoorpResVo);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseJson caseCoorperationSave(JSONObject data) {
        CoorpOperationReqDto cord = save(data,CabinetStoreCreateTypeEnum.A.getKey());
        CabinetStore cs = cord.getCabinetStore();
        if (StringUtils.isEmpty(cs.getId())) {
            cs.setId(StringUtil.getRandomIntByLength(20));
            cs.setGmt_create(DateUtil.getNowTimeStr());
            cs.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.B.getKey()));
            cs.setGmt_modify(DateUtil.getNowTimeStr());
            cabinetStoreMapper.insertSelective(cs);
        }else {
            cs.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.B.getKey()));
            cs.setGmt_modify(DateUtil.getNowTimeStr());
            cabinetStoreMapper.updateByPrimaryKeySelective(cs);
        }
        CabinetStoreProperty csp = cord.getCabinetStoreProperty();
        CabinetStoreProperty cabinetStoreProperty = cabinetStorePropertyMapper.selectByStoreId(cs.getId());
        if (cabinetStoreProperty == null) {
            csp.setId(StringUtil.getRandomIntByLength(20));
            csp.setCabinet_store_id(cs.getId());
            csp.setGmt_create(DateUtil.getNowTimeStr());
            cabinetStorePropertyMapper.insertSelective(csp);
        }else {
            csp.setCabinet_store_id(cs.getId());
            csp.setId(cabinetStoreProperty.getId());
            cabinetStorePropertyMapper.updateByPrimaryKeySelective(csp);
        }
        CabinetBussinessCoorperation cbc = cord.getCabinetBussinessCoorperation();
        CabinetContract cc = cord.getCabinetContract();
        if (cc != null) {
            if (StringUtils.isEmpty(cc.getId()) || StringUtils.equals(cc.getId(), NULL_ID)) {
                cc.setId(StringUtil.getRandomIntByLength(20));
                cc.setGmt_create(DateUtil.getNowTimeStr());
                cabinetContractMapper.insertSelective(cc);
                cbc.setContract_id(cc.getId());
            } else {
                cc.setGmt_modify(DateUtil.getNowTimeStr());
                cabinetContractMapper.updateByPrimaryKeySelective(cc);
                cbc.setContract_id(cc.getId());
            }
        }
        CabinetBussinessCoorperation cabinetBussinessCoorperation = cabinetBussinessCoorperationMapper.selectByStoreId(cs.getId());
        if (cabinetBussinessCoorperation == null) {
            cbc.setId(StringUtil.getRandomIntByLength(20));
            cbc.setStore_id(cord.getCabinetStore().getId());
            cbc.setGmt_create(DateUtil.getNowTimeStr());
            cbc.setGmt_modify(DateUtil.getNowTimeStr());
            cbc.setStatus("0");
            cabinetBussinessCoorperationMapper.insertSelective(cbc);
        }else {
            cbc.setGmt_modify(DateUtil.getNowTimeStr());
            cbc.setStatus("0");
            cbc.setStore_id(cs.getId());
            cbc.setId(cabinetBussinessCoorperation.getId());
            cabinetBussinessCoorperationMapper.updateByPrimaryKeySelective(cbc);
        }

        CabinetInfo ci = cord.getCabinetInfo();
        if (ci != null) {
            CabinetInfo cabinetInfo = cabinetInfoMapper.selectByImei(ci.getImei());
            if (cabinetInfo == null) {
                ci.setStore_id(cs.getId());
                ci.setId(StringUtil.getRandomIntByLength(20));
                ci.setCreate_status(CabinetStoreCreateStatusEnum.B.getKey());
                ci.setStatus(CabinetStoreCreateStatusEnum.B.getKey());
                cabinetInfoMapper.insertSelective(ci);
            } else {
                ci.setGmt_modify(DateUtil.getNowTimeStr());
                ci.setStore_id(cs.getId());
                ci.setId(cabinetInfo.getId());
                ci.setCreate_status(CabinetStoreCreateStatusEnum.B.getKey());
                ci.setStatus(CabinetStoreCreateStatusEnum.B.getKey());
                cabinetInfoMapper.updateByPrimaryKeySelective(ci);
            }
        }
        CreateCoorpResVo createCoorpResVo = new CreateCoorpResVo();
        createCoorpResVo.setStore_id(cs.getId());
        if (ci != null) {
            createCoorpResVo.setShowcase_imei(ci.getImei());
        }
        if (cc == null){
            createCoorpResVo.setContract_id("");
        }else {
            createCoorpResVo.setContract_id(cc.getId());
        }
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,createCoorpResVo);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public ResponseJson caseCoorperationCreate(JSONObject data) {
        boolean case_online_status = data.getBoolean("case_online_status");
        JSONObject basic = data.getJSONObject("basic");
        String bdPhone = basic.getString("bd_phone");
        String ownerPhone = basic.getString("owner_phone");
        User ownerUser = userMapper.getByPhoneNo(ownerPhone);
        User bdUser = userMapper.getByPhoneNo(bdPhone);
        if (ownerUser == null || bdUser == null){
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_NOT_REGIST,"店铺管理员未注册");
        }
        CoorpOperationReqDto cord = create(data, CabinetStoreCreateTypeEnum.A.getKey());
        if (cord == null){
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_NOT_REGIST);
        }
        CabinetStore cs = cord.getCabinetStore();
        if (StringUtils.isEmpty(cs.getId()) || StringUtils.equals(cs.getId(),NULL_ID)) {
            cs.setId(StringUtil.getRandomIntByLength(20));
            cs.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.B.getKey()));
            if (case_online_status){
                cs.setCreate_status(CabinetStoreCreateStatusEnum.C.getKey());
            }else {
                cs.setCreate_status(CabinetStoreCreateStatusEnum.B.getKey());
            }
            cabinetStoreMapper.insertSelective(cs);
        }else {
            cs.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.B.getKey()));
            if (case_online_status){
                cs.setCreate_status(CabinetStoreCreateStatusEnum.C.getKey());
            }else {
                cs.setCreate_status(CabinetStoreCreateStatusEnum.B.getKey());
            }
            cabinetStoreMapper.updateByPrimaryKeySelective(cs);
        }
        CabinetStoreProperty csp = cord.getCabinetStoreProperty();
        CabinetStoreProperty cabinetStoreProperty = cabinetStorePropertyMapper.selectByStoreId(cs.getId());
        if (cabinetStoreProperty == null) {
            csp.setCabinet_store_id(cs.getId());
            csp.setId(StringUtil.getRandomIntByLength(20));
            csp.setGmt_create(DateUtil.getNowTimeStr());
            cabinetStorePropertyMapper.insertSelective(csp);
        }else {
            csp.setCabinet_store_id(cs.getId());
            csp.setGmt_modify(DateUtil.getNowTimeStr());
            cabinetStorePropertyMapper.updateByPrimaryKeySelective(csp);
        }
        CabinetBussinessCoorperation cbc = cord.getCabinetBussinessCoorperation();

        CabinetContract cc = cord.getCabinetContract();
        if (cc != null) {
            if (StringUtils.isEmpty(cc.getId()) || StringUtils.equals(cc.getId(), NULL_ID)) {
                cc.setId(StringUtil.getRandomIntByLength(20));
                cc.setGmt_create(DateUtil.getNowTimeStr());
                cabinetContractMapper.insertSelective(cc);
                cbc.setContract_id(cc.getId());
            } else {
                cc.setGmt_modify(DateUtil.getNowTimeStr());
                cabinetContractMapper.updateByPrimaryKeySelective(cc);
                cbc.setContract_id(cc.getId());
            }
        }
        CabinetBussinessCoorperation cbcoor = cabinetBussinessCoorperationMapper.selectByStoreId(cs.getId());
        if (cbcoor == null) {
            cbc.setStore_id(cord.getCabinetStore().getId());
            cbc.setId(StringUtil.getRandomIntByLength(20));
            cbc.setGmt_create(DateUtil.getNowTimeStr());
            cbc.setStatus("0");
            cbc.setOwner_user_id(String.valueOf(ownerUser.getId()));
            cbc.setBd_user_id(String.valueOf(bdUser.getId()));
            cabinetBussinessCoorperationMapper.insertSelective(cbc);
        }else {
            cbc.setStore_id(cs.getId());
            cbc.setGmt_modify(DateUtil.getNowTimeStr());
            cbc.setOwner_user_id(String.valueOf(ownerUser.getId()));
            cbc.setBd_user_id(String.valueOf(bdUser.getId()));
            cbc.setStatus("0");
            cbc.setId(cbcoor.getId());
            cabinetBussinessCoorperationMapper.updateByPrimaryKeySelective(cbc);
        }
        CabinetInfo ci = cord.getCabinetInfo();
        CabinetInfo cabinetInfo = cabinetInfoMapper.selectByImei(ci.getImei());
        if (cabinetInfo == null){
            ci.setStore_id(cs.getId());
            ci.setId(StringUtil.getRandomIntByLength(20));
            ci.setGmt_create(DateUtil.getNowTimeStr());
            ci.setGmt_modify(DateUtil.getNowTimeStr());
            if (case_online_status){
                ci.setCreate_status(CabinetStoreCreateStatusEnum.C.getKey());
                ci.setStatus(CabinetStoreCreateStatusEnum.A.getKey());
            }else {
                ci.setStatus(CabinetStoreCreateStatusEnum.C.getKey());
                ci.setCreate_status(CabinetStoreCreateStatusEnum.B.getKey());
            }
            cabinetInfoMapper.insertSelective(ci);
            CabinetCreateRecord ccr = new CabinetCreateRecord();
            ccr.setCabinet_info_id(ci.getId());
            ccr.setStatus(CabinetStoreCreateStatusEnum.A.getKey());
            ccr.setCreateor_phone(bdPhone);
            ccr.setGmt_create(DateUtil.getNowTimeStr());
            ccr.setGmt_modify(DateUtil.getNowTimeStr());
            ccr.setId(StringUtil.getRandomIntByLength(20));
            cabinetCreateRecordMapper.insertSelective(ccr);
        }else {
            ci.setGmt_modify(DateUtil.getNowTimeStr());
            ci.setStore_id(cs.getId());
            if (case_online_status){
                ci.setCreate_status(CabinetStoreCreateStatusEnum.C.getKey());
                ci.setStatus(CabinetStoreCreateStatusEnum.A.getKey());
                CabinetCreateRecord ccr = new CabinetCreateRecord();
                ccr.setCabinet_info_id(ci.getId());
                ccr.setStatus(CabinetStoreCreateStatusEnum.A.getKey());
                ccr.setCreateor_phone(bdPhone);
                ccr.setGmt_create(DateUtil.getNowTimeStr());
                ccr.setGmt_modify(DateUtil.getNowTimeStr());
                ccr.setId(StringUtil.getRandomIntByLength(20));
                cabinetCreateRecordMapper.insertSelective(ccr);
            }else {
                ci.setStatus("1");
                ci.setCreate_status(CabinetStoreCreateStatusEnum.C.getKey());
                cabinetInfoMapper.updateByPrimaryKeySelective(ci);
            }
        }
        gzgGoodsPlanMapper.updateStockByImei(ci.getImei());
        BasicUserRole bur = basicUserRoleMapper.selectByUserId(ownerUser.getId());
        if (bur == null) {
            basicUserRoleMapper.insert(cord.getBasicUserRole());
        }
        CreateCoorpResVo createCoorpResVo = new CreateCoorpResVo();
        createCoorpResVo.setShowcase_imei(ci.getImei());
        createCoorpResVo.setStore_id(cs.getId());
        if (cc == null){
            createCoorpResVo.setContract_id("");
        }else {
            createCoorpResVo.setContract_id(cc.getId());
        }
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,createCoorpResVo);
    }

    private CoorpOperationReqDto save(JSONObject data,int type){
        CoorpOperationReqDto coorpOperationReqDto = new CoorpOperationReqDto();
        BasicReqDto basicReqDto = JSON.parseObject(data.getJSONObject("basic").toJSONString(),BasicReqDto.class);
        String bdPhone = basicReqDto.getBd_phone();
        String ownerPhone = basicReqDto.getOwner_phone();
        User bdUser = null;
        User ownerUser = null;
        if (StringUtils.isNotEmpty(bdPhone)){
            bdUser = userMapper.getByPhoneNo(bdPhone);
        }
        if (StringUtils.isNotEmpty(ownerPhone)){
            ownerUser = userMapper.getByPhoneNo(ownerPhone);
        }
        /**
         * 店铺信息
         */
        CabinetStore cabinetStore = JSON.parseObject(data.getJSONObject("cabinet_store").toJSONString(),CabinetStore.class);
        if (StringUtils.isNotEmpty(cabinetStore.getId()) && !StringUtils.equals(cabinetStore.getId(),NULL_ID) && cabinetStore.getId() != null) {
            if (CabinetStoreCreateTypeEnum.A.getKey() == type) {
                cabinetStore.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.A.getKey()));
            } else {
                cabinetStore.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.B.getKey()));
            }
        }else {
            if (CabinetStoreCreateTypeEnum.A.getKey() == type) {
                cabinetStore.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.A.getKey()));
            } else {
                cabinetStore.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.B.getKey()));
            }
            cabinetStore.setGmt_create(DateUtil.getNowTimeStr());
            cabinetStore.setGmt_modify(DateUtil.getNowTimeStr());
        }
        cabinetStore.setCreate_status(CabinetStoreCreateStatusEnum.A.getKey());
        coorpOperationReqDto.setCabinetStore(cabinetStore);

        /**
         * 店铺属性信息
         */
        CabinetStoreProperty csp = JSON.parseObject(data.getJSONObject("cabinet_store_property").toJSONString(),CabinetStoreProperty.class);
        CabinetStoreProperty cp = cabinetStorePropertyMapper.selectByStoreId(cabinetStore.getId());
        if (cp == null){
            csp.setCabinet_store_id(cabinetStore.getId());
            csp.setGmt_create(DateUtil.getNowTimeStr());
            csp.setGmt_modify(DateUtil.getNowTimeStr());
        }else {
            csp.setGmt_modify(DateUtil.getNowTimeStr());
        }
        coorpOperationReqDto.setCabinetStoreProperty(csp);

        /**
         * 合同信息
         */
        CabinetContract con = null;
        if (data.containsKey("cabinet_contract")) {
            con = JSON.parseObject(data.getJSONObject("cabinet_contract").toJSONString(), CabinetContract.class);
            if (StringUtils.isEmpty(con.getId()) || StringUtils.equals(con.getId(),NULL_ID) ||con.getId() == null) {
                con.setStatus("0");
                con.setGmt_create(DateUtil.getNowTimeStr());
                con.setGmt_modify(DateUtil.getNowTimeStr());
            }else {
                con.setGmt_modify(DateUtil.getNowTimeStr());
            }
            coorpOperationReqDto.setCabinetContract(con);
        }

        /**
         * 合作信息
         */
        CabinetBussinessCoorperation cbc = JSON.parseObject(data.getJSONObject("cabinet_bussiness_coorperation").toJSONString(),CabinetBussinessCoorperation.class);
        CabinetBussinessCoorperation cbcm = cabinetBussinessCoorperationMapper.selectByStoreId(cabinetStore.getId());
        if (cbcm != null){
            cbc.setStore_id(cbcm.getStore_id());
            cbc.setContract_id(cbcm.getContract_id());
            cbc.setGmt_modify(DateUtil.getNowTimeStr());
        }else {
            cbc.setGmt_create(DateUtil.getNowTimeStr());
            cbc.setGmt_modify(DateUtil.getNowTimeStr());
        }
        coorpOperationReqDto.setCabinetBussinessCoorperation(cbc);

        if (StringUtils.isNotEmpty(basicReqDto.getShowcase_imei())){
            CabinetInfo ci = cabinetInfoMapper.findByImei(basicReqDto.getShowcase_imei());
            if (ci != null){
                ci.setGmt_modify(DateUtil.getNowTimeStr());
                ci.setImei(basicReqDto.getShowcase_imei());
                ci.setType(String.valueOf(CabinetTypeEnum.B.getKey()));
                ci.setCreate_status(CabinetStoreCreateStatusEnum.A.getKey());
            }else {
                ci = new CabinetInfo();
                ci.setStatus("0");
                ci.setCreator(basicReqDto.getBd_name());
                ci.setCreator_phone(basicReqDto.getBd_phone());
                ci.setImei(basicReqDto.getShowcase_imei());
                ci.setGmt_create(DateUtil.getNowTimeStr());
                ci.setGmt_modify(DateUtil.getNowTimeStr());
                ci.setType(String.valueOf(CabinetTypeEnum.B.getKey()));
                ci.setCreate_status(CabinetStoreCreateStatusEnum.A.getKey());
            }
            coorpOperationReqDto.setCabinetInfo(ci);
        }
        return coorpOperationReqDto;
    }
    private CoorpOperationReqDto create(JSONObject data,int type){
        CoorpOperationReqDto coorpOperationReqDto = new CoorpOperationReqDto();
        BasicReqDto basicReqDto = JSON.parseObject(data.getJSONObject("basic").toJSONString(),BasicReqDto.class);
        String bdName = basicReqDto.getBd_name();
        String bdPhone = basicReqDto.getBd_phone();
        String ownerName = basicReqDto.getOwner_name();
        String ownerPhone = basicReqDto.getOwner_phone();
        User bdUser = userMapper.getByPhoneNo(bdPhone);
        User ownerUser = userMapper.getByPhoneNo(ownerPhone);
        if (bdUser == null || ownerUser == null){
            return null;
        }

        /**
         * 店铺信息,如果店铺存在则更新,如果不存在,则插入
         */
        CabinetStore cabinetStore = JSON.parseObject(data.getJSONObject("cabinet_store").toJSONString(),CabinetStore.class);
        if (StringUtils.isEmpty(cabinetStore.getId()) || (StringUtils.equals(cabinetStore.getId(),NULL_ID) || cabinetStore.getId() == null)) {
            cabinetStore.setGmt_create(DateUtil.getNowTimeStr());
            cabinetStore.setGmt_modify(DateUtil.getNowTimeStr());
            if (CabinetStoreCreateTypeEnum.A.getKey() == type) {
                cabinetStore.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.A.getKey()));
            } else {
                cabinetStore.setCreate_type(String.valueOf(CabinetStoreCreateTypeEnum.B.getKey()));
            }
            cabinetStore.setCreate_status(CabinetStoreCreateStatusEnum.B.getKey());
        }else {
            cabinetStore.setGmt_create(DateUtil.getNowTimeStr());
            cabinetStore.setGmt_modify(DateUtil.getNowTimeStr());
            cabinetStore.setCreate_type(String.valueOf(basicReqDto.getCreate_type()));
        }
        coorpOperationReqDto.setCabinetStore(cabinetStore);

        /**
         * 店铺属性信息
         */
        CabinetStoreProperty csp = JSON.parseObject(data.getJSONObject("cabinet_store_property").toJSONString(),CabinetStoreProperty.class);
        //TODO 通过cabinet_store_id获取csp
        CabinetStoreProperty cp = cabinetStorePropertyMapper.selectByStoreId(cabinetStore.getId());
        if (cp == null){
            csp.setCabinet_store_id(cabinetStore.getId());
            csp.setGmt_create(DateUtil.getNowTimeStr());
            csp.setGmt_modify(DateUtil.getNowTimeStr());
        }else {
            csp.setCabinet_store_id(cp.getCabinet_store_id());
            csp.setId(cp.getId());
            csp.setGmt_create(DateUtil.getNowTimeStr());
            csp.setGmt_modify(DateUtil.getNowTimeStr());
        }
        coorpOperationReqDto.setCabinetStoreProperty(csp);

        /**
         * 合同信息
         */
        if (data.containsKey("cabinet_contract")) {
            CabinetContract con = JSON.parseObject(data.getJSONObject("cabinet_contract").toJSONString(), CabinetContract.class);
            if (StringUtils.isEmpty(con.getId()) || StringUtils.equals(con.getId(),NULL_ID)){
                con.setStatus("0");
                con.setGmt_create(DateUtil.getNowTimeStr());
                con.setGmt_modify(DateUtil.getNowTimeStr());
            }else {
                con.setGmt_modify(DateUtil.getNowTimeStr());
            }
            coorpOperationReqDto.setCabinetContract(con);
        }

        /**
         * 合作信息
         */
        CabinetBussinessCoorperation cbc = JSON.parseObject(data.getJSONObject("cabinet_bussiness_coorperation").toJSONString(),CabinetBussinessCoorperation.class);
        CabinetBussinessCoorperation cbcm = cabinetBussinessCoorperationMapper.selectByStoreId(cabinetStore.getId());
        if (cbcm != null){
            cbc.setContract_id(cbcm.getContract_id());
            cbc.setGmt_create(DateUtil.getNowTimeStr());
            cbc.setGmt_modify(DateUtil.getNowTimeStr());
        }else {
            cbc.setGmt_create(DateUtil.getNowTimeStr());
            cbc.setGmt_modify(DateUtil.getNowTimeStr());
            cbc.setOwner_user_id(String.valueOf(ownerUser.getId()));
            cbc.setBd_user_id(String.valueOf(bdUser.getId()));
        }
        coorpOperationReqDto.setCabinetBussinessCoorperation(cbc);
        /**
         * 绑定角色
         */
        long roleId = basicRoleMapper.selectByRoleType(Constant.STORE_MANAGEMENT);
        BasicUserRole bur = new BasicUserRole();
        bur.setUser_id(ownerUser.getId());
        bur.setRole_id(roleId);
        bur.setId(Long.parseLong(StringUtil.getRandomIntByLength(18)));
        coorpOperationReqDto.setBasicUserRole(bur);
        /**
         * 插入展柜信息
         */
        CabinetInfo ci = cabinetInfoMapper.findByImei(basicReqDto.getShowcase_imei());
        if (ci != null){
            ci.setGmt_modify(DateUtil.getNowTimeStr());
            ci.setImei(basicReqDto.getShowcase_imei());
            ci.setCreate_status(CabinetStoreCreateStatusEnum.B.getKey());
            ci.setType(String.valueOf(CabinetTypeEnum.B.getKey()));
        }else {
            ci = new CabinetInfo();
            ci.setStatus("0");
            ci.setCreator(basicReqDto.getBd_name());
            ci.setCreator_phone(basicReqDto.getBd_phone());
            ci.setImei(basicReqDto.getShowcase_imei());
            ci.setGmt_create(DateUtil.getNowTimeStr());
            ci.setGmt_modify(DateUtil.getNowTimeStr());
            ci.setType(String.valueOf(CabinetTypeEnum.B.getKey()));
            ci.setCreate_status(CabinetStoreCreateStatusEnum.B.getKey());
        }
        coorpOperationReqDto.setCabinetInfo(ci);
        return coorpOperationReqDto;
    }

    @Override
    public ResponseJson coorperationInfo(JSONObject data) {
        log.info("center-data-coorperationInfo jsonParam={}",data.toJSONString());
        long start = System.currentTimeMillis();
        try {
            String cabinetStoreId = data.getString("cabinet_store_id");
            cabinetBussinessCoorperationMapper.selectByStoreId(cabinetStoreId);
            CreateCooperationResVO coorperationInfo =  cabinetBussinessCoorperationMapper.getCoorperationInfo(cabinetStoreId);
            JSONObject result = JSON.parseObject(JSON.toJSONString(coorperationInfo));
            if (coorperationInfo != null) {
                String cpic = coorperationInfo.getContract_pic();
                if (StringUtils.isNotEmpty(cpic)) {
                    JSONArray contract_pic = JSON.parseArray(coorperationInfo.getContract_pic());
                    result.remove("contract_pic");
                    result.put("contract_pic", contract_pic);

                } else {
                    JSONArray contract_pic = new JSONArray();
                    result.put("contract_pic", contract_pic);
                }
                String coorpic = coorperationInfo.getCoorperation_pic();
                if (StringUtils.isNotEmpty(coorpic)) {
                    JSONArray coorperation_pic = JSON.parseArray(coorperationInfo.getCoorperation_pic());
                    result.remove("coorperation_pic");
                    result.put("coorperation_pic", coorperation_pic);
                } else {
                    JSONArray coorperation_pic = new JSONArray();
                    result.put("coorperation_pic", coorperation_pic);
                }
            }
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,result);
        }catch (Exception e){
            log.error("center-data-coorperationInfo has some errors , the error message is {}",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }
    @Override
    public ResponseJson coorperationTreatCreate(JSONObject data){
        log.info("call method coorperationTreatCreate jsonParameter={}",data.toJSONString());
        long start = System.currentTimeMillis();
        try {
            CoorpListReqDto clrd = JSON.parseObject(data.toJSONString(),CoorpListReqDto.class);
            //已使用
            Page<Object> page = PageHelper.startPage(clrd.getPage_num(), clrd.getPage_size());
            List<BdStoreMsgRespVO> result = cabinetBussinessCoorperationMapper.selectPageByStatus(CabinetStoreCreateStatusEnum.A.getKey(), clrd.getPhone(),clrd.getType(),clrd.getCondition());
            PageInfo pageInfo = PageUtil.pageHelperHandle(page, result);
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("call method coorperationTreatCreat has some errors , errors message is {}",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson coorperationCreated(JSONObject data) {
        log.info("call method coorperationCreated jsonParameter={}",data.toJSONString());
        long start = System.currentTimeMillis();
        try {
            CoorpListReqDto clrd = JSON.parseObject(data.toJSONString(),CoorpListReqDto.class);
            //已使用
            Page<Object> page = PageHelper.startPage(clrd.getPage_num(), clrd.getPage_size());
            List<BdStoreMsgRespVO> result = cabinetBussinessCoorperationMapper.selectPageByStatus(CabinetStoreCreateStatusEnum.B.getKey(), clrd.getPhone(),clrd.getType(),clrd.getCondition());
            PageInfo pageInfo = PageUtil.pageHelperHandle(page, result);
            long end = System.currentTimeMillis();
            log.info("call method coorperationCreated cost {} mills",( end - start ));
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("call method coorperationTreatCreat has some errors , errors message is {}",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson coorperationAlreadyUsed(JSONObject data) {
        log.info("call method coorperationAlreadyUsed jsonParameter={}",data.toJSONString());
        long start = System.currentTimeMillis();
        try {
            CoorpListReqDto clrd = JSON.parseObject(data.toJSONString(),CoorpListReqDto.class);
            //已使用
            Page<Object> page = PageHelper.startPage(clrd.getPage_num(), clrd.getPage_size());
            List<BdStoreMsgRespVO> result = cabinetBussinessCoorperationMapper.selectPageByStatus(CabinetStoreCreateStatusEnum.C.getKey(), clrd.getPhone(),clrd.getType(),clrd.getCondition());
            PageInfo pageInfo = PageUtil.pageHelperHandle(page, result);
            long end = System.currentTimeMillis();
            log.info("call method coorperationAlreadyUsed cost {} mills",( end - start ));
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("call method coorperationTreatCreat has some errors , errors message is {}",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson coorperationAll(JSONObject data) {
        log.info("call method coorperationAll jsonParameter={}",data.toJSONString());
        long start = System.currentTimeMillis();
        try {
            CoorpListReqDto clrd = JSON.parseObject(data.toJSONString(),CoorpListReqDto.class);
            //已使用
            Page<Object> page = PageHelper.startPage(clrd.getPage_num(), clrd.getPage_size());
            List<BdStoreMsgRespVO> result = cabinetBussinessCoorperationMapper.selectPageByStatus(null, clrd.getPhone(),clrd.getType(),clrd.getCondition());
            PageInfo pageInfo = PageUtil.pageHelperHandle(page, result);
            long end = System.currentTimeMillis();
            log.info("call method coorperationAll cost {} mills",( end - start ));
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("call method coorperationTreatCreat has some errors , errors message is {}",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson search(JSONObject data) {
        log.info("call method coorperationSearch jsonParam={}",data.toJSONString());
        long start = System.currentTimeMillis();
        try {
            int page_num = data.getIntValue("page_num");
            int page_size = data.getIntValue("page_size");
            //搜索
            if (page_num == 0 || page_num == 0){
                page_num = PAGE_NUM;
                page_size = PAGE_SIZE;
            }
            Page<Object> page = PageHelper.startPage(page_num, page_size);
            String condition = data.getString("condition");
            String phone = data.getString("bd_phone");
            int type = data.getIntValue("type");
            List<BdStoreMsgRespVO> search = cabinetBussinessCoorperationMapper.search(condition, phone,type);
            PageInfo pageInfo = PageUtil.pageHelperHandle(page, search);
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,pageInfo);
        }catch (Exception e){
            log.error("call method coorperationSearch has some errors , errors message are {}",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }
    /**
     * 通过店铺id查询商务合作信息
     * @param data
     * @return
     */
    @Override
    public  ResponseJson coorperationGetByStroreId(JSONObject data){
       String storeId = String.valueOf(data.get("storeId"));
       log.info("call method coorperationSearch jsonParam={}",data.toJSONString());
       long start = System.currentTimeMillis();
       try {
           CabinetBussinessCoorperation cabinetBussinessCoorperation = cabinetBussinessCoorperationMapper.selectByStoreId(storeId);
           return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,cabinetBussinessCoorperation);
       }catch (Exception e){
           log.error("call method coorperationSearch has some errors , errors message are {}",e);
           return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
       }
   }

    @Override
    public ResponseJson basicArea(JSONObject data) {
        log.info("call method coorperationSearch jsonParam={}",data.toJSONString());
        long start = System.currentTimeMillis();
        try {
            int m_id = data.getIntValue("m_id");
            List<Map<String, Integer>> result = cabinetAreaMapper.selectCircleByMId(m_id);
            log.info("cabinetArea result={}",JSON.toJSONString(result));
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,result);
        }catch (Exception e){
            log.error("call method coorperationSearch has some errors , errors message are {}",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }


    @Override
    public ResponseJson selectByStoreIdAndStatus(JSONObject data){
        try {
            CabinetBussinessCoorperation record = JSONObject.toJavaObject(data, CabinetBussinessCoorperation.class);
            if (StringUtil.isEmpty(record.getStore_id())||StringUtil.isEmpty(record.getStatus())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数不能为空");
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, cabinetBussinessCoorperationMapper.selectByStoreIdAndStatus(record));
        } catch (Exception e) {
            log.error("CabinetBusinessCoorperationServiceImpl.selectByStoreIdAndStatus:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson findBdName(JSONObject data) {
        long userId = data.getLongValue("userId");
        String name = userMapper.findBDNameByUserId(userId);

        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,name);
    }
}
