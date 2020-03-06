package com.lhj.system.controller;

import com.lhj.common.model.Pojo;
import com.lhj.model.system.SysAccount;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroController {

    @RequestMapping("/unAuth")
    public Pojo unAuth(){

        Pojo result = new Pojo();

        result.setSuccess(false);
        result.setMessage("抱歉，您没有权限，请联系管理员!");

        return result;
    }

}
