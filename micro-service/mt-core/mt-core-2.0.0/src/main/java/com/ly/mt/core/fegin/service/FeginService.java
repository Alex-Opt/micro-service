package com.ly.mt.core.fegin.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.fegin.method.DataCenterMethod;
import com.ly.mt.core.fegin.method.ThirdCenterMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description Fegin服务接口调用
 * @Author taoye
 */
public interface FeginService {
    /**
     * @Description 调用数据中心服务接口
     * @Author taoye
     */
    String callDataCenter(DataCenterMethod method, JSONObject jsonObject) throws Exception;

    /**
     * @Description 调用第三方服务接口
     * @Author taoye
     */
    String callThirdCenter(ThirdCenterMethod method, JSONObject jsonObject) throws Exception;

    /**
     * @Description 调用第三方服务接口上传文件
     * @Author taoye
     */
    String callThirdCenter(MultipartFile file, String Path) throws Exception;
}