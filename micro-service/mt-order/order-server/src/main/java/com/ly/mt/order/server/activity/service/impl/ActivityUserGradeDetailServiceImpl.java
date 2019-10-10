package com.ly.mt.order.server.activity.service.impl;

import com.ly.mt.core.base.entity.activity.ActivityModel;
import com.ly.mt.core.base.entity.activity.ActivityUserGradeDetail;
import com.ly.mt.core.base.entity.goods.GoodsSkuModel;
import com.ly.mt.core.base.entity.point.PointGrade;
import com.ly.mt.core.base.entity.trade.OrderModel;
import com.ly.mt.core.base.entity.user.UserPointGrade;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.order.server.activity.mapper.ActivityUserGradeDetailServiceMapper;
import com.ly.mt.order.server.activity.service.ActivityUserGradeDetailService;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.ly.mt.core.base.dict.PrimaryKey.ACTIVITY_USER_GRADE_DETAIL;

/**
 * 参与促销优惠活动的等级用户使用明细表-业务层
 *
 * @author zhanglifeng
 * @date 2019-05-27
 */
@Service
public class ActivityUserGradeDetailServiceImpl extends BaseServiceImpl implements ActivityUserGradeDetailService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ActivityUserGradeDetailServiceImpl.class);
    @Resource
    private ActivityUserGradeDetailServiceMapper activityUserGradeDetailServiceMapper;

    /**
     * 批量新增
     *
     * @param orderModel
     * @return
     */
    @Override
    public int batchInsertActivityUserGradeDetail(OrderModel orderModel) throws Exception {
        List<ActivityUserGradeDetail> list = new ArrayList<>(1);
        List<GoodsSkuModel> itemList = orderModel.getItemList();
        List<ActivityModel> activityList = orderModel.getActivityList();
        if (activityList == null) {
            return 0;
        }
        String loginUserId = orderModel.getOrderVo().getBuyerId();
        //查询用户等级信息
        UserPointGrade userGrade = activityUserGradeDetailServiceMapper.getUserGradeByUserId(Long.parseLong(loginUserId));
        if (userGrade == null) {
            return 0;
        }
        String pointGradeId = userGrade.getPointGradeId();
        PointGrade pointGrade = activityUserGradeDetailServiceMapper.getUserGradeNameByGradId(pointGradeId);
        String gradeName = pointGrade.getTitle();
        Map<String, Integer> map = new HashMap(16);
        Set<String> set = new HashSet();
        for (GoodsSkuModel goodsSkuModel : itemList) {
            set.add(goodsSkuModel.getSpuId());
            Integer num = map.get(goodsSkuModel.getSpuId())==null?0:map.get(goodsSkuModel.getSpuId());
            map.put(goodsSkuModel.getSpuId(), Integer.valueOf(goodsSkuModel.getSkuNum()) + num);
        }
        ActivityUserGradeDetail activityUserGradeDetail = null;
        for (ActivityModel activityModel : activityList) {
            for (String spuId : set) {
                activityUserGradeDetail = new ActivityUserGradeDetail();
                activityUserGradeDetail.setId(SnowflakeUtil.getPrimaryKey(ACTIVITY_USER_GRADE_DETAIL));
                activityUserGradeDetail.setActivityId(activityModel.getActivityId());
                activityUserGradeDetail.setGradeId(pointGradeId);
                activityUserGradeDetail.setGradeName(gradeName);
                activityUserGradeDetail.setSpuId(spuId);
                activityUserGradeDetail.setSpuNum(map.get(spuId).toString());
                activityUserGradeDetail.setCreateTime(DateUtil.getNowTimeStr());
                list.add(activityUserGradeDetail);
            }
        }
        if(list != null && list.size()>0){
            return activityUserGradeDetailServiceMapper.batchInsertActivityUserGradeDetail(list);
        }
        return 0;
    }
}
