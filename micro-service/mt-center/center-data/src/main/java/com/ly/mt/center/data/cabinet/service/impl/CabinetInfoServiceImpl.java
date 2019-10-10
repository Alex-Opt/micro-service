package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.center.data.cabinet.dto.CabinetInfoDefaultsDto;
import com.ly.mt.center.data.cabinet.entity.CabinetCreateRecord;
import com.ly.mt.center.data.cabinet.entity.CabinetInfo;
import com.ly.mt.center.data.cabinet.mapper.CabinetCreateRecordMapper;
import com.ly.mt.center.data.cabinet.mapper.CabinetInfoMapper;
import com.ly.mt.center.data.cabinet.requestdto.SquareCabinatRequestDto;
import com.ly.mt.center.data.cabinet.response.PageInfoResponseVo;
import com.ly.mt.center.data.cabinet.service.CabinetInfoService;
import com.ly.mt.center.data.gzg.mapper.GzgGoodsPlanMapper;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class CabinetInfoServiceImpl implements CabinetInfoService {
    private static final Logger log = LoggerFactory.getLogger(CabinetInfoServiceImpl.class);

    @Resource
    CabinetInfoMapper cabinetInfoMapper;
    @Resource
    GzgGoodsPlanMapper gzgGoodsPlanMapper;
    @Resource
    CabinetCreateRecordMapper cabinetCreateRecordMapper;

    @Override
    public ResponseJson cabinatsByUser(JSONObject jsonObject) {
        try {
            SquareCabinatRequestDto param = JSONObject.toJavaObject(jsonObject, SquareCabinatRequestDto.class);
            PageHelper.startPage(param.getPageNum(), param.getPageSize(), "status ASC,last_time DESC ,id ASC");
            List<CabinetInfoDefaultsDto> defaults = cabinetInfoMapper.findByInfoDefaults(param);
            PageInfo<CabinetInfoDefaultsDto> pageInfo = new PageInfo<>(defaults);
            PageInfoResponseVo vo = new PageInfoResponseVo();
            BeanUtils.copyProperties(pageInfo, vo);
            vo.setList(defaults);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, vo);
        } catch (BeansException e) {
            log.error("CabinetInfoServiceImpl.cabinatsByUser error,e={}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson insertCabinetInfo(JSONObject jsonObject) {
        try {
            CabinetInfo cabinetInfo = JSONObject.toJavaObject(jsonObject, CabinetInfo.class);
            if (StringUtil.isEmpty(cabinetInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            cabinetInfoMapper.insert(cabinetInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            log.error("CabinetInfoServiceImpl.insertCabinetInfo 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson deleteCabinetInfo(JSONObject jsonObject) {
        try {
            CabinetInfo cabinetInfo = JSONObject.toJavaObject(jsonObject, CabinetInfo.class);
            if (StringUtil.isEmpty(cabinetInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            cabinetInfoMapper.deleteByPrimaryKey(cabinetInfo.getId());
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            log.error("CabinetInfoServiceImpl.deleteGzgCity出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson updateCabinetInfo(JSONObject jsonObject) {
        try {
            CabinetInfo cabinetInfo = JSONObject.toJavaObject(jsonObject, CabinetInfo.class);
            if (StringUtil.isEmpty(cabinetInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            cabinetInfoMapper.updateByPrimaryKey(cabinetInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            log.error("CabinetInfoServiceImpl.updateCabinetInfo 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getCabinetInfo(JSONObject jsonObject) {
        try {
            CabinetInfo cabinetInfo = JSONObject.toJavaObject(jsonObject, CabinetInfo.class);
            if (StringUtil.isEmpty(cabinetInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            cabinetInfo = cabinetInfoMapper.selectByPrimaryKey(cabinetInfo.getId());
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, cabinetInfo);
        } catch (Exception e) {
            log.error("CabinetInfoServiceImpl.getCabinetInfo 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getCabinetInfoByImei(JSONObject jsonObject) {
        try {
            CabinetInfo cabinetInfo = JSONObject.toJavaObject(jsonObject, CabinetInfo.class);

            cabinetInfo = cabinetInfoMapper.getCabinetInfoByImei(cabinetInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, cabinetInfo);
        } catch (Exception e) {
            log.error("CabinetInfoServiceImpl.getCabinetInfo 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getCabinetInfoByStoreId(JSONObject jsonObject) {
        try {
            String store_id = jsonObject.getString("store_id");

            if (StringUtil.isEmpty(store_id)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            log.info("CabinetInfoServiceImpl.getCabinetInfoByStoreId方法，传入参数 store_id = {}",store_id);
            List<CabinetInfo> cabinetInfoList = cabinetInfoMapper.getCabinetInfoByStoreId(store_id);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, cabinetInfoList);
        } catch (Exception e) {
            log.error("CabinetInfoServiceImpl.getCabinetInfoByStoreId 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @Override
    public ResponseJson getCabinetProgrammeList(JSONObject jsonObject) {
        try {
            List cabinetProgrammeList = cabinetInfoMapper.getCabinetProgrammeList();
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, cabinetProgrammeList);
        } catch (Exception e) {
            log.error("CabinetInfoServiceImpl.getCabinetProgrammeList 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }




    /**
     * 合作信息详情
     *
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson cabinatsCoopMessage(JSONObject jsonObject) {
        /** 入参 +*/
        try {
            String imei = jsonObject.getString("imei");
            String phone = jsonObject.getString("phoneNo");
            Long userId = jsonObject.getLong("userId");
            Map<String, Object> result = cabinetInfoMapper.findCoopDefault(imei,userId,phone);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,result);
        }catch (Exception e){
            log.error("CabinetInfoServiceImpl.findCabinetCoop查询异常e={}",e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 格子柜下架
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional()
    public ResponseJson downSquareCabinet(JSONObject jsonObject) {
        try {
            String imei = jsonObject.getString("imei");
            String phone = jsonObject.getString("phoneNo");
            Long userId = jsonObject.getLong("userId");
            //更新cabinet_info、gzg_goods_paln表状态
            cabinetInfoMapper.downSquareCabinet(imei,phone,userId);
            gzgGoodsPlanMapper.downCabinetByImei(imei);
            //插入日志
            CabinetInfo cabinetInfo = cabinetInfoMapper.findByImei(imei);

            CabinetCreateRecord record = new CabinetCreateRecord();
            record.setId(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_CABINET_CREATE_RECORD));
            record.setCabinet_info_id(cabinetInfo.getId());
            record.setCreateor_phone(cabinetInfo.getCreator_phone());
            record.setStatus("1");
            record.setGmt_create(DateUtil.getNowTimeStr());
            record.setGmt_modify(DateUtil.getNowTimeStr());
            cabinetCreateRecordMapper.insertSelective(record);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            log.error("CabinetInfoServiceImpl.downSquareCabinet格子柜下架失败");
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * bd展柜信息
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson showCabinatsByUser(JSONObject jsonObject) {
        try {
            SquareCabinatRequestDto param = JSONObject.toJavaObject(jsonObject, SquareCabinatRequestDto.class);
            PageHelper.startPage(param.getPageNum(), param.getPageSize(), "status ASC,last_time DESC ,id ASC");
            List<CabinetInfoDefaultsDto> showcaseCabinetsDefaults = cabinetInfoMapper.findShowcaseCabinetsDefaults(param);
            PageInfo<CabinetInfoDefaultsDto> pageInfo = new PageInfo<>(showcaseCabinetsDefaults);
            PageInfoResponseVo vo = new PageInfoResponseVo();
            BeanUtils.copyProperties(pageInfo,vo);
            vo.setList(showcaseCabinetsDefaults);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,vo);
        } catch (BeansException e) {
            log.error("CabinetInfoServiceImpl.showCabinatsByUser,查询格子柜列表一场,e={}",e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 展柜合作信息
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson showcaseCabinatsCoopMsg(JSONObject jsonObject) {
        try {
            String imei = jsonObject.getString("imei");
            String phone = jsonObject.getString("phoneNo");
            Long userId = jsonObject.getLong("userId");
            Map<String, Object> result = cabinetInfoMapper.findShowcaseCoopDefault(imei,userId,phone);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,result);
        }catch (Exception e){
            log.error("CabinetInfoServiceImpl.findCabinetCoop查询异常e={}",e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 展柜下架
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson downShowcaseCabinet(JSONObject jsonObject) {
       return downSquareCabinet(jsonObject);
    }

    /**
     * 展柜上架
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson upShowcaseCabinet(JSONObject jsonObject) {
        return upSquareCabinet(jsonObject);
    }


    /**
     * 格子柜上架
     * @param jsonObject
     * @return
     */
    private ResponseJson upSquareCabinet(JSONObject jsonObject){
        try {
            String imei = jsonObject.getString("imei");
            String phone = jsonObject.getString("phoneNo");
            Long userId = jsonObject.getLong("userId");
            //更新cabinet_info、gzg_goods_paln表状态
            cabinetInfoMapper.upSquareCabinet(imei,phone,userId);
            gzgGoodsPlanMapper.upCabinetByImei(imei);
            //插入日志
            CabinetInfo cabinetInfo = cabinetInfoMapper.findByImei(imei);

            CabinetCreateRecord record = new CabinetCreateRecord();
            record.setId(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_CABINET_CREATE_RECORD));
            record.setCabinet_info_id(cabinetInfo.getId());
            record.setCreateor_phone(cabinetInfo.getCreator_phone());
            record.setStatus("0");
            record.setGmt_create(DateUtil.getNowTimeStr());
            record.setGmt_modify(DateUtil.getNowTimeStr());
            cabinetCreateRecordMapper.insertSelective(record);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            log.error("CabinetInfoServiceImpl.downSquareCabinet格子柜下架失败");
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }

    }
}
