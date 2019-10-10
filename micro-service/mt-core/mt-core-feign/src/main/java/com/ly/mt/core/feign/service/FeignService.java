package com.ly.mt.core.feign.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.core.feign.ThirdCenterMethod;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Fegin服务接口调用
 *
 * @author taoye
 */
public interface FeignService {
    /**
     * 调用数据中心服务接口
     *
     * @param method     接口枚举
     * @param jsonObject 请求参数
     * @return 数据中心返回的result
     * @throws Exception 处理失败时抛出RuntimeException
     * @author taoye
     */
    String callDataCenter(DataCenterMethod method, JSONObject jsonObject) throws Exception;

    /**
     * 调用第三方服务接口，处理失败时抛出RuntimeException
     *
     * @param method     接口枚举
     * @param jsonObject 请求参数
     * @return 第三方服务返回的result
     * @throws Exception 处理失败时抛出RuntimeException
     * @author taoye
     */
    String callThirdCenter(ThirdCenterMethod method, JSONObject jsonObject) throws Exception;

    /**
     * 调用第三方服务接口上传文件，处理失败时抛出RuntimeException
     *
     * @param file：文件
     * @param Path：路径
     * @return 第三方服务返回的result
     * @throws Exception 处理失败时抛出RuntimeException
     * @author taoye
     */
    String callThirdCenter(MultipartFile file, String Path) throws Exception;

    /**
     * 调用第三方服务接口多图片多路径上传，处理失败时抛出RuntimeException
     *
     * @param files：多文件文件
     * @param paths：多路径
     * @return 第三方服务返回的result
     * @throws Exception 处理失败时抛出RuntimeException
     * @author whl
     */
    String callThirdCenter(MultipartFile[] files,String[] paths) throws Exception;

    /**
     * 调用第三方服务多文件上传
     * @param path
     * @param file
     * @return
     */
    String callThirdCenter(String path,MultipartFile... file);
}