package com.ly.mt.center.third.al.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("AlOss上传返回实体类")
public class AlOssFile {
    @ApiModelProperty("文件名")
    private String file_name;
    @ApiModelProperty("文件大小")
    private String file_size;
    @ApiModelProperty("文件类型")
    private String content_type;
    @ApiModelProperty("文件保存路径")
    private String path;
    @ApiModelProperty("文件访问路径")
    private String url;


    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}