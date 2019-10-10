package com.ly.mt.gzg.b.server.gzgb.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.constant.gzg.GzgReplenishStatusEnum;
import com.ly.mt.core.common.constant.gzg.ReplenishRecordInvalidTypeEnum;
import com.ly.mt.core.common.constant.gzg.ReplenishRecordStatusEnum;
import com.ly.mt.core.common.entity.gzg.*;
import com.ly.mt.core.common.entity.user.User;
import com.ly.mt.core.common.response.gzgb.UserReplenishListInfoResp;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.core.common.util.RedisUtil;
import com.ly.mt.gzg.b.server.base.entity.ReplenishInfoRespVO;
import com.ly.mt.gzg.b.server.base.entity.ReplenishListRespVO;
import com.ly.mt.gzg.b.server.base.entity.response.ResplenishRespVO;
import com.ly.mt.gzg.b.server.gzgb.mapper.*;
import com.ly.mt.gzg.b.server.gzgb.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GzgReplenishRecordMapper gzgReplenishRecordMapper;
    @Autowired
    private GzgBUserRelationMapper gzgBUserRelationMapper;
    @Autowired
    private GzgReplenishOrderMapper gzgReplenishOrderMapper;
    @Autowired
    private GzgOrdersServiceMapper gzgOrdersServiceMapper;
    @Autowired
    private GzgHotelMapper gzgHotelMapper;
    @Autowired
    private GzgInfoMapper gzgInfoMapper;
    @Autowired
    private RedisServer redisServer;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Map<String,Object> common(long userId){
        log.info("公共service common 参数userId : {}",userId);
        Map<String,Object> result = new HashMap<>();
        ReplenishListRespVO replenishListRespVO = new ReplenishListRespVO();
        User user = userMapper.selectByPrimaryKey(userId);
        //用户信息
        UserReplenishListInfoResp userReplenishListInfoResp = new UserReplenishListInfoResp();
        userReplenishListInfoResp.setHeader(user.getAvatarPicSrc());
        userReplenishListInfoResp.setNickName(user.getNickName());
        result.put("userInfo",userReplenishListInfoResp);
        log.info("用户信息:{}",result);
        //补货次数和奖励信息
        List<GzgReplenishRecord> gzgReplenishRecords = gzgReplenishRecordMapper.selectByUserId(userId);
        int replenishRecordCounts = 0;
        BigDecimal reward = new BigDecimal(0);
        long expire = 0l;
        long orderGrabTime = 0l;
        ResplenishRespVO resplenishRespVO = new ResplenishRespVO();
        for (GzgReplenishRecord o : gzgReplenishRecords){
            Integer state = o.getState();
            if (ReplenishRecordStatusEnum.RECEIVE_REWARD.getKey() == state){
                replenishRecordCounts++;
                reward.add(o.getAmount());
            }
            String replenishOrderId = o.getReplenishOrderId();
            long longReplenishId = Long.valueOf(replenishOrderId);
            GzgReplenishOrder gzgReplenishOrder = gzgReplenishOrderMapper.selectById(longReplenishId);
            int replenishOrderStatus = gzgReplenishOrder.getState();
            ReplenishInfoRespVO replenishInfoRespVO = respHandler(gzgReplenishOrder,userId);
            JSONObject object = JSON.parseObject(JSON.toJSONString(replenishInfoRespVO));
            object.put("orderGrabTime",o.getGradTime().getTime());
            //抢单成功并锁定补货订单(待补货)
            /*if (ReplenishRecordStatusEnum.ORDER_GRAB.getKey() == state) {

                //收到奖励
            }*/if (GzgReplenishStatusEnum.LOCK.getKey() == replenishOrderStatus && ReplenishRecordStatusEnum.COMMIT.getKey() > state) {
                long orderCreateTime = o.getGradTime().getTime();
                expire = orderCreateTime + 600000;
                replenishListRespVO.getStayReplenish().add(replenishInfoRespVO);
                orderGrabTime = o.getGradTime().getTime();
            }else if (ReplenishRecordStatusEnum.RECEIVE_REWARD.getKey() == state){
                JSONObject receiveReward = JSON.parseObject(JSON.toJSONString(replenishInfoRespVO));
                receiveReward.put("receiveRewardTime",o.getSubmitAuditTime().getTime());
                resplenishRespVO.getReceiveAmount().add(receiveReward);
                //补货失效
            }else if (ReplenishRecordStatusEnum.FAULIRE.getKey() == state){
                JSONObject faulired = JSON.parseObject(JSON.toJSONString(replenishInfoRespVO));
                faulired.put("failuredTime",o.getInvalidTime().getTime());
                faulired.put("failuredReson", ReplenishRecordInvalidTypeEnum.getValue(o.getInvalidType()));
                resplenishRespVO.getFailured().add(faulired);
            }else{
                //待审核
                if (ReplenishRecordStatusEnum.COMMIT.getKey() == state){
                    JSONObject stayAuditObj = JSON.parseObject(JSON.toJSONString(replenishInfoRespVO));
                    stayAuditObj.put("commitStayAuditTime",o.getSubmitAuditTime().getTime());
                    resplenishRespVO.getStayAudit().add(stayAuditObj);
                }
            }
        }
        //补货完成
        result.put("replenishFinish",replenishRecordCounts);
        //累计奖励
        result.put("rewardAmount",reward);
        log.info("common 数据 : {}",result);
        //我要补货列表
        List<GzgBUserRelation> gzgBUserRelations = gzgBUserRelationMapper.selectByUserId(userId);
        log.info("用户格子柜关系数据个数:{}",gzgBUserRelations.size());
        int wyReplenishCount = 0;
        for (GzgBUserRelation r : gzgBUserRelations){
            log.info("userReplation 是否为空:{}",(r==null));
            String gzgId1 = r.getGzgId();
            long longGzgId = Long.valueOf(gzgId1);
            List<GzgReplenishOrder> gzgReplenishOrders = gzgReplenishOrderMapper.selectByGzgCode(longGzgId);
            log.info("格子柜订单-->",JSON.toJSONString(gzgReplenishOrders));
            for (GzgReplenishOrder o : gzgReplenishOrders){
                int state = o.getState();
                if (GzgReplenishStatusEnum.STAY_REPLENISH.getKey() == state){
                    ReplenishInfoRespVO replenishInfoRespVO = respHandler(o,userId);
                    replenishListRespVO.getWyReplenish().add(replenishInfoRespVO);
                }
            }
        }
        //我要补货数量
        if (replenishListRespVO.getStayReplenish().size() == 0) {
            result.put("wyReplenishCount", replenishListRespVO.getWyReplenish().size());
            //我要补货列表
            result.put("wyReplenishList", replenishListRespVO.getWyReplenish());
        }else {
            result.put("wyReplenishCount",0);
            result.put("wyReplenishList",new ArrayList());
        }
        if (replenishListRespVO.getStayReplenish().size() == 0){
            result.put("stayReplenishList",replenishListRespVO.getStayReplenish());
        }else {
            ReplenishInfoRespVO replenishInfoRespVO = replenishListRespVO.getStayReplenish().get(0);
            JSONObject editStayReplenish = JSON.parseObject(JSON.toJSONString(replenishInfoRespVO));
            editStayReplenish.put("expire", expire);
            editStayReplenish.put("orderGrabTime",orderGrabTime);
            //待补货列表
            JSONArray array = new JSONArray();
            array.add(editStayReplenish);
            result.put("stayReplenishList", array);
        }
        //待审核
        result.put("waitConfirm",replenishListRespVO.getStayAudit().size());
        //补货失效
        result.put("failure",replenishListRespVO.getFailured().size());
        //收到奖励
        result.put("receiveAccount",replenishListRespVO.getReceivedAccount().size());
        //抢单成功
        result.put("grabCount",replenishListRespVO.getStayReplenish().size());
        //待审核列表
        result.put("stayAuditList",resplenishRespVO.getStayAudit());
        //领取奖励列表
        result.put("receiveAccountList",resplenishRespVO.getReceiveAmount());
        //补货失效列表
        result.put("failureList",resplenishRespVO.getFailured());
        log.info("结果数据是:{}",result);
        return result;
    }

    private ReplenishInfoRespVO respHandler(GzgReplenishOrder o,long userId){
        log.info("respHandler gzgReplenishOrder o={}",JSON.toJSONString(o));
        String gzgId = o.getGzgId();
        log.info("respHandler gzgId={}",gzgId);
        String replenishId = o.getId();
        log.info("respHandler replenishId={}",replenishId);
        long replenishLongId = Long.valueOf(replenishId);
        ReplenishInfoRespVO replenishInfoRespVO = new ReplenishInfoRespVO();
        GzgInfo gzgInfo = gzgInfoMapper.selectByPrimaryKey(Long.parseLong(gzgId));
        GzgHotel hotel = gzgHotelMapper.selectByPrimaryKey(Long.parseLong(gzgInfo.getHotelId()));
        String name = hotel.getName();
        GzgOrderItemVo gzgOrderItemVo = gzgOrdersServiceMapper.selectItemByPrimaryKey(Long.parseLong(o.getGzgOrderItmeId()));
        log.info("respHandler common gzgOrderItemVo={}",JSON.toJSONString(gzgOrderItemVo));
        String goodsName = gzgOrderItemVo.getSkuName();
        long skuId = Long.valueOf(gzgOrderItemVo.getSkuId());
        Map<String,String> pictureMap = gzgOrdersServiceMapper.selectPictureBySkuId(skuId);
        String smallPic = null;
        if (pictureMap != null){
            smallPic = pictureMap.get("small_pic_url");
        }
        log.info("commonServiceImpl small_sku_pic={}",smallPic);
        int cabinetNo = Integer.valueOf(gzgOrderItemVo.getCabinetNo());
        replenishInfoRespVO.setAddress(name);
        replenishInfoRespVO.setCabinetNo(cabinetNo);
        replenishInfoRespVO.setGoodsName(goodsName);
        replenishInfoRespVO.setReplenishOrderId(String.valueOf(replenishLongId));
        String code = gzgInfo.getCode();
        long longCode = Long.valueOf(code);
        replenishInfoRespVO.setGzgCode(String.valueOf(longCode));
        String replenishOrderId = o.getId();
        GzgReplenishRecord record = gzgReplenishRecordMapper.selectByReplenishOrderId(Long.parseLong(replenishOrderId));
        //创建时间
        double reward = 0.0d;
        long creatTime = o.getCreateTime().getTime() / 1000;
        long currentTime = System.currentTimeMillis() / 1000;
        long minutes = (currentTime - creatTime) / 60;
        //获取奖励数
        if (minutes >= 0 && minutes <= 10) {
            reward = 7;
        } else if (minutes > 10 && minutes <= 30) {
            reward = 5;
        } else if (minutes > 30 && minutes <= 60) {
            reward = 3;
        } else {
            reward = 1;
        }

        if (record == null) {
            replenishInfoRespVO.setReward(reward);
        }else {
            if (record.getAmount() != null) {
                replenishInfoRespVO.setReward(record.getAmount().doubleValue());
            }else {
                replenishInfoRespVO.setReward(reward);
            }
        }

        String gzgOrderId = o.getGzgOrderId();
        long longOrderId = Long.valueOf(gzgOrderId);
        replenishInfoRespVO.setGzgOrderId(gzgOrderId);
        replenishInfoRespVO.setDetailGoodsPic(smallPic);
        log.info("公共common返回补货列表是:{}",JSON.toJSONString(replenishInfoRespVO));
        //缓存奖励
        String id = o.getId();
        //long replenishOrderId = Long.valueOf(id);
        String rewardCacheKey = o.getGzgId()+":"+cabinetNo+":reward:"+userId;
        String rewardStr = String.valueOf(reward);
        redisServer.set(RedisEnum.GZG_REPLENISH_ORDER_LOCK,rewardCacheKey,rewardStr);
        return replenishInfoRespVO;
    }
}
