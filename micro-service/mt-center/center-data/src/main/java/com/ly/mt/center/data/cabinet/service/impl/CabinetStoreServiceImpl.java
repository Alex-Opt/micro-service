package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.cabinet.entity.CabinetStore;
import com.ly.mt.center.data.cabinet.mapper.CabinetStoreMapper;
import com.ly.mt.center.data.cabinet.requestdto.SquareDataStatisticsRequestDto;
import com.ly.mt.center.data.cabinet.response.*;
import com.ly.mt.center.data.cabinet.service.CabinetStoreService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class CabinetStoreServiceImpl extends BaseServiceImpl implements CabinetStoreService {

    private static final Logger log = LoggerFactory.getLogger(CabinetStoreServiceImpl.class);

    @Resource
    private CabinetStoreMapper cabinetStoreMapper;

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,noRollbackFor = Exception.class)
    public ResponseJson insert(CabinetStore cabinetStore) {
        cabinetStoreMapper.insert(cabinetStore);
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }
    /**
     * 库管通过user_id查询自己所负责区域格子柜的相关信息
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson getCabinetStoreByUserId(JSONObject jsonObject) {
        Map<String ,Object> map = new HashMap<String ,Object>();
        if (StringUtil.isEmpty(jsonObject.getString("user_id"))) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数user_id不能为空");
        }
        map.put("user_id",jsonObject.getString("user_id"));
        if(!StringUtil.isEmpty(jsonObject.getString("create_status"))){
             map.put("create_status",jsonObject.getString("create_status"));
        }
        if(!StringUtil.isEmpty(jsonObject.getString("search_key"))){
            map.put("search_key",jsonObject.getString("search_key"));
        }
//        map.put("pageNum",0);
//        map.put("pageSize",10);
        int pageNum = jsonObject.getInteger("pageNum");
        int pageSize =jsonObject.getInteger("pageSize");
        PageHelper.startPage(pageNum,pageSize);
        List<CabinetStore> cabinetStoreList = cabinetStoreMapper.getCabinetStoreByUserId(map);
        log.info("库管查询自己负责区域店铺信息，查询条件  map = {}",map.toString());
        //返回个客户端封装一些page对象
        PageInfo<CabinetStore> pageInfoUserList=new PageInfo<CabinetStore>(cabinetStoreList);
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,pageInfoUserList);
    }

    @Override
    public ResponseJson update(CabinetStore cabinetStore) {
        log.info("call method update of  cabinetStoreServiceImpl jsonParam={}", JSON.toJSONString(cabinetStore));
        long start = System.currentTimeMillis();
        cabinetStoreMapper.updateByPrimaryKeySelective(cabinetStore);
        long end = System.currentTimeMillis();
        log.info("update cabinetStore cost {} mills",(end - start));
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }

    /**
     * 库管将店铺状态改为已使用
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson updateCreateStatusById(JSONObject jsonObject) {
        log.info("call method updateCreateStatusById of  cabinetStoreServiceImpl jsonParam={}", JSON.toJSONString(jsonObject));
        long start = System.currentTimeMillis();
        String id = jsonObject.getString("id");
        String create_status = jsonObject.getString("create_status");

        if (StringUtil.isEmpty(id) || StringUtil.isEmpty(create_status)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数 id 或 create_status不能为空");
        }
        cabinetStoreMapper.updateCreateStatusById(create_status,id);
        long end = System.currentTimeMillis();
        log.info("updateCreateStatusById cabinetStore cost {} mills",(end - start));
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }


    /**
     * 查询bd旗下的所有店铺
     * @param jsonObject {@link SquareDataStatisticsRequestDto}
     * @return
     */
    @Override
    public ResponseJson findBdShops(JSONObject jsonObject) {
        SquareDataStatisticsRequestDto body = JSONObject.toJavaObject(jsonObject, SquareDataStatisticsRequestDto.class);
        List<Map<String ,Object>>  maps = cabinetStoreMapper.findBdShops(body.getPhoneNo());
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,maps);
    }


    /**
     * 查询 bd数据统计汇总详情信息
     * @param jsonObject {@link SquareDataStatisticsRequestDto}
     * @return
     */
    @Override
    public ResponseJson findBdDataDetail(JSONObject jsonObject) {
        SquareDataStatisticsRequestDto body = JSONObject.toJavaObject(jsonObject, SquareDataStatisticsRequestDto.class);
        int size = cabinetStoreMapper.findBdShops(body.getPhoneNo()).size();
        Map<String,Object> map = cabinetStoreMapper.findBdDataDetail(body.getFullStartTime(),body.getFullEndTime(),body.getPhoneNo());
        map.put("store_num",size);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,map);
    }

    @Override
    public ResponseJson findBdDataStatistics(JSONObject jsonObject) {
        SquareDataStatisticsRequestDto body = JSONObject.toJavaObject(jsonObject, SquareDataStatisticsRequestDto.class);
        PageHelper.startPage(body.getPageNum(),body.getPageSize());
        List<BdDataStaticsticVo> vos =  cabinetStoreMapper.findBdDataStatistics(body.getCabinet_store_id(),body.getPhoneNo(),body.getFullStartTime(),body.getFullEndTime(),body.getStock_num());
        PageInfo<BdDataStaticsticVo> pageInfo = new PageInfo<>(vos);
        PageInfoResponseVo responseVo = new PageInfoResponseVo();
        BeanUtils.copyProperties(pageInfo , responseVo);
        List<BdNewDataStaticsticVo> result = vos.stream().map(n -> {
            return new BdNewDataStaticsticVo(n);
        }).collect(Collectors.toList());
        responseVo.setList(result);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,responseVo);
    }

    @Override
    public ResponseJson findBdStoreOrders(JSONObject jsonObject) {
        SquareDataStatisticsRequestDto body = JSONObject.toJavaObject(jsonObject, SquareDataStatisticsRequestDto.class);
        BdNewDataStaticsticVo newVo = new BdNewDataStaticsticVo();
        if (!StringUtils.isEmpty(body.getCabinet_store_id())){
            BdDataStaticsticVo vo = cabinetStoreMapper.findBdDataStatisticOne(body.getCabinet_store_id(),body.getPhoneNo(),body.getFullStartTime(),body.getFullEndTime());
             newVo = new BdNewDataStaticsticVo(vo);
        }
        PageHelper.startPage(body.getPageNum(),body.getPageSize(),"goi.create_time DESC");
        List<Orders>  orders = cabinetStoreMapper.findBdStoreOrders(body.getCabinet_store_id(),body.getFullStartTime(),body.getFullEndTime());
        PageInfo<Orders> pageInfo = new PageInfo<>(orders);
        PageInfoResponseVo pageInfoResponseVo  = new PageInfoResponseVo();
        BeanUtils.copyProperties(pageInfo,pageInfoResponseVo);
        pageInfoResponseVo.setList(orders);
        BdStoreOrdersVo<Orders> resultVo = new BdStoreOrdersVo<Orders>(pageInfoResponseVo, newVo);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,resultVo);
    }

    /**
     * 根据id查询店铺信息
     * @param jsonObject
     * whl
     * @return
     */
   public ResponseJson getCabinetStoreById(JSONObject jsonObject){
        String id = String.valueOf(jsonObject.get("id"));
        CabinetStore cabinetStore = cabinetStoreMapper.getCabinetStoreById(id);
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,cabinetStore);
    }
}
