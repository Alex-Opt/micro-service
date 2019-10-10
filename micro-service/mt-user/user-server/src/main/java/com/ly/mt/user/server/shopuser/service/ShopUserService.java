package com.ly.mt.user.server.shopuser.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.shop.LodeRunnerBo;

import java.util.List;

public interface ShopUserService {

    /**
     * b端检测手机号码是否已注册
     * @param json
     * @return
     */
    JSONObject bShopUserCheckMobile(String json);

    /**
     * b端用户注册
     * @param json
     * @return
     */
    JSONObject bShopUserRegist(String json);

    /**
     * B端用户设置密码
     * @param json
     * @return
     */
    JSONObject bShopUserSetPassword(String json);



    /**
     * 根据shopId和userId 查询 用户 等级  和 返利 fatherId
     * @param
     * @return
     */
    // List<LodeRunnerBo> getLodeRunnerLevel(String json);
}
