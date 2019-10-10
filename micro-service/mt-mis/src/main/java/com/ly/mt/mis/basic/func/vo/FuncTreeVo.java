package com.ly.mt.mis.basic.func.vo;

import com.ly.mt.core.base.eaeyui.Tree;

/**
 * 功能菜单树
 *
 * @author taoye
 */
public class FuncTreeVo extends Tree {
    /**
     * 功能名称
     */
    private String name;
    /**
     * 功能等级
     */
    private String funcLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setText(name);
    }

    public String getFuncLevel() {
        return funcLevel;
    }

    public void setFuncLevel(String funcLevel) {
        this.funcLevel = funcLevel;
        setAttributes(funcLevel);
    }
}