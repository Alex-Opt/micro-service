package com.ly.mt.core.data.basic.entity;

/**
 * basic_func
 *
 * @author taoye
 */
public class BasicFunc {
    /**
     * 主键id
     */
    private String id;
    /**
     * 功能名称
     */
    private String name;
    /**
     * 父级功能id
     */
    private String parentId;
    /**
     * 权限编码
     */
    private String code;
    /**
     * 功能连接
     */
    private String url;
    /**
     * 功能图标
     */
    private String icon;
    /**
     * 同一父id的功能排序
     */
    private String sort;
    /**
     * 功能类型
     *
     * @see com.ly.mt.core.base.dict.FuncType
     */
    private String funcType;
    /**
     * 功能等级
     *
     * @see com.ly.mt.core.base.dict.FuncLevel
     */
    private String funcLevel;
    /**
     * 项目类型
     *
     * @see com.ly.mt.core.base.dict.ProjectType
     */
    private String projectType;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 创建人id
     */
    private String createUser;
    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 修改人id
     */
    private String updateUser;
    /**
     * 有效状态
     *
     * @see com.ly.mt.core.base.dict.ValidStatus
     */
    private String validStatus;


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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }
}