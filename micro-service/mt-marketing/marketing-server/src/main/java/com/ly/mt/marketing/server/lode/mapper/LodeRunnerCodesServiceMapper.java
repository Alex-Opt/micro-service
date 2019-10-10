package com.ly.mt.marketing.server.lode.mapper;

import com.ly.mt.core.common.entity.marketing.*;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LodeRunnerCodesServiceMapper {

    /**
     *@Description 获取人员的邀请码
     *@Author  zhuyh
     */
    LodeRunnerCodes getByUId(LodeRunnerCodes vo);


    /**
     *@Description 保存邀请码
     *@Author  zhuyh
     */
    void save(LodeRunnerCodes vo);

    /**
     *@Description 查询用户的商店编号
     *@Author  zhuyh
     */
    Long getShopId(LodeRunnerCodes vo);

    /**
     *@Description 校验是否已经存在
     *@Author  zhuyh
     */
    Integer getCountByCode(LodeRunnerCodes checkParam);
}