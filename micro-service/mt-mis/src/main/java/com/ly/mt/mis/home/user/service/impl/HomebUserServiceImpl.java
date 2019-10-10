package com.ly.mt.mis.home.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.EnumUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.order.dao.OrderHbpViewDao;
import com.ly.mt.core.data.order.dao.OrderHbsViewDao;
import com.ly.mt.core.data.order.entity.OrderHbpView;
import com.ly.mt.core.data.order.entity.OrderHbsView;
import com.ly.mt.core.data.user.dao.UserHbViewDao;
import com.ly.mt.core.data.user.entity.UserHbView;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.home.user.service.HomebUserService;
import com.ly.mt.mis.home.user.vo.HomebUserDatagridVo;
import com.ly.mt.mis.home.user.vo.HomebUserOrderBuyDatagridVo;
import com.ly.mt.mis.home.user.vo.HomebUserOrderSellDatagridVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * MOTI到家-客户管理-C端客户
 *
 * @author taoye
 */
@Service
public class HomebUserServiceImpl extends BaseServiceImpl implements HomebUserService {
    @Resource
    private UserHbViewDao userHbViewDao;
    @Resource
    private OrderHbsViewDao orderHbsViewDao;
    @Resource
    private OrderHbpViewDao orderHbpViewDao;

    @Override
    public ResponseJson loadUserDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        UserHbView userHbView = JSONObject.toJavaObject(jsonObject, UserHbView.class);
        Datagrid datagrid = userHbViewDao.loadDatagridFromMysql(userHbView, page);
        List<HomebUserDatagridVo> vos = new ArrayList<>();
        List<UserHbView> views = (List<UserHbView>) datagrid.getRows();
        views.forEach(
                view -> {
                    StringBuilder registInfo = new StringBuilder();
                    registInfo.append("注册来源：").append(EnumUtil.getNameById("QuickType", view.getQuickType()));
                    registInfo.append("</br>");
                    registInfo.append("注册时间：").append(view.getCreateTime());
                    registInfo.append("</br>");
                    registInfo.append("城市：").append(view.getProvinceName()).append("-").append(view.getCityName()).append("-").append(view.getDistrictName());
                    StringBuilder sellerInfo = new StringBuilder();
                    String mobile = view.getMobile();
                    if (StringUtil.isPhoneNumber(mobile)) {
                        mobile = mobile.substring(0, 7) + "****";
                    }
                    sellerInfo.append("手机号：").append(mobile);
                    sellerInfo.append("</br>");
                    sellerInfo.append("商家姓名：").append(view.getUserName());
                    sellerInfo.append("</br>");
                    sellerInfo.append("性别：").append(EnumUtil.getNameById("Sex", view.getSex()));
                    StringBuilder sellerAttribute = new StringBuilder();
                    sellerAttribute.append("商家UID：").append(view.getId());
                    StringBuilder shopInfo = new StringBuilder();
                    shopInfo.append("店铺名称：").append(view.getShopName());
                    shopInfo.append("</br>");
                    shopInfo.append("店铺地址：").append(view.getShopAddress());
                    StringBuilder salesRecord = new StringBuilder();
                    salesRecord.append("累计售卖件数：").append(StringUtil.isNotEmpty(view.getSalesCount()) ? view.getSalesCount() : "0");
                    salesRecord.append("</br>");
                    salesRecord.append("累计售卖金额：").append(StringUtil.isNotEmpty(view.getSalesMoney()) ? view.getSalesMoney() : "0");
                    salesRecord.append("</br>");
                    salesRecord.append("最近售卖时间：").append(StringUtil.isNotEmpty(view.getLastSalesTime()) ? view.getLastSalesTime() : "无售卖记录");
                    HomebUserDatagridVo vo = new HomebUserDatagridVo();
                    vo.setRegistInfo(registInfo.toString());
                    vo.setSellerInfo(sellerInfo.toString());
                    vo.setSellerAttribute(sellerAttribute.toString());
                    vo.setShopInfo(shopInfo.toString());
                    vo.setSalesRecord(salesRecord.toString());
                    vo.setId(view.getId());
                    vos.add(vo);
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }


    @Override
    public ResponseJson loadUserHbsOrderDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        OrderHbsView view = JSONObject.toJavaObject(jsonObject, OrderHbsView.class);
        Datagrid datagrid = orderHbsViewDao.loadDatagridFromMysql(view, page);
        List<HomebUserOrderSellDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<HomebUserOrderSellDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", vo.getOrderStatus()));
                    vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", vo.getPaymentType()));
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }


    @Override
    public ResponseJson loadUserHbpOrderDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        OrderHbpView view = JSONObject.toJavaObject(jsonObject, OrderHbpView.class);
        Datagrid datagrid = orderHbpViewDao.loadDatagridFromMysql(view, page);
        List<HomebUserOrderBuyDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<HomebUserOrderBuyDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", vo.getOrderStatus()));
                    vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", vo.getPaymentType()));
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }
}