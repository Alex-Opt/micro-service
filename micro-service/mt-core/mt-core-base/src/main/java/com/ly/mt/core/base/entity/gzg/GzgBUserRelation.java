package com.ly.mt.core.base.entity.gzg;

import java.io.Serializable;
/** @deprecated */
public class GzgBUserRelation implements Serializable {
    private String id;

    private String gzgId;

    private String bUserId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGzgId() {
        return gzgId;
    }

    public void setGzgId(String gzgId) {
        this.gzgId = gzgId;
    }

    public String getbUserId() {
        return bUserId;
    }

    public void setbUserId(String bUserId) {
        this.bUserId = bUserId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gzgId=").append(gzgId);
        sb.append(", bUserId=").append(bUserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}