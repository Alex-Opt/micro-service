package com.ly.mt.gzg.b.server.gzgb.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.constant.gzg.*;
import com.ly.mt.core.common.entity.gzg.*;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.core.common.util.*;
import com.ly.mt.gzg.b.server.base.constant.GzgCabinet;
import com.ly.mt.gzg.b.server.base.entity.GzgChannelCode;
import com.ly.mt.gzg.b.server.gzgb.mapper.*;
import com.ly.mt.gzg.b.server.gzgb.service.CommonService;
import com.ly.mt.gzg.b.server.gzgb.service.ReplenishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class ReplenishServiceImpl implements ReplenishService {

    private static final String mctId = "1133210036261097472";
    private static final String appKey = "LEIYAN_GZG";
    private static final String url = "http://iot.open.ayilink.com/iot-client/open/api/auth/login";
    private static final String openDeviceUrl = "http://iot.open.ayilink.com/iot-client/open/api/device/start";
    @Resource
    private CommonService commonService;
    @Resource
    private GzgReplenishOrderMapper gzgReplenishOrderMapper;
    @Resource
    private GzgReplenishRecordMapper gzgReplenishRecordMapper;
    @Resource
    private GzgOrdersServiceMapper gzgOrdersServiceMapper;
    @Resource
    private GzgHotelMapper gzgHotelMapper;
    @Resource
    private GzgHotelUserRelationMapper gzgHotelUserRelationMapper;
    @Resource
    private RedisServer redisServer;
    @Resource
    private GzgChannelCodeMapper gzgChannelCodeMapper;
    @Resource
    private GzgInfoMapper gzgInfoMapper;
    @Resource
    private GzgCabinetMapper gzgCabinetMapper;


    @Override
    public JSONObject replenishList(String json) {
        long start = System.currentTimeMillis();
        log.info("访问replenishList方法的参数:{}",json);
        JSONObject data = JSON.parseObject(json);
        String id = data.getString("userId");
        Map<String, Object> common = commonService.common(Long.parseLong(id));
        log.info("replenishList 返回数据-->{}",common);
        long end = System.currentTimeMillis();
        log.info("execute replenishList cost {}",(end - start));
        return JsonUtil.getSuccessJson(common);
    }


    @Override
    public JSONObject giveUpReplenish(String json){
        log.info("访问giveUpReplenish方法的参数:{}",json);
        JSONObject jsonObject = JSON.parseObject(json);
        Map<String, Object> map = JsonUtil.json2Map(json);
        String userId = jsonObject.getString("userId");
        String gzgCode = jsonObject.getString("gzgCode");
        int cabinetNo = jsonObject.getInteger("cabinetNo");
        String replenishOrderId = jsonObject.getString("replenishOrderId");
        GzgHotelUserRelation hotelUser = gzgHotelUserRelationMapper.selectByUserId(Long.parseLong(userId));
        GzgReplenishRecord gzgReplenishRecord = gzgReplenishRecordMapper.selectByReplenishOrderId(Long.valueOf(replenishOrderId));
        //GzgReplenishRecord gzgReplenishRecord = new GzgReplenishRecord();
        gzgReplenishRecord.setState(ReplenishRecordStatusEnum.FAULIRE.getKey());
        gzgReplenishRecord.setInvalidTime(new Date());
        gzgReplenishRecord.setInvalidType(ReplenishRecordInvalidTypeEnum.REPLENISH_GIVE_UP.getKey());
        gzgReplenishRecordMapper.updateByPrimaryKey(gzgReplenishRecord);
        String checkCacheKey = gzgCode+":"+cabinetNo;
        String replenishCacheKey = gzgCode+":"+cabinetNo+":"+userId;
        redisServer.del(RedisEnum.GZG_REPLENISH_ORDER_LOCK,replenishCacheKey);
        redisServer.del(RedisEnum.GZG_REPLENISH_ORDER_LOCK,checkCacheKey);
        GzgReplenishOrder order = gzgReplenishOrderMapper.selectByPrimaryKey(Long.parseLong(replenishOrderId));
        order.setState(GzgReplenishStatusEnum.STAY_REPLENISH.getKey());
        gzgReplenishOrderMapper.updateByPrimaryKeySelective(order);
        return JsonUtil.getSuccessJson();
    }

    /**
     * 我要补货进入
     * @param json
     * @return
     */
    @Override
    public JSONObject wyReplenish(String json) {
        log.info("访问wyReplenish方法的参数:{}",json);
        JSONObject jsonObject = JSON.parseObject(json);
        String replenishOrderId = jsonObject.getString("replenishOrderId");
        String userId = jsonObject.getString("userId");
        String gzgCode = jsonObject.getString("gzgCode");
        int cabinetNo = jsonObject.getInteger("cabinetNo");
        long longOrderId = Long.valueOf(replenishOrderId);
        GzgReplenishOrder gzgReplenishOrder = gzgReplenishOrderMapper.selectById(longOrderId);
        String cacheKey = gzgCode+":"+cabinetNo;
        //String cacheKey = Constant.ORDER_GRAB_LOCK_KEY+gzgCode+"_"+cabinetNo;
        boolean flag = redisServer.hasKey(RedisEnum.GZG_REPLENISH_ORDER_LOCK, cacheKey);
        //抢单失败
        if (flag){
            return JsonUtil.getErrorJson(ResultCodeEnum.ORDER_GRAB_FAIL);
        }
        //抢单成功,生成抢单记录
        GzgReplenishRecord record = gzgReplenishRecordMapper.selectByReplenishOrderId(Long.parseLong(replenishOrderId));
        if (record == null) {
            GzgReplenishRecord gzgReplenishRecord = new GzgReplenishRecord();
            gzgReplenishRecord.setReplenishUserId(userId);
            gzgReplenishRecord.setInvalidType(ReplenishRecordStatusEnum.ORDER_GRAB.getKey());
            gzgReplenishRecord.setInvalidTime(new Date());
            gzgReplenishRecord.setReplenishOrderId(replenishOrderId);
            gzgReplenishRecord.setGradTime(new Date());
            gzgReplenishRecord.setId(StringUtil.getRandomIntByLength(10));
            gzgReplenishRecord.setState(ReplenishRecordStatusEnum.ORDER_GRAB.getKey());
            gzgReplenishRecordMapper.insertSelective(gzgReplenishRecord);
        }else {
            record.setState(ReplenishRecordStatusEnum.ORDER_GRAB.getKey());
            record.setGradTime(new Date());
            gzgReplenishRecordMapper.updateByPrimaryKeySelective(record);
        }
        //缓存当前补货单补货记录状态
        Map<String,Object> cacheMap = new HashMap<>();
        cacheMap.put("orderGrab", ReplenishProcessStatusEnum.SUCCESS.getKey());
        cacheMap.put("orderGrabTime",new Date().getTime());
        cacheMap.put("lastProgress",1);
        //流程缓存
        String progressKey = gzgCode+":"+cabinetNo+":"+userId;
        redisServer.setEntity(RedisEnum.GZG_REPLENISH_PROGRESS,progressKey,cacheMap);
        String gzgReplenishOrderStr = JSON.toJSONString(gzgReplenishOrder);
        String checkCacheKey = gzgCode+":"+cabinetNo;
        redisServer.set(RedisEnum.GZG_REPLENISH_ORDER_LOCK,checkCacheKey,"true");
        redisServer.expire(RedisEnum.GZG_REPLENISH_ORDER_LOCK,checkCacheKey,600);
        String userCacheKey = gzgCode+":"+cabinetNo+":"+userId;
        redisServer.set(RedisEnum.GZG_REPLENISH_ORDER_LOCK,userCacheKey,"true");
        redisServer.expire(RedisEnum.GZG_REPLENISH_ORDER_LOCK,userCacheKey,600);
        String gzgOrderItmeId = gzgReplenishOrder.getGzgOrderItmeId();
        long orderItemId = Long.valueOf(gzgOrderItmeId);
        //GzgOrderItemVo gzgOrderItemVo = gzgOrdersServiceMapper.selectItemByPrimaryKey(orderItemId);
        long orderGrabTime = new Date().getTime();
        long expireTime = orderGrabTime + (10*60*1000);
        gzgReplenishOrder.setState(GzgReplenishStatusEnum.LOCK.getKey());
        gzgReplenishOrderMapper.updateByPrimaryKeySelective(gzgReplenishOrder);
        return JsonUtil.getSuccessJson(expireTime);
    }

    @Override
    public JSONObject goodsCheck(String json){
        log.info("访问goodsCheck方法的参数:{}",json);
        JSONObject jsonObject = JSON.parseObject(json);
        String goodsBarCodes = jsonObject.getString("goodsBarCode");
        String replenishOrderId = jsonObject.getString("replenishOrderId");
        long orderId = Long.valueOf(replenishOrderId);
        GzgReplenishOrder gzgReplenishOrder1 = gzgReplenishOrderMapper.selectById(orderId);
        String gzgOrderItmeId = gzgReplenishOrder1.getGzgOrderItmeId();
        long longItemId = Long.valueOf(gzgOrderItmeId);
        GzgOrderItemVo gzgOrderItemVo = gzgOrdersServiceMapper.selectItemByPrimaryKey(longItemId);
        String skuCode = gzgOrderItemVo.getGySkuCode();
        log.info("gzgOrderItemVo skuCode={}",skuCode);
        Long userId = jsonObject.getLongValue("userId");
        String[] split = goodsBarCodes.split(",");
        boolean flag = true;
        for (String goodsBarCode : split){
            long code = Long.valueOf(goodsBarCode);
            GzgChannelCode gzgChannelCode = gzgChannelCodeMapper.selectByBarCode(code);
            if (gzgChannelCode == null){
                flag = false;
            }/*else {
                if (!StringUtils.equals(skuCode,gzgChannelCode.getSixNineCode())){
                    flag=false;
                    return JsonUtil.getErrorJson(ResultCodeEnum.GZG_GOODS_CHECK_BARCODE_TO_SKUID_ERROR);
                }
            }*/
        }
        long longOrderId = Long.valueOf(replenishOrderId);
        GzgReplenishOrder gzgReplenishOrder = gzgReplenishOrderMapper.selectById(longOrderId);
        String gzgId = gzgReplenishOrder.getGzgId();
        long longGzgId = Long.valueOf(gzgId);
        GzgInfo gzgInfo = gzgInfoMapper.selectByPrimaryKey(longGzgId);
        String progressKey = gzgInfo.getCode()+":"+gzgReplenishOrder.getCabinetNo()+":"+userId;
        String cacheEntity = redisServer.get(RedisEnum.GZG_REPLENISH_PROGRESS, progressKey);
        Map<String,Object> cacheMap = JSON.parseObject(cacheEntity,new TypeReference<Map<String,Object>>(){});
        if (!flag){
            cacheMap.put("goodsCheck","fail");
            cacheMap.put("goodsCheckTime",new Date().getTime());
            cacheMap.put("lastProgress",2);
            redisServer.setEntity(RedisEnum.GZG_REPLENISH_PROGRESS,progressKey,cacheMap);
            return JsonUtil.getErrorJson(ResultCodeEnum.GZG_REPLENISH_GOODS_NOT_EXISTS);
        }
        log.info("progressKey={}",progressKey);
        String uId = String.valueOf(userId);
        log.info("商品校验用户id={}",uId);
        String s = redisServer.get(RedisEnum.GZG_REPLENISH_PROGRESS,progressKey);
        cacheMap.put("goodsCheck","success");
        cacheMap.put("goodsCheckTime",new Date().getTime());
        cacheMap.put("lastProgress",2);
        //GzgReplenishOrder gzgReplenishOrder = gzgReplenishOrderMapper.selectByPrimaryKey(replenishOrderId);
        GzgReplenishRecord record = gzgReplenishRecordMapper.selectByReplenishOrderId(longOrderId);
        long expire = record.getGradTime().getTime()+600000;//.getCreateTime().getTime() + (10 * 60 * 1000);
        redisServer.setEntity(RedisEnum.GZG_REPLENISH_PROGRESS, progressKey,cacheMap);
        cacheMap.put("expire",expire);
        JSONObject result = JSON.parseObject(JSON.toJSONString(cacheMap));
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject cabinetCheck(String json){
        log.info("访问cabinetCheck方法的参数:{}",json);
        JSONObject object = JSON.parseObject(json);
        String gzgCode = object.getString("gzgCode");
        String userId = object.getString("userId");
        int cabinetNo = object.getInteger("cabinetNo");
        Map<String,Object> openParam = new HashMap<>();
        openParam.put("mctId","1133210036261097472");
        JSONArray array = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("cabinetNo",cabinetNo);
        obj.put("goodsCount",1);
        obj.put("goodsId","1014683550252797952");
        obj.put("goodsPrice",new Random().nextInt(200));
        obj.put("goodsName","雾化弹");
        array.add(obj);
        openParam.put("goodsInfo",array);
        openParam.put("deviceImei",gzgCode);
        openParam.put("money", new Random().nextInt(200));
        openParam.put("orderId",StringUtil.getRandomIntByLength(15));
        openParam.put("payType","1");
        String md5 = null;
        try {
            md5 = MD5Util.md5(mctId+"LEIYAN_GZG").toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String strParam = "mctId="+mctId+"&signature="+md5;
        log.info("获取格子柜信息 ： {}"+strParam);
        JSONObject o = null;
        String progressCacheKey = gzgCode+":"+cabinetNo+":"+userId;
        if (StringUtils.equals(gzgCode,gzgCode)){
            log.info("补货打开格子柜-->{}",gzgCode);
            String s1 = redisServer.get(RedisEnum.GZG_REPLENISH_PROGRESS, progressCacheKey);
            o = JSON.parseObject(s1);
            o.put("cabinetCheck","success");
            o.put("cabinetCheckTime",new Date().getTime());
            o.put("lastProgress",3);
            redisServer.setEntity(RedisEnum.GZG_REPLENISH_PROGRESS,progressCacheKey,o);
            JSONObject httpPost = HttpClientUtil.httpPost(url, strParam);
            String content = httpPost.getJSONObject("data").getString("content");
            log.info("调用亿联登录服务获取token={}",content);
            Map<String,String> header = new HashMap<>();
            header.put("token",content);
            Map<String,String> parameter = JSON.parseObject(JSON.toJSONString(openParam),new TypeReference<Map<String,String>>(){});
            JSONObject jsonObject = HttpClientUtil.httpPostForm(openDeviceUrl,parameter,header,"UTF-8");
            log.info("打开格子柜返回信息:{}",jsonObject);
            return JsonUtil.getSuccessJson(o);
        }else {
            o.put("cabinetCheck","fail");
            o.put("cabinetCheckTime",new Date().getTime());
            o.put("lastProgress",3);
            redisServer.setEntity(RedisEnum.GZG_REPLENISH_PROGRESS,progressCacheKey,o);
            return JsonUtil.getErrorJson(ResultCodeEnum.GZG_CHECK_FAILURE);
        }
    }

    @Override
    public JSONObject commitAudit(String json) {
        log.info("访问commitAudit方法的参数:{}",json);
        JSONObject jsonObject = JSON.parseObject(json);
        String userId = jsonObject.getString("userId");
        String replenishOrderId = jsonObject.getString("replenishOrderId");
        String picture = jsonObject.getString("checkPicture");
        GzgReplenishRecord gzgReplenishRecord1 = gzgReplenishRecordMapper.selectByReplenishOrderId(Long.valueOf(replenishOrderId));
        gzgReplenishRecord1.setState(ReplenishRecordStatusEnum.COMMIT.getKey());
        gzgReplenishRecord1.setSubmitAuditTime(new Date());
        gzgReplenishRecord1.setAuditPicture(picture);
        long longOrderId = Long.valueOf(replenishOrderId);
        GzgReplenishOrder gzgReplenishOrder = gzgReplenishOrderMapper.selectById(longOrderId);
        gzgReplenishOrder.setState(GzgReplenishStatusEnum.FINISHED.getKey());
        gzgReplenishOrderMapper.updateByPrimaryKey(gzgReplenishOrder);
        String gzgId = gzgReplenishOrder.getGzgId();
        long longGzgId = Long.valueOf(gzgId);
        GzgInfo gzgInfo = gzgInfoMapper.selectByPrimaryKey(longGzgId);
        String rewardCacheKey = gzgInfo.getId()+":"+gzgReplenishOrder.getCabinetNo()+":reward:"+userId;
        String s1 = redisServer.get(RedisEnum.GZG_REPLENISH_ORDER_LOCK, rewardCacheKey);
        gzgReplenishRecord1.setAmount(new BigDecimal(Double.valueOf(s1)));
        gzgReplenishRecordMapper.updateByPrimaryKey(gzgReplenishRecord1);
        String gzgCode = gzgInfo.getCode();
        String cabinetNo = gzgReplenishOrder.getCabinetNo();
        Integer intNo = Integer.valueOf(cabinetNo);
        GzgCabinet gzgCabinet = gzgCabinetMapper.selectByCodeAndCabinetCode(gzgCode, intNo);
        gzgCabinet.setStatus(1);
        gzgCabinet.setGmtModify(new Date());
        gzgCabinetMapper.updateByPrimaryKey(gzgCabinet);
        String progressCacheKey = gzgCode+":"+cabinetNo+":"+userId;
        String s = redisServer.get(RedisEnum.GZG_REPLENISH_PROGRESS, progressCacheKey);
        JSONObject progress = JSON.parseObject(s);
        progress.put("auditSuccess","true");
        progress.put("auditSuccessTime",(gzgReplenishRecord1.getSubmitAuditTime().getTime() + (3 * 24 * 60 * 60 +1000)));
        progress.put("lastProgress",5);
        String cacheCabinetNo = gzgCode+":"+cabinetNo;
        redisServer.del(RedisEnum.GZG_REPLENISH_ORDER_LOCK,cacheCabinetNo);
        String lockCacheKey = gzgCode+":"+cabinetNo+":"+userId;
        redisServer.del(RedisEnum.GZG_REPLENISH_ORDER_LOCK,lockCacheKey);
        return JsonUtil.getSuccessJson();
    }

    @Override
    public JSONObject replenishReward(String json) {
        log.info("访问replenishReward方法的参数:{}",json);
        JSONObject jsonObject = JSON.parseObject(json);
        String userId = jsonObject.getString("userId");
        List<GzgReplenishRecord> gzgReplenishRecords = gzgReplenishRecordMapper.selectByUserId(Long.parseLong(userId));
        double reward = 0;
        int replenishFinishCount = 0;
        for (GzgReplenishRecord record : gzgReplenishRecords){
            int status = record.getState();
            reward += record.getAmount().doubleValue();
            if (ReplenishRecordStatusEnum.CHECK_PASS.getKey() == status){
                replenishFinishCount++;
            }
        }
        JSONObject result = new JSONObject();
        result.put("reward",reward);
        result.put("cumulativeReward",reward);
        result.put("replenishCount",replenishFinishCount);
        result.put("tixian",0);
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject replenishDetail(String json){
        log.info("访问replenishDetail方法的参数:{}",json);
        JSONArray result = new JSONArray();
        JSONObject jsonObject = JSON.parseObject(json);
        String userId = jsonObject.getString("userId");
        String replenishOrderId = jsonObject.getString("replenishOrderId");
        GzgReplenishRecord record = gzgReplenishRecordMapper.selectByReplenishOrderId(Long.parseLong(replenishOrderId));
        int status = record.getState();
        log.info("订单记录状态是{}",status);
        GzgReplenishOrder gzgReplenishOrder = gzgReplenishOrderMapper.selectByPrimaryKey(Long.parseLong(replenishOrderId));
        long orderItemId = Long.parseLong(gzgReplenishOrder.getGzgOrderItmeId());
        GzgOrderItemVo orderItemVo = gzgOrdersServiceMapper.selectItemByPrimaryKey(orderItemId);
        String skuName = orderItemVo.getSkuName();
        long skuId = Long.valueOf(orderItemVo.getSkuId());
        Map<String,String> pictureMap = gzgOrdersServiceMapper.selectPictureBySkuId(skuId);
        String smallPic = null;
        if (pictureMap != null){
            smallPic = pictureMap.get("small_pic_url");
        }
        long code = Long.valueOf(orderItemVo.getCode());
        int cabinetNo = Integer.valueOf(orderItemVo.getCabinetNo());
        GzgInfo gzgInfo = gzgInfoMapper.selectByGzgCode(code);
        long hotelId = Long.parseLong(gzgInfo.getHotelId());
        GzgHotel hotel = gzgHotelMapper.selectByPrimaryKey(hotelId);
        log.info("通过酒店id获取的酒店信息是:{}",JSON.toJSONString(hotel));
        JSONObject o = new JSONObject();
        o.put("goodsName",skuName);
        o.put("gzgCode",code);
        o.put("cabinetNo",cabinetNo);
        o.put("address",hotel.getName());
        o.put("detailGoodsPic",smallPic);
        String progressKey = gzgInfo.getCode()+":"+cabinetNo+":"+userId;
        String s = redisServer.get(RedisEnum.GZG_REPLENISH_PROGRESS, progressKey);
        JSONObject obj = JSON.parseObject(s);
        o.put("progress",obj);
        String rewardCacheKey = gzgReplenishOrder.getGzgId()+":"+cabinetNo+":reward:"+userId;
        String reward = redisServer.get(RedisEnum.GZG_REPLENISH_ORDER_LOCK,rewardCacheKey);
        o.put("reward",reward);
        return JsonUtil.getSuccessJson(o);
    }

    @Override
    public JSONObject upload(String json) {
        log.info("上传图片dataParam:{}",json);
        JSONObject jsonObject = JSON.parseObject(json);
        long userId = jsonObject.getLong("userId");
        String gzgCode = jsonObject.getString("gzgCode");
        String cabinetNo = jsonObject.getString("cabinetNo");
        String progressCacheKey = gzgCode+":"+cabinetNo+":"+userId;
        String s = redisServer.get(RedisEnum.GZG_REPLENISH_PROGRESS, progressCacheKey);
        Map<String,Object> cacheMap = JsonUtil.json2Map(s);
        cacheMap.put("upload","success");
        cacheMap.put("uploadTime",new Date().getTime());
        cacheMap.put("lastProgress",4);
        redisServer.setEntity(RedisEnum.GZG_REPLENISH_PROGRESS,progressCacheKey,cacheMap);
        return JsonUtil.getSuccessJson(cacheMap);
    }

    @Override
    public JSONObject ruleInfo(String json) {
        log.info("replenishServiceImpl ruleInfo jsonParam={}",json);
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject result = new JSONObject();
        long userId = jsonObject.getLong("userId");
        String replenishOrderId = jsonObject.getString("replenishOrderId");
        GzgReplenishRecord gzgReplenishRecord = gzgReplenishRecordMapper.selectByReplenishOrderId(Long.parseLong(replenishOrderId));
        log.info("ruleInfo gzgReplenishRecord={}",gzgReplenishRecord);
        GzgReplenishOrder order = gzgReplenishOrderMapper.selectByPrimaryKey(Long.parseLong(replenishOrderId));
        GzgInfo gzgInfo = gzgInfoMapper.selectByPrimaryKey(Long.parseLong(order.getGzgId()));
        String cacheReward = gzgInfo.getCode()+":"+order.getCabinetNo()+":reward:"+userId;
        String s = redisServer.get(RedisEnum.GZG_REPLENISH_ORDER_LOCK, cacheReward);
        int state = gzgReplenishRecord.getState();
        if (GzgReplenishStatusEnum.STAY_REPLENISH.getKey() < 2){
            List<GzgReplenishRecord> gzgReplenishRecords = gzgReplenishRecordMapper.selectByUserIdAndStatus(userId, ReplenishRecordStatusEnum.RECEIVE_REWARD.getKey());
            long rewards = 0;
            for (GzgReplenishRecord record : gzgReplenishRecords){
                rewards+=record.getAmount().longValue();
            }
            result.put("orderCreateTime",order.getCreateTime());
            result.put("reward",rewards);
            return JsonUtil.getSuccessJson(result);
        }
        if (ReplenishRecordStatusEnum.COMMIT.getKey() == state){
            result.put("commitTime",gzgReplenishRecord.getSubmitAuditTime());
            result.put("orderCreateTime",order.getCreateTime());
            result.put("reward",gzgReplenishRecord.getAmount());
        }else{
            if (ReplenishRecordStatusEnum.RECEIVE_REWARD.getKey() == state) {
                result.put("receiveAccountTime", gzgReplenishRecord.getInvalidTime());
                result.put("orderCreateTime", order.getCreateTime());
                result.put("reward", gzgReplenishRecord.getAmount());
            }
        }
        return JsonUtil.getSuccessJson(result);
    }
}
