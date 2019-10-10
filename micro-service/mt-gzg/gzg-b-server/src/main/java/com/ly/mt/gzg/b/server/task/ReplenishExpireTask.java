package com.ly.mt.gzg.b.server.task;

import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.constant.gzg.GzgReplenishStatusEnum;
import com.ly.mt.core.common.constant.gzg.ReplenishRecordInvalidTypeEnum;
import com.ly.mt.core.common.constant.gzg.ReplenishRecordStatusEnum;
import com.ly.mt.core.common.entity.gzg.GzgInfo;
import com.ly.mt.core.common.entity.gzg.GzgReplenishOrder;
import com.ly.mt.core.common.entity.gzg.GzgReplenishRecord;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgInfoMapper;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgReplenishOrderMapper;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgReplenishRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class ReplenishExpireTask {

    @Autowired
    private GzgReplenishOrderMapper gzgReplenishOrderMapper;
    @Autowired
    private GzgReplenishRecordMapper gzgReplenishRecordMapper;
    @Autowired
    private RedisServer redisServer;
    @Autowired
    private GzgInfoMapper gzgInfoMapper;

    @Scheduled(cron = "0/1 * * * * *")
    public void expireTask(){
        List<GzgReplenishRecord> records = gzgReplenishRecordMapper.selectByStatus(ReplenishRecordStatusEnum.ORDER_GRAB.getKey());
        for (GzgReplenishRecord record : records){
            long orderGrabTime = record.getGradTime().getTime() + 600000;
            long currentTime = System.currentTimeMillis();
            long overDueTime = orderGrabTime - currentTime;
            if (overDueTime <= 0){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDateTimeStr = sdf.format(new Date());
                log.info("replenishRecordId={},userId={} 补货超时,设置记录状态为补货失效,设置补货状态为待补货, date={}",record.getId(),record.getReplenishUserId(),currentDateTimeStr);
                String replenishOrderId = record.getReplenishOrderId();
                long longId = Long.valueOf(replenishOrderId);
                GzgReplenishOrder order = gzgReplenishOrderMapper.selectById(longId);
                String gzgId = order.getGzgId();
                long longGzgId = Long.valueOf(gzgId);
                log.info("longGzgId={}",longGzgId);
                GzgInfo gzgInfo = gzgInfoMapper.selectByPrimaryKey(longGzgId);
                String checkCacheKey = gzgInfo.getCode()+":"+order.getCabinetNo();
                String replenishCacheKey = gzgInfo.getCode()+":"+order.getCabinetNo()+":"+record.getReplenishUserId();
                redisServer.del(RedisEnum.GZG_REPLENISH_ORDER_LOCK,replenishCacheKey);
                redisServer.del(RedisEnum.GZG_REPLENISH_ORDER_LOCK,checkCacheKey);
                log.info("删除锁定的格子柜:{} 用户锁定:{}",checkCacheKey,replenishCacheKey);
                log.info("expireTask longId={}",longId);
                GzgReplenishOrder gzgReplenishOrder = gzgReplenishOrderMapper.selectById(longId);
                gzgReplenishOrder.setState(GzgReplenishStatusEnum.STAY_REPLENISH.getKey());
                gzgReplenishOrderMapper.updateByPrimaryKeySelective(gzgReplenishOrder);
                record.setState(ReplenishRecordStatusEnum.FAULIRE.getKey());
                record.setInvalidType(ReplenishRecordInvalidTypeEnum.REPLENISHI_OVER_DUE.getKey());
                record.setInvalidTime(new Date());
                gzgReplenishRecordMapper.updateByPrimaryKeySelective(record);
            }
        }
    }
}
