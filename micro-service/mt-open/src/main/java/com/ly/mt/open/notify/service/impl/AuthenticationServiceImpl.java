package com.ly.mt.open.notify.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.MD5Util;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.open.base.service.impl.BaseServiceImpl;
import com.ly.mt.open.notify.service.AuthenticationService;
import com.ly.mt.open.notify.service.ProvideApiService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.ORDER_LIST_BY_SOURCE;
import static com.ly.mt.core.feign.DataCenterMethod.USER_GET_USER_BY_LOGIN_NAME;

@Service
public class AuthenticationServiceImpl extends BaseServiceImpl implements AuthenticationService {

    private static String localAccount = "OPEN-API-MOTI";


    @Override
    public ResponseJson login(String account, String password, HttpServletRequest request) throws Exception {
        if (account.equals(localAccount)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login_name", account);
            String userResult = callDataCenter(USER_GET_USER_BY_LOGIN_NAME, jsonObject);
            if(StringUtil.isNotEmpty(userResult)) {
                JSONObject userJson = JSONObject.parseObject(userResult);
                if(password.equals(userJson.getString("password"))) {
                    HttpSession session = request.getSession();
                    JSONObject json = new JSONObject();
                    json.put("account", account);
                    json.put("password", password);
                    json.put("sign", StringUtil.getRandomStr(10));
                    String code = MD5Util.md5(json.toString());
                    session.setAttribute("account", localAccount);
                    session.setAttribute("code", code);
                    return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, code);
                }
            }
        }
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "认证失败");
    }
}
