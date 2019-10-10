package com.ly.mt.mis.base.service.impl;

import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.basic.dao.*;
import com.ly.mt.core.data.basic.entity.BasicRole;
import com.ly.mt.core.data.basic.entity.BasicUserRole;
import com.ly.mt.core.data.cabinet.dao.CabinetCategroyDao;
import com.ly.mt.core.data.cabinet.dao.CabinetPlanDao;
import com.ly.mt.core.data.coupon.dao.CouponInfoDao;
import com.ly.mt.core.data.goods.dao.*;
import com.ly.mt.core.data.gzg.dao.*;
import com.ly.mt.core.data.order.dao.OrderHcViewDao;
import com.ly.mt.core.data.order.dao.OrdersBattleInfoDao;
import com.ly.mt.core.data.payment.dao.PaymentDetailDao;
import com.ly.mt.core.data.record.dao.RecordImportDao;
import com.ly.mt.core.data.shop.dao.ShopInfoDao;
import com.ly.mt.core.data.trade.dao.TradeOrderCouponDao;
import com.ly.mt.core.data.trade.dao.TradeOrderItemsDao;
import com.ly.mt.core.data.user.dao.UserAddressDao;
import com.ly.mt.core.data.user.dao.UserDao;
import com.ly.mt.core.logistics.service.Kd100Service;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.mis.base.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 模块公共实现类，处理本模块公共方法
 *
 * @author taoye
 */
@Service
public class BaseServiceImpl implements BaseService {
    @Resource
    public RedisService redisService;
    @Resource
    public Kd100Service kd100Service;

    @Resource
    public BasicAreaDao basicAreaDao;
    @Resource
    public BasicFuncDao basicFuncDao;
    @Resource
    public BasicRoleDao basicRoleDao;
    @Resource
    public BasicRoleRightDao basicRoleRightDao;
    @Resource
    public BasicUserRoleDao basicUserRoleDao;

    @Resource
    public CabinetCategroyDao cabinetCategroyDao;
    @Resource
    public CabinetPlanDao cabinetPlanDao;

    @Resource
    public CouponInfoDao couponInfoDao;

    @Resource
    public GoodsCategroyInfoDao goodsCategroyInfoDao;
    @Resource
    public GoodsInfoViewDao goodsInfoViewDao;
    @Resource
    public GoodsSkuPictureDao goodsSkuPictureDao;
    @Resource
    public GoodsSkuInfoDao goodsSkuInfoDao;
    @Resource
    public GoodsSkuCodeDao goodsSkuCodeDao;
    @Resource
    public GoodsSpuInfoDao goodsSpuInfoDao;

    @Resource
    public GzgHotelDao gzgHotelDao;
    @Resource
    public GzgInfoDao gzgInfoDao;
    @Resource
    public GzgOrderDao gzgOrderDao;
    @Resource
    public GzgOrderItemDao gzgOrderItemDao;

    @Resource
    public OrderHcViewDao orderHcViewDao;
    @Resource
    public OrdersBattleInfoDao ordersBattleInfoDao;

    @Resource
    public PaymentDetailDao paymentDetailDao;

    @Resource
    public RecordImportDao recordImportDao;

    @Resource
    public ShopInfoDao shopInfoDao;

    @Resource
    public TradeOrderCouponDao tradeOrderCouponDao;
    @Resource
    public TradeOrderItemsDao tradeOrderItemsDao;

    @Resource
    public UserDao userDao;
    @Resource
    public UserAddressDao userAddressDao;


    @Override
    public String getLoginUserId() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == sra) {
            return null;
        }
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("userId"));
    }


    @Override
    public String getLoginUserName() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == sra) {
            return null;
        }
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("userName"));
    }


    @Override
    public String getBetweenSql(String start, String end) {
        int length = 10;
        if (StringUtil.isDate(start) && start.length() <= length) {
            start += " 00:00:00";
        }
        if (StringUtil.isDate(end) && end.length() <= length) {
            end += " 23:59:59";
        }
        String betweenSql = null;
        if (StringUtil.isNotEmpty(start) && StringUtil.isNotEmpty(end)) {
            betweenSql = " BETWEEN '" + start + "' AND '" + end + "'";
        } else if (StringUtil.isNotEmpty(start)) {
            betweenSql = " >= '" + start + "'";
        } else if (StringUtil.isNotEmpty(end)) {
            betweenSql = " <= '" + end + "'";
        }
        return betweenSql;
    }


    @Override
    public String getRoleNamesByUserId(String userId) {
        if (StringUtil.isEmpty(userId)) {
            return null;
        }
        List<BasicUserRole> basicUserRoles = basicUserRoleDao.listBasicUserRoleByUserIdFromRedis(userId);
        StringBuilder sb = new StringBuilder();
        for (BasicUserRole basicUserRole : basicUserRoles) {
            String roleId = basicUserRole.getRoleId();
            if (StringUtil.isEmpty(roleId)) {
                continue;
            }
            BasicRole basicRole = basicRoleDao.getBasicRoleByIdFromRedis(roleId);
            String roleName = basicRole.getName();
            if (StringUtil.isEmpty(roleName)) {
                continue;
            }
            sb.append(roleName).append(",");
        }
        if (sb.length() > 0) {
            return sb.deleteCharAt(sb.length() - 1).toString();
        }
        return null;
    }


    @Override
    public String getUserIdsByRoleId(String roleId) {
        StringBuilder sb = new StringBuilder();
        List<BasicUserRole> basicUserRoles = basicUserRoleDao.listBasicUserRoleByRoleIdFromRedis(roleId);
        if (basicUserRoles.size() <= 0) {
            return null;
        }
        for (BasicUserRole basicUserRole : basicUserRoles) {
            sb.append(basicUserRole.getUserId()).append(",");
        }
        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}