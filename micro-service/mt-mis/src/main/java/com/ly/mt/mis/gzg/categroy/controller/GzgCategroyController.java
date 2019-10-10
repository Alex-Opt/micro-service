package com.ly.mt.mis.gzg.categroy.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.gzg.categroy.service.GzgCategroyService;
import com.ly.mt.mis.gzg.user.controller.GzgUserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 货柜类型管理
 *
 * @author taoye
 */
@Controller
@RequestMapping("/gzg/categroy")
public class GzgCategroyController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgCategroyController.class);
    @Resource
    private GzgCategroyService service;

    /**
     * 跳转货柜类型列表页面
     *
     * @author taoye
     */
    @RequestMapping("/categroy")
    public String categroy() {
        return "/gzg/categroy/gzg_categroy";
    }


    /**
     * 跳转货柜类型新增页面
     *
     * @author taoye
     */
    @RequestMapping("/categroyAdd")
    public String categroyAdd() {
        return "/gzg/categroy/gzg_categroy_add";
    }


    /**
     * 跳转货柜类型修改页面
     *
     * @author taoye
     */
    @RequestMapping("/categroyEdit")
    public String categroyEdit() {
        return "/gzg/categroy/gzg_categroy_edit";
    }


    /**
     * 校验货柜名称
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/checkName")
    public ResponseJson checkName(HttpServletRequest request) {
        try {
            return service.checkName(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("GzgCategroyController.checkName error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 新增
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/insertCategroy")
    public ResponseJson insertCategroy(HttpServletRequest request) {
        try {
            return service.insertCategroy(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("GzgCategroyController.insertCategroy error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 修改
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/updateCategroy")
    public ResponseJson updateCategroy(HttpServletRequest request) {
        try {
            return service.updateCategroy(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("GzgCategroyController.updateCategroy error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 货柜类型分页表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadCategroyDatagrid")
    public ResponseJson loadCategroyDatagrid(HttpServletRequest request) {
        try {
            return service.loadCategroyDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("GzgCategroyController.loadCategroyDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 货柜类型下拉框
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadCategroyComboboxSelect")
    public ResponseJson loadCategroyComboboxSelect(HttpServletRequest request) {
        try {
            return service.loadCategroyComboboxSelect(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("GzgCategroyController.loadCategroyComboboxSelect error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}