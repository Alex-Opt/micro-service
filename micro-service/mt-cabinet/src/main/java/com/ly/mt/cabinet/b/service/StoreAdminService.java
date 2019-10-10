package com.ly.mt.cabinet.b.service;

import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.StoreAdminOrdersRequestBody;
import com.ly.mt.cabinet.b.common.response.CabinetImeiRespVo;
import com.ly.mt.cabinet.b.common.response.PageInfoResponseVo;
import com.ly.mt.cabinet.b.common.response.StoreAdminOrdersRespVo;

import java.util.List;

/**
 * 店铺管理员service
 */
public interface StoreAdminService {



    List<CabinetImeiRespVo> findUserCabinetImeis(TokenUserMessage body);


    //PageInfoResponseVo<StoreAdminOrdersRespVo> findConditionsOrders(StoreAdminOrdersRequestBody body, TokenUserMessage user);

   String findConditionsOrders(StoreAdminOrdersRequestBody body, TokenUserMessage user);

}
