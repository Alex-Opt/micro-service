package com.ly.mt.core.common.entity.hd;

import java.io.Serializable;
import java.util.Date;

public class WechatHelpMaster implements Serializable {
    private Long id;

    private Long wechatId;

    private Long wechatMasterId;

    private String taskStatus;

    private Long taskId;

    private Long activityId;

    private String userName;

    private String phone;

    private String shareUrl;

    private String shareOssUrl;

    private String openId;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWechatId() {
        return wechatId;
    }

    public void setWechatId(Long wechatId) {
        this.wechatId = wechatId;
    }

    public Long getWechatMasterId() {
        return wechatMasterId;
    }

    public void setWechatMasterId(Long wechatMasterId) {
        this.wechatMasterId = wechatMasterId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl == null ? null : shareUrl.trim();
    }

    public String getShareOssUrl() {
        return shareOssUrl;
    }

    public void setShareOssUrl(String shareOssUrl) {
        this.shareOssUrl = shareOssUrl == null ? null : shareOssUrl.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", wechatId=").append(wechatId);
        sb.append(", wechatMasterId=").append(wechatMasterId);
        sb.append(", taskStatus=").append(taskStatus);
        sb.append(", taskId=").append(taskId);
        sb.append(", activityId=").append(activityId);
        sb.append(", userName=").append(userName);
        sb.append(", phone=").append(phone);
        sb.append(", shareUrl=").append(shareUrl);
        sb.append(", shareOssUrl=").append(shareOssUrl);
        sb.append(", openId=").append(openId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}