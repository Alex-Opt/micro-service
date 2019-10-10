package com.ly.mt.mis.basic.dict.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.basic.dict.service.DictService;
import com.ly.mt.mis.basic.dict.service.impl.DictServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 字典枚举
 *
 * @author taoye
 */
@Controller
@RequestMapping("/basic/dict")
public class DictController {
    private final static Logger LOGGER = LoggerFactory.getLogger(DictServiceImpl.class);
    @Resource
    private DictService service;

    /**
     * 新增页面字典下拉框加载
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadDictComboboxAdd")
    public ResponseJson loadDictComboboxAdd(@RequestParam(value = "dictName") String dictName) {
        try {
            return service.loadDictComboboxAdd(dictName);
        } catch (Exception e) {
            LOGGER.error("DictController.loadDictComboboxAdd error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 查询条件字典下拉框加载
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadDictComboboxSelect")
    public ResponseJson loadDictComboboxSelect(@RequestParam(value = "dictName") String dictName) {
        try {
            return service.loadDictComboboxSelect(dictName);
        } catch (Exception e) {
            LOGGER.error("DictController.loadDictComboboxSelect error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
