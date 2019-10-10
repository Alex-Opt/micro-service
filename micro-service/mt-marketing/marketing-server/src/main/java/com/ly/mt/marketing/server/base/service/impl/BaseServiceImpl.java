package com.ly.mt.marketing.server.base.service.impl;

import com.ly.mt.core.server.service.impl.CoreServiceImpl;
import com.ly.mt.marketing.server.base.service.BaseService;

/**
 * @创建人 zhuyh
 * @创建时间 2019/6/14
 * @描述
 */
public class BaseServiceImpl  extends CoreServiceImpl implements BaseService {

    /**
     * 总数
     */
    protected static final String TOTAL = "total";

    /**
     * 返回列表
     */
    protected static final String ROWS = "rows";
    @Override
    public Long getUserId() {
        // 目前没有办法模拟登录用户，获取不到用户的id，临时用自己的用户id //
//        return 580842177651937280L;
        return Long.valueOf(getLoginUserId());
    }
}
