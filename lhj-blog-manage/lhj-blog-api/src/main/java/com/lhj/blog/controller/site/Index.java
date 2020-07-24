package com.lhj.blog.controller.site;

import com.lhj.blog.dto.IndexDto;
import com.lhj.common.support.JsonSupport;
import com.lhj.model.blog.NotebookCategory;
import com.lhj.model.blog.SiteAttrs;
import com.lhj.model.system.SysMenu;
import com.lhj.mybatis.service.DataBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private DiscoveryClient client;

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

    /**
    * 首页数据初始化
    * */
    @RequestMapping("/initData")
    public IndexDto initData(@RequestBody IndexDto param){
        logger.info("加载首页数据开始");
        IndexDto result = new IndexDto();
        try {
            SiteAttrs siteAttrs = new SiteAttrs();
            siteAttrs.setAttrKey("index_attr");
            SiteAttrs attrs = dataBaseService.selectOne("findSiteAttrs", siteAttrs);
            result.setSiteAttrs(attrs);

            NotebookCategory notebookCategory = new NotebookCategory();
            notebookCategory.setCategoryName("skill");
            NotebookCategory category = dataBaseService.selectOne("findNotebookCategory", notebookCategory);

            notebookCategory.setParentId(category.getSid());
            notebookCategory.setCategoryName("");
            notebookCategory.setStatus("1");
            notebookCategory.setOrderby("sort_key");
            List<NotebookCategory> categorys = dataBaseService.selectList("findNotebookCategory", notebookCategory);
            category.setRows(categorys);
            result.setNotebookCategory(category);

            result.setSuccess(true);
        } catch(Exception e) {
            logger.error("加载首页数据异常",e);
            e.printStackTrace();
        }

        logger.info("加载首页数据结束");

        return  result;
    }

    /**
     * 服务发现
     * */
    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    public List discovery()
    {
        List<String> list = client.getServices();
        System.out.println("服务列表：" + list);

        List<ServiceInstance> srvList = client.getInstances("lhj-blog-manage");
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
        }

        list.add("哈哈哈哈");

        return list;
    }

}
