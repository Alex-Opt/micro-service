package com.ly.mt.mall.h5.goods.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.goods.service.RotationInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.ROTATION_INFO_LIST;

/**
 * 商品spu业务层
 */
@Service
public class RotationInfoServiceImpl extends BaseServiceImpl implements RotationInfoService {

    private final static Logger Logger = LoggerFactory.getLogger(RotationInfoServiceImpl.class);

    @Override
    public ResponseJson queryRotationInfoList() throws Exception {
        String result = callDataCenter(ROTATION_INFO_LIST, new JSONObject());
        JSONArray array = JSONObject.parseArray(result);
        List list = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            JSONObject node = array.getJSONObject(i);
            Map map = new HashMap();
            map.put("id", node.getString("id"));
            map.put("pictureUrl", node.getString("picture_url"));
            map.put("remark", node.getString("remark"));
            list.add(map);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
    }

    @Override
    public ResponseJson rotationInfoSave(String json) throws Exception {
        return null;
    }
}
