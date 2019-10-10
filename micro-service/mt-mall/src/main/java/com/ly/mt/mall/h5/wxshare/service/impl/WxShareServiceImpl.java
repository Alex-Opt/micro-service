package com.ly.mt.mall.h5.wxshare.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.wxshare.service.WxShareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.ThirdCenterMethod.WX_APPLET_CODE_UNLIMIT;
import static com.ly.mt.core.feign.ThirdCenterMethod.WX_SHARE_GETSIGN;

@Service
public class WxShareServiceImpl extends BaseServiceImpl implements WxShareService {

    private final static Logger LOGGER = LoggerFactory.getLogger(WxShareServiceImpl.class);

    /**
     * 获取微信分享需要的参数
     *
     * @param url
     * @return
     * @Author wanglong
     */
    @Override
    public ResponseJson getShareParam(String url) {
        try {
            JSONObject param = new JSONObject();
            param.put("url", url);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, callThirdCenter(WX_SHARE_GETSIGN, param));
        } catch (Exception e) {
            LOGGER.error("获取微信分享参数签名出错 {}", e);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
    }

    /**
     * [微信小程序二维码生成接口]
     *
     * @param params
     * @return
     * @author zhanglifeng
     */
    @Override
    public ResponseJson getWxaCodeUnLimit(JSONObject params) {
        LOGGER.info("--------------------------------微信小程序二维码生成接口入参：{}", params);
        try {
            String codeResult = callThirdCenter(WX_APPLET_CODE_UNLIMIT, params);
            LOGGER.info("-------------生成二维码三方接口返回值：{}", codeResult);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, codeResult);
        } catch (Exception e) {
            LOGGER.error("微信小程序二维码生成接口出错 {}", e);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
    }

}
