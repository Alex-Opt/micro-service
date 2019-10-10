package com.ly.mt.mis.basic.area.vo;

import com.ly.mt.core.base.eaeyui.Tree;

/**
 * 行政区域树
 *
 * @author taoye
 */
public class AreaTreeVo extends Tree {
    /**
     * 行政区域id
     */
    private String mId;
    /**
     * 行政区域名称
     */
    private String name;
    /**
     * 行政区域等级
     */
    private String level;


    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
        setId(mId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setText(name);
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
        setAttributes(level);
    }
}