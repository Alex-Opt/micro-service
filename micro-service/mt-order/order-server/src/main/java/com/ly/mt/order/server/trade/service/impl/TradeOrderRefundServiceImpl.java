package com.ly.mt.order.server.trade.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.dict.OrderRefundType;
import com.ly.mt.core.base.dict.OrderStatus;
import com.ly.mt.core.base.dict.RefundStatus;
import com.ly.mt.core.base.entity.goods.GoodsSkuAttr;
import com.ly.mt.core.base.entity.goods.GoodsSkuInfo;
import com.ly.mt.core.base.entity.goods.GoodsSpuInfoForSkuVo;
import com.ly.mt.core.base.entity.trade.*;
import com.ly.mt.core.base.method.ThirdServerMethodEnum;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.configs.service.ConfigsService;
import com.ly.mt.order.server.goods.GoodsSkuService;
import com.ly.mt.order.server.goods.mapper.GoodsSkuInfoServiceMapper;
import com.ly.mt.order.server.trade.decorator.DecoratorCommonServiceComponent;
import com.ly.mt.order.server.trade.mapper.TradeOrderItemServiceMapper;
import com.ly.mt.order.server.trade.mapper.TradeOrderRefundMapper;
import com.ly.mt.order.server.trade.mapper.TradeOrdersServiceMapper;
import com.ly.mt.order.server.trade.service.TradeOrderRefundItemService;
import com.ly.mt.order.server.trade.service.TradeOrderRefundService;
import com.ly.mt.order.server.user.service.UserProfitLogsService;
import com.ly.mt.order.server.user.service.UserProfitsService;
import com.ly.mt.order.server.user.service.UserTreesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.ly.mt.core.base.dict.BillingStatus.BILLING_STATUS_WAITING_BILL;
import static com.ly.mt.core.base.dict.OrderRefundCancel.ORDER_REFUND_NO_CANCEL;
import static com.ly.mt.core.base.dict.OrderRefundCancel.ORDER_REFUND_YES_CANCEL;
import static com.ly.mt.core.base.dict.PicturePlaceholder.PICTURE_PLACEHOLDER_SKU;
import static com.ly.mt.core.base.dict.PrimaryKey.*;
import static com.ly.mt.core.base.dict.ProfitType.PROFIT_TYPE_REFUND;
import static com.ly.mt.core.base.dict.RefundStatus.REFUND_STATUS_AGREE_RETURN;
import static com.ly.mt.core.base.dict.RefundStatus.REFUND_STATUS_APPLY_RETURN;
import static com.ly.mt.core.base.entity.ResponseCode.*;

/**
 * 订单退货 服务层实现
 *
 * @author 484876123@qq.com
 * @date 2019-06-25
 */
@Service
public class TradeOrderRefundServiceImpl extends BaseServiceImpl implements TradeOrderRefundService {

    /**
     * 日志声明
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrderRefundServiceImpl.class);

    @Resource
    private GoodsSkuService goodsSkuService;
    /**
     * 订单
     */
    @Resource
    private TradeOrdersServiceMapper tradeOrdersServiceMapper;

    @Resource
    private GoodsSkuInfoServiceMapper goodsSkuInfoServiceMapper;

    /**
     * 订单明细
     */
    @Resource
    TradeOrderItemServiceMapper tradeOrderItemServiceMapper;
    /**
     * 退款
     */
    @Resource
    private TradeOrderRefundMapper tradeOrderRefundMapper;
    /**
     * 退款明细
     */
    @Autowired
    TradeOrderRefundItemService tradeOrderRefundItemService;

    /**
     * 查询人员树结构
     */
    @Autowired
    UserTreesService userTreesService;
    /**
     * 查询配置表数据
     */
    @Autowired
    ConfigsService configsService;

    /**
     * 收益日志表
     */
    @Autowired
    UserProfitLogsService userProfitLogsService;

    /**
     * 收益表操作
     */
    @Autowired
    UserProfitsService userProfitsService;


    /**
     * 查询订单退货信息
     *
     * @param json 订单退货ID
     * @return 订单退货信息
     */
    @Override
    public JSONObject selectTradeOrderRefundById(String json) {
        return JsonUtil.getSuccessJson(tradeOrderRefundMapper.selectTradeOrderRefundById(Long.parseLong(json)));
    }

    /**
     * 查询订单退货列表
     *
     * @param tradeOrderRefund 订单退货信息
     * @return 订单退货集合
     */
    @Override
    public List<TradeOrderRefundInfo> selectTradeOrderRefundList(TradeOrderRefundInfo tradeOrderRefund) {
        return tradeOrderRefundMapper.selectTradeOrderRefundList(tradeOrderRefund);
    }

    /**
     * 新增订单退货
     *
     * @param tradeOrderRefund 订单退货信息
     * @return 结果
     */
    @Override
    public int insertTradeOrderRefund(TradeOrderRefundInfo tradeOrderRefund) {
        return tradeOrderRefundMapper.insertTradeOrderRefund(tradeOrderRefund);
    }

    /**
     * 修改订单退货
     *
     * @param tradeOrderRefund 订单退货信息
     * @return 结果
     */
    @Override
    public int updateTradeOrderRefund(TradeOrderRefundInfo tradeOrderRefund) {
        return tradeOrderRefundMapper.updateTradeOrderRefund(tradeOrderRefund);
    }

    /**
     * 创建退款单
     * orderid:订单id
     * buyerId:买家Id
     * refundReason:退货类型
     * refundDesc:退货描述
     * skuId：退货SKUID
     *
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject addTradeOrderRefund(String jsonObject) throws Exception {
        JSONObject json = JSON.parseObject(jsonObject);
//		将jsonObject对象转为退货单实体类
        TradeOrderRefundInfo refundInfo = JSON.parseObject(jsonObject, TradeOrderRefundInfo.class);
        String refundNo = SnowflakeUtil.getPrimaryKey(ORDER_REFUND_ID);
        refundInfo.setId(refundNo);
//		根据传入订单id获取订单基础数据
        TradeOrder orderJson = tradeOrdersServiceMapper.selectByPrimaryKey(Long.parseLong(refundInfo.getOrderId()));
//		获取订单状态
        String orderStatus = orderJson.getOrderStatus();
//		判断订单状态,订单状态为等待付款与订单完成则禁止退款
        if (OrderStatus.ORDER_STATUS_PENDING_PAYMENT.getId().equals(orderStatus) || OrderStatus.ORDER_STATUS_COMPLETE.getId().equals(orderStatus)) {
            return JsonUtil.getErrorJson(TRADE_ORDER_REFUND_STATUS_ERROR);
        }
//		通过skuId进行判断是整单退货还是部分退货
        String skuId = json.getString("skuId");
        //获取订单详情列表
        List<TradeOrderItem> tradeOrderItemList;
        //获取退货单详情列表
        List<TradeOrderRefundItem> tradeOrderRefundItems;
        Integer refundsCount;
        if (StringUtil.isEmpty(skuId)) {
//			skuId为空情况下则整单退货
            refundInfo.setRefundType(OrderRefundType.REFUND_TYPE_ALL_RETURN.getId());
//			全部退货通过orderId查询全部订单明细
            tradeOrderItemList = tradeOrderItemServiceMapper.selectByOrderId(json.getLong("orderId"));
            tradeOrderRefundItems = tradeOrderRefundItemService.selectTradeOrderRefundItemByOrderId(json.getLong("orderId"));
            refundsCount = tradeOrderItemList.size();
        } else {
//			skuId不为空的情况下部分退货
            refundInfo.setRefundType(OrderRefundType.REFUND_TYPE_PART_RETURN.getId());
//			部分退货通过orderId,skuId,status查询部分订单明细
            //查询json
            JSONObject findJson = new JSONObject();
            findJson.put("orderId", json.getLong("orderId"));
            findJson.put("skuId", json.getLong("skuId"));
            tradeOrderItemList = tradeOrderItemServiceMapper.selectByOrderIdAndSkuId(findJson);
            //判断详情中的sku是否存在
            if (tradeOrderItemList.size() == 0) {
                return JsonUtil.getErrorJson(TRADE_ORDER_REFUND_COUNT_ERROR);
            }
            tradeOrderRefundItems = tradeOrderRefundItemService.selectTradeOrderRefundItemByOrderIdAndSkuId(findJson);
            //部分退货退货数默认为1
            refundsCount = Integer.parseInt(refundInfo.getRefundsCount());
        }
//		获取退款单明细 根据orderid用于比较 退单数量
        if (this.isCount(tradeOrderItemList, tradeOrderRefundItems, refundsCount)) {
            return JsonUtil.getErrorJson(TRADE_ORDER_REFUND_COUNT_ERROR);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //退货金额汇总
        double refund_price = 0;
        //优惠金额汇总
        double subtract_coupon_price = 0;
        //邮费金额汇总
        double freight_price = 0;

        //拼装退货单详情
        List<TradeOrderRefundItem> list = new ArrayList<>();
//		循环订单详情，获取订单详细的金额信息
        for (TradeOrderItem tradeOrderItem : tradeOrderItemList) {
            //退款单详情实体
            TradeOrderRefundItem tradeOrderRefundItem = new TradeOrderRefundItem();
            //生成id
            tradeOrderRefundItem.setId(SnowflakeUtil.getPrimaryKey(ORDER_REFUND_ITEM_ID));
            //存入订单Id
            tradeOrderRefundItem.setOrderId(tradeOrderItem.getOrderId());
            //存入skuid
            tradeOrderRefundItem.setSkuId(tradeOrderItem.getSkuId());
            //存入退货单id
            tradeOrderRefundItem.setRefundId(refundInfo.getId());
            //存入订单单价
            tradeOrderRefundItem.setSkuPrice(tradeOrderItem.getSkuPrice());
            //退款数量 判断规则 如果有skuid则退款数量1 否则全部数量
            if (OrderRefundType.REFUND_TYPE_ALL_RETURN.getId().equals(refundInfo.getRefundType())) {
                tradeOrderRefundItem.setRefundNum(tradeOrderItem.getSkuNum());
            } else {
               // tradeOrderRefundItem.setRefundNum("1");
                tradeOrderRefundItem.setRefundNum(refundInfo.getRefundsCount());
            }
            //商品订单金额（分）
//			tradeOrderRefundItem.setSkuOrderPrice(orderJson.getString(""));
            //计算实际退货金额
            String price = this.priceCalculation(tradeOrderItem.getPaymentPrice(), tradeOrderItem.getSkuNum(), tradeOrderRefundItem.getRefundNum());
            tradeOrderRefundItem.setSkuRefundPrice(price);
            //计算优惠金额
            String couponPrice = this.priceCalculation(tradeOrderItem.getPomotionPrice(), tradeOrderItem.getSkuNum(), tradeOrderRefundItem.getRefundNum());
            //存入扣减分摊优惠金额（分）
            tradeOrderRefundItem.setSubtractCouponPrice(couponPrice);
            //运费金额
            String freighPrice = this.priceCalculation(tradeOrderItem.getFreightPrice(), tradeOrderItem.getSkuNum(), tradeOrderRefundItem.getRefundNum());
            tradeOrderRefundItem.setSubtractFreightPrice(freighPrice);
            //退货单明细状态未取消
            tradeOrderRefundItem.setStatus(ORDER_REFUND_NO_CANCEL.getId());
            //订单明细创建时间
            tradeOrderRefundItem.setCreateTime(sdf.format(new Date()));
            //加入到list中整体保存
            list.add(tradeOrderRefundItem);
            //将全部明细退款金额汇总
            refund_price += Double.parseDouble(price);
            subtract_coupon_price += Double.parseDouble(couponPrice);
            freight_price += Double.parseDouble(freighPrice);
        }
//		开始拼接退货单详情数据
//		拼装申请退款状态
        refundInfo.setRefundStatus(REFUND_STATUS_APPLY_RETURN.getId());
        //存入退款总金额
        refundInfo.setRefundPrice(refund_price);
        // 存入优惠总金额
        refundInfo.setSubtractCouponPrice(subtract_coupon_price);
        //存入运费金额
        refundInfo.setRefundFreightPrice(freight_price);
        //存入取消申请状态,状态为未申请
        refundInfo.setStatus(ORDER_REFUND_NO_CANCEL.getId());

        //订单退货信息 创建时间
        refundInfo.setCreateTime(sdf.format(new Date()));

        //保存订单信息
        tradeOrderRefundMapper.insertTradeOrderRefund(refundInfo);
        //批量保存订单明细
        tradeOrderRefundItemService.insertByBatchTradeOrderRefundItem(list);

        //将订单退款表示改为申请退款状态
        HashMap<String, Object> map = new HashMap<>();
        map.put("orderId", Long.valueOf(refundInfo.getOrderId()));
        map.put("isRefund", Integer.valueOf(RefundStatus.ORDER_REFUND_EXAMINE.getId()));
        map.put("refundTime", sdf.format(new Date()));
        tradeOrdersServiceMapper.updateTradeOrderRefundType(map);
        JSONObject resultJson = new JSONObject();
        resultJson.put("refundId",refundNo);
        return JsonUtil.getSuccessJson(resultJson);
    }

    /**
     * 根据卖家id查询查询退货单列表
     *
     * @param jsonObject
     */
    @Override
    public JSONObject getBuyerRefunds(String jsonObject) {
        //转换参数
        JSONObject json = JSONObject.parseObject(jsonObject);
        //配置分页参数
//		页号 json.getInteger("page")
//		每页条数 json.getInteger("rows")
        int pageNum = json.getInteger("page");
        int pageSize = json.getInteger("rows");
        PageHelper.startPage(pageNum, pageSize);
        //1.根据用户id查询出订单表数据。
        //联合查询
        List<TradeOrderRefundInfoVo> list = tradeOrderRefundMapper.getBuyerRefunds(json.getLong("buyerId"));
        PageInfo<TradeOrderRefundInfoVo> pageInfo = new PageInfo<>(list);
        JSONObject result = new JSONObject();
        result.put("total", pageInfo.getTotal());
        result.put("rows", list);
        return JsonUtil.getSuccessJson(result);
    }

    /**
     * 根据退货单id修改退货单状态
     *
     * @param json
     * @return
     */
    @Override
    public JSONObject updateRefundStatus(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        //传入状态不可为空
        if (StringUtil.isEmpty(jsonObject.getString("refundStatus"))) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        //获取退货单信息
        TradeOrderRefundInfo refund = tradeOrderRefundMapper.selectTradeOrderRefundById(jsonObject.getLong("id"));
        //退货单信息是否为空 空则禁止执行
        if (refund == null) {
            return JsonUtil.getErrorJson(TRADE_ORDER_REFUND_NULL);
        }
        //判断当前退货单退货状态是否为为取消退货
        if (!ORDER_REFUND_NO_CANCEL.getId().equals(refund.getStatus())) {
            return JsonUtil.getErrorJson(TRADE_ORDER_REFUND_CANCEL_ERROR);
        }
        //状态如果为同意时，计算奖励金退款
        if (REFUND_STATUS_AGREE_RETURN.getId().equals(jsonObject.getString("refundStatus"))) {
            returnProfit(refund);
        }
        //根据id更新退货单状态
        tradeOrderRefundMapper.updateRefundStatus(jsonObject);
        return JsonUtil.getSuccessJson();
    }


    /**
     * 更新退货单基础信息
     *
     * @param json
     * @return
     */
    @Override
    public JSONObject updateRefundInfo(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
//		查询退款单当前信息
        TradeOrderRefundInfo refund = tradeOrderRefundMapper.selectTradeOrderRefundById(jsonObject.getLong("id"));
        //退货单信息是否为空 空则禁止执行
        if (refund == null) {
            return JsonUtil.getErrorJson(TRADE_ORDER_REFUND_NULL);
        }
        //判断当前退货单退货状态是否为为取消退货
        if (!ORDER_REFUND_NO_CANCEL.getId().equals(refund.getStatus())) {
            return JsonUtil.getErrorJson(TRADE_ORDER_REFUND_CANCEL_ERROR);
        }
        //根据id更新退回单信息
        tradeOrderRefundMapper.updateRefundInfo(refund);
        return JsonUtil.getSuccessJson();
    }

    /**
     * 取消退货
     *
     * @param json id 退货单ID
     * @param json
     * @return
     */
    @Override
    public JSONObject updateRefundCancel(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        /**
         * 根据当前Id拿到退货单基础信息
         */
        TradeOrderRefundInfo refund = tradeOrderRefundMapper.selectTradeOrderRefundById(jsonObject.getLong("id"));
        //判断当前退货单状态 如不是申请退款状态禁止取消
        if (!REFUND_STATUS_APPLY_RETURN.getId().equals(refund.getRefundStatus())) {
            return JsonUtil.getErrorJson(TRADE_ORDER_REFUND_CANCEL_STATUS_ERROR);

        }
        //判断当前退货单退货状态是否为为取消退货
        if (!ORDER_REFUND_NO_CANCEL.getId().equals(refund.getStatus())) {
            return JsonUtil.getErrorJson(TRADE_ORDER_REFUND_CANCEL_ERROR);
        }
        //将取消退货单状态更新至已取消
        jsonObject.put("status", ORDER_REFUND_YES_CANCEL.getId());
        //根据id更新取消状态
        jsonObject.put("cancel_time", DateUtil.getNowTimeStr());
        tradeOrderRefundMapper.updateRefundCancel(jsonObject);

        //更新退货单详情 将状态改为取消
        TradeOrderRefundItem tradeOrderRefundItem = new TradeOrderRefundItem();
        tradeOrderRefundItem.setRefundId(refund.getId());
        tradeOrderRefundItem.setStatus(ORDER_REFUND_YES_CANCEL.getId());
        tradeOrderRefundItemService.updateTradeOrderRefundItemStatus(tradeOrderRefundItem);

        //根据orderid查询是否还有未取消退货的退货单
        TradeOrderRefundInfo findrefundInfo = new TradeOrderRefundInfo();
        findrefundInfo.setOrderId(refund.getOrderId());
        findrefundInfo.setStatus(ORDER_REFUND_NO_CANCEL.getId());
        //查询该订单是否还有退货单
        List<TradeOrderRefundInfo> byOrderIdAndStatus = tradeOrderRefundMapper.getByOrderIdAndStatus(findrefundInfo);

        //如果没有结果则需要更新订单状态
        if (byOrderIdAndStatus.size() == 0) {
            //更新订单中退货状态 并将退货时间更新至空
            HashMap<String, Object> map = new HashMap<>();
            map.put("orderId", Long.valueOf(refund.getOrderId()));
            map.put("isRefund", Integer.valueOf(RefundStatus.ORDER_REFUND_NO.getId()));
            map.put("refundTime", null);
            tradeOrdersServiceMapper.updateTradeOrderRefundType(map);
        }
        return JsonUtil.getSuccessJson();
    }


    /**
     * 根据退款单id查询详情信息
     *
     * @param json
     */
    @Override
    public JSONObject getRefundItem(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        //根据id查询退货单基础信息
        TradeOrderRefundInfo refundInfo = tradeOrderRefundMapper.selectTradeOrderRefundById(jsonObject.getLong("id"));
        //        //判断退货单信息是否为空
        if (refundInfo == null) {
            return JsonUtil.getErrorJson(TRADE_ORDER_REFUND_NULL);
        }
        //将退货单信息转为JSONObject格式
        JSONObject refundInfoItem = JSONObject.parseObject(JSON.toJSONString(refundInfo,SerializerFeature.WriteNullStringAsEmpty));

        refundInfoItem.put("refundSuccessDesc","退款成功");
        refundInfoItem.put("auditDesc","您的订单财务审核中,请稍后");
        refundInfoItem.put("createDesc","您已提交申请，等待审核中");
        //根据退货单Id查询退货单详情
        List<TradeOrderRefundItem> tradeOrderRefundItemList = tradeOrderRefundItemService.getRerundid(jsonObject.getLong("id"));
        //判断退货单详情不为空请求 存入退货单中
        if (tradeOrderRefundItemList.size() > 0) {
            for(TradeOrderRefundItem item:tradeOrderRefundItemList){
                GoodsSkuInfo skuInfo = goodsSkuService.getGoodsSkuInfoBySkuId(Long.valueOf(item.getSkuId()));
               //GoodsSpuInfoForSkuVo skuInfo =  goodsSkuInfoServiceMapper.getGoodsSpuInfoBySpuId(Long.valueOf(item.getSkuId()));
                //商品图片连接
                String pictureUrl = tradeOrdersServiceMapper.getPictureUrlBySkuId(Long.parseLong(item.getSkuId()));
                if (StringUtil.isEmpty(pictureUrl)) {
                    pictureUrl = PICTURE_PLACEHOLDER_SKU.getId();
                }
                item.setPictureUrl(pictureUrl);
                TradeOrderItem tradeOrderItemDo = tradeOrdersServiceMapper.
                        selectSpuNameBySpuId(Long.parseLong(skuInfo.getSpuId()));
                item.setSpuName(tradeOrderItemDo.getSpuName());
                //根据skuId查询商品属性
                GoodsSkuAttr goodsSkuAttr = tradeOrdersServiceMapper.selectSkuAttr(Long.parseLong(item.getSkuId()));
                if (goodsSkuAttr != null) {
                    String[] strings = goodsSkuAttr.getAttrId().split(":");
                    List<Long> attrIdList = new ArrayList<>();
                    if (strings != null && strings.length > 0) {
                        for (String s : strings) {
                            attrIdList.add(Long.parseLong(s));
                        }
                        List<Map> maps = tradeOrdersServiceMapper.selectGoodsAttrValueListByAttrId(attrIdList);
                        item.setGoodsAttrValues(maps);
                    }
                }
            }
            refundInfoItem.put("tradeOrderRefundItems", tradeOrderRefundItemList);
        }
        return JsonUtil.getSuccessJson(refundInfoItem);
    }

    @Override
    public JSONObject wxRefund(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        //根据id查询退货单基础信息
        TradeOrderRefundInfo refundInfo = tradeOrderRefundMapper.selectTradeOrderRefundById(Long.parseLong(jsonObject.getString("refundId")));

        List<TradeOrder> tradeOrders = tradeOrdersServiceMapper.selectByOrderNo(jsonObject.getString("orderNo"));
        if(tradeOrders == null|| tradeOrders.size()== 0){
            return JsonUtil.getErrorJson(RESPONSE_CODE_ERROR);
        }
        String totalFee = tradeOrders.get(0).getOrderMoney();
        JSONObject param = new JSONObject();
        jsonObject.put("refundFee",refundInfo.getRefundPrice());
        jsonObject.put("orderMoney",totalFee);
        try{
            JSONObject resultJson =callFNService(ThirdServerMethodEnum.WX_PAY_REFUND, jsonObject);
            if(RESPONSE_CODE_SUCCESS.getCode().equals(resultJson.get("code"))){
                //更新退款单完成时间
                TradeOrderRefundInfo updateRefund = new TradeOrderRefundInfo();
                updateRefund.setFinishTime(DateUtil.getNowTimeStr());
                tradeOrderRefundMapper.updateTradeOrderRefund(updateRefund);
                return JsonUtil.getSuccessJson();
            }else{
                LOGGER.error("调用center-third  wxrefund出错");
                return JsonUtil.getErrorJson(RESPONSE_CODE_ERROR);
            }
        }catch (Exception e){
           LOGGER.error("调用center-third  wxrefund出错");
            return JsonUtil.getErrorJson(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 比较退货数量是否大于订单数量
     *
     * @param tradeOrderItemList       订单详情
     * @param tradeOrderRefundItemList 退货单详情
     * @param refundsCount             本次要退货数量
     * @return true 表试退货单大于订单数不可退货 false可以退款
     */
    private boolean isCount(List<TradeOrderItem> tradeOrderItemList, List<TradeOrderRefundItem> tradeOrderRefundItemList, Integer refundsCount) {
        //判断如果退款单值为空 直接允许退款
        if (tradeOrderRefundItemList== null ||tradeOrderRefundItemList.size() == 0) {
            return false;
        }
        //计算订单总数
        Integer orderCount = 0;
        for (TradeOrderItem tradeOrderItem : tradeOrderItemList) {

            orderCount += Integer.parseInt(tradeOrderItem.getSkuNum());
        }
        //退货单总数+本次退货数量>订单总数 禁止退货
        if ((tradeOrderRefundItemList.size() + refundsCount) > orderCount) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 金额计算
     * 三种明细计算金额
     * 1：计算实际退货金额 公式=订单详细实付金额/订单详细购买数量*退款数量
     * 2: 计算优惠金额 公式=订单详细优惠价格/订单详细订单数量*退款数量
     * 3: 计算运费金额 公式=订单详细运费金额/订单详细订单数量*退款数量
     *
     * @param price       订单详细金额
     * @param orderSkuNum 订单数量
     * @param refundNum   退款数量
     * @return
     */
    private String priceCalculation(String price, String orderSkuNum, String refundNum) {
        Double freighPrice = Double.parseDouble(price) / Double.parseDouble(orderSkuNum) * Integer.parseInt(refundNum);
        return String.valueOf(freighPrice);
    }


    /**
     * 退款单在同意退货时 执行
     * 退款奖励金收回
     *
     * @param refund 退货单
     */
    private void returnProfit(TradeOrderRefundInfo refund) {
        //获取买家树列表
        List<JSONObject> userTreelist = new ArrayList<>();
        Long buyerId = Long.valueOf(refund.getBuyerId());
        for (int i = 1; i <= 6; i++) {
            JSONObject userJson = userTreesService.getByUserId(buyerId);
            if (userJson != null && userJson.getLong("father_id") != null) {
                userJson.put("level", i);
                userTreelist.add(userJson);
                buyerId = userJson.getLong("father_id");
            } else {
                break;
            }
        }
        //获取收益配置表数据
        List<JSONObject> configs = configsService.getConfigs();
        //对比用户树 与 配置表级别对应 计算退奖励金金额
        //创建收益日志数组
        List<JSONObject> logsList = new ArrayList<>();
        //循环用户树
        for (int i = 0; i < userTreelist.size(); i++) {
            //拿到单独用户数据与级别
            JSONObject userTreeInfo = userTreelist.get(i);
            //循环配置规则
            for (int k = 0; k < configs.size(); k++) {
                JSONObject configInfo = configs.get(k);
                //使用级别与配置规则做比较
                if (configInfo.getInteger("level") == userTreeInfo.getInteger("level")) {
                    //计算退款金额 退款金额* 对应级别的规则
                    double profit = refund.getRefundPrice() * configInfo.getDouble("proportion");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = sdf.format(new Date());
                    JSONObject userProfitsInfo = userProfitsService.getByUserId(userTreeInfo.getLong("user_id"));
                    if (userProfitsInfo != null) {
                        //修改冻结金额
                        userProfitsInfo.put("lodeFrozen", userProfitsInfo.getDouble("lode_frozen") - profit);
                        //修改总金额
                        userProfitsInfo.put("totalProfit", userProfitsInfo.getDouble("total_profit") - profit);
                        //修改时间
                        userProfitsInfo.put("modifyTime", date);
                        //更新收益表
                        userProfitsService.updateRefundProfits(userProfitsInfo);
                    }
                    //拼装logs数据
                    JSONObject logs = new JSONObject();
                    //生成id
                    logs.put("id", SnowflakeUtil.getPrimaryKey(SHOP_PROFIT_LOGS));
                    //生成订单id
                    logs.put("orderId", refund.getOrderId());
                    //生成用户Id
                    logs.put("userId", userTreeInfo.getLong("user_id"));
                    //生成订单提供者编号
                    logs.put("providerId", refund.getBuyerId());
                    //生成类型：退货类型
                    logs.put("profitType", PROFIT_TYPE_REFUND.getId());
                    //生成退奖励金金额
                    logs.put("profit", profit - (profit + profit));
                    //生成状态
                    logs.put("status", BILLING_STATUS_WAITING_BILL.getId());
                    //生成创建时间
                    logs.put("createTime", date);
                    //生成修改时间
                    logs.put("modifyTime", date);
                    logsList.add(logs);
                }
            }
        }
        //批量保存日志数据
        userProfitLogsService.addBatchUserProfitLogs(logsList);
    }
}
