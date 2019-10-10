package com.ly.mt.core.data.user.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.user.dao.UserHcViewDao;
import com.ly.mt.core.data.user.entity.UserHcView;
import com.ly.mt.core.data.user.mapper.UserHcViewMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserHcView操作接口
 *
 * @author taoye
 */
@Service
public class UserHcViewDaoImpl extends BaseDaoServiceImpl implements UserHcViewDao {
    @Resource
    private UserHcViewMapper mapper;

    @Override
    public Datagrid loadDatagridFromMysql(UserHcView userHcView, Page page) {
        Assert.notNull(page, "UserHcViewDaoImpl.loadDatagridFromMysql page must not be null");
        PageHelper.startPage(page.getPage(), page.getRows());
        List<UserHcView> list = mapper.listUserHcView(userHcView);
        PageInfo<UserHcView> pageInfo = new PageInfo<>(list);
        Datagrid datagrid = new Datagrid();
        datagrid.setTotal(pageInfo.getTotal());
        datagrid.setRows(list);
        return datagrid;
    }
}