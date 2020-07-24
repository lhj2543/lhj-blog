package com.lhj.ribbon;

import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * Ribbon 自定义负载均衡算法
 * 官方文档明确给出了警告：
 * 这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，
 * 否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，也就是说
 * 我们达不到特殊化定制的目的了。
 */
@Configurable
public class MySelfRule {

    @Bean
    public IRule myRule()
    {
        return new RandomRuleCustom();//自定义算法类
    }


}
