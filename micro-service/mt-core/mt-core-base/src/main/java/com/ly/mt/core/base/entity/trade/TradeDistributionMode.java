package com.ly.mt.core.base.entity.trade;

import com.ly.mt.core.base.entity.base.BaseEntity;
/** @deprecated */
public class TradeDistributionMode extends BaseEntity {

    private String distributionName;

    private String ddistributionPrice;


    public String getDistributionName() {
        return distributionName;
    }

    public void setDistributionName(String distributionName) {
        this.distributionName = distributionName == null ? null : distributionName.trim();
    }

    public String getDdistributionPrice() {
        return ddistributionPrice;
    }

    public void setDdistributionPrice(String ddistributionPrice) {
        this.ddistributionPrice = ddistributionPrice;
    }
}