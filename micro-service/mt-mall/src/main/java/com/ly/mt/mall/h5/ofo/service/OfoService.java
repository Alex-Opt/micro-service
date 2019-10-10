package com.ly.mt.mall.h5.ofo.service;

import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description ofo分销接口
 * @Author
 */
public interface OfoService {
    /**
     * @Description 查询goodsOfo  spu列表
     * @Author
     */
    ResponseJson goodsOfoSpuList();

    /**
     *
     * @param spuId
     * @return
     */
    ResponseJson queryGoodsOfoSpuById(String spuId);

    /**
     * 根据skuId查询图片
     * @param skuId
     * @return
     */
    ResponseJson querySkupicturcBySkuId(String skuId);

    /**
     * @Description 用户注册登录
     * @Author
     */
    ResponseJson mobileLogin(String userName,String mobile,String dynamicCode, HttpServletRequest request);

    /**
     * 下订单
     * @param skuId
     * @param skuNum
     * @param provinceCode
     * @param provinceName
     * @param cityCode
     * @param cityName
     * @param districtCode
     * @param districtName
     * @param userAddress
     * @return
     */
    ResponseJson createOrder(String skuId,String skuNum,String provinceCode,String provinceName,String cityCode,
                             String cityName,String districtCode,String districtName,String userAddress,String mobile,String appType,String actiClass,HttpServletRequest request);


    /**
     * 保存类型和参数
     * @param type
     * @param code
     * @return
     */
    ResponseJson saveTypeAndCode(String type,String code, HttpServletRequest request);


    /**
     * 保存支付方式
     * @param orderId
     * @param paymentType
     * @return
     */
    ResponseJson savePaymentType(String orderId,String paymentType,String freight);

    /**
     * 根据appType和actiClass 查询数据
     * @param appType
     * @param actiClass
     * @return
     */
    ResponseJson queryGoodsFrontByActiClass(String appType,String actiClass);

    /**
     * 根据订单来源，查询数据
     * @param orderSource
     * @return
     */
    ResponseJson queryOrderList(String orderSource);

    ResponseJson  applyWithdrawal(String userId);
}