package com.ly.mt.core.data.user.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.user.dao.UserHbViewDao;
import com.ly.mt.core.data.user.entity.UserHbView;
import com.ly.mt.core.data.user.mapper.UserHbViewMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserHbView操作接口
 *
 * @author taoye
 */
@Service
public class UserHbViewDaoImpl extends BaseDaoServiceImpl implements UserHbViewDao {
    @Resource
    private UserHbViewMapper mapper;

    @Override
    public Datagrid loadDatagridFromMysql(UserHbView userHbView, Page page) {
        Assert.notNull(page, "UserHbViewDaoImpl.loadDatagridFromMysql page must not be null");
        PageHelper.startPage(page.getPage(), page.getRows());
        List<UserHbView> list = mapper.listUserHbView(userHbView);
        PageInfo<UserHbView> pageInfo = new PageInfo<>(list);
        Datagrid datagrid = new Datagrid();
        datagrid.setTotal(pageInfo.getTotal());
        datagrid.setRows(list);
        return datagrid;
    }
}