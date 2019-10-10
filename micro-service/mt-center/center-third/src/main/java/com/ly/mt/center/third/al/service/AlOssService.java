package com.ly.mt.center.third.al.service;

import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description 文件上传
 * @Author taoye
 */
public interface AlOssService {
    /**
     * @Description 文件上传
     * @Author taoye
     */
    ResponseJson uploadFile(MultipartFile file, String path);
    /**
     * 多文件上传
     */
    ResponseJson uploadFiles(String path,MultipartFile... file);

    /**
     * OSS第三方服务上传文件接口
     *
     * @param files：文件集合
     * @param paths：文件路径集合
     * @return JSONObject
     * @author wanghongliang
     */
    ResponseJson uploadsMultipleFiles(MultipartFile[] files,String[] paths);
}