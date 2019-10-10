package com.ly.mt.mis.basic.area.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.basic.area.service.AreaService;
import com.ly.mt.mis.basic.dict.service.impl.DictServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 行政区域
 *
 * @author taoye
 */
@Controller
@RequestMapping("/basic/area")
public class AreaController {
    private final static Logger LOGGER = LoggerFactory.getLogger(DictServiceImpl.class);
    @Resource
    private AreaService service;

    /**
     * 行政区域下拉框
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadAreaCombobox")
    public ResponseJson loadAreaCombobox(HttpServletRequest request) {
        try {
            return service.loadAreaCombobox(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("AreaController.loadAreaCombobox error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 行政区域树
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadAreaTree")
    public ResponseJson loadAreaTree(HttpServletRequest request) {
        try {
            return service.loadAreaTree(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("AreaController.loadAreaTree error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
