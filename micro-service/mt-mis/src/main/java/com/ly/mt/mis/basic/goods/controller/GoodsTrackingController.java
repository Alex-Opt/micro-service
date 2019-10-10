package com.ly.mt.mis.basic.goods.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mis.basic.goods.service.GoodsTrackingService;
import com.ly.mt.mis.basic.goods.util.GoodsExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

/**
 * 追踪码管理
 *
 * @author taoye
 */
@Controller
@RequestMapping("/basic/goods/tracking")
public class GoodsTrackingController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsTrackingController.class);
    @Resource
    private GoodsTrackingService service;

    /**
     * 跳转追踪码管理页面
     *
     * @author taoye
     */
    @RequestMapping("/tracking")
    public String goods() {
        return "/basic/goods/goods_tracking";
    }


    /**
     * 跳转追踪码导入页面
     *
     * @author taoye
     */
    @RequestMapping("/upload")
    public String upload() {
        return "/basic/goods/goods_tracking_upload";
    }


    /**
     * 上传记录表格信息
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/loadRecordDatagrid")
    public ResponseJson loadRecordDatagrid(HttpServletRequest request) {
        try {
            return service.loadRecordDatagrid(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("TrackingCodeController.loadRecordDatagrid error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 上传记录表格信息删除
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/deleteRecordImport")
    public ResponseJson deleteRecordImport(HttpServletRequest request) {
        try {
            return service.deleteRecordImport(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("TrackingCodeController.deleteRecordImport error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 模版下载
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/trackingCodeDownload")
    public void trackingCodeDownload(HttpServletResponse response) {
        try {
            GoodsExcelUtil.trackingCodeDownload(response);
        } catch (Exception e) {
            LOGGER.error("TrackingCodeController.trackingCodeDownload error ", e);
        }
    }


    /**
     * 上传校验
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/trackingCodeUpload")
    public ResponseJson trackingCodeUpload(@RequestParam MultipartFile file) {
        try {
            return service.trackingCodeUpload(file);
        } catch (Exception e) {
            LOGGER.error("TrackingCodeController.trackingCodeUpload error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 导入
     *
     * @author taoye
     */
    @ResponseBody
    @RequestMapping("/trackingCodeImport")
    public ResponseJson trackingCodeImport(HttpServletRequest request) {
        try {
            return service.trackingCodeImport(RequestUtil.getJSONObject(request));
        } catch (Exception e) {
            LOGGER.error("TrackingCodeController.trackingCodeImport error ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}