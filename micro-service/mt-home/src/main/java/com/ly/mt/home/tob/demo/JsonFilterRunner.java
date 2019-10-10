package com.ly.mt.home.tob.demo;

import net.bytebuddy.implementation.attribute.AnnotationRetention;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author joel
 * @description: TODO
 * @date 2019/9/211:50
 */
@Aspect
@Component
public class JsonFilterRunner {

    @Pointcut
    public void test(){
        System.out.println("helloWorld");
    }

    /*@Before("@within(JsonFilter)")
    public void doBefore(Renderp){

    }*/
}
