package com.lhj.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    /**
     * required = false 解决idea 提示报错could no autowire
     */
    @Autowired(required = false)
    RestTemplate restTemplate;

    /**
     * 日志
     */
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 微服务服务访问地址
     */
    //private static final String REST_URL_PREFIX = "http://localhost:9090/rivermanage";
    private static final String REST_URL_PREFIX = "http://LHJ-BLOG-MANAGE/rivermanage";


    @RequestMapping(value = "/test")
    public List test(){
        List restult = new ArrayList();

        try {
            restult = restTemplate.getForObject(REST_URL_PREFIX + "/site/index/discovery", List.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return restult;
    }

}
