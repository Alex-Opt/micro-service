package com.ly.mt.blue.tooth.news.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.blue.tooth.base.dict.CollectStatusEnum;
import com.ly.mt.blue.tooth.base.service.impl.BaseServiceImpl;
import com.ly.mt.blue.tooth.news.service.BluetoothNewsCollectService;
import com.ly.mt.blue.tooth.news.vo.BluetoothNewsDetailVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.springframework.stereotype.Service;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_BLUETOOTH_COLLECT;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;

/**
 * @description:
 * @author: wanghongliang
 * @create: 2019-09-07 15:41
 **/
@Service
public class BluetoothNewsCollectServiceImpl extends BaseServiceImpl implements BluetoothNewsCollectService {

    /**
     * @Description 根据id删除用户收藏 删除时需要减去收藏数量
     * @Author wanghongliang
     */
    @Override
    public ResponseJson cancelBluetoothNewsCollect(String newsId) {
        String userId =getLoginUserId();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("news_id",newsId);
        jsonObject.put("user_id",userId);
        //根据新闻id和用户id删除用户收藏
        String result = callDataCenter(BLUETOOTH_NEWS_COLLECT_DELETE, jsonObject);
        int i = Integer.parseInt(result);
        JSONObject collectObject = new JSONObject();
        collectObject.put("id",newsId);
        //更新文章收藏数量剪掉1
        String collectResult = callDataCenter(BLUETOOTH_NEWSR_UPDATE_COLLECT_MINUS_NUMBER, jsonObject);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    /**
     * @Description 用户加入收藏 加入收藏时需要更新新闻收藏数量
     * @Author wanghongliang
     */
    @Override
    public ResponseJson joinBluetoothNewsCollect(String newsId) {
        String userId =getLoginUserId();
        String id = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_BLUETOOTH_COLLECT);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("news_id",newsId);
        jsonObject.put("user_id",userId);
        //加入用户收藏
        String result = callDataCenter(BLUETOOTH_NEWS_COLLECT_INSERT, jsonObject);
        int i = Integer.parseInt(result);
        JSONObject collectObject = new JSONObject();
        collectObject.put("id",newsId);
        //更新文章收藏数量加上1
        String collectResult = callDataCenter(BLUETOOTH_NEWSR_UPDATE_COLLECT_NUMBER, collectObject);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }
}