package com.project.config;

import com.project.interceptor.ValidateDataInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebAppConfig
 * @Description todo 注册拦截器 配置多个拦截器
 * @Author lu
 * @Date 2020/4/13
 * @Version 1.0
 */
@Configuration
public class ConfigWebIntercept implements WebMvcConfigurer {


    @Autowired
    private ValidateDataInterceptor validateDataInterceptor;

    /**
     * @description:todo 配置拦截器
     * @author: xiaolu
     * @Date: 2020/4/13
     * @return:
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 接口数字签名认证
        registry.addInterceptor(validateDataInterceptor).addPathPatterns(
                "/api/v1/validateData"
        ).excludePathPatterns();

        registry.addInterceptor(validateDataInterceptor).addPathPatterns(
                "/api/v1/validateData"
        ).excludePathPatterns();


    }


}
