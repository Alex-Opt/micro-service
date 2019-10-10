package com.ly.mt.mall.h5.profits.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.profits.service.UserProfitsService;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class UserProfitsServiceImpl extends BaseServiceImpl implements UserProfitsService {


    private final static Logger LOGGER = LoggerFactory.getLogger(UserProfitsServiceImpl.class);


    /**
     * 名称数据
     */
    private static final String[] nameDatas = {"朱","秦","尤","许","何","吕","施","张"
            ,"孔","曹","严","华","金","魏","陶","姜"
            ,"赵","钱","孙","李","周","吴","郑","王"
            ,"冯","陈","褚","卫","蒋","沈","韩","杨"};

    // 单笔收益金额最大值
    private static final Integer PROFITS_TOP_SINGLE_MAX_VALUE = 25;
    // 单笔收益金额最小值
    private static final Integer PROFITS_TOP_SINGLE_MIN_VALUE = 15;
    // 月成交次数
    private static final Integer PROFITS_TOP_MONTH_ORDER = 2;
    // 显示系数
    private static final Integer PROFITS_TOP_MONTH_SHOW_COEFFICIENT = 5;
    // 随机位数最大值
    private static final Integer PROFITS_TOP_MONTH_END_MAX = 5;
    // 排行榜中最大的星星数
    private static final Integer PROFITS_TOP_START_MAX_VAL = 4;


    @Override
    public ResponseJson getCashScorolling() {
        // 生成的数据
        List list = new ArrayList();
        Map resultMap = new HashMap();
        Random ra = new Random();
        for (int i = 0; i< 20; i ++){
            Map map = new HashMap();
            map.put("userName", getRandomName(ra));
            map.put("profit", BigDecimal.valueOf(getRandom(ra, 100)));
            list.add(map);
        }
        resultMap.put("rows", list);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, resultMap);
    }

    @Override
    public ResponseJson getRank() {
        // 先查询真实数据，如果真实数据不足就用假数据代替
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("searchDate", new Date());
        try{
            Random ra = new Random();
            //获取数据中心数据
            List rankList = getTopList(jsonObject, ra);
            // 获取假数据
            getProfitsUserRanks(rankList, ra);
            // 排序

            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, rankList);
        }catch (Exception e){
            LOGGER.error("获取赚钱排行榜数据中心报错！", e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getDetails() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", getLoginUserId());
        try {
            String result = callDataCenter(DataCenterMethod.PROFITS_USER_PROFITS_DETAILS, jsonObject);
            if (StringUtil.isEmpty(result)) {
                LOGGER.error("数据中心返回用户赚钱数据为空,userId={}", getLoginUserId());
                return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
            }
            JSONObject resultJson = JSONObject.parseObject(result);
            Map resultMap = new HashMap();
            resultMap.put("totalProfit", resultJson.getString("totalProfit"));
            resultMap.put("lode", resultJson.getString("lode"));
            resultMap.put("lodeFrozen", resultJson.getString("lodeFrozen"));
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, resultJson);
        } catch (Exception e) {
            LOGGER.error("调用数据中心报错！", e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }    }

    @Override
    public ResponseJson getLogs(Integer page, Integer rows, Integer type) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", getLoginUserId());
        jsonObject.put("rows", rows);
        jsonObject.put("page", page);
        jsonObject.put("type", type);
        try {
            String result = callDataCenter(DataCenterMethod.PROFITS_USER_PROFIT_LOGS, jsonObject);

            JSONObject resultJson = JSONObject.parseObject(result);
            JSONArray array = resultJson.getJSONArray("rows");
            Map resultMap = new HashMap();
            resultMap.put("total", resultJson.getString("total"));
            List list = new ArrayList();
            if (!CollectionUtils.isEmpty(array)){
                for (int i = 0; i < array.size(); i++) {
                    JSONObject node = array.getJSONObject(i);
                    Map map = new HashMap();
                    map.put("id", node.getString("id"));
                    map.put("profitType", node.getIntValue("profitType"));
                    map.put("friendName", node.getString("friendName"));
                    map.put("profit", node.getString("profit"));
                    map.put("createTime", node.getString("createTime"));
                    list.add(map);
                }
            }
            resultMap.put("rows", list);
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, resultMap);
        } catch (Exception e) {
            LOGGER.error("调用数据中心报错！", e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description:    获取排行榜的假数据
     * @Author:         zhuyh
     * @CreateDate:     2019/7/5 7:06
     */
    private void getProfitsUserRanks(List rankList, Random ra) {
        if (rankList.size() >= 10){
            return ;
        }
        int size = 10 - rankList.size();
        // 生成剩余的排行榜值
        for (int i =0 ; i < size; i++){
            Map map = new HashMap();
            map.put("userName", getRandomName(ra) + getStar(ra, null));
            map.put("profits", new BigDecimal(getProfitsByTop(ra)));
            rankList.add(map);
        }
    }

    /**
     * @Description:    获取排行榜中的值
     * @Author:         zhuyh
     */
    private Integer getProfitsByTop(Random ra){
        // 排行榜中的赚钱收益额：单笔收益金额(15-25元) * 月成交次数 (2) * 显示系数(5)  - 随机尾数（1-5）
        return (getRandom(ra, PROFITS_TOP_SINGLE_MAX_VALUE - PROFITS_TOP_SINGLE_MIN_VALUE) + PROFITS_TOP_SINGLE_MIN_VALUE) * PROFITS_TOP_MONTH_ORDER * PROFITS_TOP_MONTH_SHOW_COEFFICIENT - getRandom(ra, PROFITS_TOP_MONTH_END_MAX);
    }

    /**
     * @Description: 获取排行榜数据中心数据
     * @Author:         zhuyh
     */
    private List getTopList(JSONObject jsonObject, Random ra) throws Exception {
        List list = new ArrayList();
        String resultRankList = callDataCenter(DataCenterMethod.PROFITS_USER_PROFIT_LOGS_FRIEDNS_ORDER_RANK, jsonObject);
        JSONObject resultJson = JSONObject.parseObject(resultRankList);
        // 为数据处理姓名
        JSONArray array = resultJson.getJSONArray("rows");
        for (int i = 0; i < array.size(); i++) {
            JSONObject node = array.getJSONObject(i);
            Map map = new HashMap();
            String userName = node.getString("userName");
            map.put("userName", userName.substring(0,1) + getStar(ra, userName.length() -1 > 4 ? 4 : userName.length() - 1));
            map.put("profits", node.getString("profits"));
            list.add(map);
        }
        return list;
    }



    /**
     * @Description: 获取星星数
     * @Author:         zhuyh
     */
    private String getStar(Random ra, Integer value) {
        if (value == null){
            value = getRandom(ra, PROFITS_TOP_START_MAX_VAL) + 1;
        }
        String starStr = "";
        for (int i = 0 ; i < value; i++){
            starStr += "*";
        }
        return starStr;
    }


    /**
     * @Description:    随机获得姓氏名称
     * @Author:         zhuyh
     */
    private String getRandomName(Random ra) {
        return nameDatas[getRandom(ra, nameDatas.length)];
    }



    /**
     * @Description: 生成随机数
     * @Author:         zhuyh
     */
    private int getRandom(Random ra, int length) {
        return ra.nextInt(length);
    }

}
