package com.ly.mt.cabinet.b.common.aspect;

import com.ly.mt.cabinet.b.common.bo.SysLogBO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Component
@Aspect
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
    private volatile SysLogBO sysLogBO = new SysLogBO();

    @Pointcut("@annotation(com.ly.mt.cabinet.b.common.annotation.LogRequired)")
    public void logPoint(){}

    @Before("logPoint()")
    public void before(JoinPoint joinPoint){
        long start = System.currentTimeMillis();
        //获取目标方法的参数信息
        Signature signature = joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        sysLogBO.setClassName(className);
        sysLogBO.setMethodName(methodName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sysLogBO.setCreateDate(sdf.format(LocalDateTime.now()));
        String[] params = ((CodeSignature)signature).getParameterNames();
        Object[] paramValues = joinPoint.getArgs();
        for (int i = 0 ; i < params.length ; i++){
            sysLogBO.getParams().put(params[i],paramValues[i]);
        }
        long end = System.currentTimeMillis();
        sysLogBO.setExecuteTime(String.valueOf(end-start));
        log.info("调用 {} 的 {} 方法 , 参数是 {} ",className,methodName,sysLogBO.getParams());
    }


    @Around("logPoint()")
    public void doAround(ProceedingJoinPoint point){

    }
}
