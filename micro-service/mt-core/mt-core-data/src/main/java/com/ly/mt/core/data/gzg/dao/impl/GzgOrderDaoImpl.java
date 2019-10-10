package com.ly.mt.core.data.gzg.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.gzg.dao.GzgOrderDao;
import com.ly.mt.core.data.gzg.entity.GzgOrder;
import com.ly.mt.core.data.gzg.mapper.GzgOrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_GZG_ORDER_ENTITY_ID;

/**
 * GzgOrder操作接口
 *
 * @author taoye
 */
@Service
public class GzgOrderDaoImpl extends BaseDaoServiceImpl implements GzgOrderDao {
    @Resource
    private GzgOrderMapper mapper;

    @Override
    public GzgOrder getGzgOrderByIdFromRedis(String id) {
        Assert.notNull(id, "GzgOrderDaoImpl.getGzgOrderByIdFromRedis id must not be null");
        String gzgOrderJson = redisService.get(REDIS_GZG_ORDER_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(gzgOrderJson)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(gzgOrderJson), GzgOrder.class);
        }
        GzgOrder gzgOrder = new GzgOrder();
        gzgOrder.setId(id);
        gzgOrder = mapper.getGzgOrder(gzgOrder);
        if (null != gzgOrder) {
            redisService.setEntity(REDIS_GZG_ORDER_ENTITY_ID, id, gzgOrder);
            return gzgOrder;
        } else {
            return new GzgOrder();
        }
    }

    @Override
    public List<GzgOrder> listGzgOrderFromMysql(GzgOrder gzgOrder) {
        Assert.notNull(gzgOrder, "GzgOrderDaoImpl.listGzgOrderFromMysql gzgOrder must not be null");
        return mapper.listGzgOrder(gzgOrder);
    }

    @Override
    public Datagrid loadDatagridFromMysql(GzgOrder gzgOrder, Page page) {
        Assert.notNull(page, "GzgOrderDaoImpl.loadDatagridFromMysql page must not be null");
        PageHelper.startPage(page.getPage(), page.getRows());
        List<GzgOrder> list = mapper.listGzgOrder(gzgOrder);
        PageInfo<GzgOrder> pageInfo = new PageInfo<>(list);
        Datagrid datagrid = new Datagrid();
        datagrid.setTotal(pageInfo.getTotal());
        datagrid.setRows(list);
        return datagrid;
    }
}