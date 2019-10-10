package com.ly.mt.core.oss;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.ly.mt.core.common.constant.FilePathEnum;
import com.ly.mt.core.common.entity.basic.BasicFileVo;
import com.ly.mt.core.common.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

import static com.ly.mt.core.common.constant.IdEnum.RANDOM;

/**
 * @author zhanglifeng
 * @date 2019-05-17
 */
@Component
@RefreshScope
public class OssServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OssServer.class);
    @Value("${oss.bucketName}")
    private String bucketName;
    @Value("${oss.accessKeyID}")
    private String accessKeyID;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.baseURL}")
    private String baseURL;


    /**
     * @Description 文件上传oss
     * @Author zhanglifeng
     */
    public String upload(MultipartFile upLoadFile, FilePathEnum filePathEnum, Date expiration) throws Exception {
        OSS client = null;
        BasicFileVo file = new BasicFileVo();
        try {
            if (null == expiration) {
                // 设置URL过期时间为50年
                expiration = new Date(System.currentTimeMillis() + 50 * 365 * 24 * 3600 * 1000);
            }
            String fileName = upLoadFile.getOriginalFilename();
            String contentType = upLoadFile.getContentType();
            long fileSize = upLoadFile.getSize();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);
            objectMetadata.setContentLength(fileSize);

            String filePrefix = fileName.substring(fileName.lastIndexOf("."));
            String newName = IdUtil.getId(RANDOM) + filePrefix;
            String filePath = filePathEnum.getPath() + "/" + newName;

            client = new OSSClientBuilder().build(endpoint, accessKeyID, accessKeySecret);
            client.putObject(bucketName, filePath, upLoadFile.getInputStream(), objectMetadata);
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            URL url = client.generatePresignedUrl(bucketName, filePath, expiration);

            // 返回文件信息
            file.setFileName(fileName);
            file.setFileSize(String.valueOf(fileSize));
            file.setContentType(contentType);
            file.setPath(filePath);
            file.setUrl(url.toString());
        } catch (Exception e) {
            LOGGER.error("文件上传出错:", e);
        } finally {
            if (null != client) {
                client.shutdown();
            }
        }
        return JSONObject.toJSONString(file);
    }


    /**
     *
     * 上传文件返回url路径
     * @author panjingtian
     * @param inputStream  二维码文件流
     * @param filePathEnum
     * @param expiration
     * @return url oss存储文件的url路径
     */
    public String upload(InputStream inputStream, FilePathEnum filePathEnum, Date expiration) throws IOException {
        OSSClient client = getOssClient();
        String url = null;

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("image/png");
            String fileName = IdUtil.getId(RANDOM);
            String filePath = filePathEnum.getPath() + "/" + fileName;
            client.putObject(bucketName, filePath, inputStream, objectMetadata);
            url = client.generatePresignedUrl(bucketName, filePath, expiration).toString();
            return url.toString();
        }catch (Exception e){
            return url;
        }finally {
            inputStream.close();
            client.shutdown();
        }
    }

    /**
     * @description 文件下载
     * @Author zhanglifeng
     */
    public void download(String path) {
        OSS client = null;
        try {
            client = new OSSClientBuilder().build(endpoint, accessKeyID, accessKeySecret);
            OSSObject ossObject = client.getObject(new GetObjectRequest(bucketName, path));
            BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
            }
            reader.close();
        } catch (Exception e) {
            LOGGER.error("文件下载出错:", e);
        } finally {
            if (null != client) {
                client.shutdown();
            }
        }
    }


    /**
     * 拿到ossclient
     * （后期重构可以单独写几个工厂负责拿到第三方client的工厂，尽量不掺在业务代码里）
     * @author panjingtian
     * 注意使用完成后关闭client
     * @return ossClient
     */
    private OSSClient getOssClient() {
        CredentialsProvider provider = new DefaultCredentialProvider(accessKeyID, accessKeySecret);
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        OSSClient client = new OSSClient(endpoint, provider, clientBuilderConfiguration);
        return client;
    }

}