package com.lhj.blog.controller.manage;

import com.lhj.common.support.DateSupport;
import com.lhj.common.support.JsonSupport;
import com.lhj.model.blog.NotebookCategory;
import com.lhj.mybatis.service.DataBaseService;
import com.lhj.system.support.SessionSupport;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/*
* 笔记分类管理 controller
* author:liuhaijiang
*/
@RestController
@RequestMapping("/noteBookCategory")
public class NotebookCategoryController {

    /*日志*/
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DataBaseService dataBaseService;

    @Autowired
    JsonSupport jsonSupport;

    @Autowired
    SessionSupport sessionSupport;

    //@CrossOrigin /*跨域请求支持*/
    @RequestMapping(value = "/query")
    public NotebookCategory query(NotebookCategory param){

        logger.info("查询笔记分类列表开始");
        NotebookCategory result = param;
        try {
            NotebookCategory NotebookCategory = new NotebookCategory();
            NotebookCategory.setParentId("-1");
            NotebookCategory.setOrderby("sort_key");
            List<NotebookCategory> roots = dataBaseService.selectList("findNotebookCategory", NotebookCategory);

            param.setCustomWhere(" and parent_id <> '-1' ");
            if(StringUtils.isBlank(param.getOrderby())){
                param.setOrderby("sort_key asc");
            }
            List<NotebookCategory> rows = dataBaseService.selectList("findNotebookCategory", param);

            for (NotebookCategory m:roots){
                m.setExpand(param.isExpand());
                this.recurrence(m,rows,param);
                //System.err.println(jsonSupport.toJson(m));
            }

            result.setRows(roots);
            result.setSuccess(true);
            result.setMessage("查询数据成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("查询笔记分类列表异常");
            logger.error("查询笔记分类列表异常",e);
            e.printStackTrace();
        }
        logger.info("查询笔记分类列表结束");
        return  result;
    }

    /*递归笔记分类*/
    public void recurrence(NotebookCategory row,List<NotebookCategory> rows,NotebookCategory params){

        for(NotebookCategory m:rows){

            if(StringUtils.equals(m.getParentId(),row.getSid())){
                m.setExpand(params.isExpand());
                row.getChildren().add(m);
                this.recurrence(m,rows,params);
            }

        }

    }

    @PostMapping(value = "/deletes")
    public NotebookCategory delete(@RequestBody NotebookCategory param){

        logger.info("删除笔记分类开始");
        logger.info(jsonSupport.toJson(param));
        NotebookCategory result = new NotebookCategory();
        try {

            if(param.getChildren().size()>0){
                this.delChildrenNode(param.getChildren());
            }

            dataBaseService.delete("deleteNotebookCategory", param);

            result.setSuccess(true);
            result.setMessage("删除笔记分类成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("删除笔记分类异常");
            logger.error("删除笔记分类异常",e);
            e.printStackTrace();
        }

        logger.info("删除笔记分类结束");
        return  result;
    }

    /*递归删除笔记分类*/
    public void delChildrenNode(List<NotebookCategory> rows){

        for(NotebookCategory m:rows){
            dataBaseService.delete("deleteNotebookCategory", m);
            this.delChildrenNode(m.getChildren());
        }

    }


    @PostMapping(value = "/save")
    public NotebookCategory save(@RequestBody NotebookCategory param){

        logger.info("新增/修改笔记分类开始 ");
        NotebookCategory result = param;
        try {
            String userCd = sessionSupport.getUserCd();
            Date systemDate = DateSupport.getSystemDate();

            param.setUpdateBy(userCd);
            param.setUpdateDate(systemDate);

            if(StringUtils.isBlank(param.getSid())){

                param.setCreatedBy(userCd);
                param.setCreatedDate(systemDate);
                dataBaseService.insert("addNotebookCategory",param);

            }else{
                dataBaseService.update("updateNotebookCategory",param);

            }

            result.setSuccess(true);
            result.setMessage("保存成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("保存异常");
            logger.error("保存异常",e);
            e.printStackTrace();
        }

        logger.info("新增/修改笔记分类结束");
        return  result;
    }

}
