package com.ly.mt.center.third.al.service.impl;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.ObjectMetadata;
import com.ly.mt.center.third.al.service.AlOssService;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_RANDOM;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @author zhanglifeng
 * @date 2019-05-17
 */
@Service
public class AlOssServiceImpl extends BaseServiceImpl implements AlOssService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlOssServiceImpl.class);
    @Resource
    YmlConfig yml;

    /**
     * @Description 文件上传
     * @Author taoye
     */
    @Override
    public ResponseJson uploadFile(MultipartFile file, String path) {
        OSSClient client = getOssClient();
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            String fileName = file.getOriginalFilename();
            String filePrefix = fileName.substring(fileName.lastIndexOf("."));
            String newName = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM) + filePrefix;
            path = path + "/" + newName;
            // 文件上传
            client.putObject(yml.getAlOssBucketName(), path, file.getInputStream(), objectMetadata);
            // 生成以GET方法访问的签名URL,访客可以直接通过浏览器访问相关内容,设置URL过期时间为50年
            URL url = client.generatePresignedUrl(yml.getAlOssBucketName(), path, new Date(System.currentTimeMillis() + 50 * 365 * 24 * 3600 * 1000*50));
            // 返回文件访问路径
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, url.toString());
        } catch (Exception e) {
            LOGGER.error("文件上传出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        } finally {
            if (null != client) {
                client.shutdown();
            }
        }
    }
    @Override
    public ResponseJson uploadFiles(String path,MultipartFile... file){
        OSSClient ossClient = getOssClient();
        List<String> returnUrls = new ArrayList<>();
        try {
            ObjectMetadata metaData = new ObjectMetadata();
            Arrays.asList(file).forEach(f->{
                metaData.setContentType(f.getContentType());
                metaData.setContentLength(f.getSize());
                String fileName = f.getOriginalFilename();
                String filePrefix = fileName.substring(fileName.lastIndexOf("."));
                String newName = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM) + filePrefix;
                String newPath = path + "/" + newName;
                try {
                    ossClient.putObject(yml.getAlOssBucketName(),newPath,f.getInputStream(),metaData);
                    URL url = ossClient.generatePresignedUrl(yml.getAlOssBucketName(),newPath,new Date(System.currentTimeMillis() + 50 * 365 * 24 * 3600 * 1000));
                    returnUrls.add(url.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,returnUrls);
        }catch (Exception e){
            LOGGER.error("file upload has error,the error message is {}",e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }finally {
            if (ossClient != null){
                ossClient.shutdown();
            }
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
    @Override
    public ResponseJson uploadsMultipleFiles(MultipartFile[] files, String[] paths){
        OSSClient ossClient = getOssClient();
        List<String> returnUrls = new ArrayList<>();
        try {
            ObjectMetadata metaData = new ObjectMetadata();
            int i = 0;
            for(MultipartFile f: files) {
                metaData.setContentType(f.getContentType());
                metaData.setContentLength(f.getSize());
                String fileName = f.getOriginalFilename();
                String filePrefix = fileName.substring(fileName.lastIndexOf("."));
                String newName = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_RANDOM) + filePrefix;
                String newPath = paths[i] + "/" + newName;
                i++;
                try {
                    ossClient.putObject(yml.getAlOssBucketName(), newPath, f.getInputStream(), metaData);
                    URL url = ossClient.generatePresignedUrl(yml.getAlOssBucketName(), newPath, new Date(System.currentTimeMillis() + 50 * 365 * 24 * 3600 * 1000));
                    returnUrls.add(url.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,returnUrls);
        }catch (Exception e){
            LOGGER.error("file upload has error,the error message is {}",e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }finally {
            if (ossClient != null){
                ossClient.shutdown();
            }
        }
    }

    /**
     * 拿到ossclient
     * （后期重构可以单独写几个工厂负责拿到第三方client的工厂，尽量不掺在业务代码里）
     *
     * @return ossClient
     * @author panjingtian
     * 注意使用完成后关闭client
     */
    private OSSClient getOssClient() {
        CredentialsProvider provider = new DefaultCredentialProvider(yml.getAlOssAccessKeyID(), yml.getAlOssAccessKeySecret());
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        return new OSSClient(yml.getAlOssEndpoint(), provider, clientBuilderConfiguration);
    }
}