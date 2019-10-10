package com.ly.mt.mis.basic.func.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.basic.func.service.FuncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 系统管理-菜单管理
 *
 * @author taoye
 */
@Controller
@RequestMapping("/basic/func")
public class FuncController {
    private final static Logger LOGGER = LoggerFactory.getLogger(FuncController.class);
    @Resource
    private FuncService funcService;

    /**
     * 跳转菜单管理页面
     *
     * @author taoye
     */
    @RequestMapping("/func")
    public String func() {
        return "/basic/func/func";
    }

    /**
     * 跳转新增页面
     *
     * @author taoye
     */
    @RequestMapping("/funcAdd")
    public String funcAdd() {
        return "/basic/func/func_add";
    }

    /**
     * 跳转修改页面
     *
     * @author taoye
     */
    @RequestMapping("/funcEdit")
    public String funcEdit() {
        return "/basic/func/func_edit";
    }


    /**
     * 新增
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/insertFunc")
    public ResponseJson insertFunc(HttpServletRequest request) {
        try {
            return funcService.insertFunc(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("FuncController.insertFunc error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 删除
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/deleteFunc")
    public ResponseJson deleteFunc(HttpServletRequest request) {
        try {
            return funcService.deleteFunc(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("FuncController.deleteFunc error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 修改
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/updateFunc")
    public ResponseJson updateFunc(HttpServletRequest request) {
        try {
            return funcService.updateFunc(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("FuncController.updateFunc error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 上移、下移
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/sortFunc")
    public ResponseJson sortFunc(HttpServletRequest request) {
        try {
            return funcService.sortFunc(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("FuncController.sortFunc error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 功能菜单表格树
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadFuncTreegrid")
    public ResponseJson loadFuncTreegrid(HttpServletRequest request) {
        try {
            return funcService.loadFuncTreegrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("FuncController.loadFuncTreegrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 功能菜单下拉树
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadFuncCombotree")
    public ResponseJson loadFuncCombotree(HttpServletRequest request) {
        try {
            return funcService.loadFuncCombotree(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("FuncController.loadFuncCombotree error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 功能菜单树
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadFuncTree")
    public ResponseJson loadFuncTree() {
        try {
            return funcService.loadFuncTree();
        } catch (Exception e) {
            LOGGER.error("FuncController.loadFuncTree error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}