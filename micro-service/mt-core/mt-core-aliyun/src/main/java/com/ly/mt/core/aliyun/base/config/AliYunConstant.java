package com.ly.mt.core.aliyun.base.config;

import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 静态常量配置
 *
 * @author taoye
 */
public class AliYunConstant {
    private final static Logger LOGGER = LoggerFactory.getLogger(AliYunConstant.class);

    /**
     * 环境
     */
    private static final String PROFILE_DEV = "dev";
    private static final String PROFILE_TEST = "test";
    private static final String PROFILE_GRAY = "gray";
    private static final String PROFILE_PROD = "prod";


    /**
     * sms
     */
    public static final String SMS_DOMAIN = "dysmsapi.aliyuncs.com";
    public static final String SMS_VERSION = "2017-05-25";
    public static final String SMS_ACTION = "SendSms";
    public static final String SMS_ACCESS_KEY_ID = "LTAIRPwOUgffGrdQ";
    public static final String SMS_ACCESS_SECRET = "yKY62OLDjO7W4SioHPDmBRFEdfSTa9";


    /**
     * oss
     */
    public static final String OSS_ACCESS_KEY_ID = "LTAItbmtUS0mjRGt";
    public static final String OSS_ACCESS_SECRET = "HeHzWVf6bnJXsSTQuqXgzvnoX2tpVs";
    public static final String OSS_ENDPOINT = "oss-cn-beijing.aliyuncs.com";
    private static String OSS_BUCKET_NAME;
    private static final String OSS_BUCKET_NAME_DEV = "moti-dev";
    private static final String OSS_BUCKET_NAME_TEST = "moti-test";
    private static final String OSS_BUCKET_NAME_FORMAL = "moti-formal";

    /**
     * 根据环境获取bucketName
     *
     * @author taoye
     */
    public static String getOssBucketName(String profile) throws Exception {
        if (StringUtil.isEmpty(OSS_BUCKET_NAME)) {
            if (PROFILE_DEV.equals(profile)) {
                OSS_BUCKET_NAME = OSS_BUCKET_NAME_DEV;
            } else if (PROFILE_TEST.equals(profile)) {
                OSS_BUCKET_NAME = OSS_BUCKET_NAME_TEST;
            } else if (PROFILE_GRAY.equals(profile) || PROFILE_PROD.equals(profile)) {
                OSS_BUCKET_NAME = OSS_BUCKET_NAME_FORMAL;
            } else {
                LOGGER.error("AliYunConstant.getOssBucketName error profile={} not in [dev,test,gray,prod]", profile);
            }
        }
        LOGGER.info("AliYunConstant.getOssBucketName profile={} OSS_BUCKET_NAME={}", profile, OSS_BUCKET_NAME);
        return OSS_BUCKET_NAME;
    }
}