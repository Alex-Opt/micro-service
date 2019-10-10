package com.ly.mt.core.data.goods.entity;

/**
 * goods_categroy_info
 *
 * @author taoye
 */
public class GoodsCategroyInfo {
    /**
     * 主键id
     */
    private String id;
    /**
     * 父类目Id
     */

    private String parentId;
    /**
     * 类目名称
     */
    private String name;
    /**
     * 类目状态
     *
     * @see com.ly.mt.core.base.dict.CategroyStatus
     */
    private String status;
    /**
     * 类目级别
     *
     * @see com.ly.mt.core.base.dict.CategroyLevel
     */
    private String lev;
    /**
     * 排序号
     */
    private String sortNumber;
    /**
     * 创建时间
     */
    private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}