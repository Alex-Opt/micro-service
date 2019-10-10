package com.ly.mt.core.common.entity.hd.vo;

import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @description
 * 主用户信息
 * @author panjingtian
 * @date 2019/8/15 2:38 AM
 */
@Data
public class WechatSimpMsgVo implements Serializable {


    private String wechatHeadeImgUrl;

    private String wechatNickname;

}
