package com.ly.mt.activity.wechart.service.impl;

import com.ly.mt.activity.base.service.impl.BaseServiceImpl;
import com.ly.mt.activity.wechart.service.WeChartAuthService;
import com.ly.mt.activity.wechart.vo.WeChartOpenIdVo;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.wechat.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 微信公众号认证登录
 *
 * @author taoye
 */
@Service
public class WeChartAuthServiceImpl extends BaseServiceImpl implements WeChartAuthService {
    @Autowired
    private AuthService authService;

    @Override
    public ResponseJson<WeChartOpenIdVo> getOpenId(String code) throws Exception {
        String openId = authService.getOpenId(code);
        WeChartOpenIdVo vo = new WeChartOpenIdVo();
        vo.setOpenId(openId);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, vo);
    }
}