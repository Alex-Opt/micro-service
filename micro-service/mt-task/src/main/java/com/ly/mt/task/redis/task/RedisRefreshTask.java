package com.ly.mt.task.redis.task;

import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.redis.dao.RedisRefreshDao;
import com.ly.mt.core.data.redis.entity.RedisRefresh;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.task.redis.service.*;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.RedisRefreshStatus.REDIS_REFRESH_STATUS_ERROR;
import static com.ly.mt.core.base.dict.RedisRefreshStatus.REDIS_REFRESH_STATUS_FINISH;
import static com.ly.mt.core.redis.RedisKey.REDIS_REDIS_REFRESH_STRING_ID_REFRESH_TYPE_TRIGGER_TYPE_ID;
import static com.ly.mt.task.redis.dict.RefreshType.*;

/**
 * redis缓存更新处理任务
 *
 * @author taoye
 */
@Component
public class RedisRefreshTask extends QuartzJobBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRefreshTask.class);
    @Resource
    private RedisService redisService;
    @Resource
    private RedisRefreshDao redisRefreshDao;
    @Resource
    private CouponService couponService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private GzgService gzgService;
    @Resource
    private UserService userService;
    @Resource
    private ShopService shopService;
    @Resource
    private TradeService tradeService;
    @Resource
    private OrderService orderService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<RedisRefresh> list = redisRefreshDao.listRedisRefreshFromMysql();
        LOGGER.info("RedisRefreshTask.listRedisRefreshFromMysql size={} time={}", list.size(), DateUtil.getNowTimeStr());
        for (RedisRefresh refresh : list) {
            try {
                String param = refresh.getRefreshType() + ":" + refresh.getTriggerType() + ":" + refresh.getId1() + refresh.getId2() + refresh.getId3();
                String json = redisService.get(REDIS_REDIS_REFRESH_STRING_ID_REFRESH_TYPE_TRIGGER_TYPE_ID, param);
                if (StringUtil.isEmpty(json)) {
                    redisRefresh(refresh);
                }
                refresh.setRefreshStatus(REDIS_REFRESH_STATUS_FINISH.getId());
                redisService.setEntity(REDIS_REDIS_REFRESH_STRING_ID_REFRESH_TYPE_TRIGGER_TYPE_ID, param, refresh, 10, TimeUnit.SECONDS);
            } catch (Exception e) {
                LOGGER.error("RedisRefreshTask.redisRefresh error ", e);
                refresh.setRefreshStatus(REDIS_REFRESH_STATUS_ERROR.getId());
            }
        }
        if (list.size() > 0) {
            redisRefreshDao.updateRedisRefreshList(list);
            LOGGER.info("RedisRefreshTask.updateRedisRefreshList size={} time={}", list.size(), DateUtil.getNowTimeStr());
        }
    }


    /**
     * 缓存更新处理
     *
     * @author taoye
     */
    private void redisRefresh(RedisRefresh refresh) throws Exception {
        switch (Integer.valueOf(refresh.getRefreshType())) {
            case REDIS_REFRESH_TYPE_GOODS_SKU_PICTURE:
                goodsService.refreshGoodsSkuPicture(refresh);
                break;
            case REDIS_REFRESH_TYPE_GOODS_SKU_CODE:
                goodsService.refreshGoodsSkuCode(refresh);
                break;
            case REDIS_REFRESH_TYPE_GOODS_SKU_INFO:
                goodsService.refreshGoodsSkuInfo(refresh);
                break;
            case REDIS_REFRESH_TYPE_GOODS_SPU_INFO:
                goodsService.refreshGoodsSpuInfo(refresh);
                break;
            case REDIS_REFRESH_TYPE_USER:
                userService.refreshUser(refresh);
                break;
            case REDIS_REFRESH_TYPE_USER_ADDRESS:
                userService.refreshUserAddress(refresh);
                break;
            case REDIS_REFRESH_TYPE_COUPON_INFO:
                couponService.refreshCouponInfo(refresh);
                break;
            case REDIS_REFRESH_TYPE_GZG_HOTEL:
                gzgService.refreshGzgHotel(refresh);
                break;
            case REDIS_REFRESH_TYPE_GZG_INFO:
                gzgService.refreshGzgInfo(refresh);
                break;
            case REDIS_REFRESH_TYPE_GZG_ORDER:
                gzgService.refreshGzgOrder(refresh);
                break;
            case REDIS_REFRESH_TYPE_GZG_ORDER_ITEM:
                gzgService.refreshGzgOrderItem(refresh);
                break;
            case REDIS_REFRESH_TYPE_SHOP_PURCHASES:
                shopService.refreshShopPurchases(refresh);
                break;
            case REDIS_REFRESH_TYPE_SHOP_PURCHASES_ITEMS:
                shopService.refreshShopPurchasesItems(refresh);
                break;
            case REDIS_REFRESH_TYPE_SHOP_INFO:
                shopService.refreshShopInfo(refresh);
                break;
            case REDIS_REFRESH_TYPE_TRADE_ORDERS:
                tradeService.refreshTradeOrders(refresh);
                break;
            case REDIS_REFRESH_TYPE_TRADE_ORDER_TIMES:
                tradeService.refreshTradeOrderItems(refresh);
                break;
            case REDIS_REFRESH_TYPE_TRADE_ORDER_COUPON:
                tradeService.refreshTradeOrderCoupon(refresh);
                break;
            case REDIS_REFRESH_TYPE_ORDERS_BATTLE_INFO:
                orderService.refreshOrdersBattleInfo(refresh);
                break;
            default:
                throw new RuntimeException("RedisRefreshTask.redisRefresh refreshType not existence");
        }
    }
}