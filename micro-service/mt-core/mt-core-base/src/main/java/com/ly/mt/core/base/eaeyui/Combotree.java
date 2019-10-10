package com.ly.mt.core.base.eaeyui;

import java.util.List;

/**
 * easyui下拉树
 *
 * @author taoye
 */
public class Combotree {
    private String id;
    private String text;
    private List<?> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }
}