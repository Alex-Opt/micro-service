package com.ly.mt.core.common.entity.hd;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class WechatUser implements Serializable {
    private Long id;

    private String wechatNickname;

    private String wechatHeadeImgUrl;

    private String wechatSexdesc;

    private String status;

    private String openId;

    private String phone;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWechatNickname() {
        return wechatNickname;
    }

    public void setWechatNickname(String wechatNickname) {
        this.wechatNickname = wechatNickname;
    }

    public String getWechatHeadeImgUrl() {
        return wechatHeadeImgUrl;
    }

    public void setWechatHeadeImgUrl(String wechatHeadeImgUrl) {
        this.wechatHeadeImgUrl = wechatHeadeImgUrl == null ? null : wechatHeadeImgUrl.trim();
    }

    public String getWechatSexdesc() {
        return wechatSexdesc;
    }

    public void setWechatSexdesc(String wechatSexdesc) {
        this.wechatSexdesc = wechatSexdesc == null ? null : wechatSexdesc.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", wechatNickname=").append(wechatNickname);
        sb.append(", wechatHeadeImgUrl=").append(wechatHeadeImgUrl);
        sb.append(", wechatSexdesc=").append(wechatSexdesc);
        sb.append(", status=").append(status);
        sb.append(", openId=").append(openId);
        sb.append(", phone=").append(phone);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}