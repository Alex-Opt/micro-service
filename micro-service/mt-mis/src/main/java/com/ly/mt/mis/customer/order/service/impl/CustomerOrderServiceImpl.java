package com.ly.mt.mis.customer.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.EnumUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.data.order.dao.OrderBackstageViewDao;
import com.ly.mt.core.data.order.entity.OrderBackstageView;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.customer.order.service.CustomerOrderService;
import com.ly.mt.mis.customer.order.vo.CustomerOrderDatagridVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.dict.OrderCategory.*;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 客服后台-查询工具-订单查询
 *
 * @author taoye
 */
@Service
public class CustomerOrderServiceImpl extends BaseServiceImpl implements CustomerOrderService {
    @Resource
    private OrderBackstageViewDao orderBackstageViewDao;

    @Override
    public ResponseJson loadOrderDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        String orderCategory = jsonObject.getString("orderCategory");
        OrderBackstageView view = JSONObject.toJavaObject(jsonObject, OrderBackstageView.class);
        // TODO 蛋疼设计，需根据各模块订单类型变化调整
        if (ORDER_CATEGORY_HOME_B.getId().equals(orderCategory)) {
            view.setOrderSource("10");
        } else if (ORDER_CATEGORY_HOME_C.getId().equals(orderCategory)) {
            view.setOrderSource("1,2,3,4");
        } else if (ORDER_CATEGORY_CABINET_C.getId().equals(orderCategory)) {
            view.setOrderSource("30");
        }
        Datagrid datagrid = orderBackstageViewDao.loadDatagridFromMysql(view, page);
        List<CustomerOrderDatagridVo> orderBackstageDatagridVos = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<CustomerOrderDatagridVo>>() {
        });
        orderBackstageDatagridVos.forEach(
                orderBackstageDatagridVo -> {
                    orderBackstageDatagridVo.setOrderTypeName(EnumUtil.getNameById("OrderType", orderBackstageDatagridVo.getOrderType()));
                    orderBackstageDatagridVo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", orderBackstageDatagridVo.getPaymentType()));
                    orderBackstageDatagridVo.setDistributionName(EnumUtil.getNameById("DistributeType", orderBackstageDatagridVo.getDistributionId()));
                    orderBackstageDatagridVo.setOrderSourceName(EnumUtil.getNameById("OrderSource", orderBackstageDatagridVo.getOrderSource()));
                    orderBackstageDatagridVo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", orderBackstageDatagridVo.getOrderStatus()));
                }
        );
        datagrid.setRows(orderBackstageDatagridVos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }
}