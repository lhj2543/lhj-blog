package com.lhj.consumer.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced //开启负载均衡 springCloud Ribbon基于netflix ribbon 实现的一套客户端负载均衡工具
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * Ribbon 切换负载均衡算法
     * RoundRobinRule 轮询（默认）
     * RandomRule 随机
     */
    /*@Bean
    public IRule getIrule(){
        return  new RandomRule();
    }*/

}
