package com.ly.mt.blue.tooth.news.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.blue.tooth.base.dict.CollectStatusEnum;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.login.service.impl.LoginServiceImpl;
import com.ly.mt.blue.tooth.news.service.BluetoothNewsService;
import com.ly.mt.blue.tooth.news.vo.BluetoothNewsDetailVo;
import com.ly.mt.blue.tooth.news.vo.BluetoothNewsListVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * @description:
 * @author: wanghongliang
 * @create: 2019-09-07 15:40
 **/
@Service
public class BluetoothNewsServiceImpl extends BaseServiceImpl implements BluetoothNewsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BluetoothNewsServiceImpl.class);
    /**
     * @Description 查询新闻列表数据
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothNewsList() {
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        String result = callDataCenter(BLUETOOTH_NEWS_GET, jsonObject);
        List<BluetoothNewsListVo> listVos = JSONObject.parseArray(result,BluetoothNewsListVo.class);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,listVos);
    }
    /**
     * @Description 查询用户收藏新闻列表数据
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothNewsCollectList() {
        String userId = getLoginUserId();
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",userId);
        String result = callDataCenter(BLUETOOTH_NEWS_COLLECT_GET, jsonObject);
        List<BluetoothNewsListVo> listVos = JSONObject.parseArray(result,BluetoothNewsListVo.class);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,listVos);
    }

    /**
     * @Description 更新新闻阅读次数+1
     * 1.更新新闻阅读数量
     * 2.更细新闻收藏人数
     * @Author wanghongliang
     */
    @Override
    public ResponseJson updateBluetoothNewsReadNumber(String newsId) {
        // 调用后台接口
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",newsId);
        String result = callDataCenter(BLUETOOTH_NEWSR_UPDATE_READ_NUMBER, jsonObject);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    /**
     * @Description 查询新闻详情
     * @Author wanghongliang
     */
    @Override
    public ResponseJson getBluetoothNewsDetail(String newsId) {
        String userId =getLoginUserId();
        //查询新闻详情
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",newsId);
        String result = callDataCenter(BLUETOOTH_NEWSR_GET_DETAIL, jsonObject);
        BluetoothNewsDetailVo bluetoothNewsDetailVo =JSONObject.parseObject(result,BluetoothNewsDetailVo.class);
        //查询新闻详情
        JSONObject collectObject = new JSONObject();
        collectObject.put("news_id",newsId);
        //查询用户是否收藏
        collectObject.put("user_id",userId);
        String collectResult = callDataCenter(BLUETOOTH_NEWS_COLLECT_GET_USER, collectObject);
        if(StringUtil.isEmpty(collectResult)){
            bluetoothNewsDetailVo.setIsCollect(CollectStatusEnum.NO_COLLECT.getId());//未收藏
        }else{
            bluetoothNewsDetailVo.setIsCollect(CollectStatusEnum.COLLECT.getId());//已收藏
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,bluetoothNewsDetailVo);
    }
}