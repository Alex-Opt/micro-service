package com.ly.mt.center.data.basic.entity;

import java.io.Serializable;
import java.util.Date;

public class BasicRole implements Serializable {
    private Long id;

    private String name;

    private Long parent_id;

    private Boolean role_level;

    private Boolean role_type;

    private Integer project_type;

    private Date create_time;

    private Long create_user;

    private Date update_time;

    private Long update_user;

    private Boolean valid_status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Boolean getRole_level() {
        return role_level;
    }

    public void setRole_level(Boolean role_level) {
        this.role_level = role_level;
    }

    public Boolean getRole_type() {
        return role_type;
    }

    public void setRole_type(Boolean role_type) {
        this.role_type = role_type;
    }

    public Integer getProject_type() {
        return project_type;
    }

    public void setProject_type(Integer project_type) {
        this.project_type = project_type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Long create_user) {
        this.create_user = create_user;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Long getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(Long update_user) {
        this.update_user = update_user;
    }

    public Boolean getValid_status() {
        return valid_status;
    }

    public void setValid_status(Boolean valid_status) {
        this.valid_status = valid_status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", parent_id=").append(parent_id);
        sb.append(", role_level=").append(role_level);
        sb.append(", role_type=").append(role_type);
        sb.append(", project_type=").append(project_type);
        sb.append(", create_time=").append(create_time);
        sb.append(", create_user=").append(create_user);
        sb.append(", update_time=").append(update_time);
        sb.append(", update_user=").append(update_user);
        sb.append(", valid_status=").append(valid_status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}