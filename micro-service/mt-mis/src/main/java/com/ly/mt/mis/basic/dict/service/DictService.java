package com.ly.mt.mis.basic.dict.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 字典枚举操作接口
 *
 * @author taoye
 */
public interface DictService {
    /**
     * 根据字典枚举名字加载新增页面字典枚举下拉框
     *
     * @param dictName 字典枚举名称
     * @return 字典枚举json
     * @author taoye
     */
    ResponseJson loadDictComboboxAdd(String dictName) throws Exception;

    /**
     * 根据字典枚举名字加载查询条件字典枚举下拉框
     *
     * @param dictName 字典枚举名称
     * @return 字典枚举json
     * @author taoye
     */
    ResponseJson loadDictComboboxSelect(String dictName) throws Exception;
}