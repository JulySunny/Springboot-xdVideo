package net.xdclass.xdvideo.config;

import net.xdclass.xdvideo.interceptor.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 杨强
 * @Date: 2019/8/27 15:27
 * @Version 1.0
 * @Discription 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercepter())
                .addPathPatterns("/user/api/v1/*/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
