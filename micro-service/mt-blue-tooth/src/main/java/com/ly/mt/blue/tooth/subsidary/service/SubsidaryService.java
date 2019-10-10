package com.ly.mt.blue.tooth.subsidary.service;

import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.web.multipart.MultipartFile;

public interface SubsidaryService {
    /**
     * 保存用户每日抽烟不高于目标设定
     * @throws Exception
     */
    ResponseJson saveOrModifySmokingTarget(String smokingTarget) throws Exception;

    /**
     * 保存用户达标天数设定
     * @throws Exception
     */
    ResponseJson saveOrModifyComplianceDaysTarget(String complianceDaysTarget) throws Exception;

    /**
     * 保存用户投诉建议
     * @throws Exception
     */
    ResponseJson saveUserSuggest(String name,String mobile,String remark) throws Exception;

    /**
     * 获取最新固件版本
     * @throws Exception
     */
    ResponseJson getFirmwareUpgrade(String type,String terminalType) throws Exception;

}