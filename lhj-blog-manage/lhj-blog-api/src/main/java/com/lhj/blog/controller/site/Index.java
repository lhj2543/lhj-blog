package com.lhj.blog.controller.site;

import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.SysMenu;
import com.lhj.mybatis.service.DataBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
@Controller
@ResponseBody
等效于 @RestController
*/
@RestController
@RequestMapping("/index")
public class Index extends  SiteBaseController{

    @Autowired
    JsonSupport jsonSupport;

    @Autowired
    DataBaseService dataBaseService;

    /*日志*/
    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/loadTopMenus")
    public SysMenu loadTopMenus(@RequestBody SysMenu param){
        logger.info("加载个人主页菜单开始");

        SysMenu result = new SysMenu();
        try {
            param.setCategory("site-top");
            param.setStatus("1");
            param.setOrderby("sort_key");
            List<SysMenu> rows = dataBaseService.selectList("findSysMenu", param);
            result.setRows(rows);
            result.setSuccess(true);
        } catch(Exception e) {
            logger.error("加载个人主页菜单结束异常",e);
            e.printStackTrace();
        }

        logger.info("加载个人主页菜单结束");

        return  result;
    }

}
