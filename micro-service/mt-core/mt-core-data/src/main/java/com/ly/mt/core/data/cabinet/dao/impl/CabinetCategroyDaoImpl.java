package com.ly.mt.core.data.cabinet.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.cabinet.dao.CabinetCategroyDao;
import com.ly.mt.core.data.cabinet.entity.CabinetCategroy;
import com.ly.mt.core.data.cabinet.mapper.CabinetCategroyMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_CABINET_CATEGROY_ENTITY_ID;

/**
 * CabinetCategroy操作接口
 *
 * @author taoye
 */
@Service
public class CabinetCategroyDaoImpl extends BaseDaoServiceImpl implements CabinetCategroyDao {
    @Resource
    private CabinetCategroyMapper mapper;

    @Override
    public void insertCabinetCategroy(CabinetCategroy cabinetCategroy) {
        Assert.notNull(cabinetCategroy, "CabinetCategroyDaoImpl.cabinetCategroy cabinetCategroy must not be null");
        Assert.notNull(cabinetCategroy.getId(), "CabinetCategroyDaoImpl.cabinetCategroy.id must not be null");
        redisService.setEntity(REDIS_CABINET_CATEGROY_ENTITY_ID, cabinetCategroy.getId());
        mapper.insertCabinetCategroy(cabinetCategroy);
    }


    @Override
    public int updateCabinetCategroy(CabinetCategroy cabinetCategroy) {
        Assert.notNull(cabinetCategroy, "CabinetCategroyDaoImpl.cabinetCategroy must not be null");
        Assert.notNull(cabinetCategroy.getId(), "CabinetCategroyDaoImpl.cabinetCategroy.id must not be null");
        redisService.del(REDIS_CABINET_CATEGROY_ENTITY_ID, cabinetCategroy.getId());
        return mapper.updateCabinetCategroy(cabinetCategroy);
    }

    @Override
    public CabinetCategroy getCabinetCategroyByIdFromMysql(String id) {
        Assert.notNull(id, "CabinetCategroyDaoImpl.getCabinetCategroyByIdFromMysql id must not be null");
        String json = redisService.get(REDIS_CABINET_CATEGROY_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), CabinetCategroy.class);
        }
        CabinetCategroy cabinetCategroy = new CabinetCategroy();
        cabinetCategroy.setId(id);
        cabinetCategroy = getCabinetCategroyFromMysql(cabinetCategroy);
        if (null != cabinetCategroy) {
            redisService.setEntity(REDIS_CABINET_CATEGROY_ENTITY_ID, id, cabinetCategroy);
            return cabinetCategroy;
        } else {
            return new CabinetCategroy();
        }
    }


    @Override
    public CabinetCategroy getCabinetCategroyFromMysql(CabinetCategroy cabinetCategroy) {
        return mapper.getCabinetCategroy(cabinetCategroy);
    }

    @Override
    public List<CabinetCategroy> listCabinetCategroyFromMysql(CabinetCategroy cabinetCategroy) {
        return mapper.listCabinetCategroy(cabinetCategroy);
    }


    @Override
    public Datagrid loadDatagridFromMysql(CabinetCategroy cabinetCategroy, Page page) {
        Assert.notNull(page, "CabinetCategroyDaoImpl.loadDatagridFromMysql page must not be null");
        PageHelper.startPage(page.getPage(), page.getRows());
        List<CabinetCategroy> list = mapper.listCabinetCategroy(cabinetCategroy);
        PageInfo<CabinetCategroy> pageInfo = new PageInfo<>(list);
        Datagrid datagrid = new Datagrid();
        datagrid.setTotal(pageInfo.getTotal());
        datagrid.setRows(list);
        return datagrid;
    }
}