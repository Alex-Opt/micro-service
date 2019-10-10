package com.ly.mt.gzg.b.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.method.GzgBMethodEnum;
import com.ly.mt.gzg.b.web.base.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"c端生成订单通知b端生成补货记录"})
@RestController
@RequestMapping("/gzgb/notify")
public class NotifyController extends BaseController {

    @PostMapping("/cNotify")
    @Async
    public String cNotify(@RequestBody String json){
        JSONObject o = JSON.parseObject(json);
        callGzgBServer(GzgBMethodEnum.GZG_C_NOTIF_B, o);
        return "success";
    }
}
