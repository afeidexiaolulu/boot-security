package com.mytest.security.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author
 * @version 1.00
 * @time 2020/2/19 0019  下午 9:28
 */
@Configuration  //配置类
public class WebMvcConfig implements WebMvcConfigurer {


    //默认Url根路径跳转到/login，此url为spring security提供
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("redirect:/login");
//    }

    //默认Url根路径跳转到/login，此url为spring security提供
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("redirect:/login-view");

        registry.addViewController("/login-view").setViewName("login");
    }
}
