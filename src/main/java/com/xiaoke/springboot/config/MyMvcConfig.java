package com.xiaoke.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//拓展springmvc的功能
//指明这是个配置类
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    //浏览器发送/****请求，来到*****页面
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/login").setViewName("login");
        registry.addViewController("/bar").setViewName("bar");
        registry.addViewController("/user/register").setViewName("register");
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/type/add").setViewName("typeadd");
        registry.addViewController("/goods/list").setViewName("goodslist");
        registry.addViewController("/goods/add").setViewName("addgood");
        registry.addViewController("/goods/details").setViewName("gooddetails");
        registry.addViewController("/goods/updata").setViewName("goodupdata");
        registry.addViewController("/orders/addOrders").setViewName("addOrders");
        registry.addViewController("/orders/userOrders").setViewName("myOrders");
        registry.addViewController("/orders/allOrders").setViewName("allOrders");
        registry.addViewController("/shoppingcart/myshopcart").setViewName("myshopcart");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }
}
