package com.ly.mt.core.data.order.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.order.dao.OrderBackstageViewDao;
import com.ly.mt.core.data.order.entity.OrderBackstageView;
import com.ly.mt.core.data.order.mapper.OrderBackstageViewMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * OrderBackstageView操作接口
 *
 * @author taoye
 */
@Service
public class OrderBackstageViewDaoImpl extends BaseDaoServiceImpl implements OrderBackstageViewDao {
    @Resource
    private OrderBackstageViewMapper mapper;

    @Override
    public Datagrid loadDatagridFromMysql(OrderBackstageView orderInfoView, Page page) {
        Assert.notNull(page, "OrderBackstageViewDaoImpl.loadDatagridFromMysql page must not be null");
        PageHelper.startPage(page.getPage(), page.getRows());
        List<OrderBackstageView> list = mapper.listOrderBackstageView(orderInfoView);
        PageInfo<OrderBackstageView> pageInfo = new PageInfo<>(list);
        Datagrid datagrid = new Datagrid();
        datagrid.setTotal(pageInfo.getTotal());
        datagrid.setRows(list);
        return datagrid;
    }
}