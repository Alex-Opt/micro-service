package com.ly.mt.gzg.b.server.base.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReplenishInfoRespVO {
    private Double reward;
    private String goodsName;
    private String gzgCode;
    private int cabinetNo;
    private String address;
    private String gzgOrderId;
    private String replenishOrderId;
    private String detailGoodsPic;
}
