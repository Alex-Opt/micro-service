package com.ly.mt.core.logistics.entity;

import java.util.List;

/**
 * 物流信息
 *
 * @author taoye
 */
public class LogisticsInfo {
    /**
     * 快递公司编码
     */
    private String logisticsCode;
    /**
     * 快递公司名称
     */
    private String logisticsName;
    /**
     * 快递单号
     */
    private String logisticsNo;
    /**
     * 快递单当前状态
     */
    private String logisticsState;
    /**
     * 快递单当前状态名称
     */
    private String logisticsStateName;
    /**
     * 物流详细节点信息
     */
    private List<LogisticsNodeInfo> logisticsNodeInfos;


    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getLogisticsState() {
        return logisticsState;
    }

    public void setLogisticsState(String logisticsState) {
        this.logisticsState = logisticsState;
    }

    public String getLogisticsStateName() {
        return logisticsStateName;
    }

    public void setLogisticsStateName(String logisticsStateName) {
        this.logisticsStateName = logisticsStateName;
    }

    public List<LogisticsNodeInfo> getLogisticsNodeInfos() {
        return logisticsNodeInfos;
    }

    public void setLogisticsNodeInfos(List<LogisticsNodeInfo> logisticsNodeInfos) {
        this.logisticsNodeInfos = logisticsNodeInfos;
    }
}