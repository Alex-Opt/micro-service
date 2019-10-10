package com.ly.mt.order.server.activity.mapper;

import com.ly.mt.core.base.entity.activity.ActivityGoodsDetail;
import com.ly.mt.core.base.entity.activity.ActivityInfo;
import com.ly.mt.core.base.entity.activity.ActivityUserGrade;
import com.ly.mt.core.base.entity.activity.ActivityUserGradeDetail;
import com.ly.mt.core.base.entity.goods.GoodsSkuPrice;
import com.ly.mt.core.base.entity.user.UserPointGrade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ActivityInfoServiceMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ActivityInfo record);

    ActivityInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ActivityInfo record);



    /**
     * 查询在有效其内的满减活动
     * @param record
     * @return
     */
    List<ActivityInfo> selectActivityForFullReductionValid(ActivityInfo record);



    /**
     * //根据活动ID查询实体
     * @param activityId
     * @return
     */
    List<ActivityGoodsDetail> selectByActivityId(long activityId);

    /**
     * 查询使用次数和s已经购买的spu数量
     */

    Map<String,Object> selectUseCountBySpuIDAndActivityId(ActivityUserGradeDetail record);

    /**
     * 根据活动Id查询实体
     */

    ActivityUserGrade selectUserGradeByActivityId(long activityId);

    /**
     * 根据用户Id查询用户等级
     * @param userId
     * @return
     */
    UserPointGrade selectGradeIdByUsrId(String userId);



    /**
     * 查询商品sku价格数据
     * @param skuId
     * @return
     */
    GoodsSkuPrice queryGoodsSkuPriceBySkuId(Long skuId);

}