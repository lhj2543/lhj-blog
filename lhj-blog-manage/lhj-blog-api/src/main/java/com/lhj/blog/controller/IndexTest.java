package com.lhj.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;



/*
@Controller
@ResponseBody
等效于 @RestController
*/
@RestController
public class IndexTest {

    /*@RequestMapping("/")
    public String hello(){
        String result = "欢迎进入lhj-blog";

        return  result;
    }
*/

    @RequestMapping("/test")
    public Map<String,Object> index(){
        Map<String,Object> result = new HashMap();

        result.put("status","success");
        result.put("message","hello springBoot");

        return  result;
    }

}
