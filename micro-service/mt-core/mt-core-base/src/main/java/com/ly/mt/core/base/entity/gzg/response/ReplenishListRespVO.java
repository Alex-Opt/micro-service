package com.ly.mt.core.base.entity.gzg.response;



import java.util.ArrayList;
import java.util.List;

/** @deprecated */
public class ReplenishListRespVO {
    //我要补货
    private List<ReplenishInfoRespVO> wyReplenish;
    //待补货
    private List<ReplenishInfoRespVO> stayReplenish;
    //待审核
    private List<ReplenishInfoRespVO> stayAudit;
    //已到账
    private List<ReplenishInfoRespVO> receivedAccount;
    //已失效
    private List<ReplenishInfoRespVO> failured;

    public ReplenishListRespVO(){
        this.wyReplenish = new ArrayList<>();
        this.stayAudit = new ArrayList<>();
        this.failured = new ArrayList<>();
        this.receivedAccount = new ArrayList<>();
        this.stayReplenish = new ArrayList<>();
    }
}
