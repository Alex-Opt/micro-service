package com.ly.mt.core.common.entity.gzg.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReplenishInfoRespVO {
    private BigDecimal reward;
    private String goodsName;
    private long gzgCode;
    private int cabinetNo;
    private String address;
    private long gzgOrderId;
    private long replenishOrderId;
    private String detailGoodsPic;
}
