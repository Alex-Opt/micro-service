package com.ly.mt.mall.h5.user.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.EnumUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Api(description = "数据字典接口")
@RestController
@RequestMapping("/mall/h5/user/dict")
public class DictController {
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
    public ResponseJson listDictByName(@RequestParam(value = "dictName") String dictName) {
        // 校验参数非空
        if (StringUtil.isEmpty(dictName)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
        List<Map<String, String>> list = EnumUtil.listDictByDictName(dictName);
        if (null == list || list.size() <= 0) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
    }
}