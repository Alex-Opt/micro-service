package com.ly.mt.center.third.al.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(description = "阿里——OSS服务")
@RestController
@RequestMapping("/center/third/")
public class AlOssController {
    @ApiOperation("文件上传")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/al/oss/upload")
    public ResponseJson upload(@ApiParam(value = "上传文件", required = true) @RequestPart(value = "file") MultipartFile file,
                               @ApiParam(value = "上传路径", required = true) @RequestParam(value = "path") String path) {
        return null;
    }
}