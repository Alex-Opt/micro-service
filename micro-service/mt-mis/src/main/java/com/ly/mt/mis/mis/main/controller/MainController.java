package com.ly.mt.mis.mis.main.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.mis.main.service.MainService;
import com.ly.mt.mis.mis.main.vo.TopMenuVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 主页
 *
 * @author taoye
 */
@Controller
@RequestMapping("/mis/main")
public class MainController {
    private final static Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    @Resource
    private MainService service;

    /**
     * 主页
     *
     * @author taoye
     */
    @RequestMapping("/main")
    public String main(Model model) {
        String nowDate = DateUtil.getNowDateStr();
        String week = DateUtil.getWeekOfDate(new Date());
        model.addAttribute("dateWeek", nowDate + " " + week);
        List<TopMenuVo> list = new ArrayList<>();
        try {
            list = service.loadTopMenu();
        } catch (Exception e) {
            LOGGER.error("MainController.main loadTopMenu error ", e);
        }
        if (null != list && list.size() > 0) {
            model.addAttribute("modules", list);
            model.addAttribute("module", list.get(0));
        }
        return "/mis/main/main";
    }

    /**
     * 加载左侧功能菜单
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadLeftMenu")
    public ResponseJson loadLeftMenu(HttpServletRequest request) {
        try {
            return service.loadLeftMenu(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("MainController.loadLeftMenu error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}