package com.ly.mt.center.third.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.al.service.AlOssService;
import com.ly.mt.center.third.base.util.SpringBeanUtil;
import com.ly.mt.core.base.entity.RequestJson;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;

@RestController
@RequestMapping("/center/third")
public class BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @PostMapping("/requestDistribute")
    public ResponseJson requestDistribute(@RequestBody RequestJson requestJson) {
        try {
            /*LOGGER.info("center-third:requestJson={}", requestJson.toString());*/
            Object obj = SpringBeanUtil.getBean(requestJson.getServerName());
            if (null == obj) {
                LOGGER.error("center-third:请求接口不存在");
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            Method method = obj.getClass().getDeclaredMethod(requestJson.getFunctionName(), new Class[]{JSONObject.class});
            ResponseJson responseJson = (ResponseJson) method.invoke(obj, requestJson.getJsonObject());
            /*LOGGER.info("center-third:responseJson={}", responseJson.toString());*/
            return responseJson;
        } catch (Exception e) {
            LOGGER.error("center-third:请求执行异常=", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    @PostMapping("/upload")
    public ResponseJson upload(@RequestPart(value = "file") MultipartFile file, @RequestParam(value = "path") String path) {
        try {
            AlOssService service = (AlOssService) SpringBeanUtil.getBean("alOssServiceImpl");
            return service.uploadFile(file, path);
        } catch (Exception e) {
            LOGGER.error("center-third:upload执行异常=", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @PostMapping("/uploads")
    public ResponseJson uploads(@RequestParam(value = "path") String path,@RequestPart(value = "files") MultipartFile[] files) {
        try {
            AlOssService service = (AlOssService) SpringBeanUtil.getBean("alOssServiceImpl");
            return service.uploadFiles(path,files);
        } catch (Exception e) {
            LOGGER.error("center-third:upload执行异常=", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * OSS第三方服务上传文件接口
     *
     * @param files：文件集合
     * @param paths：文件路径集合
     * @return JSONObject
     * @author wanghongliang
     */
    @PostMapping("/uploadsMultipleFiles")
    public ResponseJson uploadsMultipleFiles(@RequestParam("files") MultipartFile[] files,@RequestParam("paths") String[] paths) {
        try {
            AlOssService service = (AlOssService) SpringBeanUtil.getBean("alOssServiceImpl");
            return service.uploadsMultipleFiles(files,paths);
        } catch (Exception e) {
            LOGGER.error("center-third:upload执行异常=", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}