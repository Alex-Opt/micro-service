package com.ly.mt.mis.gzg.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.EnumUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.cabinet.entity.CabinetPlan;
import com.ly.mt.core.data.goods.entity.GoodsSkuCode;
import com.ly.mt.core.data.goods.entity.GoodsSkuInfo;
import com.ly.mt.core.data.goods.entity.GoodsSpuInfo;
import com.ly.mt.core.data.gzg.entity.*;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.gzg.order.service.GzgOrderService;
import com.ly.mt.mis.gzg.order.vo.GzgOrderDatagridVo;
import com.ly.mt.mis.gzg.order.vo.GzgOrderInfoGoodsVo;
import com.ly.mt.mis.gzg.order.vo.GzgOrderInfoVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * MOTI售卖柜-订单管理-订单列表
 *
 * @author taoye
 */
@Service
public class GzgOrderServiceImpl extends BaseServiceImpl implements GzgOrderService {
    @Override
    public GzgOrderInfoVo loadOrderInfo(String id) throws Exception {
        GzgOrderInfoVo vo = new GzgOrderInfoVo();
        // 订单信息
        GzgOrder gzgOrder = gzgOrderDao.getGzgOrderByIdFromRedis(id);
        vo.setId(gzgOrder.getId());
        vo.setCreateTime(gzgOrder.getCreateTime());
        vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", gzgOrder.getOrderStatus()));
        // 商品信息
        List<GzgOrderInfoGoodsVo> vos = null;
        List<GzgOrderItem> list = gzgOrderItemDao.listGzgOrderItemByOrderIdFromRedis(id);
        if (null != list && list.size() > 0) {
            vos = JSONObject.parseObject(JSONObject.toJSONString(list), new TypeReference<List<GzgOrderInfoGoodsVo>>() {
            });
            vos.forEach(
                    goods -> {
                        String skuId = goods.getSkuId();
                        if (StringUtil.isNotEmpty(skuId)) {
                            GoodsSkuInfo skuInfo = goodsSkuInfoDao.getGoodsSkuInfoByIdFromRedis(skuId);
                            String spuId = skuInfo.getSpuId();
                            if (StringUtil.isNotEmpty(spuId)) {
                                GoodsSpuInfo spuInfo = goodsSpuInfoDao.getGoodsSpuInfoByIdFromRedis(spuId);
                                goods.setSpuName(spuInfo.getName());
                            }
                            GoodsSkuCode skuCode = goodsSkuCodeDao.getGoodsSkuCodeBySkuIdFromRedis(skuId);
                            goods.setCode(skuCode.getCode());
                        }
                    }
            );
        }
        vo.setGoods(vos);
        // 格子柜信息
        String code = gzgOrder.getCode();
        if (StringUtil.isNotEmpty(code)) {
            vo.setCode(code);
            GzgInfo gzgInfo = gzgInfoDao.getGzgInfoByCodeFromRedis(code);
            String planId = gzgInfo.getPlanId();
            if (StringUtil.isNotEmpty(planId)) {
                CabinetPlan cabinetPlan = cabinetPlanDao.getCabinetPlanByIdFromRedis(planId);
                vo.setPlanName(cabinetPlan.getPlanName());
            }
        }
        String hotelId = gzgOrder.getHotelId();
        if (StringUtil.isNotEmpty(hotelId)) {
            GzgHotel gzgHotel = gzgHotelDao.getGzgHotelByIdFromRedis(hotelId);
            vo.setAddress(gzgHotel.getAddress());
            vo.setName(gzgHotel.getName());
            String bdId = gzgHotel.getBdId();
            if (StringUtil.isNotEmpty(bdId)) {
                User user = userDao.getUserByIdFromRedis(bdId);
                vo.setBdName(user.getUserName());
                vo.setBdMobile(user.getMobile());
            }
            String hotelAdminId = gzgHotel.getHotelAdminId();
            if (StringUtil.isNotEmpty(hotelAdminId)) {
                User user = userDao.getUserByIdFromRedis(hotelAdminId);
                vo.setHotelAdminMobile(user.getMobile());
            }
        }
        // 支付信息
        vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", gzgOrder.getPaymentType()));
        vo.setPaymentNo(gzgOrder.getPaymentNo());
        vo.setOldMoney(gzgOrder.getOrderOldMoney());
        vo.setPayMoney(gzgOrder.getOrderMoney());
        vo.setPayTime(gzgOrder.getOrderFinishTime());
        return vo;
    }


    @Override
    public ResponseJson loadOrderDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        GzgOrder gzgOrder = JSONObject.toJavaObject(jsonObject, GzgOrder.class);
        gzgOrder.setCreateTime(getBetweenSql(jsonObject.getString("createTimeStart"), jsonObject.getString("createTimeEnd")));
        gzgOrder.setOrderMoney(getBetweenSql(jsonObject.getString("orderMoneyStart"), jsonObject.getString("orderMoneyEnd")));
        Datagrid datagrid = gzgOrderDao.loadDatagridFromMysql(gzgOrder, page);
        List<GzgOrder> orders = (List<GzgOrder>) datagrid.getRows();
        if (null != orders && orders.size() > 0) {
            List<GzgOrderDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(orders), new TypeReference<List<GzgOrderDatagridVo>>() {
            });
            vos.forEach(
                    vo -> {
                        vo.setOrderSourceName("H5");
                        vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", vo.getOrderStatus()));
                        vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", vo.getPaymentType()));
                        String hotelId = vo.getHotelId();
                        if (StringUtil.isNotEmpty(hotelId)) {
                            GzgHotel gzgHotel = gzgHotelDao.getGzgHotelByIdFromRedis(hotelId);
                            vo.setHotelName(gzgHotel.getName());
                        }
                    }
            );
            datagrid.setRows(vos);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }

    @Override
    public List<GzgOrderDatagridVo> listOrderExcel(JSONObject jsonObject) throws Exception {
        GzgOrder gzgOrder = JSONObject.toJavaObject(jsonObject, GzgOrder.class);
        gzgOrder.setCreateTime(getBetweenSql(jsonObject.getString("createTimeStart"), jsonObject.getString("createTimeEnd")));
        gzgOrder.setOrderMoney(getBetweenSql(jsonObject.getString("orderMoneyStart"), jsonObject.getString("orderMoneyEnd")));
        List<GzgOrder> list = gzgOrderDao.listGzgOrderFromMysql(gzgOrder);
        List<GzgOrderDatagridVo> vos = new ArrayList<>();
        if (null != list && list.size() > 0) {
            vos = JSONObject.parseObject(JSONObject.toJSONString(list), new TypeReference<List<GzgOrderDatagridVo>>() {
            });
            vos.forEach(
                    vo -> {
                        String hotelId = vo.getHotelId();
                        if (StringUtil.isNotEmpty(hotelId)) {
                            GzgHotel gzgHotel = gzgHotelDao.getGzgHotelByIdFromRedis(hotelId);
                            vo.setHotelName(gzgHotel.getName());
                        }
                        vo.setOrderSourceName("H5");
                        vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", vo.getOrderStatus()));
                        vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", vo.getPaymentType()));
                    }
            );
        }
        return vos;
    }
}