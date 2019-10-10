package com.ly.mt.center.data.cabinet.service.impl;

import com.ly.mt.center.data.cabinet.entity.CabinetStoreProperty;
import com.ly.mt.center.data.cabinet.mapper.CabinetStorePropertyMapper;
import com.ly.mt.center.data.cabinet.service.CabinetStorePropertyService;
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
public class CabinetStorePropertyServiceImpl implements CabinetStorePropertyService {

    private static final Logger log = LoggerFactory.getLogger(CabinetStorePropertyServiceImpl.class);
    @Resource
    private CabinetStorePropertyMapper cabinetStorePropertyMapper;
    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,noRollbackFor = Exception.class)
    public ResponseJson insert(CabinetStoreProperty cabinetStoreProperty) {
        cabinetStorePropertyMapper.insert(cabinetStoreProperty);
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }
}
