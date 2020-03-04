package com.lhj.test;

import com.lhj.blog.mapper.annotation.SysUserMapper;
import com.lhj.common.support.JsonSupport;
import com.lhj.model.system.SysUser;
import com.lhj.mybatis.service.DataBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest {

    @Autowired
    JsonSupport jsonSupport;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    DataBaseService dataBaseService;

    @Test
    public  void  myBaitsAnnotationTest(){

        List<SysUser> sysUsers = sysUserMapper.queryList(new SysUser());

        String result = jsonSupport.toJson(sysUsers);

        System.err.println(result);
    }

    @Test
    public  void  myBaitsXmlSelectOneTest(){

        SysUser sysUser=new SysUser();
        sysUser.setSid("33785726009001313386579");

        SysUser sysUsers = dataBaseService.selectOne("findSysUser", sysUser);

        System.err.println(jsonSupport.toJson(sysUsers));
    }

    @Test
    public  void  myBaitsXmlFindListTest(){

        SysUser sysUser=new SysUser();


        List<SysUser> sysUsers = dataBaseService.selectList("findSysUser", sysUser);

        System.err.println("size="+sysUsers.size());
        System.err.println(jsonSupport.toJson(sysUsers));
    }

    @Test
    public  void  myBaitsXmlTest(){

        SysUser sysUser=new SysUser();

        sysUser = dataBaseService.selectOne("countSysUser", sysUser);
        System.err.println("total=============="+sysUser.getTotal());
        sysUser.setPage(2);

        List<SysUser> sysUsers = dataBaseService.selectListByPage("findSysUser", sysUser);

        System.err.println("size="+sysUsers.size());
        System.err.println(jsonSupport.toJson(sysUsers));
    }

    @Test
    public  void  myBaitsXmlTestInster(){

        try{
            for (int i = 10; i < 20; i++) {
                SysUser sysUser=new SysUser();
                sysUser.setUserCd("admin_" + (i+1));

                sysUser.setCreatedBy("lhj");
                sysUser.setCreatedDate(new Date());

                int flag = dataBaseService.insert("addSysUser", sysUser);

                System.err.println("myBaitsXmlTestInster==flag="+flag);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
