package com.ly.mt.center.data.hd.mapper;

import com.ly.mt.center.data.hd.entity.HdPartner;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhanglifeng
 */
@Mapper
public interface HdPartnerMapper {
    /**
     * @param hdPartner
     * @Description 保存HdPartner
     * @author zhanglifeng
     */
    int insertHdPartner(HdPartner hdPartner);

    /**
     * 根据手机号查询是否已经存在
     * @param mobile
     * @return
     */
    HdPartner queryPartnerBy(String mobile);

}