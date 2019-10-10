package com.ly.mt.core.data.goods.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.goods.dao.GoodsInfoViewDao;
import com.ly.mt.core.data.goods.entity.GoodsInfoView;
import com.ly.mt.core.data.goods.mapper.GoodsInfoViewMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * GoodsInfoView操作接口
 *
 * @author taoye
 */
@Service
public class GoodsInfoViewDaoImpl extends BaseDaoServiceImpl implements GoodsInfoViewDao {
    @Resource
    private GoodsInfoViewMapper mapper;

    @Override
    public Datagrid loadDatagridFromMysql(GoodsInfoView goodsInfoView, Page page) {
        Assert.notNull(page, "GoodsInfoViewDaoImpl.loadDatagridFromMysql page must not be null");
        PageHelper.startPage(page.getPage(), page.getRows());
        List<GoodsInfoView> list = mapper.listGoodsInfoView(goodsInfoView);
        PageInfo<GoodsInfoView> pageInfo = new PageInfo<>(list);
        Datagrid datagrid = new Datagrid();
        datagrid.setTotal(pageInfo.getTotal());
        datagrid.setRows(list);
        return datagrid;
    }
}