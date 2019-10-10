package com.ly.mt.cabinet.b.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.dict.StoreCreateStatusEnum;
import com.ly.mt.cabinet.b.common.response.CreateCooperationResVO;
import com.ly.mt.cabinet.b.entity.CabinetInfo;
import com.ly.mt.cabinet.b.entity.CabinetStore;
import com.ly.mt.cabinet.b.common.request.WarehouseKeeperVo;
import com.ly.mt.cabinet.b.login.vo.TokenInfo;
import com.ly.mt.cabinet.b.service.CabinetStoreService;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ly.mt.core.feign.DataCenterMethod.*;

@Service
public class CabinetStoreServiceImpl extends BaseServiceImpl implements CabinetStoreService {

    private static final Logger log = LoggerFactory.getLogger(CabinetStoreServiceImpl.class);

    /**
     *  仓库人员通过自己的user_id获取所负责区域所有商家的信息
     * @return
     */
    @Override
    public ResponseJson getCabinetStoreByUserId(WarehouseKeeperVo warehouseKeeperVo,String user_id) {
//        TokenInfo tokenInfo = getTokenInfo();
//        String userId = tokenInfo.getUserId();
        String cabinetStoreListByUserId = getCabinetStoreListByUserId(warehouseKeeperVo,user_id);

//        List<CabinetStore> cabinetStoreList = JSONArray.parseArray(cabinetStoreListByUserId, CabinetStore.class);

        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,cabinetStoreListByUserId);
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
    public ResponseJson getCabinetStoreDetail(String storeId) {
        JSONObject jsonObject = new JSONObject();
        CreateCooperationResVO cabinetStoreCoopMessage = getCabinetStoreCoopMessage(storeId);
        String create_status = cabinetStoreCoopMessage.getCreate_status();
        if((StoreCreateStatusEnum.C.getKey()+"").equals(create_status)){
            List<CabinetInfo> cabinetInfoList = getCabinetInfoByStoreId(storeId);
            jsonObject.put("cabinetInfoList",cabinetInfoList);
        }
        jsonObject.put("cabinetStoreCoopMessage",cabinetStoreCoopMessage);

        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,jsonObject);
    }

    /**
     * 通过userId查询该用户所负责区域所有柜子信息
     * @param warehouseKeeperVo
     * @return
     */
    public String getCabinetStoreListByUserId(WarehouseKeeperVo warehouseKeeperVo,String user_id){
        JSONObject jsonObject  =  new JSONObject();
        String create_status = warehouseKeeperVo.getCreate_status();
        if(!"0".equals(create_status)){
            jsonObject.put("create_status",create_status);
        }else {
            jsonObject.put("create_status","");
        }
        jsonObject.put("user_id",user_id);

        jsonObject.put("pageNum",warehouseKeeperVo.getPageNum());
        jsonObject.put("pageSize",warehouseKeeperVo.getPageSize());
        jsonObject.put("search_key",warehouseKeeperVo.getSearch_key());
        String resultCabinetStore = callDataCenter(CABINET_STORE_GET_BY_USERID, jsonObject);
//        List<CabinetStore> cabinetStoreList = JSONArray.parseArray(resultCabinetStore, CabinetStore.class);
        return resultCabinetStore;
    }

    /**
     * 通过storeId查询该店铺合作信息
     * @param store_id
     * @return
     */
    public CreateCooperationResVO getCabinetStoreCoopMessage(String store_id){
        JSONObject jsonObject  =  new JSONObject();
        jsonObject.put("cabinet_store_id",store_id);
        String resultCabinetStoreCoopMsg = callDataCenter(CABINET_COORPERATION_INFO, jsonObject);

        CreateCooperationResVO createCooperationResVO = JSONObject.toJavaObject(JSONObject.parseObject(resultCabinetStoreCoopMsg), CreateCooperationResVO.class);

        return createCooperationResVO;
    }


    /**
     * 通过storeId查询该店铺柜子信息
     * @param store_id
     * @return
     */
    public List<CabinetInfo> getCabinetInfoByStoreId(String store_id){
        JSONObject jsonObject  =  new JSONObject();
        jsonObject.put("store_id",store_id);
        String resultMsg = callDataCenter(CABINET_INFO_GET_BY_STORE_ID, jsonObject);

        List<CabinetInfo> cabinetInfoList = JSONArray.parseArray(resultMsg, CabinetInfo.class);

        return cabinetInfoList;
    }






}
