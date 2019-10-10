package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.gzg.entity.GzgPlan;
import com.ly.mt.center.data.gzg.entity.GzgProgramme;
import com.ly.mt.center.data.gzg.mapper.GzgPlanMapper;
import com.ly.mt.center.data.gzg.mapper.GzgProgrammeMapper;
import com.ly.mt.center.data.gzg.service.GzgPlanService;
import com.ly.mt.center.data.gzg.service.GzgProgrammeService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class GzgProgrammeServiceImpl extends BaseServiceImpl implements GzgProgrammeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgProgrammeServiceImpl.class);
    @Resource
    GzgProgrammeMapper mapper;

    /**
     * @Description 根据条件查询 GzgProgramme
     * @Author gongjy
     *
     */
    @Override
    public ResponseJson getGzgProgrammeListByName(JSONObject jsonObject) {
        try {
            GzgProgramme gzgProgramme = JSONObject.toJavaObject(jsonObject, GzgProgramme.class);
            List<GzgProgramme> gzgProgrammeListByName = mapper.getGzgProgrammeListByName(gzgProgramme);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgProgrammeListByName);
        } catch (Exception e) {
            LOGGER.error("GzgProgrammeServiceImpl.getGzgProgrammeListByName 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }



}