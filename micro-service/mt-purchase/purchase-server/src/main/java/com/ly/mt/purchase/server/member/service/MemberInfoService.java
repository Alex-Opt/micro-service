package com.ly.mt.purchase.server.member.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 进货页面会员信息
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-16 21:40:40
 */
public interface MemberInfoService {

    /**
     * B端 查询会员信息
     * @param json
     * @return
     * @throws Exception
     */
    JSONObject getInfoByUserId(String json) throws Exception;
}
