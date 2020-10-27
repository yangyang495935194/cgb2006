package com.jt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 实现跨域的方式
     * 需要配置服务端程序
     * 方法说明:
     *      1.addMapping(/**) 允许什么样的请求可以跨域  所有的请求
     *      2.allowedOrigins("*")可以允许任意的域名
     *      3.allowCredentials(true) 跨域时是否允许携带cookie等参数
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true);
    }
}