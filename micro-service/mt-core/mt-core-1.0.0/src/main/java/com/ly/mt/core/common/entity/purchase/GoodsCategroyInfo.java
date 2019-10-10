package com.ly.mt.core.common.entity.purchase;

import java.io.Serializable;

/**
 * 类目
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-17 23:48:48
 */
public class GoodsCategroyInfo implements Serializable {

    private String id;

    private String name;

    private String parentId;

    private String status;

    private String sortNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(String sortNumber) {
        this.sortNumber = sortNumber;
    }

    @Override
    public String toString() {
        return "GoodsCategroyInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", status='" + status + '\'' +
                ", sortNumber='" + sortNumber + '\'' +
                '}';
    }
}
