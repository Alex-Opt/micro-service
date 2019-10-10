package com.ly.mt.purchase.server.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.purchase.MemberInfoVO;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.purchase.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.purchase.server.member.mapper.MemberInfoMapper;
import com.ly.mt.purchase.server.member.service.MemberInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 进货页面会员信息具体实现
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-16 21:43:43
 */
@Service
public class MemberInfoServiceImpl extends BaseServiceImpl implements MemberInfoService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MemberInfoServiceImpl.class);

    @Resource
    private MemberInfoMapper memberInfoMapper;

    /**
     * B端 查询会员信息
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject getInfoByUserId(String json) throws Exception {
        String loginUserId = getLoginUserId();
        LOGGER.info("当前用户ID为 {}",loginUserId);
        MemberInfoVO memberInfoVO = memberInfoMapper.selectInfoByUserId(Long.parseLong(loginUserId));
        LOGGER.info("当前用户的会员信息为 {}",memberInfoVO);
        return JsonUtil.getSuccessJson(memberInfoVO);
    }
}
