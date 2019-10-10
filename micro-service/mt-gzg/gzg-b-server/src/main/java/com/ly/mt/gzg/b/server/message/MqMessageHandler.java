package com.ly.mt.gzg.b.server.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.template.style.Style0;
import com.ly.mt.core.common.constant.IdEnum;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.constant.gzg.GzgReplenishStatusEnum;
import com.ly.mt.core.common.entity.gzg.*;
import com.ly.mt.core.common.entity.user.User;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.core.common.util.IdUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.gzg.b.server.gzgb.mapper.*;
import com.ly.mt.gzg.b.server.gzgb.service.impl.GtPushMessageService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class MqMessageHandler {
    @Resource
    private GzgReplenishOrderMapper gzgReplenishOrderMapper;
    @Resource
    private GzgInfoMapper gzgInfoMapper;
    @Resource
    private GzgHotelStockMapper gzgHotelStockMapper;
    @Autowired
    private GzgOrdersServiceMapper gzgOrdersServiceMapper;
    @Resource
    private GzgHotelMapper gzgHotelMapper;
    @Resource
    private GzgHotelUserRelationMapper gzgHotelUserRelationMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private GtPushMessageService gtPushMessageService;
    @Resource
    private RedisServer redisServer;

    public void msgHandler(String msg){
        log.info("msgHandler 收到的消息体是:{}",msg);
        JSONObject json = JSON.parseObject(msg);
        String gzgOrderId = json.getString("gzgOrderId");
        String gzgOrderItemId = json.getString("gzgOrderItemId");
        String gzgCode = json.getString("gzgCode");
        GzgInfo gzgInfo = gzgInfoMapper.selectByGzgCode(Long.parseLong(gzgCode));
        String gzgId = gzgInfo.getId();
        String cabinetNo = json.getString("cabineNo");
        log.info("cabinetNo");
        List<GzgReplenishOrder> gzgReplenishOrders = gzgReplenishOrderMapper.selectByOrderId(Long.valueOf(gzgOrderId));
        GzgOrderItemVo orderItemVo = gzgOrdersServiceMapper.selectItemByPrimaryKey(Long.valueOf(gzgOrderItemId));
        log.info("通过orderItemId获取GzgOrderItemVo={}",orderItemVo);
        try {
            log.info("通过订单itemId获取订单OrderItem信息，result={}",JSON.toJSONString(orderItemVo));
            String hotelId = gzgInfo.getHotelId();
            String skuId = orderItemVo.getSkuId();
            if (gzgReplenishOrders == null || gzgReplenishOrders.size() == 0) {
                log.info("收到c端订单消息,插入补货订单");
                GzgReplenishOrder gzgReplenishOrder = new GzgReplenishOrder();
                gzgReplenishOrder.setHotelId(hotelId);
                gzgReplenishOrder.setCabinetNo(orderItemVo.getCabinetNo());
                gzgReplenishOrder.setGzgId(gzgId);
                gzgReplenishOrder.setGzgOrderItmeId(gzgOrderItemId);
                gzgReplenishOrder.setGzgOrderId(gzgOrderId);
                String id = StringUtil.getRandomIntByLength(10);
                gzgReplenishOrder.setId(id);
                gzgReplenishOrder.setState(GzgReplenishStatusEnum.STAY_REPLENISH.getKey());
                gzgReplenishOrder.setCreateTime(new Date());
                log.info("msg monitor the gzgReplenishOrder data is {}", JSON.toJSONString(gzgReplenishOrder));
                gzgReplenishOrderMapper.insert(gzgReplenishOrder);
            }
            log.info("查询库存hotelId={},skuId={}", hotelId, skuId);
            try {
                GzgHotelStock stock = gzgHotelStockMapper.findByHotelIdAndSkuId(Long.valueOf(hotelId), Long.valueOf(skuId));
                log.info("库存stock={}", JSON.toJSONString(stock));
                if (stock != null) {
                    Integer sellTotalNum = stock.getSellOutNum() + 1;
                    Integer totalNum = stock.getTotalNum() - 1;
                    Integer surplusNum = stock.getSurplusNum() - 1;
                    stock.setTotalNum(totalNum);
                    stock.setSellOutNum(sellTotalNum);
                    stock.setSurplusNum(totalNum);
                    log.info("c to b message hotel stock={}", JSON.toJSONString(stock));
                    gzgHotelStockMapper.updateByPrimaryKey(stock);
                }
            }catch (Exception e){
                log.info("收到C端补货订单处理是出现异常，异常信息是:",e);
            }
        }catch (Exception e){
            log.info("收到C端补货订单处理时发生异常，异常信息={}",e);
        }


        try {
            log.info("开始推送消息到app");
            pushMsg(gzgCode);
            log.info("app消息推送成功");
        }catch (Exception e){
            log.error("push message fail,the message is {}",e);
        }

    }

    /**
     * 推送订单到每个绑定该格子柜的补货人员
     * @param gzgCode
     */
    private void pushMsg(String gzgCode) throws Exception{
        log.info("pushMsg parameter={}",gzgCode);
        String hotelId = gzgInfoMapper.selectByGzgCode(Long.parseLong(gzgCode)).getHotelId();
        GzgHotel gzgHotel = gzgHotelMapper.selectByPrimaryKey(Long.parseLong(hotelId));
        if (gzgHotel == null){
            log.info("gzgCode {} 对应的酒店id {} 在hotel表中没有找到对应的数据",gzgCode,hotelId);
        }
        List<Long> userIds = gzgHotelUserRelationMapper.selectByHotelIdAndType(Long.parseLong(hotelId), 2);
        List<String> clientIds = new ArrayList<>();
        userIds.forEach(id -> {
            User user = userMapper.selectByPrimaryKey(id);
            String phoneNo = user.getMobile();
            String clientId = user.getClientId();
            if (StringUtils.isNotBlank(clientId)) {
                clientIds.add(clientId);
            }
        });
        if (clientIds.size() > 0) {
            Style0 style = getStyle(7);
            String content = "系统又有一笔新的补货订单，赶紧去抢单吧~";
            gtPushMessageService.PushList(clientIds, style, content);
        }

    }

    private Style0 getStyle(int reward){
        String title = "B端格子柜";
        String text = "有新的补货订单，奖励" + reward + "元，快来抢单吧";
        String logo = "https://mall.motivape.cn";
        String logoUrl = "https://moti-dev.oss-cn-beijing.aliyuncs.com/image/user/goods/2/single/112492576846/112492581718.jpg";
        Style0 style0 = gtPushMessageService.getStyle0(title, text, logo, logoUrl);
        return style0;
    }
}
