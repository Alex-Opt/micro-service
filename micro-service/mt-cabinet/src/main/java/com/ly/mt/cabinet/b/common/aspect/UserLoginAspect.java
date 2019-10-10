package com.ly.mt.cabinet.b.common.aspect;

import com.ly.mt.cabinet.b.common.exception.AESException;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.core.base.entity.ResponseJson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 用户登录异常拦截
 */
@Aspect
@Component
public class UserLoginAspect {

    private static final Logger log = LoggerFactory.getLogger(UserLoginAspect.class);
    ThreadLocal<Long> startTime = new ThreadLocal<>();


    @Pointcut("execution(public * com.ly.mt.cabinet.b.controller..*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }


    /**
     * 返回记录日志操作
     * @param resopnseData
     * @throws Throwable
     */
    @AfterReturning(returning = "resopnseData", pointcut = "webLog()")
    public void doAfterReturning(Object resopnseData) throws Throwable {
        // 处理完请求，返回内容
        log.info("RESPONSE : " + resopnseData);
        log.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
        startTime.remove();//用完之后记得清除，不然可能导致内存泄露;
    }


    /**
     * 统一异常拦截管理
     * @param joinPoint
     * @return
     */
    @Around("execution(public * com.ly.mt.cabinet.b.controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {

            return joinPoint.proceed();
        } catch (AESException e){
            return Resp.createByErrorMessage(e.getMessage());
        }
    }


}
