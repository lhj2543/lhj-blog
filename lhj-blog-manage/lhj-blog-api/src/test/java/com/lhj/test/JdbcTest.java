package com.lhj.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public  void  jdbcTets()  {

        /*Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataSource.getConnection();
            String sql = "select * from sys_user" ;

            *//*PreparedStatement的优点：
                1、其使用参数设置，可读性好，不易记错。在statement中使用字符串拼接，可读性和维护性比较差。
                2、其具有预编译机制，性能比statement更快。
                3、其能够有效防止SQL注入攻击。*//*

            ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String sid = rs.getString("sid");
                String userCd = rs.getString("user_cd");
                System.err.println("sid="+sid+",userCd="+userCd);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(con != null){
                try{
                    con.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try{
                    ps.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }*/
    }

    @Test
    public void jdbcTemplatTest(){
        int flag = jdbcTemplate.update("update sys_user set user_cd='lhj' where sid='1' ");
        System.err.println("更新用户flag="+flag);
    }



}
