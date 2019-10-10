package com.ly.mt.core.base.entity.user;


/**
 * @author zhanglifeng
 * 问题反馈图片表
 *//** @deprecated */
public class UserFeedbackImages {
    private String id;

    private String feedbackId;

    private String imagesUrl;

    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl == null ? null : imagesUrl.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}