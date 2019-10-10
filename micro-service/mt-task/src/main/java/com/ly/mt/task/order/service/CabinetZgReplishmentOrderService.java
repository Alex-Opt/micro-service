package com.ly.mt.task.order.service;

public interface CabinetZgReplishmentOrderService {

    /**
     * 查询每个BD下需要补货的展柜个数 并且发送短信和push/目前先只发短信
     * @throws Exception
     */
    void processCabinetZgReplishmentOrder() throws  Exception;

}