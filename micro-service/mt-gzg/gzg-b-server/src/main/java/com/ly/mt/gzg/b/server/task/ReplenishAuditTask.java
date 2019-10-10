package com.ly.mt.gzg.b.server.task;

import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.constant.gzg.GzgReplenishStatusEnum;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class ReplenishAuditTask {

    @Autowired
    private GzgReplenishRecordMapper gzgReplenishRecordMapper;
    @Autowired
    private GzgReplenishOrderMapper gzgReplenishOrderMapper;
    @Autowired
    private GzgInfoMapper gzgInfoMapper;
    @Autowired
    private RedisServer redisServer;

    @Scheduled(cron = "0 0 8 * * *")
    public void auditTask(){
        log.info("audit task execute at {}",new Date());
        List<GzgReplenishRecord> gzgReplenishRecords = gzgReplenishRecordMapper.selectByStatus(ReplenishRecordStatusEnum.COMMIT.getKey());
        for (GzgReplenishRecord record : gzgReplenishRecords){
            String replenishOrderId = record.getReplenishOrderId();
            long auditSubmitTime = record.getSubmitAuditTime().getTime()/1000;
            long currentTime = System.currentTimeMillis()/1000;
            long days = (currentTime - auditSubmitTime)/3600/24;
            if (days >= 2){
                log.info("奖励到账");
                record.setState(ReplenishRecordStatusEnum.RECEIVE_REWARD.getKey());
                GzgReplenishOrder order = gzgReplenishOrderMapper.selectByPrimaryKey(Long.parseLong(replenishOrderId));
                String rewardCacheKey = order.getGzgId()+":"+order.getCabinetNo()+":reward:"+record.getReplenishUserId();
                String s = redisServer.get(RedisEnum.GZG_REPLENISH_ORDER_LOCK, rewardCacheKey);
                record.setAmount(new BigDecimal(Double.parseDouble(s)));
                record.setArrivalAccountTime(new Date());
                gzgReplenishRecordMapper.updateByPrimaryKeySelective(record);
                GzgReplenishOrder gzgReplenishOrder = gzgReplenishOrderMapper.selectById(Long.parseLong(replenishOrderId));
                gzgReplenishOrder.setState(GzgReplenishStatusEnum.FINISHED.getKey());
                gzgReplenishOrderMapper.updateByPrimaryKey(gzgReplenishOrder);
            }
        }
    }
}
