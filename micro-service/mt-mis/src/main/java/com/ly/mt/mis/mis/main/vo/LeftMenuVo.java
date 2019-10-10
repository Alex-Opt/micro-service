package com.ly.mt.mis.mis.main.vo;

import java.util.List;

public class LeftMenuVo {
    private String id;
    private String parentId;
    private String name;
    private String url;
    private String icon;
    private String funcLevel;
    private List<LeftMenuVo> children;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFuncLevel() {
        return funcLevel;
    }

    public void setFuncLevel(String funcLevel) {
        this.funcLevel = funcLevel;
    }

    public List<LeftMenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<LeftMenuVo> children) {
        this.children = children;
    }
}