package com.ly.mt.cabinet.b.replenish.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ly.mt.cabinet.b.common.dict.CabinetTypeEnum;
import com.ly.mt.cabinet.b.common.dict.SmsTypeEnum;
import com.ly.mt.cabinet.b.login.vo.TokenInfo;
import com.ly.mt.cabinet.b.replenish.bo.CabinetBussinessCoorperationBo;
import com.ly.mt.cabinet.b.replenish.bo.CabinetReplenish;
import com.ly.mt.cabinet.b.replenish.bo.GoodsInfoNameBo;
import com.ly.mt.cabinet.b.replenish.service.ReplenishmentGirdService;
import com.ly.mt.cabinet.b.replenish.service.ReplenishmentShowCaseService;
import com.ly.mt.cabinet.b.replenish.vo.CabinetReplenishListVo;
import com.ly.mt.cabinet.b.replenish.vo.CabinetStoreVo;
import com.ly.mt.cabinet.b.sms.service.SmsService;
import com.ly.mt.cabinet.base.config.YmlConfig;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.Device.entity.DeviceStartGoodsInfoVO;
import com.ly.mt.cabinet.c.Device.entity.DeviceStartVO;
import com.ly.mt.cabinet.c.Device.service.DeviceCallStartService;
import com.ly.mt.cabinet.c.enumEntity.DeviceServiceEnum;
import com.ly.mt.cabinet.c.good.entity.GzgGoodsPlan;
import com.ly.mt.cabinet.c.order.entity.GzgOrder;
import com.ly.mt.cabinet.c.order.entity.GzgOrderItem;
import com.ly.mt.cabinet.c.rujin.entity.GzgRujinRelation;
import com.ly.mt.cabinet.c.rujin.entity.RujinUrlEnum;
import com.ly.mt.cabinet.c.tools.HttpClientUtil;
import com.ly.mt.core.base.dict.ReplenishOrderTypeEnum;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.*;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.cabinet.b.common.dict.CabinetTypeEnum.CABINET_TYPE_RU_JIN;
import static com.ly.mt.cabinet.b.common.dict.CabinetTypeEnum.CABINET_TYPE_YI_LIAN;
import static com.ly.mt.cabinet.b.common.dict.ReplenishStatusEnum.REPLENISH_STATUS_PASSED;
import static com.ly.mt.cabinet.b.common.dict.ReplenishStatusEnum.REPLENISH_STATUS_WAITING_GRAB;
import static com.ly.mt.cabinet.c.rujin.entity.RujinDoEnum.RUJIN_ONLINE;
import static com.ly.mt.core.base.dict.AlOssPath.AL_OSS_PATH_IMAGE_REPLENIS_BD;
import static com.ly.mt.core.base.dict.CabinetMessageType.MESSAGE_TYPE_GZG;
import static com.ly.mt.core.base.dict.CabinetReadStatus.READ_STATUS_NO;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_CABINET_MESSAGE;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * @author wanghongliang
 * @description 格子柜B-补货-接口服务层。
 * @date 2019-09-05
 */
@Service
public class ReplenishmentGirdServiceImpl extends BaseServiceImpl implements ReplenishmentGirdService {

    private static final Logger logger = LoggerFactory.getLogger(ReplenishmentGirdServiceImpl.class);
    @Resource
    YmlConfig yml;

    @Resource
    private DeviceCallStartService deviceCallStartService;

    @Autowired
    SmsService smsService;

    @Autowired
    ReplenishmentShowCaseService replenishmentShowCaseService;

    /**
     * 生成补货订单
     * 根据货柜类型 生成格子柜订单和展柜订单
     * @param orderId
     * @return
     */
    @Override
    public ResponseJson createReplenishOrder(String orderId){
        if (StringUtil.isEmpty(orderId)) {
            ResponseUtil.getResponseMsg(ResponseCode.RESULT_CODE_PARAM_NEED, "订单id不能为空！");
        }
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("id", orderId);
        //据id查询一条获取订单信息
        String gzgOrderStr = callDataCenter(GZG_ORDER_GET, jsonParam);
        //转成对应的实体。
        GzgOrder gzgOrder = JSONObject.parseObject(gzgOrderStr, GzgOrder.class);//订单信息
        //订单为空时 抛出异常
        if(gzgOrder==null||StringUtil.isEmpty(gzgOrder.getId())){
            logger.info("没有此订单orderId=="+orderId);
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "没有此订单！");
        }
        logger.info("查询到订单信息为："+JSONObject.toJSONString(gzgOrder));
        //根据展柜类型调用不同的生成订单方法
        if(gzgOrder.getOrder_source().equals(CabinetTypeEnum.CABINET_TYPE_ZAN_GUI.getKey())){//展柜
            //调用展柜生成补货订单方法
            logger.info("此订单orderId=="+orderId+"调用展柜生成补货订单方法");
            replenishmentShowCaseService.createReplenishZgOrder(gzgOrder);//生成展柜补货订单
        }else if(gzgOrder.getOrder_source().equals(CabinetTypeEnum.CABINET_TYPE_YI_LIAN.getKey())||//格子柜
                gzgOrder.getOrder_source().equals(CabinetTypeEnum.CABINET_TYPE_RU_JIN.getKey())){
            logger.info("此订单orderId=="+orderId+"调用生成格子柜补货订单");
            return createReplenishOrderAndPushMsg(gzgOrder);//生成格子柜补货订单
        }else{
            logger.info("orderId=="+orderId+"此订单没有Order_source=="+gzgOrder.getOrder_source());
        }
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "生成补货单成功！");
    }
    /**
     * 该接口完成的操作有
     * 1.生成格子柜的补货单,以供格子柜所在的店员，店主，或者BD进行补货操作。
     * 2.更新gzg_goods_plan表stock库存字段，若stock字段为0，前端页面这个地方会是灰色，用户无法购买
     * 3.一个订单对应多条明细,即对应多个商品,需要生成多条补货单,但push只发给一个人
     * 4.如果有押金，推送消息给给该柜所在门店店员发送补货push，否则push给BD
     *
     * @param gzgOrder
     * @return
     */
    public ResponseJson createReplenishOrderAndPushMsg(GzgOrder gzgOrder) {
        //据订单id获取订单明细集合信息
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("order_id", gzgOrder.getId());
        String gzgOrderItemStr = callDataCenter(GZG_ORDER_ITEMS_GET_LIST, jsonParam);


        List<GzgOrderItem> gzgOrderItems = JSONObject.parseArray(gzgOrderItemStr, GzgOrderItem.class);//订单明细

        //发现为多条明细时生成多条补货单
        //无论是否为6号格子，都产生的实际就一条记录
        //通过店铺id 查询商务合作表 得到商务合作信息 用户userID/BD手机号等
        JSONObject storeParam = new JSONObject();
        storeParam.put("storeId",gzgOrder.getHotel_id());//店铺id
        String cabinetBussinessCoorperationResult = callDataCenter(CABINET_COORPERATION_GET_BY_STRORE, storeParam);//商务合作信息
        CabinetBussinessCoorperationBo cabinetBussinessCoorperationVo = JSONObject.parseObject(cabinetBussinessCoorperationResult, CabinetBussinessCoorperationBo.class);
        //通过店铺id 查询店铺名称
        storeParam.remove("storeId");//店铺id
        storeParam.put("id",gzgOrder.getHotel_id());//店铺id
        String cabinetStoreResult = callDataCenter(CABINET_STORE_GET_BY_ID, storeParam);//查询店铺信息
        CabinetStoreVo cabinetStoreVo = JSONObject.parseObject(cabinetStoreResult,CabinetStoreVo.class);
        if(cabinetBussinessCoorperationVo==null||StringUtil.isEmpty(cabinetBussinessCoorperationVo.getBdUserId())||cabinetStoreVo==null){
            logger.info("通过店铺id查询商务合作信息没查询到userId");
            ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "未查询到店铺信息！");
        }
        String userId =cabinetBussinessCoorperationVo.getBdUserId();
        String skuName ="";//发短信内容 sku名称
        String mobile=cabinetBussinessCoorperationVo.getBdPhone();//BD手机号
        String shopName=cabinetStoreVo.getName();//店铺名称
        //生成补货单实体
        for(GzgOrderItem item:gzgOrderItems){
            CabinetReplenish cabinetReplenish = getCabinetReplenishBean(gzgOrder, item,userId);
            logger.info("开始生成补货单实体信息："+JSONObject.toJSONString(cabinetReplenish));
            //保存补货单到数据库
            callDataCenter(GZG_CABINET_REPLENISH_INSERT, JSONObject.parseObject(JSONObject.toJSONString(cabinetReplenish)));
            logger.info("结束生成补货单实体信息："+JSONObject.toJSONString(cabinetReplenish));
            skuName =skuName+item.getSku_name()+"、";
        }
        //开始给店铺的BD负责人发送短信
        sendNotificationBdGzg(mobile, SmsTypeEnum.NOTIFICATION_GZG_BD.getId(),shopName,skuName.substring(0,skuName.length()-1));
        //appPush后期添加上
        //放入消息
        saveCabientMessage(userId);
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "生成补货单成功！");
    }

    /**
     * 格子柜BD补货短信
     * @param mobile
     * @param type
     * @param shopName
     * @param skuName
     */
    public void sendNotificationBdGzg(String mobile, String type, String shopName,String skuName){
        Map<String,String> templateMap  = new HashMap<String,String>();
        templateMap.put("shop_name",shopName);
        templateMap.put("sku_name",skuName);
        String templateParms = JSONObject.toJSONString(templateMap);
        try{
            smsService.sendNotificationSms(mobile,type,templateParms);
        }catch (Exception e){
            logger.info("BD格子柜补货发送短信异常",e);
        }
    }

    /**
     * 放入用户消息
     * @param userId
     */
    public void saveCabientMessage(String userId){
        String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_CABINET_MESSAGE);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message_id",id);//消息id
        jsonObject.put("user_id",userId);
        jsonObject.put("message_type",MESSAGE_TYPE_GZG.getId());//格子柜消息类型
        jsonObject.put("read_status",READ_STATUS_NO.getId());//是否已读
        jsonObject.put("message_title","格子柜补货");//暂且写死
        jsonObject.put("message_content","格子柜补货");//暂且写死
        jsonObject.put("create_time",DateUtil.getNowTimeStr());//取当前系统时间
        jsonObject.put("modify_time",DateUtil.getNowTimeStr());//取当前系统时间
        String result = callDataCenter(GZG_CABINET_MESSAGE_INSERT, jsonObject);//商务合作信息
    }
    /**
     * 封装BD补货实体
     * @param gzgOrder
     * @param gzgOrderItem
     * @return
     */
    private CabinetReplenish getCabinetReplenishBean(GzgOrder gzgOrder, GzgOrderItem gzgOrderItem,String bdUserId){
        String id =RandomUtil.generateReplenishMentId();//生成补货订单ID
        CabinetReplenish cabinetReplenish = new CabinetReplenish();
        cabinetReplenish.setId(id);
        cabinetReplenish.setOrder_type(ReplenishOrderTypeEnum.BD_ORDER.getKey());//暂且写死BD补货单
        cabinetReplenish.setOrder_finish_time(gzgOrder.getOrder_finish_time());//订单完成时间
        cabinetReplenish.setCabinet_code(gzgOrder.getImei());
        cabinetReplenish.setCabinet_no(gzgOrderItem.getCabinet_no());

        cabinetReplenish.setSku_id(gzgOrderItem.getSku_id());
        if(CABINET_TYPE_YI_LIAN.getKey().equals(gzgOrder.getOrder_source()) && "6".equals(gzgOrderItem.getCabinet_no())){
            logger.info("为亿联且是6号,查询69码表："+gzgOrder.getId());
            //如果为亿联的六号格子，则69码专门走一套逻辑
            JSONObject json = new JSONObject();
            json.put("sku_id",gzgOrderItem.getSku_id());
            String resultStr = callDataCenter(GZG_SUIT_BARCODE_INFO_GET, json);
            List<String> barcodeList = JSONObject.parseArray(resultStr,String.class);
            StringBuilder barcodeStr = new StringBuilder();
            for (String barcode:  barcodeList) {
                barcodeStr.append(barcode).append(",");
            }
            String substring = barcodeStr.toString().substring(0, barcodeStr.toString().length() - 1);
            cabinetReplenish.setSix_nine_code(substring);
        }else{
            //否则直接查询处69码放进去即可。
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("imei",gzgOrder.getImei());
            jsonParam.put("sku_id",gzgOrderItem.getSku_id());
            String gzgGoodsPlanListStr = callDataCenter(GZG_GOODS_PLAN_GET, jsonParam);//查询skuid
            List<GzgGoodsPlan> gzgGoodsPlans = JSONObject.parseArray(gzgGoodsPlanListStr, GzgGoodsPlan.class);
            GzgGoodsPlan gzgGoodsPlan = gzgGoodsPlans.get(0);
            cabinetReplenish.setSix_nine_code(gzgGoodsPlan.getBar_code());//????????此处疑问 放的是到底码什么69码?
        }
        //二期需要根据店铺信息 决定应该给BD推送还是应该店铺员工还是管理员推送
        cabinetReplenish.setUser_id(bdUserId);//此处插入BDuserId
        //当是如今需要查询
        if(gzgOrder.getOrder_source().equals(CABINET_TYPE_RU_JIN.getKey())){
            //否则直接查询处69码放进去即可。
            JSONObject replationParam = new JSONObject();
            replationParam.put("name",gzgOrder.getImei());
            String relationResult = callDataCenter(GZG_RUJIN_RELATION_GET_BY_NAME, replationParam);//查询如今的编号
            if(relationResult==null&&StringUtil.isEmpty(relationResult)){
                logger.info("查询如金关系数据没查到,订单编号为:"+gzgOrder.getId());
            }
            GzgRujinRelation gzgRujinRelation = JSONObject.parseObject(relationResult,GzgRujinRelation.class);
            logger.info("查询如金关系数据为,"+JSONObject.toJSONString(gzgRujinRelation)+"/订单编号为:"+gzgOrder.getId());
            cabinetReplenish.setCabinet_code(gzgRujinRelation.getTid());
        }
        cabinetReplenish.setCabinet_type(gzgOrder.getOrder_source());
        cabinetReplenish.setOrder_id(gzgOrder.getId());
        cabinetReplenish.setOrder_item_id(gzgOrderItem.getId());
        cabinetReplenish.setCabinet_store_id(gzgOrder.getHotel_id());
        cabinetReplenish.setStatus(REPLENISH_STATUS_WAITING_GRAB.getKey());
        try {
            //本次BD补货不涉及超时时间
            //String replenishment_timeout_time = DateUtil.getAfterMinutesDateFromPointTime(10, DateUtil.getNowTimeStr());
            //cabinetReplenish.setReplenishment_timeout_time(replenishment_timeout_time);
        } catch (Exception e) {
            logger.error("获取补货单超时时间的工具类出现异常：{}",e);
            throw new RuntimeException();
        }
        cabinetReplenish.setCreate_time(DateUtil.getNowTimeStr());
        return cabinetReplenish;
    }

    /**
     * BD补货订单列表
     * @param status
     * @return
     */
    @Override
    public ResponseJson replenishOrderListBd(String status){
        /**
         * 通过userID查询补货订单列表
         * 1.查询列表图片
         * 2.sku_id 查询spuname+sku+name
         * 3.店铺名称
         * 4.详细地址 查询地址+详细地址
         */
        TokenInfo tokenInfo = getTokenInfo();
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("userId", tokenInfo.getUserId());
        jsonParam.put("status", status);
        jsonParam.put("orderType", ReplenishOrderTypeEnum.BD_ORDER.getKey());
        //查询补货订单
        String orderReulst = callDataCenter(GZG_CABINET_REPLENISH_GET, jsonParam);
        List<CabinetReplenishListVo> listVos = JSONObject.parseArray(orderReulst,CabinetReplenishListVo.class);
        //下面封装spuName+skuName
        for(CabinetReplenishListVo cabinetReplenishListVo:listVos){
            String skuId = cabinetReplenishListVo.getSkuId();
            JSONObject goodsParam = new JSONObject();
            goodsParam.put("id", skuId);
            String goodsReulst = callDataCenter(GZG_GOODS_INFO_NAME_GET, goodsParam);
            GoodsInfoNameBo goodsInfoNameBo = JSONObject.parseObject(goodsReulst,GoodsInfoNameBo.class);
            cabinetReplenishListVo.setSkuName(goodsInfoNameBo.getSkuName());
            cabinetReplenishListVo.setSpuName(goodsInfoNameBo.getSpuName());
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, listVos);
    }

    /**
     * BD货柜效验
     * @param scanCabinetCode
     * @param replenishId 补货订单id
     * @return
     */
    @Override
    public ResponseJson cabinetValidate(String scanCabinetCode,String replenishId){
        //查询补货订单详情
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("id", replenishId);
        String result = callDataCenter(GZG_CABINET_REPLENISH_GET_DETAIL,jsonParam);
        CabinetReplenish cabinetReplenish = JSONObject.parseObject(result,CabinetReplenish.class);
        if(cabinetReplenish==null||cabinetReplenish.getId()==null){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "补货订单id有误!");
        }
        //判断code值是否一样
        if(!scanCabinetCode.equals(cabinetReplenish.getCabinet_code())){
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "非需补货货柜的二维码");
        }
        //判断格子柜类型 调用不同厂商的开门接口
        if(cabinetReplenish.getCabinet_type().equals(CABINET_TYPE_RU_JIN.getKey())){
            try {
                //调用如今打开舱门
                openRuJinDevice(cabinetReplenish.getCabinet_code(),cabinetReplenish.getCabinet_no());
            }catch (Exception e){
                logger.info("打开"+cabinetReplenish.getCabinet_code()+"/"+cabinetReplenish.getCabinet_no()+"如今柜子失败！");
            }
        }else if(cabinetReplenish.getCabinet_type().equals(CABINET_TYPE_YI_LIAN.getKey())){//调用亿联打开舱门
            try {
                //调用亿联打开舱门
                openYlDevice(cabinetReplenish.getCabinet_code(),cabinetReplenish.getCabinet_no(),replenishId);
            }catch (Exception e){
                logger.info("打开"+cabinetReplenish.getCabinet_code()+"/"+cabinetReplenish.getCabinet_no()+"亿联柜子失败！");
            }
        }
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "货柜效验成功!");
    }

    /**
     * 打开如今的格子柜
     * @param cabinetCode 货柜编号
     * @param cabinetNo 多个货道号可以用“-” 区分，如： 1-3-5-6，表示 1 号， 3 号， 5 号，6 号 4 个货道同时开门
     * @return
     * @throws Exception
     */
    public Boolean openRuJinDevice(String cabinetCode, String cabinetNo) throws Exception {
        String url = RujinUrlEnum.RUJIN_SERVER.getValue();//维护打开门
        String hg = "1";
        String hd = cabinetNo;
        long times = System.currentTimeMillis();
        JSONObject jsonObject =new JSONObject();

        jsonObject.put("tid",cabinetCode);
        jsonObject.put("hg",hg);
        jsonObject.put("hd",hd);
        jsonObject.put("do",RUJIN_ONLINE.getKey());
        String str = cabinetCode+hg+hd+times+yml.getRujinSkey();
        String token = MD5Util.md5(str).toLowerCase();
        jsonObject.put("times",times);
        jsonObject.put("token",token);
        JSONObject jsonResult = HttpClientUtil.httpPost(url, jsonObject);
        String error = jsonResult.getString("error");
        if(!"0".equals(error)){
            logger.info("维护开门请求失败，编号为 {} 失败，失败原因 {}",cabinetCode,jsonResult.getString("emsg"));
            return false;
        }
        return true;
    }

    /**
     * 获取亿联格子柜信息
     * @param json
     * @return
     * @throws Exception
     */
    public JSONObject getYlToken(String json) throws Exception {
        String md5 = MD5Util.md5(yml.getYlMctId() + yml.getApiKey()).toLowerCase();
        String strParam = "mctId=" + yml.getYlMctId() + "&signature=" + md5;
        logger.info("获取亿联格子柜信息入参： {}" + strParam);
        String url = DeviceServiceEnum.GET_TOKEN.getValue();
        JSONObject httpPost = HttpClientUtil.httpPost(url, strParam);
        String content = httpPost.getJSONObject("data").getString("content");
        logger.info("获取亿联格子柜信息出参{}", JSONObject.toJSONString(httpPost));
        return httpPost;
    }

    /**
     * 补货打开亿联柜子
     *
     * @param
     * @return
     */
    public Boolean openYlDevice(String cabinetCode, String cabinetNo,String replenishId) {
        JSONObject getToken = null;
        String code = cabinetCode;
        try {
            getToken = getYlToken("open");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取柜子token异常，无返回值，异常原因：{}", e.getMessage());
            return false;
        }
        if (!getToken.getBoolean("success")) {
            logger.info("获取柜子token值失败，有返回值，失败原因：{}", getToken);
            return false;
        }
        String token = getToken.getJSONObject("data").getString("content");
        logger.info("打開格子櫃时获取token值是:{},柜子的參數是:{}", token, code);

        //拼接商品信息 用于开门
        DeviceStartVO deviceStartVO = new DeviceStartVO();
        List<DeviceStartGoodsInfoVO> deviceStartGoodsInfoVOS = new ArrayList<DeviceStartGoodsInfoVO>();

        DeviceStartGoodsInfoVO deviceStartGoodsInfoVO = new DeviceStartGoodsInfoVO();
        logger.info("获取格子柜的格子号：{}", cabinetNo);
        deviceStartGoodsInfoVO.setCabinetNo(Integer.parseInt(cabinetNo));
        deviceStartGoodsInfoVO.setGoodsCount(1);
        deviceStartGoodsInfoVO.setGoodsName("魔笛电子烟");
        deviceStartGoodsInfoVO.setGoodsPrice(Double.parseDouble("0.01"));//商品价格
        deviceStartGoodsInfoVOS.add(deviceStartGoodsInfoVO);

        deviceStartVO.setMctId(yml.getYlMctId());
        deviceStartVO.setDeviceImei(code);
        deviceStartVO.setMoney(Double.parseDouble("0.01"));
        deviceStartVO.setPayType("0");// 0-其他， 1-微信， 2-支付宝，
        deviceStartVO.setOrderId(replenishId);
        deviceStartVO.setGoodsInfo(deviceStartGoodsInfoVOS);

        //打开亿联的柜子
        JSONObject jsonObject = deviceCallStartService.openDeviceChannel(JSON.toJSONString(deviceStartVO), token);
        logger.info("请求打开亿联格子柜，格子柜的返回值是：{}", jsonObject);
        Boolean success = jsonObject.getBoolean("success");
        if (!success) {
            logger.info("请求打开柜子失败，失败的值{}", jsonObject);
            return false;
        }
        return true;
    }

    /**
     * BD补货提交审核
     * @param replenishId
     * @param file
     * @return
     */
    @Override
    @TxcTransaction(appName = "cabinet")
    public ResponseJson sumbitAuditBd(String replenishId, MultipartFile file){
        try {
            //查询补货订单详情
            JSONObject orderParam = new JSONObject();
            orderParam.put("id", replenishId);
            String result = callDataCenter(GZG_CABINET_REPLENISH_GET_DETAIL,orderParam);
            CabinetReplenish cabinetReplenish = JSONObject.parseObject(result,CabinetReplenish.class);
            logger.info("提交审核时,补货订单数据为："+JSONObject.toJSONString(cabinetReplenish));
            if(cabinetReplenish==null){
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "补货订单id有误!");
            }
            String xid = TxcContext.getCurrentXid();
            String url = callThirdCenter(file, AL_OSS_PATH_IMAGE_REPLENIS_BD.getPath());
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("xid",xid);
            jsonParam.put("id", replenishId);
            jsonParam.put("status", REPLENISH_STATUS_PASSED.getKey());
            jsonParam.put("audit_picture_url", url);//图片url
            jsonParam.put("submit_check_time",DateUtil.getNowTimeStr());//提交审核时间
            jsonParam.put("update_time",DateUtil.getNowTimeStr());//更新时间
            jsonParam.put("orderType", ReplenishOrderTypeEnum.BD_ORDER.getKey());
            //更新补货订单状态
            String orderReulst = callDataCenter(GZG_CABINET_REPLENISH_UPDATE, jsonParam);
            //开始更新库存数量 亿联直接通过imei更新即可
            if(cabinetReplenish.getCabinet_type().equals(CABINET_TYPE_YI_LIAN.getKey())){
                logger.info("进入亿联更新库存流程："+JSONObject.toJSONString(cabinetReplenish));
                JSONObject stockParam = new JSONObject();
                stockParam.put("xid",xid);
                stockParam.put("imei", cabinetReplenish.getCabinet_code());
                stockParam.put("sku_id", cabinetReplenish.getSku_id());
                stockParam.put("cabinet_no", cabinetReplenish.getCabinet_no());
                stockParam.put("stock", 1);
                String updateReulst = callDataCenter(GZG_GOODS_PLAN_UPDATE_STOCK, stockParam);
                logger.info("更新亿联货柜库存,"+JSONObject.toJSONString(stockParam, SerializerFeature.WriteMapNullValue)+"/补货订单id为:"+cabinetReplenish.getId());
            }else if(cabinetReplenish.getCabinet_type().equals(CABINET_TYPE_RU_JIN.getKey())){//如今时需要查询一下关系
                logger.info("进入如今更新库存流程："+JSONObject.toJSONString(cabinetReplenish));
                JSONObject stockParam = new JSONObject();
                stockParam.put("xid",xid);
                //如今时 需要查询关系
                stockParam.put("sku_id", cabinetReplenish.getSku_id());
                JSONObject replationParm = new JSONObject();
                replationParm.put("tid",cabinetReplenish.getCabinet_code());
                logger.info("开始查询如今对应关系:"+cabinetReplenish.getCabinet_code());
                String relationResult = callDataCenter(GZG_RUJIN_RELATION_GET_BY_TID, replationParm);
                if(relationResult==null&&StringUtil.isEmpty(relationResult)){
                    logger.info("查询如金关系数据没查到,补货订单id为:"+cabinetReplenish.getId());
                }
                GzgRujinRelation gzgRujinRelation = JSONObject.parseObject(relationResult,GzgRujinRelation.class);
                logger.info("查询如金关系数据为,"+JSONObject.toJSONString(gzgRujinRelation, SerializerFeature.WriteMapNullValue)+"/补货订单id为:"+cabinetReplenish.getId());
                stockParam.put("imei", gzgRujinRelation.getName());
                //stockParam.put("cabinet_code", gzgRujinRelation.getName());//通过name去更新
                stockParam.put("stock", 1);
                logger.info("更新如今货柜库存,"+JSONObject.toJSONString(stockParam, SerializerFeature.WriteMapNullValue)+"/补货订单id为:"+cabinetReplenish.getId());
                String updateReulst = callDataCenter(GZG_GOODS_PLAN_UPDATE_STOCK, stockParam);
            }

        } catch (Exception e) {
            logger.error("更新补货订单异常：{}",e);
            throw new RuntimeException();
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }
}
