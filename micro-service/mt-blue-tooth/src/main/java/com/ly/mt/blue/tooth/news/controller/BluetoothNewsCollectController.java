package com.ly.mt.blue.tooth.news.controller;

import com.ly.mt.blue.tooth.base.annotation.CustomLog;
import com.ly.mt.blue.tooth.news.service.BluetoothNewsCollectService;
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

@Api(description = "蓝牙新闻收藏操作接口")
@RestController
@RequestMapping("/bluetooth/collect/news")
public class BluetoothNewsCollectController {
    private final static Logger Logger = LoggerFactory.getLogger(BluetoothNewsCollectController.class);
    @Resource
    BluetoothNewsCollectService bluetoothNewsCollectService;

    @Resource
    BluetoothNewsService bluetoothNewsService;

    @ApiOperation("用户删除收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻id", paramType = "query", required = true)
    })
    @CustomLog
    @PostMapping("/cancelBluetoothNewsCollect")
    public ResponseJson cancelBluetoothNewsCollect(@RequestParam(value = "newsId") String newsId) {
        try {
            // 校验参数
            if (StringUtil.isEmpty(newsId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"新闻id不能为空");
            }
            return bluetoothNewsCollectService.cancelBluetoothNewsCollect(newsId);
        } catch (Exception e) {
            Logger.error("蓝牙APP-获取新闻列表错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("用户加入收藏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻id", paramType = "query", required = true)
    })
    @CustomLog
    @PostMapping("/joinBluetoothNewsCollect")
    public ResponseJson joinBluetoothNewsCollect(@RequestParam(value = "newsId") String newsId) {
        try {
            // 校验参数
            if (StringUtil.isEmpty(newsId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"新闻id不能为空");
            }
            return bluetoothNewsCollectService.joinBluetoothNewsCollect(newsId);
        } catch (Exception e) {
            Logger.error("蓝牙APP-获取收藏新闻列表出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @ApiOperation("获取收藏新闻列表")
    @ApiResponses({
            @ApiResponse(code = 0, message = "获取成功!"),
            @ApiResponse(code = 1, message = "获取异常!")
    })
    @CustomLog
    @PostMapping("/getBluetoothNewsCollectList")
    public ResponseJson<BluetoothNewsListVo> getBluetoothNewsCollectList() {
        try {
            return bluetoothNewsService.getBluetoothNewsCollectList();
        } catch (Exception e) {
            Logger.error("蓝牙APP-获取收藏新闻列表出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}