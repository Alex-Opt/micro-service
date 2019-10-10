package com.ly.mt.mis.basic.goods.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.web.multipart.MultipartFile;

/**
 * 追踪码管理
 *
 * @author taoye
 */
public interface GoodsTrackingService {
    /**
     * 上传记录表格信息
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson loadRecordDatagrid(JSONObject jsonObject) throws Exception;

    /**
     * 上传记录表格信息删除
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson deleteRecordImport(JSONObject jsonObject) throws Exception;

    /**
     * 上传
     *
     * @param file 文件
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson trackingCodeUpload(MultipartFile file) throws Exception;

    /**
     * 导入
     *
     * @param jsonObject 入参
     * @return ResponseJson 反参
     * @throws Exception 异常
     * @author taoye
     */
    ResponseJson trackingCodeImport(JSONObject jsonObject) throws Exception;
}