package com.lhj.blog.controller.manage;

import com.lhj.common.support.DateSupport;
import com.lhj.common.support.JsonSupport;
import com.lhj.model.blog.NotebookMain;
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
* 笔记管理 controller
* author:liuhaijiang
*/
@RestController
@RequestMapping("/noteBook")
public class NotebookController {

    /*日志*/
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DataBaseService dataBaseService;

    @Autowired
    JsonSupport jsonSupport;

    @Autowired
    SessionSupport sessionSupport;

    @RequestMapping(value = "/query")
    public NotebookMain query(NotebookMain param){

        logger.info("查询笔记列表开始");
        NotebookMain result = param;
        try {
            NotebookMain NotebookMain = dataBaseService.selectOne("countNotebookMain", param);
            result.setTotal(NotebookMain.getTotal());

            if(StringUtils.isBlank(param.getOrderby())){
                param.setOrderby("update_date desc");
            }

            if(StringUtils.isNotBlank(param.getSearchValue())){
                param.setCustomWhere(" and (title like '%"+param.getSearchValue()+"%' or bodys like '%"+param.getSearchValue()+"%') ");
            }

            List<NotebookMain> rows = dataBaseService.selectListByPage("findNotebookMain", param);

            result.setRows(rows);
            result.setSuccess(true);
            result.setMessage("查询数据成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("查询笔记列表异常");
            logger.error("查询笔记列表异常",e);
            e.printStackTrace();
        }
        logger.info("查询笔记列表结束");
        return  result;
    }

    @RequestMapping(value = "/detail")
    public NotebookMain detail(NotebookMain param){

        logger.info("查询笔记详情开始");
        NotebookMain result = param;
        try {

            if(StringUtils.isNotBlank(param.getSid())){
                NotebookMain NotebookMain = dataBaseService.selectOne("findNotebookMain", param);
                result = NotebookMain==null?result:NotebookMain;
            }else{
                result.setBodys("");
            }
            result.setSuccess(true);
            result.setMessage("查询笔记详情成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("查询笔记详情异常");
            logger.error("查询笔记详情异常",e);
            e.printStackTrace();
        }

        logger.info("查询笔记详情结束");

        return  result;
    }

    @PostMapping(value = "/deletes")
    public NotebookMain delete(@RequestBody NotebookMain param){

        logger.info("删除笔记开始");
        logger.info(jsonSupport.toJson(param));
        NotebookMain result = new NotebookMain();
        try {
            for(NotebookMain u:param.getRows()){
                dataBaseService.delete("deleteNotebookMain", u);
            }

            result.setSuccess(true);
            result.setMessage("删除笔记成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("删除笔记异常");
            logger.error("删除笔记异常",e);
            e.printStackTrace();
        }

        logger.info("删除笔记结束");
        return  result;
    }


    @PostMapping(value = "/modify")
    public NotebookMain modify(@RequestBody NotebookMain param){

        logger.info("新增/修改笔记开始");
        NotebookMain result = param;
        try {
            String userCd = sessionSupport.getUserCd();
            Date systemDate = DateSupport.getSystemDate();

            param.setUpdateBy(userCd);
            param.setUpdateDate(systemDate);

            if(StringUtils.isBlank(param.getSid())){

                param.setCreatedBy(userCd);
                param.setCreatedDate(systemDate);
                dataBaseService.insert("addNotebookMain",param);

            }else{
                dataBaseService.update("updateNotebookMain",param);
            }

            result.setSuccess(true);
            result.setMessage("保存成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("保存异常");
            logger.error("保存异常",e);
            e.printStackTrace();
        }

        logger.info("新增/修改笔记结束");
        return  result;
    }

}
