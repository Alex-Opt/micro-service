package com.ly.mt.cabinet.c.standby.Intercept;


import com.ly.mt.cabinet.b.common.interceptor.LoginsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 */
@Configuration
public class ControllerConfiguration implements WebMvcConfigurer {
    @Autowired
    private OpenCabinetInterceptor openCabinetInterceptor;
    @Bean
    public OpenCabinetInterceptor demoInterceptor() {
        return new OpenCabinetInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(openCabinetInterceptor).addPathPatterns("/cabinet/c/h5OpenDevice/user/**");
    }
}

