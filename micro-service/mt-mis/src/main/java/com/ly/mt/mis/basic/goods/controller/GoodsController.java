package com.ly.mt.mis.basic.goods.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.basic.goods.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 商品管理
 *
 * @author taoye
 */
@Controller
@RequestMapping("/basic/goods")
public class GoodsController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);
    @Resource
    private GoodsService service;

    /**
     * 跳转商品管理页面
     *
     * @author taoye
     */
    @RequestMapping("/goods")
    public String goods() {
        return "/basic/goods/goods";
    }

    /**
     * 跳转商品信息查看页面
     *
     * @author taoye
     */
    @RequestMapping("/goodsInfo")
    public String goodsInfo() {
        return "/basic/goods/goods_info";
    }


    /**
     * SKU图片加载
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadSkuPicture")
    public ResponseJson loadSkuPicture(HttpServletRequest request) {
        try {
            return service.loadSkuPicture(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("GoodsController.loadSkuPicture error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 商品信息分页表格
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadGoodsDatagrid")
    public ResponseJson loadGoodsDatagrid(HttpServletRequest request) {
        try {
            return service.loadGoodsDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("GoodsController.loadGoodsDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}