package com.lhj.blog.controller.manage;

import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.SysApp;
import com.lhj.mybatis.service.DataBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/sysApp")
public class SysAppController {

    /*日志*/
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DataBaseService dataBaseService;

    @Autowired
    JsonSupport jsonSupport;


    @RequestMapping(value = "/queryAll")
    public List<SysApp> queryAll(){

        logger.info("查询系系统应用列表开始");

        List<SysApp> result = new ArrayList<SysApp>();

        try {
            result = dataBaseService.selectList("findSysApp");
            System.err.println(result.size());
            System.out.println("11111111111111");

        } catch(Exception e) {
            logger.error("查询用户列表异常",e);
            e.printStackTrace();
        }

        logger.info("查询系系统应用列表开始");

        return  result;
    }



}
