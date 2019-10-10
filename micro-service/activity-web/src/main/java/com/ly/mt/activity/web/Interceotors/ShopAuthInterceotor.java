package com.ly.mt.activity.web.Interceotors;


import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.web.annotations.ShopLoginAuth;
import com.ly.mt.activity.web.annotations.TimeDown;
import com.ly.mt.activity.web.annotations.TimingMethodDown;
import com.ly.mt.activity.web.feign.ActivityFeignClient;
import com.ly.mt.core.common.method.ActivityMethodEnum;
import com.ly.mt.core.common.server.RedisServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * @description
 *
 *  门店活动B端登录token拦截器
 *
 *  登录在header中添加token或者param中
 *
 * @author panjingtian
 * @date 2019/6/12 4:01 PM
 */
@Component
public class ShopAuthInterceotor extends HandlerInterceptorAdapter {


    private final static String TOKEN = "token";

    @Autowired
    RedisServer redisServer;

    @Autowired
    private ActivityFeignClient activityServiceClient;


    public ShopAuthInterceotor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            TimingMethodDown timingMethodDown = ((HandlerMethod) handler).getMethodAnnotation(TimingMethodDown.class);
            ShopLoginAuth shopLoginAuth = ((HandlerMethod) handler).getMethodAnnotation(ShopLoginAuth.class);

            boolean a = handler instanceof ShopLoginAuth;
            boolean b = handler instanceof TimingMethodDown;
            if (timingMethodDown != null && shopLoginAuth != null) {
                boolean flag = false;
                flag = Auto(request, response, handler);
                flag = timingMethodDown(request, response, handler);
                return flag;
            } else if (timingMethodDown != null) {
                return timingMethodDown(request, response, handler);
            } else if (shopLoginAuth != null) {
                return Auto(request, response, handler);
            }
            return true;
        }
        return true;
    }

    /**
     * web定时拒绝访问策略
     * 配合自定义声明注解使用
     * {@link TimingMethodDown}
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    private boolean timingMethodDown(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        TimingMethodDown timingMethodDown = ((HandlerMethod) handler).getMethodAnnotation(TimingMethodDown.class);
        //对比当前时间
        String downtime = timingMethodDown.downtime();
        if(StringUtils.isEmpty(downtime)){
            throw new IllegalArgumentException("请使用专用的json格式声明过期日期,{\"year\": 2019,\"month\": 8,\"dayOfMonth\": 22,\"hour\": 23,\"minute\": 28,\"second\": 59}");
        }

        TimeDown timeDown = JSONObject.toJavaObject(JSONObject.parseObject(downtime), TimeDown.class);
        LocalDateTime nowTime = LocalDateTime.now(); //当前时间
        LocalDateTime endTime = LocalDateTime.of(timeDown.getYear(), timeDown.getMonth(),
                timeDown.getDayOfMonth(), timeDown.getHour(), timeDown.getMinute(), timeDown.getSecond()); //活动结束时间

        //结束活动条件
        if (nowTime.isAfter(endTime)){
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            ResObj resObj = new ResObj("2","活动结束啦");
            try {
                writer.write(resObj.toString());
                writer.close();
                return false;
            } finally {
                writer.close();
            }
        }
        return true;

    }

    /**
     * 权限认证
     * @param request
     * @param response
     * @param handler
     * @return
     */
    private boolean Auto(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ShopLoginAuth shopLoginAuth = ((HandlerMethod) handler).getMethodAnnotation(ShopLoginAuth.class);
        String token = request.getHeader(TOKEN);
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(TOKEN);
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ResObj resObj = new ResObj("3","无效用户或未登录");
        try {
            if (StringUtils.isEmpty(token)) {
                writer.write(resObj.toString());
                writer.close();
                return false;
            }
            String result = redisServer.getVauel(TOKEN+token);
            if (!StringUtils.isEmpty(result)) {
                String phoneNum = token.substring(21);
                JSONObject resulyJson = call(phoneNum);
                String code = resulyJson.getString("code");
                if ("0".equals(code)){
                    writer.write(resObj.toString());
                    writer.close();
                    return false;
                }

                return true;
            }
            writer.write(resObj.toString());
            writer.close();
            return false;
        } finally {
            writer.close();
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }


    private JSONObject call(String phone){
        JSONObject map = new JSONObject(2);
        map.put("phone",phone);
        map.put("serviceName", ActivityMethodEnum.SHUO_GET_FIND_MANAGER.getServiceName());
        map.put("functionName",ActivityMethodEnum.SHUO_GET_FIND_MANAGER.getFunctionName());
        return activityServiceClient.requestDistribute(map);
    }
}

class ResObj{

    private String code;
    private String msg;

    public ResObj() {
    }

    public String getCode() {
        return code;
    }

    public ResObj(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{" +"\""+
                "code" +  "\"" + ":"+ "\"" + code + "\"" + "," +
                "\""+ " msg" + "\""+ ":"+ "\""+ msg + "\""+
                '}';
    }
}