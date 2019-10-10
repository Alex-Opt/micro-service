package com.ly.mt.core.getui.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.gexin.rp.sdk.template.style.Style6;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.getui.base.service.GtPushCabinetBMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.getui.base.config.GeTuiConstant.*;
import static com.ly.mt.core.getui.base.dict.GtOpenType.OPEN_URL;

/**
 * @date 2019-09-26
 * @author wanghongliang
 * @description 格子柜B 个推服务接口
 */
@Service("gtPushCabinetBMessageServiceImpl")
public class GtPushCabinetBMessageServiceImpl implements GtPushCabinetBMessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GtPushCabinetBMessageServiceImpl.class);
    /**
     * 推送给单个用户
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson pushSingle(JSONObject jsonObject) throws Exception {
        String appKey = CABINETB_GT_APP_KEY;
        String appId = CABINETB_GT_APPID_;
        String masterSecret = CABINETB_GT_APP_MASTER_SECRET;
        /**openType 1-打开连接 2-打开app*/
        String url = jsonObject.getString("url");
        /**openType 1-打开连接 2-打开app*/
        String openType = jsonObject.getString("openType");
        String content = jsonObject.getString("content");
        String cid = jsonObject.getString("cid");
        Style0 style0 = getStyle0(jsonObject);
        /* IGtPush push = new IGtPush(host,appKey, masterSecret);*/
        IGtPush push = new IGtPush(appKey, masterSecret);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        /**离线有效时间，单位为毫秒，可选*/
        message.setOfflineExpireTime(24 * 3600 * 1000);
        if (OPEN_URL.getId().equals(openType)) {
            LinkTemplate template = getLinkTemplate(url, style0);
            message.setData(template);
        } else {
            NotificationTemplate template = getNotificationTemplate(content, style0);
            message.setData(template);
        }
        /**1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发*/
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(cid);
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            LOGGER.error("消息推送给个人发生异常，异常信息：" + e.getMessage(), e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        if (ret != null) {
            LOGGER.info("消息推送返回的状态：" + ret.getResponse().toString());
        } else {
            LOGGER.info("服务器响应异常");
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    /**
     * 对指定列表用户推送消息
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson pushList(JSONObject jsonObject) throws Exception {
        String appKey = CABINETB_GT_APP_KEY;
        String appId = CABINETB_GT_APPID_;
        String masterSecret = CABINETB_GT_APP_MASTER_SECRET;
        String url = jsonObject.getString("url");
        /**openType 1-打开连接 2-打开app*/
        String openType = jsonObject.getString("openType");
        String content = jsonObject.getString("content");
        String cidsTemp = jsonObject.getString("cidList");
        List<String> cidList = JSONObject.parseArray(cidsTemp, String.class);
        Style0 style0 = getStyle0(jsonObject);
        /** 配置返回每个用户返回用户状态，可选*/
        System.setProperty("gexin_pushList_needDetails", "true");
        /**IGtPush push = new IGtPush(host,appKey, masterSecret);*/
        IGtPush push = new IGtPush(appKey, masterSecret);
        ListMessage message = new ListMessage();
        if (OPEN_URL.getId().equals(openType)) {
            LinkTemplate template = getLinkTemplate(url, style0);
            message.setData(template);
        } else {
            NotificationTemplate template = getNotificationTemplate(content, style0);
            message.setData(template);
        }
        /**设置消息离线，并设置离线时间*/
        message.setOffline(true);
        /**离线有效时间，单位为毫秒，可选*/
        message.setOfflineExpireTime(24 * 1000 * 3600);
        /**配置推送目标*/
        List targets = new ArrayList();
        Target target;
        for (String cid : cidList) {
            target = new Target();
            target.setAppId(appId);
            target.setClientId(cid);
            /* target.setAlias("");*/
            targets.add(target);
        }
        /**taskId用于在推送时去查找对应的message*/
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        LOGGER.info("消息推送返回的状态：" + ret.getResponse().toString());
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    /**
     * 对指定应用群推消息
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson pushAPP(JSONObject jsonObject) throws Exception {
        String appKey = CABINETB_GT_APP_KEY;
        String appId = CABINETB_GT_APPID_;
        String masterSecret = CABINETB_GT_APP_MASTER_SECRET;
        String url = jsonObject.getString("url");
        /**openType 1-打开连接 2-打开app*/
        String openType = jsonObject.getString("openType");
        String content = jsonObject.getString("content");
        Style0 style0 = getStyle0(jsonObject);
        IGtPush push = new IGtPush(appKey, masterSecret);
        AppMessage message = new AppMessage();
        if (OPEN_URL.getId().equals(openType)) {
            LinkTemplate template = getLinkTemplate(url, style0);
            message.setData(template);
        } else {
            NotificationTemplate template = getNotificationTemplate(content, style0);
            message.setData(template);
        }
        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        //推送给App的目标用户需要满足的条件
        AppConditions cdt = new AppConditions();
        List<String> appIdList = new ArrayList<String>();
        appIdList.add(appId);
        message.setAppIdList(appIdList);
        //手机类型
        List<String> phoneTypeList = new ArrayList<String>();
        //省份
        List<String> provinceList = new ArrayList<String>();
/*        provinceList.add("浙江");
        provinceList.add("上海");
        provinceList.add("北京");
        //杭州市
        provinceList.add("33010000");
        //成都市
        provinceList.add("51010000");*/
        cdt.addCondition(AppConditions.REGION, provinceList);
        //自定义tag
        List<String> tagList = new ArrayList<String>();

        cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList);
        cdt.addCondition(AppConditions.REGION, provinceList);
        cdt.addCondition(AppConditions.TAG, tagList);
        message.setConditions(cdt);

        IPushResult ret = push.pushMessageToApp(message, "任务别名_toApp");
        LOGGER.info("消息推送返回的状态：" + ret.getResponse().toString());
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    /**
     * 点击通知 打开网页 模板
     * @return
     */
    private LinkTemplate getLinkTemplate(String url, Style0 style0) {
        String appKey = CABINETB_GT_APP_KEY;
        String appId = CABINETB_GT_APPID_;
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setStyle(style0);
        /* template.setStyle(getStyle6());*/
        // 设置打开的网址地址
        template.setUrl(url);
        return template;
    }

    /**
     * 点击通知 打开应用（APP） 模板
     *
     * @param content 透传内容，不支持转义字符
     * @return
     */
    private NotificationTemplate getNotificationTemplate(String content, Style0 style0) {
        String appKey = CABINETB_GT_APP_KEY;
        String appId = CABINETB_GT_APPID_;
        NotificationTemplate template = new NotificationTemplate();
        /**设定接收的应用*/
        template.setAppId(appId);
        /**用于鉴定身份是否合法*/
        template.setAppkey(appKey);
        /**透传内容，不支持转义字符*/
        template.setTransmissionContent(content);
        /**收到消息是否立即启动应用： 1为立即启动，2则广播等待客户端自启动*/
        template.setTransmissionType(2);
        /**通知栏消息布局样式(Style0 系统样式 Style1 个推样式 Style4 背景图样式 Style6 展开式通知样式)，setStyle是新方法，使用了该方法后原来的设置标题、文本等方法就不起效*/
        template.setStyle(style0);
        /* template.setStyle(getStyle6());*/
        /**透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动*/
        return template;
    }

    /**
     * 通知栏消息布局样式(Style0 系统样式 Style1 个推样式 Style4 背景图样式 Style6 展开式通知样式)
     *
     * @return
     */
    private Style0 getStyle0(JSONObject jsonObject) {
        /**通知栏标题 */
        String title = jsonObject.getString("title");
        /** 通知栏内容*/
        String text = jsonObject.getString("text");
        /** 配置通知栏图标*/
        String logo = jsonObject.getString("logo");
        /** 配置通知栏网络图标*/
        String logoUrl = jsonObject.getString("logoUrl");
        Style0 style = new Style0();
        style.setTitle(title);
        style.setText(text);
        style.setLogo(logo);
        style.setLogoUrl(logoUrl);
        /**设置通知是否响铃，震动，或者可清除*/
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        return style;
    }

    /**
     * 大图标通知样式
     *
     * @return
     */
    private Style6 getStyle6(JSONObject jsonObject) {
        Style6 style = new Style6();
        /**通知栏标题 */
        String title = jsonObject.getString("title");
        /** 通知栏内容*/
        String text = jsonObject.getString("text");
        /** 配置通知栏图标*/
        String logo = jsonObject.getString("logo");
        /** 配置通知栏网络图标*/
        String logoUrl = jsonObject.getString("logoUrl");
        style.setTitle(title);
        style.setText(text);
        style.setLogo(logo);
        style.setLogoUrl(logoUrl);
        /**设置通知是否响铃，震动，或者可清除*/
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        /**通知展示大图样式，参数是大图的URL地址*/
        String bigStyle = jsonObject.getString("bigStyle");
        style.setBigStyle1(bigStyle);
        return style;
    }
}
