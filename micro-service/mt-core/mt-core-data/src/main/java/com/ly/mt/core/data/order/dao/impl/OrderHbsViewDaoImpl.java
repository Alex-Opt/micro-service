package com.ly.mt.core.data.order.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.order.dao.OrderHbsViewDao;
import com.ly.mt.core.data.order.entity.OrderHbsView;
import com.ly.mt.core.data.order.mapper.OrderHbsViewMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_ORDER_HBS_VIEW_ENTITY_ID;

/**
 * OrderHbsView操作接口
 *
 * @author taoye
 */
@Service
public class OrderHbsViewDaoImpl extends BaseDaoServiceImpl implements OrderHbsViewDao {
    @Resource
    private OrderHbsViewMapper mapper;

    @Override
    public OrderHbsView getOrderHbsViewByIdFromRedis(String id) {
        Assert.notNull(id, "OrderHbsViewDaoImpl.getOrderHbsViewByIdFromRedis id must not be null");
        String json = redisService.get(REDIS_ORDER_HBS_VIEW_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), OrderHbsView.class);
        }
        OrderHbsView view = new OrderHbsView();
        view.setId(id);
        view = mapper.getOrderHbsView(view);
        if (null != view) {
            redisService.setEntity(REDIS_ORDER_HBS_VIEW_ENTITY_ID, id, view);
            return view;
        } else {
            return new OrderHbsView();
        }
    }

    @Override
    public Datagrid loadDatagridFromMysql(OrderHbsView orderHbsView, Page page) {
        Assert.notNull(page, "OrderHbsViewDaoImpl.loadDatagridFromMysql page must not be null");
        PageHelper.startPage(page.getPage(), page.getRows());
        List<OrderHbsView> list = mapper.listOrderHbsView(orderHbsView);
        PageInfo<OrderHbsView> pageInfo = new PageInfo<>(list);
        Datagrid datagrid = new Datagrid();
        datagrid.setTotal(pageInfo.getTotal());
        datagrid.setRows(list);
        return datagrid;
    }

    @Override
    public List<OrderHbsView> listOrderHbsViewFromMysql(OrderHbsView orderHbsView) {
        Assert.notNull(orderHbsView, "OrderHbsViewDaoImpl.listOrderHbsViewFromMysql orderHbsView must not be null");
        return mapper.listOrderHbsView(orderHbsView);
    }
}