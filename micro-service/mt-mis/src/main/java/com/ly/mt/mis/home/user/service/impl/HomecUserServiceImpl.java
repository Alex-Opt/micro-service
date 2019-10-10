package com.ly.mt.mis.home.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.EnumUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.order.dao.OrderHcViewDao;
import com.ly.mt.core.data.order.entity.OrderHcView;
import com.ly.mt.core.data.user.dao.UserHcViewDao;
import com.ly.mt.core.data.user.entity.UserHcView;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.home.user.service.HomecUserService;
import com.ly.mt.mis.home.user.vo.HomecUserDatagridVo;
import com.ly.mt.mis.home.user.vo.HomecUserOrderDatagridVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * MOTI到家-客户管理-C端客户
 *
 * @author taoye
 */
@Service
public class HomecUserServiceImpl extends BaseServiceImpl implements HomecUserService {
    @Resource
    private UserHcViewDao userHcViewDao;
    @Resource
    private OrderHcViewDao orderHcViewDao;

    @Override
    public ResponseJson loadUserDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        UserHcView userHcView = JSONObject.toJavaObject(jsonObject, UserHcView.class);
        Datagrid datagrid = userHcViewDao.loadDatagridFromMysql(userHcView, page);
        List<HomecUserDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<HomecUserDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    String orderId = vo.getOrderId();
                    if (StringUtil.isNotEmpty(orderId)) {
                        OrderHcView orderHcView = orderHcViewDao.getOrderHcViewByIdFromRedis(orderId);
                        vo.setOrderTime(orderHcView.getCreateTime());
                        vo.setGoodsNames(orderHcView.getGoodsNames());
                    }
                    String areaName = "-";
                    String provinceName = vo.getProvinceName();
                    String cityName = vo.getCityName();
                    if (StringUtil.isNotEmpty(provinceName) && StringUtil.isNotEmpty(cityName)) {
                        areaName = provinceName + "-" + cityName;
                    } else if (StringUtil.isNotEmpty(provinceName)) {
                        areaName = provinceName;
                    } else if (StringUtil.isNotEmpty(cityName)) {
                        areaName = cityName;
                    }
                    vo.setProvinceName(areaName);
                    vo.setSexName(EnumUtil.getNameById("Sex", vo.getSex()));
                    String mobile = vo.getMobile();
                    if (StringUtil.isPhoneNumber(mobile)) {
                        mobile = mobile.substring(0, 7) + "****";
                        vo.setMobile(mobile);
                    }
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }


    @Override
    public ResponseJson loadUserHcOrderDatagrid(JSONObject jsonObject) throws Exception {
        Page page = JSONObject.toJavaObject(jsonObject, Page.class);
        OrderHcView view = JSONObject.toJavaObject(jsonObject, OrderHcView.class);
        Datagrid datagrid = orderHcViewDao.loadDatagridFromMysql(view, page);
        List<HomecUserOrderDatagridVo> vos = JSONObject.parseObject(JSONObject.toJSONString(datagrid.getRows()), new TypeReference<List<HomecUserOrderDatagridVo>>() {
        });
        vos.forEach(
                vo -> {
                    vo.setOrderTypeName(EnumUtil.getNameById("OrderType", vo.getOrderType()));
                    vo.setOrderStatusName(EnumUtil.getNameById("OrderStatus", vo.getOrderStatus()));
                    vo.setPaymentTypeName(EnumUtil.getNameById("PaymentType", vo.getPaymentType()));
                    vo.setDistributionName(EnumUtil.getNameById("DistributeType", vo.getDistributionId()));
                }
        );
        datagrid.setRows(vos);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, datagrid);
    }
}