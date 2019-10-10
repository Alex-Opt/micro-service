package com.ly.mt.marketing.web.base.fegin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 *@Description
 *@Author  zhuyh
 */
@Component
public class MarketingServerHystrix implements MarketingServerClient {
    @Override
    public JSONObject requestDistribute(JSONObject jsonObject) {
        throw new RuntimeException("marketing-server服务异常!");
    }
}
