package com.lhj.blog.configuration;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/*
*@EnableWebMvc 该注解springMvc配置全部自己配置，不使用springBoot默认配置
*继承WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
**/
@Configuration
//这个注解很重要啊，不写不生效
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebMvcConfg extends WebMvcAutoConfiguration implements WebMvcRegistrations, WebMvcConfigurer {

    /*
    跨越请求处理
    */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")//需要跨域访问的Map路径
        .allowedOrigins("http://localhost:8080")//允许跨域访问的ip及端口
        //.allowedOrigins("*")//允许跨域访问的ip及端口
        .allowedHeaders("*")//允许跨域访问的Headers内容
        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")//允许跨域访问的方法，OPTIONS必须设置Shiro中用到
        .allowCredentials(true);
    }

    /**
     * 处理mybites 懒加载返回json 异常
     * 方法2：//在所有相关的类前加上@JsonIgnoreProperties, 作用是json序列化时忽略bean中的一些属性序列化和反序列化时抛出的异常. 解决 mybatis 关联查询json 异常
     @JsonIgnoreProperties(value = {"handler"})
     * */
    /*@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = new ObjectMapper();

        //null字段不返回
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // mybatis 使用懒加载后，返回JSON报错
        // 而且这个配置不能放在“null字段不返回”配置前面，否则"null字段不返回"配置会失效
        // @see https://www.liangzl.com/get-article-detail-5363.html
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }*/

    /*
    继承父类 RequestMapping 路径
    */
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ControllerApiRequestMappingHandlerMapping();
    }

    @Override
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        return null;
    }

    @Override
    public ExceptionHandlerExceptionResolver getExceptionHandlerExceptionResolver() {
        return null;
    }

}
