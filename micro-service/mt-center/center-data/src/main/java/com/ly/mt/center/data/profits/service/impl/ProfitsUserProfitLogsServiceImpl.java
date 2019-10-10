package com.ly.mt.center.data.profits.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.profits.entity.ProfitsUserProfitsLogsParam;
import com.ly.mt.center.data.profits.entity.ProfitsUserProfitsLogsVO;
import com.ly.mt.center.data.profits.entity.ProfitsUserRankVo;
import com.ly.mt.center.data.profits.mapper.ProfitsUserProfitLogsServiceMapper;
import com.ly.mt.center.data.profits.service.ProfitsUserProfitLogsService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @Description: 用户赚钱日志
 * @Author: zhuyh
 */
@Service
public class ProfitsUserProfitLogsServiceImpl extends BaseServiceImpl implements ProfitsUserProfitLogsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProfitsUserProfitLogsServiceImpl.class);

    @Autowired
    private ProfitsUserProfitLogsServiceMapper mapper;

    @Override
    public ResponseJson getLogs(JSONObject jsonObject) {
        ProfitsUserProfitsLogsParam param = JSONObject.toJavaObject(jsonObject, ProfitsUserProfitsLogsParam.class);
        PageHelper.startPage(param.getPage(), param.getRows());
        List<ProfitsUserProfitsLogsVO> list = mapper.getLogs(param);
        PageInfo<ProfitsUserProfitsLogsVO> pageInfo = new PageInfo<>(list);
        Map result = new HashMap();
        result.put("total", pageInfo.getTotal());
        result.put("rows", list);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, result);
    }

    @Override
    public ResponseJson getFriednsOrderRank(JSONObject jsonObject) {
        try {
            ProfitsUserRankVo rankVo = JSONObject.toJavaObject(jsonObject, ProfitsUserRankVo.class);
            PageHelper.startPage(1, 10);
            List<ProfitsUserRankVo> rankVoList = mapper.getFriendsOrderRank(rankVo);
            Map result = new HashMap();
            result.put("rows", rankVoList);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, result);
        } catch (Exception e) {
            LOGGER.error("ProfitsUserProfitLogsServiceImpl.getFriednsOrderRank:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
