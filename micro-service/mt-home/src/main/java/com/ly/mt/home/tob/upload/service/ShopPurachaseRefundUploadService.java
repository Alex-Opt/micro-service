package com.ly.mt.home.tob.upload.service;

import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.web.multipart.MultipartFile;

public interface ShopPurachaseRefundUploadService {
    /**
     * 单文件上传
     *
     * @param file
     * @return
     */
    ResponseJson uploadFile(MultipartFile file);

    /**
     * 多文件上传
     *
     * @param file
     * @return
     */
    ResponseJson uploadFiles(MultipartFile... file);
}
