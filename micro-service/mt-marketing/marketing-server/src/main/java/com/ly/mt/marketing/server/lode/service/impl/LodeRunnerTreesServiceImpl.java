package com.ly.mt.marketing.server.lode.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.common.entity.marketing.*;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.marketing.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.marketing.server.lode.mapper.LodeRunnerTreesServiceMapper;
import com.ly.mt.marketing.server.lode.service.LodeRunnerTreesService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 zhuyh
 * @创建时间 2019/6/19
 * @描述
 */
@Service
public class LodeRunnerTreesServiceImpl extends BaseServiceImpl implements LodeRunnerTreesService {

    // 淘金收益
    private static final String LODE_PROFITS = "lodeProfits";

    // 淘金排名
    private static final String LODE_RANK = "lodeRank";

    // 淘金用户数
    private static final String LODE_USER_COUNT = "lodeUserCount";

    @Resource
    private LodeRunnerTreesServiceMapper lodeRunnerTreesServiceMapper;
    @Override
    public JSONObject getDetails(String json) throws Exception {
        LodeRunnerTreesParamsVo lodeRunner = JSONObject.parseObject(json, LodeRunnerTreesParamsVo.class);
        lodeRunner.setUserId(getUserId());
        Map<String, Object> result = new HashMap<>();
        // 分页查询直接下级
        PageHelper.startPage(lodeRunner.getPage(), lodeRunner.getRows());
        List<LodeRunnerVo> list = lodeRunnerTreesServiceMapper.getNextRunnerList(lodeRunner);
        PageInfo<LodeRunnerVo> pageInfo = new PageInfo<LodeRunnerVo>(list);
        result.put(TOTAL, pageInfo.getTotal());
        result.put(ROWS, getDetails(list, getUserId()));
        return JsonUtil.getSuccessJson(result);
    }

    /**
     *@Description 获取详情
     *@Author  zhuyh
     */
    private List<LodeRunnerVo> getDetails(List<LodeRunnerVo> list, Long userId) {
        if(CollectionUtils.isEmpty(list)){
            return list;
        }
        // 入住商家id
        List<Long> liveList = new ArrayList<>();
        list.forEach(s->{
            if (s != null && "Y".equals(s.getLiveInState())){
                liveList.add(s.getShopId());
            }
        });
        if (CollectionUtils.isEmpty(liveList)){
            return list;
        }
        // 上架进货数量map<shop_id, count>
        Map<Long, Integer> purchasesCountMap = getPurchasesCount(liveList);

        Map<Long, BigDecimal> profitsMap = getProfits(liveList, userId);
        return getFinishDetails(list, purchasesCountMap, profitsMap);
    }

    /**
     *@Description 获取最终详情
     *@Author  zhuyh
     */
    private List<LodeRunnerVo> getFinishDetails(List<LodeRunnerVo> list, Map<Long, Integer> purchasesCountMap, Map<Long, BigDecimal> profitsMap) {
        list.forEach(s ->{
            if (s != null){
                if (purchasesCountMap.containsKey(s.getShopId())){
                    s.setPurachasesNum(purchasesCountMap.get(s.getShopId()));
                } else {
                    s.setPurachasesNum(0);
                }
                if(profitsMap.containsKey(s.getShopId())){
                    s.setProfits(profitsMap.get(s.getShopId()));
                } else {
                    s.setProfits(BigDecimal.valueOf(0.0));
                }
            }
        });

        return list;
    }

    /**
     *@Description 获取返利
     *@Author  zhuyh
     */
    private Map<Long, BigDecimal> getProfits(List<Long> liveList, Long userId) {
        if (CollectionUtils.isEmpty(liveList)){
            return new HashMap<>();
        }
        LodeRunnerProfitsParamsVo vo= new LodeRunnerProfitsParamsVo();
        vo.setShopIds(liveList);
        vo.setUserId(userId);
        List<ShopProfitsLogs> list = lodeRunnerTreesServiceMapper.selectShopProfits(vo);
        if (CollectionUtils.isEmpty(list)){
            return new HashMap<>();
        }
        Map<Long, BigDecimal> map = new HashMap<>();
        list.forEach(s ->{
            if (s != null){
                map.put(s.getShopId(), s.getProfit());
            }
        });
        return map;
    }

    /**
     *@Description 获取商家进货数量
     *@Author  zhuyh
     */
    private Map<Long, Integer> getPurchasesCount(List<Long> liveList) {
        List<ShopPurchasesVo> purchasesVos = lodeRunnerTreesServiceMapper.selectShopPurchasesCount(liveList);
        if (CollectionUtils.isEmpty(purchasesVos)){
            return new HashMap<>();
        }
        Map<Long, Integer> map = new HashMap<>(purchasesVos.size());
        purchasesVos.forEach(v ->{
            if (v != null){
                map.put(v.getShopId(), v.getCou());
            }
        });
        return map;
    }

    @Override
    public JSONObject getTeamHeadDetails(String json) throws Exception {
        LodeRunnerTreesParamsVo lodeRunner = JSONObject.parseObject(json, LodeRunnerTreesParamsVo.class);
        lodeRunner.setUserId(getUserId());
        Map<String, Object> result = new HashMap<>();
        // 分页查询直接下级
        PageHelper.startPage(lodeRunner.getPage(), lodeRunner.getRows());
        List<LodeRunnerVo> list = lodeRunnerTreesServiceMapper.getNextRunnerList(lodeRunner);
        PageInfo<LodeRunnerVo> pageInfo = new PageInfo<LodeRunnerVo>(list);
        result.put(TOTAL, pageInfo.getTotal());
        result.put(ROWS, getTeamDetails(list));
        return JsonUtil.getSuccessJson(result);
    }

    /**
     *@Description 查询团队详情
     *@Author  zhuyh
     */
    private List<LodeRunnerVo> getTeamDetails(List<LodeRunnerVo> list) {
        if (CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        for (LodeRunnerVo vo : list){
            if (vo != null && vo.getUserId() != null){
                LodeRunnerVo details = lodeRunnerTreesServiceMapper.selectTeamDetails(vo);
                if (details != null){
                    vo.setProfits(details.getProfits());
                    vo.setPurachasesNum(details.getPurachasesNum());
                } else {
                    vo.setPurachasesNum(0);
                    vo.setProfits(BigDecimal.valueOf(0.0));
                }
            }
        }
        return list;
    }

    @Override
    public JSONObject getMine(String json) throws Exception {
        LodeRunnerProfitsParamsVo paramsVo = new LodeRunnerProfitsParamsVo();
        paramsVo.setUserId(getUserId());
        // 查询我的淘金收益
        Map<String, Object> result = new HashMap<>();
        LodeRunnerProfitsParamsVo vo = lodeRunnerTreesServiceMapper.getMyLodeProfits(paramsVo);
        if (vo == null){
            vo = new LodeRunnerProfitsParamsVo();
            vo.setProfits(BigDecimal.valueOf(0.0));
        }
        result.put(LODE_PROFITS, vo.getProfits());
        // 查询我的淘金排名
        result.put(LODE_RANK, lodeRunnerTreesServiceMapper.getLodeRankByMe(vo));
        // 查询总淘金人数
        result.put(LODE_USER_COUNT, lodeRunnerTreesServiceMapper.getLodeUserCount());
        return JsonUtil.getSuccessJson(result);
    }
}
