package com.ly.mt.core.aliyun.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author taoye
 */
public interface OssService {
    /**
     * 文件上传
     *
     * @param file 文件
     * @param path 路径
     * @return url
     * @author taoye
     */
    String uploadFile(MultipartFile file, String path) throws Exception;
}