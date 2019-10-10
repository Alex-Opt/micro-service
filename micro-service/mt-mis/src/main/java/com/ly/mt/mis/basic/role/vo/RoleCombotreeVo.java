package com.ly.mt.mis.basic.role.vo;

import com.ly.mt.core.base.eaeyui.Combotree;

public class RoleCombotreeVo extends Combotree {
    private String name;
    private String parentId;
    private String roleLevel;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setText(name);
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }
}