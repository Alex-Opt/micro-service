package com.ly.mt.blue.tooth.news.controller;

import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.login.vo.LoginResultVo;
import com.ly.mt.blue.tooth.news.service.BluetoothNewsService;
import com.ly.mt.blue.tooth.news.vo.BluetoothNewsDetailVo;
import com.ly.mt.blue.tooth.news.vo.BluetoothNewsListVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@Api(description = "蓝牙新闻操作接口")
@RestController
@RequestMapping("/bluetooth/news")
public class BluetoothNewsController {
    private final static Logger Logger = LoggerFactory.getLogger(BluetoothNewsController.class);
    @Resource
    BluetoothNewsService bluetoothNewsService;


    @ApiOperation("获取新闻列表")
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取异常!")
    })
    @CustomLog
    @PostMapping("/getBluetoothNewsList")
    public ResponseJson<BluetoothNewsListVo> getBluetoothNewsList() {
        try {
            return bluetoothNewsService.getBluetoothNewsList();
        } catch (Exception e) {
            Logger.error("蓝牙APP-获取新闻列表错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }



    @ApiOperation("更新新闻阅读次数+1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻id", paramType = "query", required = true)
    })
    @CustomLog
    @PostMapping("/updateBluetoothNewsReadNumber")
    public ResponseJson updateBluetoothNewsReadNumber(String newsId) {
        // 校验参数
        if (StringUtil.isEmpty(newsId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"新闻id不能为空");
        }
        try {
            return bluetoothNewsService.updateBluetoothNewsReadNumber(newsId);
        } catch (Exception e) {
            Logger.error("蓝牙APP-更新新闻阅读次数:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("获取新闻详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻id", paramType = "query", required = true)
    })
    @CustomLog
    @PostMapping("/getBluetoothNewsDetail")
    public ResponseJson<BluetoothNewsDetailVo> getBluetoothNewsDetail(@RequestParam(value = "newsId") String newsId) {
        // 校验参数
        if (StringUtil.isEmpty(newsId)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"新闻id不能为空");
        }
        try {
            return bluetoothNewsService.getBluetoothNewsDetail(newsId);
        } catch (Exception e) {
            Logger.error("蓝牙APP-获取新闻详情出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}