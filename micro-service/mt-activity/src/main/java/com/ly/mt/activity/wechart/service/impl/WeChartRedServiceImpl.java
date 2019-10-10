package com.ly.mt.activity.wechart.service.impl;

import com.ly.mt.activity.base.service.impl.BaseServiceImpl;
import com.ly.mt.activity.wechart.service.WeChartRedService;
import com.ly.mt.activity.wechart.vo.WeChartSendRedVo;
import com.ly.mt.activity.wechart.vo.WeChartRedStatusVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.activity.entity.ActivitySecurityCode;
import com.ly.mt.core.wechat.service.RedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_RANDOM;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.wechat.dict.ProductType.PRODUCT_1;

/**
 * 现金红包
 *
 * @author taoye
 */
@Service
public class WeChartRedServiceImpl extends BaseServiceImpl implements WeChartRedService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WeChartRedServiceImpl.class);
    private final static String PROD_PROFILE = "prod";
    @Value("${spring.profiles.active}")
    private String profile;
    @Resource
    private RedService redService;

    @Override
    public ResponseJson sendRed(String openId, String securityCode) {
        ActivitySecurityCode activitySecurityCode = activitySecurityCodeDao.getActivitySecurityCodeBySecurityCodeFromRedis(securityCode);
        if (StringUtil.isNotEmpty(activitySecurityCode.getSecurityCode())) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "请勿盗刷,否则您将被追究法律责任!");
        }
        String orderNo = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM);
        WeChartSendRedVo vo = new WeChartSendRedVo();
        vo.setOrderNo(orderNo);
        Random random = new Random();
        int r = 0;
        // 除生产环境外发放红包金额为1分
        if (profile.equals(PROD_PROFILE)) {
            r = random.nextInt(201);
        }
        int totalAmount = 100 + r;
        int totalNum = 1;
        String wishing = "感谢您购买MOTI商品";
        String actName = "查真伪领红包";
        String remark = "公众号查真伪领红包";
        String sceneId = PRODUCT_1.getCode();
        try {
            redService.sendRed(orderNo, openId, totalAmount, totalNum, wishing, actName, remark, sceneId);
            activitySecurityCode.setSecurityCode(securityCode);
            activitySecurityCode.setCreateTime(DateUtil.getNowTimeStr());
            activitySecurityCode.setMoney(String.valueOf(totalAmount));
            activitySecurityCode.setProfile(profile);
            activitySecurityCodeDao.insertActivitySecurityCode(activitySecurityCode);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, vo);
        } catch (Exception e) {
            LOGGER.error("RedPacketServiceImpl.sendRedPacket error ", e);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_ERROR, vo);
        }
    }


    @Override
    public ResponseJson getRedStatus(String orderNo) {
        try {
            Map<String, Object> map = redService.getRedStatus(orderNo);
            WeChartRedStatusVo vo = new WeChartRedStatusVo();
            vo.setStatus(String.valueOf(map.get("status")));
            vo.setTotalAmount(String.valueOf(map.get("total_amount")));
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, vo);
        } catch (Exception e) {
            LOGGER.error("RedPacketServiceImpl.sendRedPacket error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
