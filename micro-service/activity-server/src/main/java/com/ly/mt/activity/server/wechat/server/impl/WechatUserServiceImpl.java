package com.ly.mt.activity.server.wechat.server.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.server.wechat.mapper.WechatUserMapper;
import com.ly.mt.activity.server.wechat.server.WechatUserService;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.entity.hd.WechatUser;
import com.ly.mt.core.common.entity.hd.request.WechatUserRequestBody;
import com.ly.mt.core.common.entity.hd.vo.WechatUserVo;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @description
 * 微信用户操作
 * @author panjingtian
 * @date 2019/8/13 10:39 PM
 */
@Slf4j
@Service
public class WechatUserServiceImpl implements WechatUserService {

    @Resource
    private WechatUserMapper wechatUserMapper;


    @Override
    public JSONObject addWechatUser(JSONObject jsonObject) {

        try {
            WechatUserRequestBody body = jsonObject.toJavaObject(jsonObject, WechatUserRequestBody.class);
            WechatUserVo userVo = wechatUserMapper.findByOpenId(body.getOpenId());
            if(null==userVo) {
                WechatUser user = new WechatUser();
                BeanUtils.copyProperties(body, user);
                user.setId(Long.valueOf((StringUtil.getRandomIntByLength(10))));
                user.setCreateTime(new Date());
                user.setCreateTime(new Date());
                user.setWechatHeadeImgUrl(body.getHeadImgUrl());
                user.setWechatSexdesc(body.getSexDesc());
                user.setWechatNickname(URLEncoder.encode(body.getNickname(),"utf-8"));
                int i = wechatUserMapper.insertSelective(user);
                WechatUserVo vo = new WechatUserVo();
                BeanUtils.copyProperties(user, vo);
                return JsonUtil.getSuccessJson(vo);
            }else {
                return JsonUtil.getSuccessJson(userVo);
            }
        } catch (Exception e) {
            log.error("addWechatUser,保存微信用户失败,e={}",e);
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_SYSTEM_ERROR);
        }
    }

    @Override
    public JSONObject findUserById(JSONObject jsonObject) {
        try {
            Long id = Long.valueOf(jsonObject.getString("id"));
            WechatUserVo vo = wechatUserMapper.findById(id);
            vo.setNickname(URLDecoder.decode(vo.getNickname(),"utf-8"));
            return JsonUtil.getSuccessJson(vo);
        } catch (Exception e) {
            log.error("findUserById,用户没啦，e={}");
            return JsonUtil.getErrorJson(ResultCodeEnum.USER_NOT_EXIST);

        }
    }


    @Override
    public JSONObject findUserByOpenId(JSONObject jsonObject) {
        try {
            String openId = jsonObject.getString("openId");
            WechatUserVo vo = wechatUserMapper.findByOpenId(openId);
            return JsonUtil.getSuccessJson(vo);
        } catch (Exception e) {
            log.error("findUserByOpenId，查询用户失败,e={}",e);
            return JsonUtil.getErrorJson(ResultCodeEnum.USER_NOT_EXIST);
        }


    }


}
