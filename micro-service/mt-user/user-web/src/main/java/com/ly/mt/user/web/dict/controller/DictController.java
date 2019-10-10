package com.ly.mt.user.web.dict.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.util.EnumUtil;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.user.web.base.controller.BaseController;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.ly.mt.core.common.constant.ResultCodeEnum.RESULT_CODE_PARAM_ERROR;

@Api(description = "数据字典接口")
@RestController
@RequestMapping("/user/dict")
public class DictController extends BaseController {
    @ApiOperation(
            value = "根据字典名称获取字典数据",
            notes = "1、字典公共反参说明:  \n" +
                    "   id——字典id  \n" +
                    "   name——字典名称  \n" +
                    "2、字典名称与特殊反参说明:  \n" +
                    "   配送类型——DistributeType,特殊反参price——价格  \n" +
                    "   注册来源——QuickType  \n" +
                    "   性别枚举——Sex  \n" +
                    "   退款原因枚举——RefundReason  \n"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictName", value = "字典名称", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!"),
            @ApiResponse(code = 2, message = "参数错误!")
    })
    @PostMapping("/listDictByName")
    public JSONObject listDictByName(@RequestParam(value = "dictName") String dictName) {
        // 校验参数非空
        if (StringUtil.isEmpty(dictName)) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        List<Map<String,String>> list = EnumUtil.getDictByType(dictName);
        if (null == list || list.size()<=0) {
            return JsonUtil.getErrorJson(RESULT_CODE_PARAM_ERROR);
        }
        return JsonUtil.getSuccessJson(list);
    }
}