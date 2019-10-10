package com.ly.mt.core.base.entity.goods;

import com.ly.mt.core.base.entity.base.BaseEntity;

/** @deprecated */
public class GoodsCategroyInfo extends BaseEntity {

    private String parentId;//父类目Id
    private String status;// 类目状态 1：有效， 2：:删除
    private String lev;//类目级别,1:一级类目;2:二级类目;3:三级类目,目前只支持三级类目
    private String sortNumber;//排序号,排序号越小越靠前


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLev() {
        return lev;
    }

    public void setLev(String lev) {
        this.lev = lev;
    }

    public String getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(String sortNumber) {
        this.sortNumber = sortNumber;
    }
}