package com.ly.mt.center.data.bluetooth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.bluetooth.entity.BluetoothCountLog;
import com.ly.mt.center.data.bluetooth.entity.BluetoothLog;
import com.ly.mt.center.data.bluetooth.entity.BluetoothNews;
import com.ly.mt.center.data.bluetooth.mapper.BluetoothNewsMapper;
import com.ly.mt.center.data.bluetooth.service.BluetoothNewsService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @description:
 * @author: wanghongliang
 * @create: 2019-09-07 14:39
 **/
@Service
public class BluetoothNewsServiceImpl implements BluetoothNewsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothNewsServiceImpl.class);
    @Resource
    BluetoothNewsMapper mapper;

    /**
     * @Description 查询新闻列表数据
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothNewsList(JSONObject jsonObject) {
        try {
            List<BluetoothNews> list = mapper.selectBluetoothNews();
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
        } catch (Exception e) {
            LOGGER.error("BluetoothNewsServiceImpl.getBluetoothNewsList:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**
     * @Description 查询用户收藏新闻列表数据
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothNewsCollectList(JSONObject jsonObject) {
        try {
            String userId = String.valueOf(jsonObject.get("userId"));
            List<BluetoothNews> list = mapper.selectBluetoothNewsCollect(userId);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
        } catch (Exception e) {
            LOGGER.error("BluetoothNewsServiceImpl.getBluetoothNewsCollectList:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**
     * @Description 更新新闻阅读次数+1
     * 1.更新新闻阅读数量
     * 2.更细新闻收藏人数
     * @Author wanghongliang
     */
    @Override
    public ResponseJson updateBluetoothNewsReadNumber(JSONObject jsonObject) {
        try {
            String id = String.valueOf(jsonObject.get("id"));
            int i = mapper.updateBluetoothNewsReadNumber(id);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, i);
        } catch (Exception e) {
            LOGGER.error("BluetoothNewsServiceImpl.updateBluetoothNewsReadNumber:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 更新收藏人数加1
     * @Author wanghongliang
     */
    @Override
    public ResponseJson updateBluetoothNewsCollectNumber(JSONObject jsonObject) {
        try {
            String id = String.valueOf(jsonObject.get("id"));
            int i = mapper.updateBluetoothNewsCollectNumber(id);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, i);
        } catch (Exception e) {
            LOGGER.error("BluetoothNewsServiceImpl.updateBluetoothNewsCollectNumber:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**
     * @Description 更新收藏人数减1
     * @Author wanghongliang
     */
    @Override
    public ResponseJson updateBluetoothNewsCollectMinusNumber(JSONObject jsonObject) {
        try {
            String id = String.valueOf(jsonObject.get("id"));
            int i = mapper.updateBluetoothNewsCollectMinusNumber(id);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, i);
        } catch (Exception e) {
            LOGGER.error("BluetoothNewsServiceImpl.updateBluetoothNewsCollectNumber:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**
     * @Description 查询新闻详情
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothNewsDetail(JSONObject jsonObject) {
        try {
            String id = String.valueOf(jsonObject.get("id"));
            BluetoothNews bluetoothNews = mapper.getBluetoothNewsDetail(id);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, bluetoothNews);
        } catch (Exception e) {
            LOGGER.error("BluetoothNewsServiceImpl.getBluetoothNewsDetail:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}