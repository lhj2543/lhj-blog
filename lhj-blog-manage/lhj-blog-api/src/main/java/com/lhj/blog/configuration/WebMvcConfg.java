package com.lhj.blog.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
*@EnableWebMvc 该注解springMvc配置全部自己配置，不使用springBoot默认配置
*继承WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
**/
@Configuration
public class WebMvcConfg extends WebMvcConfigurerAdapter {

    /*
    跨越请求处理
    */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")//需要跨域访问的Map路径
        .allowedOrigins("http://localhost:8080")//允许跨域访问的ip及端口
        .allowedHeaders("*")//允许跨域访问的Headers内容
        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")//允许跨域访问的方法，OPTIONS必须设置Shiro中用到
        .allowCredentials(true);
    }

}
