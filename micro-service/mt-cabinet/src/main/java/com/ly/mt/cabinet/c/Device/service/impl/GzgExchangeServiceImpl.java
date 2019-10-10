package com.ly.mt.cabinet.c.Device.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.Device.entity.DeviceStartGoodsInfoVO;
import com.ly.mt.cabinet.c.Device.entity.DeviceStartVO;
import com.ly.mt.cabinet.c.Device.service.DeviceCallStartService;
import com.ly.mt.cabinet.c.Device.service.GzgExchangeService;
import com.ly.mt.cabinet.c.order.entity.GzgOrderItem;
import com.ly.mt.cabinet.c.enumEntity.DeviceServiceEnum;
import com.ly.mt.cabinet.c.enumEntity.GzgPayTypeEnum;
import com.ly.mt.cabinet.c.tools.HttpClientUtil;
import com.ly.mt.cabinet.c.order.entity.GzgOrder;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.util.MD5Util;
import com.ly.mt.core.base.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单主表服务层
 *
 * @author gongjy
 * @date 2019-05-21
 */
@Service
@PropertySource(value = {"classpath:myconfig-c.properties"},ignoreResourceNotFound=false,encoding="UTF-8")
public class GzgExchangeServiceImpl extends BaseServiceImpl implements GzgExchangeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgExchangeServiceImpl.class);
    @Value("${gzg.mctId}")
    private String mctId;
    @Value("${gzg.apiKey}")
    private String apiKey;
    @Value("${gzg.deviceImei}")
    private String deviceImei;

    public String getMctId() {
        return mctId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getDeviceImei() {
        return deviceImei;
    }

    //	@Resource
//	private GzgOrdersServiceMapper gzgOrdersServiceMapper;
//
    @Resource
    private DeviceCallStartService deviceCallStartService;

    @Override
    public JSONObject getToken(String json) throws Exception {


        String md5 = MD5Util.md5(mctId + apiKey).toLowerCase();
        ;
        System.out.println(md5);

        String strParam = "mctId=" + mctId + "&signature=" + md5;
        LOGGER.info("获取格子柜信息 ： {}" + strParam);

//		String url ="http://iot.open.ayilink.com/iot-client/open/api/auth/LOGGER.in";
        String url = DeviceServiceEnum.GET_TOKEN.getValue();

        JSONObject httpPost = HttpClientUtil.httpPost(url, strParam);
        String content = httpPost.getJSONObject("data").getString("content");
        LOGGER.info("the token is {}", content);
        System.out.println(httpPost);
        return httpPost;
    }

    @Override
    public JSONObject getIsAvailable(String json) throws Exception {
        LOGGER.info("查询柜子是否可用传入参数:{}" + json);
        System.out.println(json);
        JSONObject parseObject = JSON.parseObject(json);//.getJSONObject("json");
        System.out.println(parseObject);

        String imei = parseObject.getString("imei");
        String token = parseObject.getString("token");

//		String url ="http://iot.open.ayilink.com/iot-client/open/api/device/isAvailable";
        String url = DeviceServiceEnum.DEVICE_REAL_CHECK.getValue();
        Map<String, String> param = new HashMap<String, String>();
        Map<String, String> header = new HashMap<String, String>();
        param.put("mctId", mctId);
        param.put("imei", imei);

        header.put("token", token);
        header.put("Content-Type", "application/x-www-form-urlencoded");
        String encoding = "";
        JSONObject httpPost = HttpClientUtil.httpPostForm(url, param, header, encoding);

        LOGGER.info("查询柜子是否可用返回信息:{}" + httpPost);
        return httpPost;
    }

    /**
     * 支付完成打开格子柜
     *
     * @param
     * @return
     */
    @Override
    public Boolean openDevice(GzgOrder gzgOrder, List<GzgOrderItem> gzgOrderItemVos, GzgPayTypeEnum gzgPayTypeEnum) {
        JSONObject getToken = null;
        String code = gzgOrder.getImei();
        String gzgOrderId = gzgOrder.getId();
        String amount = gzgOrder.getOrder_money();

        try {
            getToken = getToken("open");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("微信支付成功，获取柜子token异常，无返回值，异常原因：{}", e.getMessage());
            return false;
        }
        if (!getToken.getBoolean("success")) {
            LOGGER.info("微信支付成功，获取柜子token值失败，有返回值，失败原因：{}", getToken);
            return false;
        }
        String token = getToken.getJSONObject("data").getString("content");
        LOGGER.info("打開格子櫃时获取token值是:{},柜子的參數是:{}", token, code);

        //拼接商品信息
        DeviceStartVO deviceStartVO = new DeviceStartVO();
        List<DeviceStartGoodsInfoVO> deviceStartGoodsInfoVOS = new ArrayList<DeviceStartGoodsInfoVO>();

        for (GzgOrderItem gzgOrderItemVo : gzgOrderItemVos) {
            DeviceStartGoodsInfoVO deviceStartGoodsInfoVO = new DeviceStartGoodsInfoVO();
            LOGGER.info("获取格子柜的格子号：{}", gzgOrderItemVo.getCabinet_no());
            deviceStartGoodsInfoVO.setCabinetNo(Integer.parseInt(gzgOrderItemVo.getCabinet_no()));
            deviceStartGoodsInfoVO.setGoodsCount(1);
            deviceStartGoodsInfoVO.setGoodsName(gzgOrderItemVo.getSku_name());
            deviceStartGoodsInfoVO.setGoodsPrice(Double.parseDouble(amount));
            deviceStartGoodsInfoVOS.add(deviceStartGoodsInfoVO);
        }

        deviceStartVO.setMctId(mctId);
        deviceStartVO.setDeviceImei(code);
        deviceStartVO.setMoney(Double.parseDouble(amount));
        deviceStartVO.setPayType(gzgPayTypeEnum.getKey());// 0-其他， 1-微信， 2-支付宝，
        deviceStartVO.setOrderId(gzgOrderId);
        deviceStartVO.setGoodsInfo(deviceStartGoodsInfoVOS);

        JSONObject jsonObject = deviceCallStartService.openDeviceChannel(JSON.toJSONString(deviceStartVO), token);
        LOGGER.info("请求打开格子柜，格子柜的返回值是：{}", jsonObject);
        Boolean success = jsonObject.getBoolean("success");
        if (!success) {
            LOGGER.info("请求打开柜子失败，失败的值{}", jsonObject);
            return false;
        }
        return true;
    }

    @Override
    public Boolean getIsAvailableBycode(String imei) throws Exception {
        LOGGER.info("查询柜子是否可用service层接收到的参数:{}", imei);

        String md5 = MD5Util.md5(mctId + apiKey).toLowerCase();
        ;
        String strParam = "mctId=" + mctId + "&signature=" + md5;
        String getTokenUrl = DeviceServiceEnum.GET_TOKEN.getValue();
        JSONObject httpPost = HttpClientUtil.httpPost(getTokenUrl, strParam);
        if (!httpPost.getBoolean("success")) {
            LOGGER.info("判断设备是否可用，获取token值失败，失败消息{}", httpPost);
            return false;
        }
        String token = httpPost.getJSONObject("data").getString("content");
        LOGGER.info("判断设备是否可用获取token={}", token);

        String getDeviceInfoUrl = DeviceServiceEnum.DEVICE_INFO.getValue();
        Map<String, String> paramDevice = new HashMap<String, String>();
        Map<String, String> headerDevice = new HashMap<String, String>();
        paramDevice.put("mctId", mctId);
        paramDevice.put("imei", imei);

        headerDevice.put("token", token);
        headerDevice.put("Content-Type", "application/x-www-form-urlencoded");
        String encoding = "";
        JSONObject deviceInfo = HttpClientUtil.httpPostForm(getDeviceInfoUrl, paramDevice, headerDevice, encoding);
        LOGGER.info("文档第三接口，查询柜子信息返回信息:{}" + deviceInfo);
        if (!deviceInfo.getBoolean("success")) {
            return false;
        }

        if (!deviceInfo.getJSONObject("data").getBoolean("deviceOnline")) {
            LOGGER.info("格子柜设备不在线，格子柜编号code值{}", imei);
            return false;
        }
//		String getDeviceIsAvailableUrl = DeviceServiceEnum.DEVICE_REAL_CHECK.getValue();
//		JSONObject deviceIsAvailableInfo = HttpClientUtil.httpPostForm(getDeviceIsAvailableUrl,paramDevice,headerDevice,encoding);
//		LOGGER.info("文档第五接口，查询柜子是否可用:{}"+deviceIsAvailableInfo);
//		if(!deviceIsAvailableInfo.getBoolean("data")){
//			LOGGER.info("检测到格子柜不可用，格子柜code值{}",imei);
//			return false;
//		}
        return true;
    }

    /**
     * 库管在维护阶段打开柜门
     * @param imei 格子柜code值
     * @return
     * @throws Exception
     */
    @Override
    public Boolean maintainOpenDevice(String imei) throws Exception {
        JSONObject getToken = null;
        String gzgOrderId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_GZG_ORDER);
        try {
            getToken = getToken("open");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("微信支付成功，获取柜子token异常，无返回值，异常原因：{}", e.getMessage());
            return false;
        }
        if (!getToken.getBoolean("success")) {
            LOGGER.info("微信支付成功，获取柜子token值失败，有返回值，失败原因：{}", getToken);
            return false;
        }
        String token = getToken.getJSONObject("data").getString("content");
        LOGGER.info("打開格子櫃时获取token值是:{},柜子的參數是:{}", token, imei);

        //拼接商品信息
        DeviceStartVO deviceStartVO = new DeviceStartVO();
        List<DeviceStartGoodsInfoVO> deviceStartGoodsInfoVOS = new ArrayList<DeviceStartGoodsInfoVO>();

        for (int i = 1;i<8;i++) {
            DeviceStartGoodsInfoVO deviceStartGoodsInfoVO = new DeviceStartGoodsInfoVO();
            deviceStartGoodsInfoVO.setCabinetNo(i);
            deviceStartGoodsInfoVO.setGoodsCount(1);
            deviceStartGoodsInfoVO.setGoodsName("魔笛电子烟");
            deviceStartGoodsInfoVO.setGoodsPrice(0.01);
            deviceStartGoodsInfoVOS.add(deviceStartGoodsInfoVO);
        }

        deviceStartVO.setMctId(mctId);
        deviceStartVO.setDeviceImei(imei);
        deviceStartVO.setMoney(0.07);
        deviceStartVO.setPayType(GzgPayTypeEnum.WECHAT.getKey());// 0-其他， 1-微信， 2-支付宝，
        deviceStartVO.setOrderId(gzgOrderId);
        deviceStartVO.setGoodsInfo(deviceStartGoodsInfoVOS);

        JSONObject jsonObject = deviceCallStartService.openDeviceChannel(JSON.toJSONString(deviceStartVO), token);
        LOGGER.info("请求打开格子柜，格子柜的返回值是：{}", jsonObject);
        Boolean success = jsonObject.getBoolean("success");
        if (!success) {
            LOGGER.info("请求打开柜子失败，失败的值{}", jsonObject);
            return false;
        }
        return true;
    }
}
