package com.ly.mt.core.data.user.dao;

import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.user.entity.UserHcView;

/**
 * UserHcView操作接口
 *
 * @author taoye
 */
public interface UserHcViewDao {
    /**
     * 从mysql查询easyui datagrid
     *
     * @param userHcView 查询条件
     * @param page       分页条件
     * @return Datagrid
     * @author taoye
     */
    Datagrid loadDatagridFromMysql(UserHcView userHcView, Page page);
}