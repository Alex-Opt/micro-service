package com.ly.mt.core.data.cabinet.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.cabinet.dao.CabinetCashRecordDao;
import com.ly.mt.core.data.cabinet.dao.CabinetReplenishRewardDao;
import com.ly.mt.core.data.cabinet.entity.CabinetCashRecord;
import com.ly.mt.core.data.cabinet.entity.CabinetReplenishReward;
import com.ly.mt.core.data.cabinet.mapper.CabinetCashRecordMapper;
import com.ly.mt.core.data.cabinet.mapper.CabinetReplenishRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class CabinetReplenishRewardDaoImpl implements CabinetReplenishRewardDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CabinetReplenishRewardDaoImpl.class);

    @Resource
    private CabinetReplenishRewardMapper cabinetReplenishRewardMapper;

    @Override
    public CabinetReplenishReward getUserCabinetRewardByUserId(String userId, String type) {
        if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(type)) {
            return null;
        }
        return cabinetReplenishRewardMapper.getUserCabinetRewardByUserId(userId,type);
    }


    @Override
    public int updateRewardById(CabinetReplenishReward cabinetReplenishReward) {

        return cabinetReplenishRewardMapper.updateRewardById(cabinetReplenishReward);
    }


}
