package com.ly.mt.gzg.b.server.gzgb.service.impl;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RefreshScope
@Slf4j
public class GtPushMessageService {

    private static final String appId = "6fnMqHqWyN9zPouEHpLjU9";
    private static final String appKey = "zNxVaLm5ah979iDpALGBu5";
    private static final String appSecret = "PcdwR3yIxg9yPAgYMjZww8";
    private static final String masterSecret = "m1XgSyomvv5piVGhli2eT1";
    private static final String host = "http://sdk.open.api.igexin.com/apiex.htm";

    public void PushList(List<String> targetClientIds,Style0 style0,String content) {
        //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
        //别名推送方式
        // static String Alias1 = "";
        // static String Alias2 = "";
        // 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin_pushList_needDetails", "true");
        // 配置返回每个别名及其对应cid的用户状态，可选
        // System.setProperty("gexin_pushList_needAliasDetails", "true");
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        // 通知透传模板
        NotificationTemplate template = notificationTemplate(style0,content);
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List<Target> targets = new ArrayList<>();
        targetClientIds.forEach(x -> {
            Target target = new Target();
            target.setAppId(appId);
            target.setClientId(x);
            targets.add(target);

        });
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        String respStr = ret.getResponse().toString();
        if (StringUtils.equals(respStr,"ok")){
            log.info("个推推送消息成功");
        }else {
            log.info("个推图送消息失败");
        }
    }

    public static NotificationTemplate notificationTemplate(Style0 style0,String content) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        //Style0 style = new Style0();
        // 设置通知栏标题与内容
        //style.setTitle("抢单消息");
        //style.setText("测试个推消息体");
        // 配置通知栏图标
        //style.setLogo("icon.png");
        // 配置通知栏网络图标
        //style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        //style.setRing(true);
        //style.setVibrate(true);
        //style.setClearable(true);
        template.setStyle(style0);

        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent(content);
        return template;
    }

    /**
     * 通知栏消息布局样式(Style0 系统样式 Style1 个推样式 Style4 背景图样式 Style6 展开式通知样式)
     *
     * @param title   通知栏标题
     * @param text    通知栏内容
     * @param logo    配置通知栏图标
     * @param logoUrl 配置通知栏网络图标
     * @return
     */
    public Style0 getStyle0(String title, String text, String logo, String logoUrl) {
        Style0 style = new Style0();
        style.setTitle(title);
        style.setText(text);
        style.setLogo(logo);
        style.setLogoUrl(logoUrl);
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        return style;
    }

}
