package com.lhj.blog.controller.site;

import com.lhj.common.model.Pojo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
@Controller
@ResponseBody
等效于 @RestController
*/
@RestController
@RequestMapping("/index")
public class Index extends  SiteBaseController{

    @RequestMapping("/test")
    public Pojo Index(){
        Pojo result = new Pojo();

        return result;

    }

}
