package com.ly.mt.task.base.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @author zhanglifeng
 */
@Component
public class MtThirdServiceHystrix implements MtThirdServiceClient {
    @Override
    public JSONObject requestDistribute(JSONObject jsonObject) {
        throw new RuntimeException("center_third服务异常!");
    }
}