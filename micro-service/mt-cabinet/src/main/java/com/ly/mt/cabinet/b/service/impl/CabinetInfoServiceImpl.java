package com.ly.mt.cabinet.b.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.dict.CabinetInfoStatusEnum;
import com.ly.mt.cabinet.b.common.dict.CabinetStoreStatusEnum;
import com.ly.mt.cabinet.b.common.dict.CabinetTypeEnum;
import com.ly.mt.cabinet.b.common.dict.StoreCreateStatusEnum;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.*;
import com.ly.mt.cabinet.b.common.response.PageInfoResponseVo;
import com.ly.mt.cabinet.b.common.response.ShowCabinetByBdMsgVo;
import com.ly.mt.cabinet.b.common.response.ShowcaseCoopDefaultRespVo;
import com.ly.mt.cabinet.b.common.response.SquareCabinetBdMsgVo;
import com.ly.mt.cabinet.b.entity.CabinetCreateRecord;
import com.ly.mt.cabinet.b.entity.CabinetInfo;
import com.ly.mt.cabinet.b.entity.CabinetStore;
import com.ly.mt.cabinet.b.service.CabinetInfoService;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.Device.service.GzgExchangeService;
import com.ly.mt.cabinet.c.good.entity.GzgGoodsPlan;
import com.ly.mt.cabinet.c.programme.entity.GzgProgramme;
import com.ly.mt.cabinet.c.rujin.entity.GzgRujinRelation;
import com.ly.mt.cabinet.c.rujin.entity.RujinDoEnum;
import com.ly.mt.cabinet.c.rujin.service.GzgRujinDeviceService;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.cabinet.b.common.dict.CabinetTypeEnum.CABINET_TYPE_RU_JIN;
import static com.ly.mt.cabinet.b.common.dict.CabinetTypeEnum.CABINET_TYPE_YI_LIAN;
import static com.ly.mt.core.feign.DataCenterMethod.*;

@Service
public class CabinetInfoServiceImpl extends BaseServiceImpl implements CabinetInfoService {

    private static final Logger log = LoggerFactory.getLogger(CabinetInfoServiceImpl.class);

    @Autowired
    GzgExchangeService gzgExchangeService;
    @Autowired
    GzgRujinDeviceService gzgRujinDeviceService;
    /**
     *  新增格子柜录入信息时，返回给前端展示目前货柜类型和对应的配货方案
     * @return
     */
    @Override
    public ResponseJson getCabinetInfoVoParam(WarehouseKeeperVo warehouseKeeperVo) {
        return null;
    }

    @Override
    public ResponseJson getCabinetStoreNotUsed() {
        return null;
    }

    @Override
    public ResponseJson getCabinetStoreIsOnline() {
        return null;

    }

    @Override
    public String squareCabinets(CabinetMenageListRequestVo body, TokenUserMessage userToken) {
        return null;
    }


    /**
     * 查询旗下的管理的格子柜
     * @param body
     * @param userToken
     * @return
     */
    @Override
    public PageInfoResponseVo<SquareCabinetBdMsgVo> findCabinetsByUser(CabinetMenageListRequestVo body, TokenUserMessage userToken) {
        JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(body));
        param.put("phoneNo",userToken.getPhoneNo());
        param.put("userId",userToken.getUserId());
        String result = callDataCenter(DataCenterMethod.CABINET_FIND_CABINETS_BY_USER, param);
        PageInfoResponseVo<SquareCabinetBdMsgVo> pageInfoResponseVo = JSONObject.toJavaObject(JSONObject.parseObject(result), PageInfoResponseVo.class);
        return pageInfoResponseVo;
    }


    @Override
    public String upShowCabinet(ImeiRequestBody imei, TokenUserMessage tokenUserMessage) {
        JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(tokenUserMessage));
        param.put("imei",imei.getImei());
        String s = callDataCenter(DataCenterMethod.CABINET_UP_SHOWCASE_CABINET, param);
        return s;
    }


    /**
     * 新增格子柜录入信息时，返回给前端展示目前货柜类型和对应的配货方案
     *
     * @return
     */
    @Override
    public ResponseJson getCabinetProgrammeList() {
        List<String> programmeNameList = getProgrammeNameList();
        JSONObject jsonYilian = new JSONObject();
        JSONObject jsonRujin = new JSONObject();
        CabinetTypeEnum[] values = CabinetTypeEnum.values();
        List rujinList = new ArrayList();
        List yilianList = new ArrayList();

        for (int i = 0; i < programmeNameList.size(); i++) {
            if(programmeNameList.get(i).startsWith(CABINET_TYPE_YI_LIAN.getCode())){
                yilianList.add(programmeNameList.get(i));
            }
            if(programmeNameList.get(i).startsWith(CABINET_TYPE_RU_JIN.getCode())){
                rujinList.add(programmeNameList.get(i));
            }
        }
        jsonYilian.put("name",CABINET_TYPE_YI_LIAN.getCode());
        jsonYilian.put("programme",yilianList);


        jsonRujin.put("name",CABINET_TYPE_RU_JIN.getCode());
        jsonRujin.put("programme",rujinList);

        List listResponse = new ArrayList();
        listResponse.add(jsonYilian);
        listResponse.add(jsonRujin);

        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, listResponse);
    }

    @Override
    public ResponseJson addCabinetInfoBindStore(CabinetInfoBindStoreReqVo cabinetInfoBindStoreReqVo) throws Exception {
        log.info("CabinetInfoServiceImpl.addCabinetInfoBindStore serviceImpl 层接收的数据",cabinetInfoBindStoreReqVo.toString());
        String imei = cabinetInfoBindStoreReqVo.getImei();
        String programmeName = cabinetInfoBindStoreReqVo.getProgramme();
        boolean startsWith = true;
        if(imei.length()==15){//亿联的柜子
            startsWith  = programmeName.startsWith(CABINET_TYPE_YI_LIAN.getCode());
        }else {//如金的柜子
            startsWith  = programmeName.startsWith(CABINET_TYPE_RU_JIN.getCode());
        }
        if(!startsWith){
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,"柜子与配货方案不符");
        }
        //判断柜子是否在线
        Boolean isOnline =  true;
        if(imei.length()==15){//亿联的柜子
            isOnline  = gzgExchangeService.getIsAvailableBycode(imei);//判断是否在线
        }else {//如金的柜子
            isOnline = gzgRujinDeviceService.isOnline(imei);
        }
        if(!isOnline){
            log.info("绑定的柜子 {} 不在线不可用",imei);
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,"柜子不在线，请检查设备是否通电");
        }
        GzgRujinRelation gzgRujinRelation = null;
        if(imei.length()!=15){
            gzgRujinRelation = getGzgRujinRelationByTid(imei);//此处如金传输过来的是tid，亿联是是imei，后期如金的imei值要替换
            if(gzgRujinRelation == null){
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,"此如金柜子未绑定，请联系运维人员");
            }
            imei = gzgRujinRelation.getName();
        }
        List<GzgProgramme> gzgProgrammeList = getGzgProgrammeList(programmeName);


        //判断柜子是否已经被使用（包括下架）
        List<GzgGoodsPlan> gzgGoodsPlanList = getGzgGoodsPlanList(imei);
        if(gzgGoodsPlanList == null || gzgGoodsPlanList.size()==0){//以前未配置过该柜子，添加数据
            for (int i = 0; i < gzgProgrammeList.size(); i++) {
                GzgGoodsPlan gzgGoodsPlan = new GzgGoodsPlan();
                gzgGoodsPlan.setId(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_GZG_GOODS_PLAN));
                gzgGoodsPlan.setImei(imei);
                gzgGoodsPlan.setCabinet_no(gzgProgrammeList.get(i).getCabinet_no());
                gzgGoodsPlan.setSku_id(gzgProgrammeList.get(i).getSku_id());
                gzgGoodsPlan.setPlan_name(programmeName);
                gzgGoodsPlan.setStock(gzgProgrammeList.get(i).getStock());//柜子初始化，库存为1
                gzgGoodsPlan.setState("1");//0:不可用，1：正常使用
                gzgGoodsPlan.setSell_no("0");//初始化卖出数量为0
                gzgGoodsPlan.setCreate_time(DateUtil.getNowTimeStr());
                if(programmeName.startsWith(CABINET_TYPE_YI_LIAN.getCode())){
                     gzgGoodsPlan.setCabinet_type(CABINET_TYPE_YI_LIAN.getKey());
                }else {
                    gzgGoodsPlan.setCabinet_type(CABINET_TYPE_RU_JIN.getKey());
                }
                gzgGoodsPlanList.add(gzgGoodsPlan);
            }
                CabinetInfo cabinetInfo = new CabinetInfo();
            String primaryKeyCabinetInfo = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_CABINET_INFO);
               cabinetInfo.setId(primaryKeyCabinetInfo);
                cabinetInfo.setImei(imei);
                cabinetInfo.setStore_id(cabinetInfoBindStoreReqVo.getStore_id());
                cabinetInfo.setProgramme_name(cabinetInfoBindStoreReqVo.getProgramme());
                cabinetInfo.setCreate_status("0");//创建状态 0:已上线  1:已保存  2:待使用
                cabinetInfo.setStatus(CabinetInfoStatusEnum.A.getKey());//0:上架  1:下架
                if(programmeName.startsWith(CABINET_TYPE_YI_LIAN.getCode())){
                    cabinetInfo.setType(CABINET_TYPE_YI_LIAN.getKey());
                }else if(programmeName.startsWith(CABINET_TYPE_RU_JIN.getCode())){
                    cabinetInfo.setType(CABINET_TYPE_RU_JIN.getKey());
                }
                cabinetInfo.setCreator(cabinetInfoBindStoreReqVo.getCreator());
                cabinetInfo.setCreator_phone(cabinetInfoBindStoreReqVo.getPhone());
                cabinetInfo.setGmt_create(DateUtil.getNowTimeStr());
                cabinetInfo.setGmt_modify(DateUtil.getNowTimeStr());
            //修改cabinet_store表create_status为上线
            CabinetStore cabinetStore = new CabinetStore();
            cabinetStore.setCreate_status(StoreCreateStatusEnum.C.getKey()+"");
            cabinetStore.setId(cabinetInfoBindStoreReqVo.getStore_id());

            //修改cabinet_store表create_status为上线
            CabinetCreateRecord cabinetCreateRecord = new CabinetCreateRecord();
            cabinetCreateRecord.setId(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_CABINET_CREATE_RECORD));
            cabinetCreateRecord.setCabinet_info_id(primaryKeyCabinetInfo);
            cabinetCreateRecord.setCreateor_phone(cabinetInfoBindStoreReqVo.getPhone());
            cabinetCreateRecord.setGmt_create(DateUtil.getNowTimeStr());
            cabinetCreateRecord.setGmt_modify(DateUtil.getNowTimeStr());
            cabinetCreateRecord.setStatus(CabinetInfoStatusEnum.A.getKey());

            insertDataCabinetBindStore(cabinetInfo,gzgGoodsPlanList,cabinetStore,cabinetCreateRecord);
        }else {//以前配置过该柜子，修改数据

            if(gzgGoodsPlanList.size() != gzgProgrammeList.size()){
                return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,"配货方案与格子柜不匹配");
            }

            for (int i = 0; i < gzgGoodsPlanList.size(); i++) {
                gzgGoodsPlanList.get(i).setSku_id(gzgProgrammeList.get(i).getSku_id());
                gzgGoodsPlanList.get(i).setPlan_name(programmeName);
                gzgGoodsPlanList.get(i).setStock(gzgProgrammeList.get(i).getStock());
                gzgGoodsPlanList.get(i).setState("1");//0:不可用，1：正常使用
            }


            CabinetInfo cabinetInfo = getCabinetInfo(imei);
            log.info("=========================++++++++++++++++++++++++++=============================");
            log.info("cabinetInfoBindStoreReqVo 值 {}",cabinetInfoBindStoreReqVo.toString());
            log.info("cabinetInfoBindStoreReqVo.getStore_id() 值 {}",cabinetInfoBindStoreReqVo.getStore_id());
            cabinetInfo.setStore_id(cabinetInfoBindStoreReqVo.getStore_id());
            cabinetInfo.setProgramme_name(cabinetInfoBindStoreReqVo.getProgramme());
            cabinetInfo.setCreate_status("0");//创建状态 0:已上线  1:已保存  2:待使用
            cabinetInfo.setStatus(CabinetInfoStatusEnum.A.getKey());//0:上架  1:下架
            if(programmeName.startsWith(CABINET_TYPE_YI_LIAN.getCode())){
                cabinetInfo.setType(CABINET_TYPE_YI_LIAN.getKey());
            }else if(programmeName.startsWith(CABINET_TYPE_RU_JIN.getCode())){
                cabinetInfo.setType(CABINET_TYPE_RU_JIN.getKey());
            }
            cabinetInfo.setCreator(cabinetInfoBindStoreReqVo.getCreator());
            cabinetInfo.setCreator_phone(cabinetInfoBindStoreReqVo.getPhone());
            cabinetInfo.setGmt_modify(DateUtil.getNowTimeStr());


            CabinetStore cabinetStore = new CabinetStore();
            cabinetStore.setCreate_status(StoreCreateStatusEnum.C.getKey()+"");
            cabinetStore.setId(cabinetInfoBindStoreReqVo.getStore_id());

            updateDataCabinetBindStore(cabinetInfo,gzgGoodsPlanList,cabinetStore);
        }

        //库管打开柜门，放入货物
        Boolean openDevice = true;
        if(gzgRujinRelation == null ){//亿联的柜子
            openDevice = gzgExchangeService.maintainOpenDevice(imei);
        }else {//如金的柜子
            openDevice = gzgRujinDeviceService.maintainDevice(gzgRujinRelation.getTid(), "1-2-3-4", RujinDoEnum.RUJIN_ONLINE);
        }
        if(openDevice){
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);

        }else {
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 查询格子柜上下架中合作信息详情
     * @param imei
     * @param tokenUserMessage
     * @return
     */
    @Override
    public ShowcaseCoopDefaultRespVo findSquareCabinetCoopMsg(ImeiRequestBody imei, TokenUserMessage tokenUserMessage) {
        JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(tokenUserMessage));
        param.put("imei",imei.getImei());
        String result = callDataCenter(DataCenterMethod.CABINET_SQUARE_CABINET_COOP_MESSAGE,param);
        ShowcaseCoopDefaultRespVo vo = JSONObject.toJavaObject(JSONObject.parseObject(result), ShowcaseCoopDefaultRespVo.class);
        return vo;
    }

    @Override
    public String downSquareCabinet(ImeiRequestBody imei, TokenUserMessage tokenUserMessage) {
        JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(tokenUserMessage));
        param.put("imei",imei.getImei());
        String result = callDataCenter(DataCenterMethod.CABINET_DOWN_SQUARE_CABINET, param);
        return result;
    }

    @Override
    public PageInfoResponseVo<ShowCabinetByBdMsgVo> showcaseCabinets(BasePageRequestVo body, TokenUserMessage userToken) {
        JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(body));
        param.put("phoneNo",userToken.getPhoneNo());
        param.put("userId",userToken.getUserId());
        String result = callDataCenter(DataCenterMethod.CABINET_FIND_SHOW_CABINETS_BY_USER, param);
        PageInfoResponseVo<ShowCabinetByBdMsgVo> vo = JSONObject.toJavaObject(JSONObject.parseObject(result), PageInfoResponseVo.class);
        return vo;
    }

    @Override
    public ShowcaseCoopDefaultRespVo findShowcaseCabinetCoopMsg(ImeiRequestBody imei, TokenUserMessage tokenUserMessage) {
        JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(tokenUserMessage));
        param.put("imei",imei.getImei());

        String s = callDataCenter(DataCenterMethod.CABINET_SHOW_CABINET_COOP_MESSAGE, param);
        ShowcaseCoopDefaultRespVo vo = JSONObject.toJavaObject(JSONObject.parseObject(s), ShowcaseCoopDefaultRespVo.class);
        return vo;
    }

    @Override
    public String downShowCabinet(ImeiRequestBody imei, TokenUserMessage tokenUserMessage) {
        JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(tokenUserMessage));
        param.put("imei",imei.getImei());
        return   callDataCenter(DataCenterMethod.CABINET_DOWN_SHOWCASE_CABINET,param);
    }

    /**
     * 查询所有的配货方案名称
     *
     * @param
     * @return
     */
    public List<String> getProgrammeNameList() {
        JSONObject jsonObject = new JSONObject();
        String resultString = callDataCenter(CABINET_INFO_GET_PROGRAMME_LIST, jsonObject);
        List<String> programmeNameList = JSONArray.parseArray(resultString, String.class);
        return programmeNameList;
    }


    /**
     * 根据方案名称查询具体的配货方案
     * @param
     * @return
     */
    public List<GzgProgramme> getGzgProgrammeList(String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        String resultString = callDataCenter(GZG_PROGRAMME_GET_LIST_BY_NAME, jsonObject);
        List<GzgProgramme> gzgProgrammeList = JSONArray.parseArray(resultString, GzgProgramme.class);
        return gzgProgrammeList;
    }

    /**
     * 根据方案名称查询具体的配货方案
     * @param
     * @return
     */
    public List<GzgGoodsPlan> getGzgGoodsPlanList(String imei) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imei",imei);
        String resultString = callDataCenter(GZG_GOODS_PLAN_GET, jsonObject);
        List<GzgGoodsPlan> gzgGoodsPlanList = JSONArray.parseArray(resultString, GzgGoodsPlan.class);
        return gzgGoodsPlanList;
    }

    /**
     * 根据imei查询格子柜信息
     * @param
     * @return
     */
    public CabinetInfo getCabinetInfo(String imei) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imei",imei);
        String resultString = callDataCenter(CABINET_INFO_GET_BY_IMEI, jsonObject);
        CabinetInfo cabinetInfo = JSONObject.parseObject(resultString, CabinetInfo.class);
        return cabinetInfo;
    }



    /**
     * 仓库管理员将柜子绑定至店铺时的数据库操作 ---添加操作
     * @param
     * @return
     */
    @TxcTransaction(appName = "gzg")
    public void insertDataCabinetBindStore(CabinetInfo cabinetInfo, List<GzgGoodsPlan> gzgGoodsPlanList ,CabinetStore cabinetStore,CabinetCreateRecord cabinetCreateRecord) {
        String xid = TxcContext.getCurrentXid();
        JSONObject jsonCabinetInfo = JSONObject.parseObject(JSONObject.toJSONString(cabinetInfo));
        jsonCabinetInfo.put("xid",xid);

        JSONObject jsonGzgGoodsPlanList = new JSONObject();
        jsonGzgGoodsPlanList.put("xid",xid);
        jsonGzgGoodsPlanList.put("alist",JSONObject.toJSONString(gzgGoodsPlanList));

        JSONObject jsonCabinetStore = JSONObject.parseObject(JSONObject.toJSONString(cabinetStore));
        jsonCabinetStore.put("xid",xid);

        JSONObject jsonCabinetCreateRecord = JSONObject.parseObject(JSONObject.toJSONString(cabinetCreateRecord));
        jsonCabinetCreateRecord.put("xid",xid);

        callDataCenter(CABINET_INFO_INSERT, jsonCabinetInfo);
        callDataCenter(GZG_GOODS_PLAN_INSERT_BATCH, jsonGzgGoodsPlanList);
        callDataCenter(CABINET_STORE_UPDATE_STATUS_BY_ID, jsonCabinetStore);
        callDataCenter(CABINET_CRATE_RECORD_INSERT, jsonCabinetCreateRecord);

    }

    /**
     * 仓库管理员将柜子绑定至店铺时的数据库操作 ---更新操作
     * @param
     * @return
     */
    @TxcTransaction(appName = "gzg")
    public void updateDataCabinetBindStore(CabinetInfo cabinetInfo, List<GzgGoodsPlan> gzgGoodsPlanList,CabinetStore cabinetStore) {
        String xid = TxcContext.getCurrentXid();
        JSONObject jsonCabinetInfo = JSONObject.parseObject(JSONObject.toJSONString(cabinetInfo));
        jsonCabinetInfo.put("xid",xid);

        JSONObject jsonGzgGoodsPlanList = new JSONObject();
        jsonGzgGoodsPlanList.put("xid",xid);
        jsonGzgGoodsPlanList.put("alist",JSONObject.toJSONString(gzgGoodsPlanList));

        JSONObject jsonCabinetStore = JSONObject.parseObject(JSONObject.toJSONString(cabinetStore));
        jsonCabinetStore.put("xid",xid);

        callDataCenter(CABINET_INFO_UPDATE, jsonCabinetInfo);
        callDataCenter(GZG_GOODS_PLAN_UPDATE_BATCH, jsonGzgGoodsPlanList);
        callDataCenter(CABINET_STORE_UPDATE_STATUS_BY_ID, jsonCabinetStore);

    }


    /**
     * 通过name获取GzgRujinRelation
     * @param tid
     * @return
     */
    public GzgRujinRelation getGzgRujinRelationByTid(String tid) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tid",tid);
        String result = callDataCenter(GZG_RUJIN_RELATION_GET_BY_TID, jsonObject);
        GzgRujinRelation gzgRujinRelation = JSONObject.toJavaObject(JSONObject.parseObject(result), GzgRujinRelation.class);
        return gzgRujinRelation;
    }



}
