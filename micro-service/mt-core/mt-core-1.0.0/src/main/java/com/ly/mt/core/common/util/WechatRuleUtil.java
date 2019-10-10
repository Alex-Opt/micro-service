package com.ly.mt.core.common.util;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.hd.Rule.RuleCompareResult;
import org.springframework.util.StringUtils;

/**
 * @author panjingtian
 * @description V1规则字典
 * TODO
 * 着急上线，先写死，后期做规则字典在做解耦
 * @date 2019/8/14 11:48 PM
 */
public class WechatRuleUtil {

    public static RuleCompareResult compare(String jsonRule) {
        JSONObject jsonObject = JSONObject.parseObject(jsonRule);
        RuleCompareResult result = new RuleCompareResult();
        String ruleType = jsonObject.getString("ruleType");

        Boolean flag = false;
        switch (ruleType) {

            case "1-1":
                Integer countCondition = jsonObject.getInteger("countCondition");
                Integer countData = jsonObject.getInteger("countData");
                if (countData >= countCondition)
                    flag = true;
                break;
            case "2-2":
                String imageUrl = jsonObject.getString("imageUrl");
                if (!StringUtils.isEmpty(imageUrl))
                    flag = true;
                break;
        }

        result.setJsonObject(jsonObject);
        result.setFlag(flag);
        return result;
    }


}
