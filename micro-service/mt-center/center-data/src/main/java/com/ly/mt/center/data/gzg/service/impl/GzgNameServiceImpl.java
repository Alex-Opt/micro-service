package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.entity.GzgName;
import com.ly.mt.center.data.gzg.mapper.GzgNameMapper;
import com.ly.mt.center.data.gzg.service.GzgNameService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GzgNameServiceImpl  implements GzgNameService {

    @Resource
    GzgNameMapper mapper;

    @Override
    public ResponseJson insertGzgName(JSONObject  gzgName) {
        GzgName nam = JSONObject.toJavaObject(gzgName,GzgName.class);
        try{
            mapper.insert(nam);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
        }catch (Exception e){
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }

    }

    @Override
    public ResponseJson getGzgNameBatch(JSONObject jsonObject) {
        try{
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,
                    mapper.getGzgNameBatch(Integer.parseInt(jsonObject.getString("start")),Integer.parseInt(jsonObject.getString("size"))));
        }catch (Exception e){
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }
}
