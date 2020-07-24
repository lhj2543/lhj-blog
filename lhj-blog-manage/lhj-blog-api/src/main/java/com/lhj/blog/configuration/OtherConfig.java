package com.lhj.blog.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 *      prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
 *
 * 只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
 *
 */
@ConfigurationProperties(prefix = "otherconfig") /*默认从全局配置文件中获取值*/
@PropertySource(value = {"classpath:otherConfig.properties"}) //指定配置文件取值 yml文件好像不支持
@Component  /*（把普通pojo实例化到spring容器中，相当于配置文件中的 <bean id="" class=""/>）*/
public class OtherConfig {


    private Map<String,Object> map = new HashMap<String,Object>();


    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
