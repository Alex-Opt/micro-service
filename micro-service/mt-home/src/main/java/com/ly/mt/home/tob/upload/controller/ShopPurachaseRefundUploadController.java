package com.ly.mt.home.tob.upload.controller;

import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.home.tob.upload.service.ShopPurachaseRefundUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@Api(tags = {"upload"})
@RestController
@RequestMapping("/dj/b/refundUpload")
public class ShopPurachaseRefundUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopPurachaseRefundUploadController.class);

    @Resource
    private ShopPurachaseRefundUploadService shopPurachaseRefundUploadService;

    @ApiOperation("file upload")
    @PostMapping("/fileUpload")
    public ResponseJson fileUpload(MultipartFile... files) {
        long start = System.currentTimeMillis();
        //业务代码
        try {
            ResponseJson responseJson = shopPurachaseRefundUploadService.uploadFiles(files);
            long end = System.currentTimeMillis();
            LOGGER.info("call fileUpload of ShopPurachaseRefundUploadController cost {} mills", (end - start));
            return responseJson;
        } catch (Exception e) {
            LOGGER.info("call fileUpload of ShopPurachaseRefundUploadController has error,the error message is {}", e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

}
