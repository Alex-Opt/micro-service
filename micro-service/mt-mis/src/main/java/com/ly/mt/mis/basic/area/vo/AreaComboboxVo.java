package com.ly.mt.mis.basic.area.vo;

import com.ly.mt.core.base.eaeyui.Combobox;

/**
 * 行政区域下拉框
 *
 * @author taoye
 */
public class AreaComboboxVo extends Combobox {
    private String mId;
    private String mName;


    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
        setId(mId);
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
        setName(mName);
    }
}