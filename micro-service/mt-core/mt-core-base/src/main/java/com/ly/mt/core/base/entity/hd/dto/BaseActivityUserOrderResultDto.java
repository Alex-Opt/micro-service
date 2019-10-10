package com.ly.mt.core.base.entity.hd.dto;

import java.util.List;

/**
 * @description
 *  门店活动中个人下单成功后返回基础模型
 * @author panjingtian
 * @date 2019/6/15 12:13 PM
 *//** @deprecated */
public class BaseActivityUserOrderResultDto {

 /**
  * 取货码
  */
 private String getProductCode;

 /**
  * 门店信息
  */
  private ShopInfoDto shopInfoDto;

 /**
  *订单详情信息
  */
 private List<HdShopOrderDetailDto> orderMsg;



}
