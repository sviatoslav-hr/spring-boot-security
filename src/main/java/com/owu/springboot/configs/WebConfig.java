package com.owu.springboot.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = "file:///" +
                System.getProperty("user.home") +
                File.separator +
                "images" +
                File.separator;
        registry.addResourceHandler("/ava/**")
        .addResourceLocations(path);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // get method
        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("url").setViewName("viewName");
    }
}
