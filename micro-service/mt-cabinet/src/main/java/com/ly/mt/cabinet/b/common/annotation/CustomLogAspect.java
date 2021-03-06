package com.ly.mt.cabinet.b.common.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description: 定义日志切面
 * @author: wanghongliang
 * @create: 2019-07-24 16:37
 **/
@Component
@Aspect
public class CustomLogAspect {
    private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(CustomLogAspect.class);

    @Pointcut("@annotation(com.ly.mt.cabinet.b.common.annotation.CustomLog)")
    public void cut() {
    }

    @Around("cut()")
    public Object saveLog(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        // 请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        // 请求的方法参数值
        Object[] args = point.getArgs();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        String params = "";
        if (args != null && paramNames != null) {
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
        }
        Logger.info("[格子柜B-APP请求方法]"+className+"."+methodName+"=====");
        Logger.info("[格子柜B-APP请求参数]"+params+"=====");
        final Object proceed = point.proceed();
        long time = System.currentTimeMillis() - start;
        Logger.info("[格子柜B-请求APP出参]" + proceed + "耗时:" + time + "ms");
        return proceed;
    }
}