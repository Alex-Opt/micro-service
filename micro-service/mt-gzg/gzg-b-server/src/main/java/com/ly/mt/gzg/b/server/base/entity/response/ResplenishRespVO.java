package com.ly.mt.gzg.b.server.base.entity.response;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

@Data
public class ResplenishRespVO {
    private JSONArray stayAudit;
    private JSONArray receiveAmount;
    private JSONArray failured;

    public ResplenishRespVO(){
        this.stayAudit = new JSONArray();
        this.receiveAmount = new JSONArray();
        this.failured = new JSONArray();
    }
}
