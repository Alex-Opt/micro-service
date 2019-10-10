package com.ly.mt.mis.basic.dict.service.impl;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.EnumUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.basic.dict.service.DictService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 字典枚举操作接口
 *
 * @author taoye
 */
@Service
public class DictServiceImpl extends BaseServiceImpl implements DictService {
    @Override
    public ResponseJson loadDictComboboxAdd(String dictName) throws Exception {
        List<Map<String, String>> list = EnumUtil.listDictByDictName(dictName);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
    }

    @Override
    public ResponseJson loadDictComboboxSelect(String dictName) throws Exception {
        List<Map<String, String>> list = EnumUtil.listDictByDictName(dictName);
        Map<String, String> map = new HashMap<>();
        map.put("id", "-1");
        map.put("name", "全部");
        list.add(0, map);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
    }
}