package com.ly.mt.mis.basic.func.vo;

import java.util.List;

public class FuncTreegridVo {
    private String id;
    private String parentId;
    private String name;
    private String parentName;
    private String code;
    private String url;
    private String icon;
    private String funcType;
    private String funcTypeName;
    private String funcLevel;
    private String funcLevelName;
    private String sort;
    private String state;
    private List<FuncTreegridVo> children;

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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getFuncType() {
        return funcType;
    }

    public void setFuncType(String funcType) {
        this.funcType = funcType;
    }

    public String getFuncLevel() {
        return funcLevel;
    }

    public void setFuncLevel(String funcLevel) {
        this.funcLevel = funcLevel;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<FuncTreegridVo> getChildren() {
        return children;
    }

    public void setChildren(List<FuncTreegridVo> children) {
        this.children = children;
    }

    public String getFuncTypeName() {
        return funcTypeName;
    }

    public void setFuncTypeName(String funcTypeName) {
        this.funcTypeName = funcTypeName;
    }

    public String getFuncLevelName() {
        return funcLevelName;
    }

    public void setFuncLevelName(String funcLevelName) {
        this.funcLevelName = funcLevelName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}