package com.ly.mt.core.data.user.dao;

import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.user.entity.UserHbView;

/**
 * UserHbView操作接口
 *
 * @author taoye
 */
public interface UserHbViewDao {
    /**
     * 从mysql查询easyui datagrid
     *
     * @param userHbView 查询条件
     * @param page       分页条件
     * @return Datagrid
     * @author taoye
     */
    Datagrid loadDatagridFromMysql(UserHbView userHbView, Page page);
}