package com.lhj.system.controller;

import com.lhj.common.support.DateSupport;
import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.SysItem;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 字典管理 controller
* author:liuhaijiang
*/
@RestController
@RequestMapping("/sysItem")
public class SysItemController {

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
    //@RequiresRoles({"base","admin"}) //如果subject中有这些字典才可以访问方法。如果没有这个权限则会抛出异常AuthorizationException。
    //属于T1并且属于T2字典可以访问
    //@RequiresRoles(value={"T1","T2"})
    //属于T1或者T2字典可以访问
    //@RequiresRoles(value={"base","admin"},logical = Logical.OR)
    public SysItem query(SysItem param){

        logger.info("查询字典列表开始");
        SysItem result = param;
        try {

            if(StringUtils.isBlank(param.getCategoryId())){
                result.setSuccess(true);
                //result.setMessage("字典分类ID不能为空！");
                return result;
            }

            if(StringUtils.isBlank(param.getOrderby())){
                param.setOrderby("sort_key asc");
            }

            List<SysItem> rows = dataBaseService.selectList("findSysItem", param);

            result.setRows(rows);
            result.setSuccess(true);
            result.setMessage("查询数据成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("查询字典列表异常");
            logger.error("查询字典列表异常",e);
            e.printStackTrace();
        }
        logger.info("查询字典列表结束");
        return  result;
    }

    /*
    * 加载字典列表
    * */
    @RequestMapping(value = "/getSysItemByCategoryName")
    public SysItem getSysItemByCategoryName(@RequestBody SysItem param){

        logger.info("查询字典开始");
        SysItem result = param;
        try {

            if(StringUtils.isNotBlank(param.getCategoryName())){
                List<SysItem> rows = dataBaseService.selectList("findSysItemByCategoryName", param);

                if(rows!=null && rows.size()>0){
                    Map<String,Object> itemMap = new HashMap<String,Object>();
                    for (SysItem i:rows){
                        itemMap.put(i.getItemKey(),i.getItemValue());
                    }
                    result.setItemMap(itemMap);
                }
            }else  if(param.getCategoryNames()!=null && param.getCategoryNames().size()>0){
                List<SysItem> rows = dataBaseService.selectList("findSysItemByCategoryNames", param);

                if(rows!=null && rows.size()>0){
                    Map<String,Object> itemMap = new HashMap<String,Object>();
                    for (SysItem i:rows){
                        String categoryName = i.getCategoryName();
                        Map<String, String> item = (Map<String, String>) itemMap.get(categoryName);
                        if(item!=null){
                            item.put(i.getItemKey(),i.getItemValue());

                        }else {
                            Map<String,String> im = new HashMap<String, String>();
                            im.put(i.getItemKey(),i.getItemValue());
                            itemMap.put(categoryName,im);
                        }
                    }
                    result.setItemMap(itemMap);
                }
            }

            result.setSuccess(true);
            result.setMessage("查询字典详情成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("查询字典详情异常");
            logger.error("查询字典详情异常",e);
            e.printStackTrace();
        }

        logger.info("查询字典详情结束");

        return  result;
    }

    @PostMapping(value = "/deletes")
    public SysItem delete(@RequestBody SysItem param){

        logger.info("删除字典开始");
        logger.info(jsonSupport.toJson(param));
        SysItem result = new SysItem();
        try {
            dataBaseService.delete("deleteSysItem", param);

            result.setSuccess(true);
            result.setMessage("删除字典成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("删除字典异常");
            logger.error("删除字典异常",e);
            e.printStackTrace();
        }

        logger.info("删除字典结束");
        return  result;
    }


    @PostMapping(value = "/save")
    public SysItem modify(@RequestBody SysItem param){
        String itemKey = param.getItemKey();

        logger.info("新增/修改字典开始 itemKey="+itemKey);
        SysItem result = param;
        try {

            param.setUpdateBy(sessionSupport.getUserCd());
            param.setUpdateDate(DateSupport.getSystemDate());

            if(StringUtils.isBlank(param.getSid())){

                SysItem acc = this.isExist(itemKey);
                if(acc!=null && StringUtils.isNotBlank(acc.getSid())){
                    result.setSuccess(false);
                    result.setMessage(itemKey+"该字典key已存在！");
                    return  result;
                }

                param.setCreatedBy(sessionSupport.getUserCd());
                param.setCreatedDate(DateSupport.getSystemDate());
                dataBaseService.insert("addSysItem",param);

            }else{
                dataBaseService.update("updateSysItem",param);
            }

            result.setSuccess(true);
            result.setMessage("保存成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("保存异常");
            logger.error("保存异常",e);
            e.printStackTrace();
        }

        logger.info("新增/修改字典结束");
        return  result;
    }

    @RequestMapping(value = "/isExist")
    public SysItem isExist(String key){

        logger.info("验证字典key是否已存在开始");
        SysItem result = new SysItem();
        try {
            result.setItemKey(key);
            SysItem sysAccount = dataBaseService.selectOne("findSysItem", result);
            result = sysAccount==null?result:sysAccount;
            result.setSuccess(true);
            result.setMessage("验证字典key是否已存在成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("验证字典key是否已存在异常");
            logger.error("验证字典key是否已存在异常",e);
            e.printStackTrace();
        }

        logger.info("验证字典key是否已存在结束");
        return  result;
    }

}
