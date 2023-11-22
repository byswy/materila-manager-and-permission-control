package com.btp.rwj.config;

import com.btp.rwj.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有
        registry.addInterceptor(userInterceptor()).addPathPatterns("/**");
    }

    //自定义拦截器注入到容器
    @Bean
    public UserInterceptor userInterceptor() {
        return new UserInterceptor();
    }
}
