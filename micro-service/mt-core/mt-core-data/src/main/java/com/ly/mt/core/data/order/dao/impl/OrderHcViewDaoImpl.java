package com.ly.mt.core.data.order.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.order.dao.OrderHcViewDao;
import com.ly.mt.core.data.order.entity.OrderHcView;
import com.ly.mt.core.data.order.mapper.OrderHcViewMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_ORDER_HC_VIEW_ENTITY_ID;

/**
 * OrderHcView操作接口
 *
 * @author taoye
 */
@Service
public class OrderHcViewDaoImpl extends BaseDaoServiceImpl implements OrderHcViewDao {
    @Resource
    private OrderHcViewMapper mapper;

    @Override
    public OrderHcView getOrderHcViewByIdFromRedis(String id) {
        Assert.notNull(id, "OrderHcViewDaoImpl.getOrderHcViewByIdFromRedis id must not be null");
        String json = redisService.get(REDIS_ORDER_HC_VIEW_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), OrderHcView.class);
        }
        OrderHcView view = new OrderHcView();
        view.setId(id);
        view = mapper.getOrderHcView(view);
        if (null != view) {
            redisService.setEntity(REDIS_ORDER_HC_VIEW_ENTITY_ID, id, view);
            return view;
        } else {
            return new OrderHcView();
        }
    }

    @Override
    public Datagrid loadDatagridFromMysql(OrderHcView orderHcView, Page page) {
        Assert.notNull(page, "OrderHcViewDaoImpl.loadDatagridFromMysql page must not be null");
        PageHelper.startPage(page.getPage(), page.getRows());
        List<OrderHcView> list = mapper.listOrderHcView(orderHcView);
        PageInfo<OrderHcView> pageInfo = new PageInfo<>(list);
        Datagrid datagrid = new Datagrid();
        datagrid.setTotal(pageInfo.getTotal());
        datagrid.setRows(list);
        return datagrid;
    }

    @Override
    public List<OrderHcView> listOrderHcViewFromMysql(OrderHcView orderHcView) {
        Assert.notNull(orderHcView, "OrderHcViewDaoImpl.listOrderHcViewFromMysql orderHcView must not be null");
        return mapper.listOrderHcView(orderHcView);
    }
}