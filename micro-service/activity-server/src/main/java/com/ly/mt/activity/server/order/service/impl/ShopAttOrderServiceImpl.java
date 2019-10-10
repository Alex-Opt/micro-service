package com.ly.mt.activity.server.order.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.common.entity.hd.common.PageConditions;
import com.ly.mt.activity.server.order.mapper.HdShopAttOrderDetailMapper;
import com.ly.mt.activity.server.order.mapper.HdShopAttOrderMapper;
import com.ly.mt.activity.server.order.service.ShopAttOrderService;
import com.ly.mt.activity.server.shop.mapper.HdShopAttDetailMapper;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.constant.hd.HdActivityOrderStatusEnum;
import com.ly.mt.core.common.entity.hd.HdShopAttDetail;
import com.ly.mt.core.common.entity.hd.HdShopAttOrder;
import com.ly.mt.core.common.entity.hd.model.HdShopAttOrderVo;
import com.ly.mt.core.common.entity.hd.model.HdShopOrderDetailGoodsModel;
import com.ly.mt.core.common.entity.hd.model.MtPageinfoModel;
import com.ly.mt.core.common.entity.hd.request.BuyerOrderRequestBody;
import com.ly.mt.core.common.entity.hd.request.ChangeOrderRequestBody;
import com.ly.mt.core.common.entity.hd.request.ShopOrderRequestBody;
import com.ly.mt.core.common.entity.hd.vo.HdShopAttDetailVo;
import com.ly.mt.core.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author panjingtian
 * @description 订单操作类
 * @date 2019/6/14 10:47 AM
 */
@Service(value = "shopAttOrderServiceImpl")
public class ShopAttOrderServiceImpl implements ShopAttOrderService {

    private final static Logger log = LoggerFactory.getLogger(ShopAttOrderServiceImpl.class);

    @Resource
    private HdShopAttOrderMapper orderMapper;
    @Autowired
    private HdShopAttOrderDetailMapper orderDetailMapper;
    @Autowired
    private HdShopAttDetailMapper shopAttDetailMapper;

    /**
     * 查询当前门店活动的所有订单
     *
     * @param jsonObject {@link com.ly.mt.core.common.entity.hd.vo.HdShopAttDetailVo} 商家信息对象
     * @return
     */
    @Override
    public JSONObject shopOrders(JSONObject jsonObject) {
        ShopOrderRequestBody body = JSONObject.toJavaObject(jsonObject, ShopOrderRequestBody.class);
        Long shopId = Long.valueOf(body.getShopId());
        Long activityId = body.getActivityId();
        PageConditions pageConditions = body.getConditions();
        try {
            List<HdShopAttDetail> details = shopAttDetailMapper.findByShopIdActivityId(shopId, activityId);
            if (details.size()<1){
                return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_ORDER_NULL);
            }
            HdShopAttDetail attDetail = details.get(0);
            HdShopAttDetailVo detailVo = new HdShopAttDetailVo();
            BeanUtils.copyProperties(attDetail, detailVo);

            Map<String, Object> conditions = pageConditions.getConditions();

            PageHelper.startPage(pageConditions.getPageNum(), pageConditions.getPageSize());
            List<HdShopAttOrder> hdShopAttOrders = orderMapper.pageConditions(detailVo, conditions);
            PageInfo<HdShopAttOrder> pageInfo = new PageInfo<>(hdShopAttOrders);
            try {

                List<HdShopAttOrderVo> list = packageOrder(hdShopAttOrders);
                MtPageinfoModel<HdShopAttOrderVo> mtPageinfoModel = new MtPageinfoModel<>();
                BeanUtils.copyProperties(pageInfo, mtPageinfoModel);
                mtPageinfoModel.setList(list);
                return JsonUtil.getSuccessJson(mtPageinfoModel);
            } catch (Exception e) {
                return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_ORDER_NULL);
            }

        } catch (BeansException e) {
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_ORDER_NULL);
        }
    }


    /**
     * 封装订单数据模型
     *
     * @param orders
     * @return
     */
    private List<HdShopAttOrderVo> packageOrder(List<HdShopAttOrder> orders) {
        //拿到所有订单id
        List<String> orderIds = orders.stream().map(order -> {
            return order.getOrderId();
        }).collect(Collectors.toList());
        List<HdShopAttOrderVo> list = new ArrayList<>(orders.size());
        List<HdShopOrderDetailGoodsModel> details = orderDetailMapper.findDetailFrontByOrders(orderIds);
        orders.stream().forEach(order -> {
            HdShopAttOrderVo vo = new HdShopAttOrderVo();
            BeanUtils.copyProperties(order, vo);
            List<HdShopOrderDetailGoodsModel> detailGoodsMsgModels =
                    details.stream().filter(detail -> detail.getOrderId().equals(order.getOrderId())).collect(Collectors.toList());
            vo.setDetails(detailGoodsMsgModels);
            String buyerPhone = orderMapper.findBuyerPhoneBuyUserId(order.getUserId());
            vo.setBuyerPhone(buyerPhone);
            list.add(vo);
        });
        return list;
    }

    /**
     * 门店商家修改订单状态
     *
     * @param jsonObject string orderId
     *                   string orderStatus
     *                   string getProductCode
     * @return
     */
    @Override
    public JSONObject shopChangeOrderStatus(JSONObject jsonObject) {
        try {
            ChangeOrderRequestBody body = JSONObject.toJavaObject(jsonObject, ChangeOrderRequestBody.class);
            String orderId = body.getOrderId();
            String orderStatus = body.getOrderStatus();
            String getProuctCode = body.getGetProductCode();

            if (StringUtils.isEmpty(orderId) || StringUtils.isEmpty(orderStatus)) {
                return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_ORDER_STATUS_UPDATE_ERROR);
            }

            HdShopAttOrder order = orderMapper.findByOrderId(orderId);
            Integer resultNum;
            //取消
            if (StringUtils.isEmpty(getProuctCode)){
                resultNum =  orderMapper.shopChangeStatus(orderId, orderStatus, getProuctCode);
            }else{
                //完结
                if (!HdActivityOrderStatusEnum.FINISH.getStatus().equals(orderStatus) || !getProuctCode.equals(order.getGetGoodsCode())){
                    return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_USER_GETPRODUCT_CODE_ERROR);
                }
                resultNum =   orderMapper.shopChangeStatus(orderId, orderStatus, getProuctCode);
            }
            
            if (resultNum < 1) {
                return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_ORDER_STATUS_UPDATE_ERROR);
            }
            return JsonUtil.getSuccessJson();
        } catch (Exception e) {
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_ORDER_STATUS_UPDATE_ERROR);
        }
    }

    /**
     * 买家查询
     *
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject buyerGetOrder(JSONObject jsonObject) {
        try {
            BuyerOrderRequestBody body = JSONObject.toJavaObject(jsonObject, BuyerOrderRequestBody.class);
            Long attUserId = body.getAttUserId();
            Long attDetailId = body.getHdShopAttDetailId();

            List<HdShopAttOrder> hdShopAttOrders = orderMapper.findByUserIdAttDetailId(attUserId, attDetailId);
            List<HdShopAttOrderVo> list = packageOrder(hdShopAttOrders);
            return JsonUtil.getSuccessJson(list);
        } catch (Exception e) {
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_OPERATION_FAIL);
        }
    }

}
