package com.ly.mt.mis.gzg.categroy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.EnumUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.data.cabinet.entity.CabinetCategroy;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.gzg.categroy.service.GzgCategroyService;
import com.ly.mt.mis.gzg.categroy.vo.GzgCategroyDatagridVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_CABINET_CATEGROY;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 货柜类型管理
 *
 * @author taoye
 */
@Service
public class GzgCategroyServiceImpl extends BaseServiceImpl implements GzgCategroyService {
    @Override
    public ResponseJson checkName(JSONObject jsonObject) throws Exception {
        CabinetCategroy cabinetCategroy = JSONObject.toJavaObject(jsonObject, CabinetCategroy.class);
        cabinetCategroy = cabinetCategroyDao.getCabinetCategroyFromMysql(cabinetCategroy);
        if (null != cabinetCategroy) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "货柜名称已存在");
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson insertCategroy(JSONObject jsonObject) throws Exception {
        CabinetCategroy cabinetCategroy = JSONObject.toJavaObject(jsonObject, CabinetCategroy.class);
        cabinetCategroy.setId(SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_CABINET_CATEGROY));
        String loginName = getLoginUserName();
        cabinetCategroy.setCreateName(loginName);
        cabinetCategroy.setUpdateName(loginName);
        String time = DateUtil.getNowTimeStr();
        cabinetCategroy.setCreateTime(time);
        cabinetCategroy.setUpdateTime(time);
        cabinetCategroyDao.insertCabinetCategroy(cabinetCategroy);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson updateCategroy(JSONObject jsonObject) throws Exception {
        CabinetCategroy cabinetCategroy = JSONObject.toJavaObject(jsonObject, CabinetCategroy.class);
        cabinetCategroy.setUpdateName(getLoginUserName());
        cabinetCategroy.setUpdateTime(DateUtil.getNowTimeStr());
        cabinetCategroyDao.updateCabinetCategroy(cabinetCategroy);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson loadCategroyDatagrid(JSONObject jsonObject) throws Exception {
        CabinetCategroy cabinetCategroy = JSONObject.toJavaObject(jsonObject, CabinetCategroy.class);
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        Datagrid datagrid = cabinetCategroyDao.loadDatagridFromMysql(cabinetCategroy, page);
        List<GzgCategroyDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<GzgCategroyDatagridVo>>() {
        });
        vos.forEach(
                vo -> vo.setCabinetTypeName(EnumUtil.getNameById("CabinetType", vo.getCabinetType()))
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }


    @Override
    public ResponseJson loadCategroyComboboxSelect(JSONObject jsonObject) throws Exception {
        String q = jsonObject.getString("q");
        CabinetCategroy cabinetCategroy = new CabinetCategroy();
        cabinetCategroy.setCabinetName(q);
        List<CabinetCategroy> list = cabinetCategroyDao.listCabinetCategroyFromMysql(cabinetCategroy);
        List<Map<String, String>> result = new ArrayList<>();
        if (null != list && list.size() > 0) {
            list.forEach(
                    categroy -> {
                        Map<String, String> map = new HashMap<>(2);
                        map.put("id", categroy.getId());
                        map.put("name", categroy.getCabinetName());
                        result.add(map);
                    }
            );
            Map<String, String> map = new HashMap<>(2);
            map.put("id", "-1");
            map.put("name", "全部");
            result.add(0, map);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, result);
    }
}
