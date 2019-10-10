package com.ly.mt.core.data.order.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.order.dao.OrderHbpViewDao;
import com.ly.mt.core.data.order.entity.OrderHbpView;
import com.ly.mt.core.data.order.mapper.OrderHbpViewMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.REDIS_ORDER_HBP_VIEW_ENTITY_ID;

/**
 * OrderHbpView操作接口
 *
 * @author taoye
 */
@Service
public class OrderHbpViewDaoImpl extends BaseDaoServiceImpl implements OrderHbpViewDao {
    @Resource
    private OrderHbpViewMapper mapper;

    @Override
    public OrderHbpView getOrderHbpViewByIdFromRedis(String id) {
        Assert.notNull(id, "OrderHbpViewDaoImpl.getOrderHbpViewByIdFromRedis id must not be null");
        String json = redisService.get(REDIS_ORDER_HBP_VIEW_ENTITY_ID, id);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), OrderHbpView.class);
        }
        OrderHbpView view = new OrderHbpView();
        view.setId(id);
        view = mapper.getOrderHbpView(view);
        if (null != view) {
            redisService.setEntity(REDIS_ORDER_HBP_VIEW_ENTITY_ID, id, view);
            return view;
        } else {
            return new OrderHbpView();
        }
    }

    @Override
    public Datagrid loadDatagridFromMysql(OrderHbpView orderHbpView, Page page) {
        Assert.notNull(page, "OrderHbpViewDaoImpl.loadDatagridFromMysql page must not be null");
        PageHelper.startPage(page.getPage(), page.getRows());
        List<OrderHbpView> list = mapper.listOrderHbpView(orderHbpView);
        PageInfo<OrderHbpView> pageInfo = new PageInfo<>(list);
        Datagrid datagrid = new Datagrid();
        datagrid.setTotal(pageInfo.getTotal());
        datagrid.setRows(list);
        return datagrid;
    }

    @Override
    public List<OrderHbpView> listOrderHbpViewFromMysql(OrderHbpView orderHbpView) {
        Assert.notNull(orderHbpView, "OrderHbpViewDaoImpl.listOrderHbpViewFromMysql orderHbpView must not be null");
        return mapper.listOrderHbpView(orderHbpView);
    }
}