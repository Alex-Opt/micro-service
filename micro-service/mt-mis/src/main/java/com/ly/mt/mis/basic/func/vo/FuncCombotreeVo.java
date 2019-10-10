package com.ly.mt.mis.basic.func.vo;

import com.ly.mt.core.base.eaeyui.Combotree;

/**
 * 功能菜单下拉树
 *
 * @author taoye
 */
public class FuncCombotreeVo extends Combotree {
    /**
     * 功能菜单名字
     */
    private String name;
    /**
     * 功能菜单父id
     */
    private String parentId;
    /**
     * 功能菜单等级
     */
    private String funcLevel;


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

    public String getFuncLevel() {
        return funcLevel;
    }

    public void setFuncLevel(String funcLevel) {
        this.funcLevel = funcLevel;
    }
}