package com.lhj.system.service;

import com.lhj.common.support.DateSupport;
import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.SysAccount;
import com.lhj.model.system.SysUser;
import com.lhj.model.system.SysUserRole;
import com.lhj.mybatis.service.DataBaseService;
import com.lhj.system.support.Md5Supper;
import com.lhj.system.support.SessionSupport;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;

/**
 * @author 17822
 */
@Service
public class SysUserService {

    /*日志*/
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DataBaseService dataBaseService;

    @Autowired
    JsonSupport jsonSupport;

    @Autowired
    SessionSupport sessionSupport;

    //事务
    @Transactional
    public SysUser modify(SysUser param) throws Exception {
        String userCd = param.getUserCd();

        SysUser result = param;

        String ssuserCd = sessionSupport.getUserCd();
        Date systemDate = DateSupport.getSystemDate();

        param.setUpdateBy(ssuserCd);
        param.setUpdateDate(systemDate);

        SysAccount sysAccount = param.getSysAccount();
        sysAccount.setUpdateBy(ssuserCd);
        sysAccount.setUpdateDate(systemDate);
        sysAccount.setUserCd(userCd);

        if(!StringUtils.equals(sysAccount.getPassword(),sysAccount.getOldPassword())){
            String md5Password = Md5Supper.getMd5Value(sysAccount.getPassword(), 2, userCd);
            sysAccount.setPassword(md5Password);
        }
        /*新增用户*/
        if(StringUtils.isBlank(param.getSid())){

            SysAccount acc = this.userCdIsExist(userCd);
            if(acc!=null && StringUtils.isNotBlank(acc.getSid())){
                result.setSuccess(false);
                result.setMessage(userCd+"该用户名已存在！");
                return  result;
            }

            param.setCreatedBy(ssuserCd);
            param.setCreatedDate(systemDate);
            dataBaseService.insert("addSysUser",param);

            sysAccount.setCreatedBy(ssuserCd);
            sysAccount.setCreatedDate(systemDate);
            dataBaseService.insert("addSysAccount",sysAccount);

        }else{
            //修改用户
            dataBaseService.update("updateSysUser",param);

            dataBaseService.update("updateSysAccountByUserCd",sysAccount);

            result.setAccountStatus(sysAccount.getLicence());
        }

        //用户角色
        Set<SysUserRole> userRoles = param.getUserRoles();
        for(SysUserRole ur: userRoles){
            ur.setUpdateBy(ssuserCd);
            ur.setUpdateDate(systemDate);
            ur.setUserCd(userCd);
            if(StringUtils.isNotBlank(ur.getSid())){
                dataBaseService.update("updateSysUserRole",ur);
            }else {
                ur.setCreatedBy(ssuserCd);
                ur.setCreatedDate(systemDate);
                dataBaseService.insert("addSysUserRole",ur);
            }
        }

        return  result;
    }

    public SysAccount userCdIsExist(String userCd){

        logger.info("验证用户CD是否已存在开始");
        SysAccount result = new SysAccount();
        try {
            result.setUserCd(userCd);
            SysAccount sysAccount = dataBaseService.selectOne("findSysAccount", result);
            result = sysAccount==null?result:sysAccount;
            result.setSuccess(true);
            result.setMessage("验证用户CD是否已存在成功");

        } catch(Exception e) {
            result.setSuccess(false);
            result.setMessage("验证用户CD是否已存在异常");
            logger.error("验证用户CD是否已存在异常",e);
            e.printStackTrace();
        }

        logger.info("验证用户CD是否已存在结束");
        return  result;
    }

}
