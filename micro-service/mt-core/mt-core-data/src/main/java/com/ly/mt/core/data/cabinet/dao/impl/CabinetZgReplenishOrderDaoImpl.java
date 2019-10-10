package com.ly.mt.core.data.cabinet.dao.impl;

import com.ly.mt.core.data.cabinet.dao.CabinetZgReplenishOrderDao;
import com.ly.mt.core.data.cabinet.entity.CabinetZgReplenishOrder;
import com.ly.mt.core.data.cabinet.mapper.CabinetZgReplenishOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: wanghongliang
 * @create: 2019-09-23 18:51
 **/
@Service
public class CabinetZgReplenishOrderDaoImpl implements CabinetZgReplenishOrderDao {
    private static final Logger logger = LoggerFactory.getLogger(CabinetZgReplenishOrderDaoImpl.class);

    @Resource
    private CabinetZgReplenishOrderMapper cabinetZgReplenishOrderMapper;

    @Override
    public List<CabinetZgReplenishOrder> getCabinetZgReplenish() {
        return cabinetZgReplenishOrderMapper.getCabinetZgReplenish();
    }
}