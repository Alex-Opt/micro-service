package com.ly.mt.order.server.activity.service.impl;

import com.ly.mt.core.base.entity.activity.*;
import com.ly.mt.core.base.entity.goods.GoodsSkuModel;
import com.ly.mt.core.base.entity.goods.GoodsSkuPrice;
import com.ly.mt.core.base.entity.user.UserPointGrade;
import com.ly.mt.order.server.activity.mapper.ActivityInfoServiceMapper;
import com.ly.mt.order.server.activity.service.ActivityInfoService;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.ly.mt.core.base.dict.ActivityUserScope.ACTIVITY_ALL_CATEGORIES;
import static com.ly.mt.core.base.dict.ActivityUserScope.ACTIVITY_PART_CATEGORIES;
import static com.ly.mt.core.base.dict.CouponActivityType.COUPON_ACTIVITY_TYPE_FULL_REDUCTION;
import static com.ly.mt.core.base.dict.GradeLimitType.*;

@Service
public class ActivityInfoServiceImpl extends BaseServiceImpl implements ActivityInfoService {
    private  List<String>  activityIds = new ArrayList<>();

    //日志
    private final static Logger LOGGER = LoggerFactory.getLogger(ActivityInfoServiceImpl.class);



    @Resource
    ActivityInfoServiceMapper activityInfoServiceMapper;

    @Override
    public BigDecimal calcPreferentialPrice(List<GoodsSkuModel> itemList,String oldOriginPrice,String userId) {

        //查询活动(在有效期内的满减活动)
       BigDecimal orderDiscountMoney = BigDecimal.ZERO;
       String currentUserId =userId;
        try{
            ActivityInfo param = new ActivityInfo();
            param.setCouponActivityType(COUPON_ACTIVITY_TYPE_FULL_REDUCTION.getId());
            List<ActivityInfo> activityObjects = activityInfoServiceMapper.
                    selectActivityForFullReductionValid(param);
            Optional<List<ActivityInfo>> option = Optional.ofNullable(activityObjects);
            UserPointGrade userPointGrade = activityInfoServiceMapper.selectGradeIdByUsrId(currentUserId);
            Optional<UserPointGrade> userPoint = Optional.ofNullable(userPointGrade);
            //当前用户等级
            long currentGrade = 0L;
            if(userPoint.isPresent() && userPoint.get() != null){
                currentGrade = Long.valueOf(userPoint.get().getPointGradeId());
            }

            if(option.isPresent() && option.get().size() >0){
                for (ActivityInfo info : option.get()) {
                        //根据活动ID查询限制类型
                        ActivityUserGrade activityUserGrade  =  activityInfoServiceMapper.selectUserGradeByActivityId(Long.valueOf(info.getId()));
                        Optional<ActivityUserGrade> activityUserGradeOptional = Optional.ofNullable(activityUserGrade);
                        if(activityUserGradeOptional.isPresent()){
                            String limitType = activityUserGradeOptional.get().getLimitType();
                            //根据活动ID查询参与活动的具体spu信息
                            List<ActivityGoodsDetail>  activityGoodsDetails = activityInfoServiceMapper.selectByActivityId(Long.valueOf(info.getId()));
                            Optional<List<ActivityGoodsDetail>> listOptional = Optional.ofNullable(activityGoodsDetails);
                            if(listOptional.isPresent()){
                                //如果是限定商品
                                if(ACTIVITY_PART_CATEGORIES.getId().equals(info.getGoodsLimitCategory())){
                                    orderDiscountMoney = orderDiscountMoney.add(judgeUserGradeLimit(itemList,orderDiscountMoney, currentGrade, info, activityUserGrade, limitType, listOptional.get(), true));
                                }else if(ACTIVITY_ALL_CATEGORIES.getId().equals(info.getGoodsLimitCategory())){
                                    //如果是全品类
                                        orderDiscountMoney = orderDiscountMoney.add(judgeUserGradeLimit(itemList, orderDiscountMoney, currentGrade, info, activityUserGrade, limitType, listOptional.get(), false));
                                }
                            }
                        }
                }
                return orderDiscountMoney;
            }else{
                return null;
            }
        }catch (Exception e){
            LOGGER.error("查询满减优惠活动异常",e.getMessage());
            return orderDiscountMoney;
        }
    }


    private BigDecimal judgeUserGradeLimit(List<GoodsSkuModel> itemList, BigDecimal orderDiscountMoney, long currentGrade, ActivityInfo info, ActivityUserGrade ctivityUserGrade, String limitType, List<ActivityGoodsDetail> activityGoodsDetails, boolean categoryLimitFlag) {
        //用户等级无限制
        if (GRADE_NO_LIMIT.getId().equals(limitType)){
            //等级用户（群体）单次购买spu商品数量允许的最大值（ -1-无限制 。正常多少表示限制多少）'
            String userLimtCount = ctivityUserGrade.getUserLimitCount();
            //促销活动允许用户所属等级（群体）使用总次数上限（ -1-无限制 。正常多少表示限制多少）
            long totalCount = ctivityUserGrade.getTotalCount();
            if(activityGoodsDetails != null && activityGoodsDetails.size()>0){
                if(Long.valueOf(userLimtCount) != -1){
                    orderDiscountMoney =calcActivityPrice(itemList, activityGoodsDetails, info, ctivityUserGrade, categoryLimitFlag);
                }
            }
        }else if (GRADE_MIN_LIMIT.getId().equals(limitType)) {
            //有最低等级限制
            //如果当前用户ID >= 最低限制ID
            if(currentGrade >= Long.valueOf(ctivityUserGrade.getGradeMinId())) {
                orderDiscountMoney = calcActivityPrice(itemList, activityGoodsDetails, info, ctivityUserGrade, categoryLimitFlag);
            }
        }else if (GRADE_MAX_LIMIT.getId().equals(limitType)){
            //如果当前用户ID <= 最高限制ID
            if (currentGrade <= Long.valueOf(ctivityUserGrade.getGradeMaxId())){
                orderDiscountMoney = orderDiscountMoney.add(calcActivityPrice(itemList, activityGoodsDetails, info, ctivityUserGrade, categoryLimitFlag));
            }
        }else if(GRADE_MIN_MAX_LIMIT.getId().equals(limitType)){
            ////如果当前用户ID <= 最高限制ID >= 最高限制
            if(currentGrade >= Long.valueOf(ctivityUserGrade.getGradeMinId()) && currentGrade <= Long.valueOf(ctivityUserGrade.getGradeMaxId())){
                orderDiscountMoney = orderDiscountMoney.add(calcActivityPrice(itemList, activityGoodsDetails, info, ctivityUserGrade, categoryLimitFlag));
            }
        }
        return orderDiscountMoney;
    }


    private BigDecimal calcActivityPrice(List<GoodsSkuModel> itemList, List<ActivityGoodsDetail> activityGoodsDetails, ActivityInfo info,ActivityUserGrade ctivityUserGrade, boolean categoryLimitFlag) {
        //调试点
        BigDecimal orderDiscountMoney = new BigDecimal(Double.toString(0L));
        Map<String,Integer> itemMap = itemList.stream()
                .collect(Collectors.groupingBy(v -> v.getSpuId(),
                        Collectors.summingInt(v -> Integer.valueOf(v.getSkuNum()))));
        if(!itemMap.isEmpty()){
            Iterator<Map.Entry<String, Integer>> it = itemMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Integer> entry = it.next();
               //System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                for(ActivityGoodsDetail detail:activityGoodsDetails){
                    if(categoryLimitFlag == true){
                        if(entry.getKey().equals(detail.getSpuId())){
                            //根据订单中的sku价格*数量 和info.start_fee作比较
                            BigDecimal totalPrice = BigDecimal.ZERO;
                            for (GoodsSkuModel mo : itemList) {
                                    if(entry.getKey().equals(mo.getSpuId())){
                                        //找出skuId去表中查价格
                                        GoodsSkuPrice goodsPri = activityInfoServiceMapper.queryGoodsSkuPriceBySkuId(Long.parseLong(mo.getSkuId()));

                                        BigDecimal price = new BigDecimal(goodsPri.getSkuPrice());
                                        BigDecimal skuNum = new BigDecimal(mo.getSkuNum());
                                        totalPrice = price.multiply(skuNum);
                                    }
                            }
                            if(totalPrice.compareTo(new BigDecimal(info.getStartFee())) > 0){
                                //该商品参与了活动
                                orderDiscountMoney =  orderDiscountMoney.add(getOrderDiscountMoney(info,ctivityUserGrade,orderDiscountMoney,entry));
                            }

                        }
                    }else{
                        //全品类
                        if(entry.getValue() <= Integer.parseInt(info.getMaxSellNum())){
                            orderDiscountMoney =  orderDiscountMoney.add(getOrderDiscountMoney(info,ctivityUserGrade,orderDiscountMoney,entry));
                        }
                    }
                }
            }
        }
        return orderDiscountMoney;
    }

    private BigDecimal getOrderDiscountMoney(ActivityInfo info, ActivityUserGrade ctivityUserGrade, BigDecimal orderDiscountMoney, Map.Entry<String, Integer> entry) {

        Map<String,Object> resultMap = new HashMap<>();
        List<ActivityModel> activityModelList = new ArrayList<>();

        if(info.getMaxSellNum() != null && Integer.valueOf(info.getMaxSellNum()) >0){
            //不限制 不检查 限制条件就剩下单次允许最大购买量和允许使用的总次数这两个即可。
            //如果user_limit_count为-1，表示不限制。不为-1则表示限制数。入参可以统计购买的商品数量有没有超过单次的数值
            ActivityUserGradeDetail activityUserGradeDetail = new ActivityUserGradeDetail();
            activityUserGradeDetail.setActivityId(info.getId());
            activityUserGradeDetail.setSpuId(entry.getKey());
            Map<String,Object> map = activityInfoServiceMapper.selectUseCountBySpuIDAndActivityId(activityUserGradeDetail);
            if( Long.valueOf(ctivityUserGrade.getUserLimitCount()) != -1 &&
                    //统计购买的商品数量有没有超过单次的数值
                    //Long.valueOf(String.valueOf(map.get("scount"))) 统计的购买数量
                    Long.valueOf(ctivityUserGrade.getUserLimitCount()) >= Long.valueOf(entry.getValue())){
                //如果有优惠价
                if(info.getDenomination() != null){
                    String denomination = info.getDenomination();
                    orderDiscountMoney =   orderDiscountMoney.add(new BigDecimal(denomination));
                    this.activityIds.add(info.getId());
                }
            }
            if(Long.valueOf(ctivityUserGrade.getUserLimitCount()) == -1 ){
                if(ctivityUserGrade.getTotalCount() >0 &&
                        //如果total_count>0,通过用户所属等级去用户等级购买明细表统计该群体已经购买了多少商品，有没有超限
                        ctivityUserGrade.getTotalCount() > Long.valueOf(String.valueOf(map.get("scount")))){
                    if(info.getDenomination() != null){
                        String denomination = info.getDenomination();
                        orderDiscountMoney.add(new BigDecimal(denomination));
                        this.activityIds.add(info.getId());
                    }
                }
            }
        }
        return orderDiscountMoney;
    }

    @Override
    public List<String> getActivityIds() {
        return activityIds;
    }


    @Override
    public List<ActivityModel> getActivityModelsForOrder(){
        List<ActivityModel> result = new ArrayList<>();
        Optional<List<String>> option  = Optional.ofNullable(this.activityIds);
        if(option.isPresent() && option.get().size() >0){
            for (String actvityId:option.get()) {
                    ActivityInfo info = activityInfoServiceMapper.selectByPrimaryKey(Long.parseLong(actvityId));
                    if(info != null){
                        ActivityModel m = new ActivityModel(info.getId(),info.getName(), info.getDenomination(),
                                info.getDiscountRate(),info.getStartTime(),info.getEndTime());
                        result.add(m);
                    }
            }
        }
        return result;
    }
}
