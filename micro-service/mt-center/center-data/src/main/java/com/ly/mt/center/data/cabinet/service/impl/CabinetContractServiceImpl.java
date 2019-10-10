package com.ly.mt.center.data.cabinet.service.impl;

import com.ly.mt.center.data.cabinet.entity.CabinetContract;
import com.ly.mt.center.data.cabinet.mapper.CabinetContractMapper;
import com.ly.mt.center.data.cabinet.service.CabinetContractService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CabinetContractServiceImpl implements CabinetContractService {

    private static final Logger log = LoggerFactory.getLogger(CabinetContractServiceImpl.class);
    @Resource
    private CabinetContractMapper cabinetContractMapper;

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,noRollbackFor = Exception.class)
    public ResponseJson insert(CabinetContract cabinetContract) {
        cabinetContractMapper.insert(cabinetContract);
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }
}
