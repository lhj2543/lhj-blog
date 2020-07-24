package com.lhj.feign.controller;

import com.lhj.feign.feignService.FeignTestService;
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
     * 日志
     */
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    FeignTestService feignTestService;


    @RequestMapping(value = "/test")
    public List test(){
        List restult = new ArrayList();

        try {
            restult = feignTestService.discovery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return restult;
    }

}
