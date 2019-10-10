package com.ly.mt.center.data.basic.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BasicArea {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("上级区域id")
    private String pid;
    @ApiModelProperty("区域名称")
    private String name;
    @ApiModelProperty("商城区域id")
    private String m_id;
    @ApiModelProperty("商城区域名称")
    private String m_name;
    @ApiModelProperty("前端组件定位需要字段")
    private String m_index;
    @ApiModelProperty("区域代码")
    private String code;
    @ApiModelProperty("区域级别")
    private String level;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("修改时间")
    private String modify_time;
    @ApiModelProperty("数据来源")
    private String data_source;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_index() {
        return m_index;
    }

    public void setM_index(String m_index) {
        this.m_index = m_index;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source;
    }

}