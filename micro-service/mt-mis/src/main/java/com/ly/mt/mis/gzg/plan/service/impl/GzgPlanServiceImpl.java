package com.ly.mt.mis.gzg.plan.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.data.cabinet.entity.CabinetCategroy;
import com.ly.mt.core.data.cabinet.entity.CabinetPlan;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.gzg.plan.service.GzgPlanService;
import com.ly.mt.mis.gzg.plan.vo.GzgPlanDatagridVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 配货方案管理
 *
 * @author taoye
 */
@Service
public class GzgPlanServiceImpl extends BaseServiceImpl implements GzgPlanService {
    @Override
    public ResponseJson checkName(JSONObject jsonObject) throws Exception {
        return null;
    }

    @Override
    public ResponseJson insertPlan(JSONObject jsonObject) throws Exception {
        return null;
    }

    @Override
    public ResponseJson updatePlan(JSONObject jsonObject) throws Exception {
        return null;
    }

    @Override
    public ResponseJson loadPlanDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        CabinetPlan cabinetPlan = JSONObject.toJavaObject(jsonObject, CabinetPlan.class);
        Datagrid datagrid = cabinetPlanDao.loadDatagridFromMysql(cabinetPlan, page);
        List<GzgPlanDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<GzgPlanDatagridVo>>() {
        });
        if (vos.size() > 0) {
            List<CabinetPlan> list = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<CabinetPlan>>() {
            });
            Map<Long, Map<String, Long>> map = gzgInfoDao.getCountByCabinetPlansFromMysql(list);
            vos.forEach(
                    vo -> {
                        CabinetCategroy cabinetCategroy = cabinetCategroyDao.getCabinetCategroyByIdFromMysql(vo.getCabinetCategroyId());
                        vo.setCabinetCategroyName(cabinetCategroy.getCabinetName());
                        vo.setGoodsCount(cabinetCategroy.getGoodsCount());
                        String count = String.valueOf(map.get(Long.valueOf(vo.getId())).get("count"));
                        vo.setPlanCount(count == null ? "0" : count);
                    }
            );
            datagrid.setRows(vos);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }
}