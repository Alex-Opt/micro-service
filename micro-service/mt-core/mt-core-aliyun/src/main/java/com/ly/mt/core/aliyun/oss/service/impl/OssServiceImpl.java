package com.ly.mt.core.aliyun.oss.service.impl;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.ObjectMetadata;
import com.ly.mt.core.aliyun.base.config.AliYunConstant;
import com.ly.mt.core.aliyun.oss.service.OssService;
import com.ly.mt.core.base.util.SnowflakeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Date;

import static com.ly.mt.core.aliyun.base.config.AliYunConstant.*;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_RANDOM;

/**
 * 文件上传
 *
 * @author taoye
 */
@Service
public class OssServiceImpl implements OssService {
    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    public String uploadFile(MultipartFile file, String path) throws Exception {
        Assert.notNull(file, "OssServiceImpl.uploadFile file must not be null");
        Assert.notNull(path, "OssServiceImpl.uploadFile path must not be null");
        CredentialsProvider provider = new DefaultCredentialProvider(OSS_ACCESS_KEY_ID, OSS_ACCESS_SECRET);
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        OSSClient client = new OSSClient(OSS_ENDPOINT, provider, clientBuilderConfiguration);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        String fileName = file.getOriginalFilename();
        String filePrefix = fileName.substring(fileName.lastIndexOf("."));
        String newName = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM) + filePrefix;
        path = path + "/" + newName;
        // 文件上传
        String bucketName = AliYunConstant.getOssBucketName(profile);
        client.putObject(bucketName, path, file.getInputStream(), objectMetadata);
        // 生成以GET方法访问的签名URL,访客可以直接通过浏览器访问相关内容,设置URL过期时间为50年
        URL url = client.generatePresignedUrl(bucketName, path, new Date(System.currentTimeMillis() + 50 * 365 * 24 * 3600 * 1000));
        client.shutdown();
        // 返回文件访问路径
        return url.toString();
    }
}