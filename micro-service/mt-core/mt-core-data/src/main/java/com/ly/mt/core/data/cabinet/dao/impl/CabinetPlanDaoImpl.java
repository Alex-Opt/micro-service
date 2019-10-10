package com.ly.mt.core.data.cabinet.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.cabinet.dao.CabinetPlanDao;
import com.ly.mt.core.data.cabinet.entity.CabinetPlan;
import com.ly.mt.core.data.cabinet.mapper.CabinetPlanMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_CABINET_PLAN_ENTITY_ID;

/**
 * CabinetPlan操作接口
 *
 * @author taoye
 */
@Service
public class CabinetPlanDaoImpl extends BaseDaoServiceImpl implements CabinetPlanDao {
    @Resource
    private CabinetPlanMapper mapper;


    @Override
    public CabinetPlan getCabinetPlanByIdFromRedis(String id) {
        Assert.notNull(id, "CabinetPlanDaoImpl.getCabinetPlanByIdFromRedis id must not be null");
        String json = redisService.get(REDIS_CABINET_PLAN_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), CabinetPlan.class);
        }
        CabinetPlan cabinetPlan = new CabinetPlan();
        cabinetPlan.setId(id);
        return getCabinetPlanFromMysql(cabinetPlan);
    }


    @Override
    public CabinetPlan getCabinetPlanFromMysql(CabinetPlan cabinetPlan) {
        cabinetPlan = mapper.getCabinetPlan(cabinetPlan);
        if (null != cabinetPlan) {
            redisService.setEntity(REDIS_CABINET_PLAN_ENTITY_ID, cabinetPlan.getId(), cabinetPlan);
            return cabinetPlan;
        } else {
            return new CabinetPlan();
        }
    }


    @Override
    public Datagrid loadDatagridFromMysql(CabinetPlan cabinetPlan, Page page) {
        Assert.notNull(page, "CabinetPlanDaoImpl.loadDatagridFromMysql page must not be null");
        PageHelper.startPage(page.getPage(), page.getRows());
        List<CabinetPlan> list = mapper.listCabinetPlan(cabinetPlan);
        PageInfo<CabinetPlan> pageInfo = new PageInfo<>(list);
        Datagrid datagrid = new Datagrid();
        datagrid.setTotal(pageInfo.getTotal());
        datagrid.setRows(list);
        return datagrid;
    }
}