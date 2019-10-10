package com.ly.mt.core.data.cabinet.dao.impl;

import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.cabinet.dao.CabinetRewardRecordDao;
import com.ly.mt.core.data.cabinet.entity.CabinetRewardRecord;
import com.ly.mt.core.data.cabinet.mapper.CabinetRewardRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CabinetRewardRecordDaoImpl implements CabinetRewardRecordDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CabinetRewardRecordDaoImpl.class);

    @Resource
    private CabinetRewardRecordMapper cabinetRewardRecordMapper;


    @Override
    public List<CabinetRewardRecord> getRecordList(String create_time) {
        if (StringUtil.isEmpty(create_time)) {
            return null;
        }
        return cabinetRewardRecordMapper.getRecordList(create_time);
    }

    @Override
    public int updateCabinetRewardRecordByIdList(List idList, String update_time) {
        if (null == idList || idList.size()==0) {
            return 0;
        }
        return cabinetRewardRecordMapper.updateCabinetRewardRecordByIdList(idList,update_time);
    }

    @Override
    public int updateRecordById(CabinetRewardRecord cabinetRewardRecord) {
        if(StringUtil.isEmpty(cabinetRewardRecord.getId())){
            return 0;
        }
        return cabinetRewardRecordMapper.updateRecordById(cabinetRewardRecord);
    }
}
