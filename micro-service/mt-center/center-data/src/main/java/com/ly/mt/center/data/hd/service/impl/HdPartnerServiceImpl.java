package com.ly.mt.center.data.hd.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.hd.entity.HdPartner;
import com.ly.mt.center.data.hd.mapper.HdPartnerMapper;
import com.ly.mt.center.data.hd.service.HdPartnerService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 合伙人处理数据中心层
 *
 * @author zhanglifeng
 * @date 2019-08-21
 */
@Service
public class HdPartnerServiceImpl extends BaseServiceImpl implements HdPartnerService {
    private final static Logger LOGGER = LoggerFactory.getLogger(HdPartnerServiceImpl.class);

    @Resource
    private HdPartnerMapper hdPartnerMapper;

    @Override
    public ResponseJson addPartner(JSONObject jsonObject) {
        try {
            HdPartner hdPartner = JSONObject.toJavaObject(jsonObject, HdPartner.class);
            if (StringUtil.isEmpty(hdPartner.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            hdPartnerMapper.insertHdPartner(hdPartner);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdPartnerServiceImpl.addPartner 方法执行异常:" + e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson queryPartnerByMobile(JSONObject jsonObject) {
        String partner_mobile = jsonObject.getString("partner_mobile");
        HdPartner hdPartner = hdPartnerMapper.queryPartnerBy(partner_mobile);
        if(hdPartner != null && hdPartner.getId() != null){
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,"true");
        }else{
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,"false");
        }
    }

}
