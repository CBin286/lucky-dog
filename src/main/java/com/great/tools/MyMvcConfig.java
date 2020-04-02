package com.great.tools;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /*资源处理器*/
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/" + "/images/");
        registry.addResourceHandler("/back/**").addResourceLocations("/WEB-INF/" + "/back/");
        registry.addResourceHandler("/front/**").addResourceLocations("/WEB-INF/" + "/front/");
    }

}

