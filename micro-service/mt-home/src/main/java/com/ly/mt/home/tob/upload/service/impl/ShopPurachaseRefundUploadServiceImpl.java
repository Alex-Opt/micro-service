package com.ly.mt.home.tob.upload.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.home.base.service.impl.BaseServiceImpl;
import com.ly.mt.home.tob.upload.service.ShopPurachaseRefundUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ShopPurachaseRefundUploadServiceImpl extends BaseServiceImpl implements ShopPurachaseRefundUploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopPurachaseRefundUploadServiceImpl.class);

    @Override
    public ResponseJson uploadFile(MultipartFile file) {
        return uploadFiles(file);
    }

    @Override
    public ResponseJson uploadFiles(MultipartFile... file) {
        long start = System.currentTimeMillis();
        try {
            String path = "image/shop-purachases/refund";
            JSONObject parameter = new JSONObject();
            parameter.put("file", file);
            parameter.put("path", path);
            String callBackData = callThirdCenter(path, file);
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, callBackData);
        } catch (Exception e) {
            LOGGER.error("call single file upload of UploadServiceImpls uploadFile has error,the error message is {}", e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }
}
