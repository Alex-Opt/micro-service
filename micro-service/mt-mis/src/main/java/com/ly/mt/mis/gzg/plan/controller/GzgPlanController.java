package com.ly.mt.mis.gzg.plan.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.gzg.plan.service.GzgPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 配货方案管理
 *
 * @author taoye
 */
@Controller
@RequestMapping("/gzg/plan")
public class GzgPlanController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgPlanController.class);
    @Resource
    private GzgPlanService service;

    /**
     * 跳转配货方案列表页面
     *
     * @author taoye
     */
    @RequestMapping("/plan")
    public String categroy() {
        return "/gzg/plan/gzg_plan";
    }


    /**
     * 跳转配货方案新增页面
     *
     * @author taoye
     */
    @RequestMapping("/planAdd")
    public String categroyAdd() {
        return "/gzg/plan/gzg_plan_add";
    }


    /**
     * 跳转配货方案修改页面
     *
     * @author taoye
     */
    @RequestMapping("/planEdit")
    public String categroyEdit() {
        return "/gzg/plan/gzg_plan_edit";
    }


    /**
     * 校验配货方案名称
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/checkName")
    public ResponseJson checkName(HttpServletRequest request) {
        try {
            return service.checkName(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("GzgPlanController.checkName error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 新增
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/insertPlan")
    public ResponseJson insertPlan(HttpServletRequest request) {
        try {
            return service.insertPlan(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("GzgPlanController.insertPlan error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 修改
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/updatePlan")
    public ResponseJson updatePlan(HttpServletRequest request) {
        try {
            return service.updatePlan(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("GzgPlanController.updatePlan error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 配货方案分页表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadPlanDatagrid")
    public ResponseJson loadPlanDatagrid(HttpServletRequest request) {
        try {
            return service.loadPlanDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("GzgPlanController.loadPlanDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}