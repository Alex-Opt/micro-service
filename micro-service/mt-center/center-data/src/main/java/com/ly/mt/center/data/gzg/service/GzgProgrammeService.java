package com.ly.mt.center.data.gzg.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgProgrammeService {


    /**
     * @Description 根据条件查询GzgProgramme
     * @Author gongjy
     *
     */
    ResponseJson getGzgProgrammeListByName(JSONObject jsonObject);


}