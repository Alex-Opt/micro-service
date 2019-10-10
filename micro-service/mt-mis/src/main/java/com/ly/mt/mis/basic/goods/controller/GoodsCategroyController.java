package com.ly.mt.mis.basic.goods.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.basic.goods.service.GoodsCategroyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 商品类目管理
 *
 * @author taoye
 */
@Controller
@RequestMapping("/basic/goods/categroy")
public class GoodsCategroyController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsCategroyController.class);
    @Resource
    private GoodsCategroyService service;

    /**
     * 商品信息分页表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadGoodsCategroyCombotree")
    public ResponseJson loadGoodsCategroyCombotree(HttpServletRequest request) {
        try {
            return service.loadGoodsCategroyCombotree(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("GoodsCategroyController.loadGoodsCategroyCombotree error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}