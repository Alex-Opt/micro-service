package com.ly.mt.activity.web.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description
 * @author panjingtian
 * @date 2019/6/15 1:53 PM
 */
@FeignClient(value = "activity-server",fallback = ActivityServerHystrix.class)
public interface ActivityFeignClient {

    @RequestMapping(value = "/activity/public/server/requestDistribute" ,method = RequestMethod.POST)
    JSONObject requestDistribute(@RequestBody JSONObject map);

}
