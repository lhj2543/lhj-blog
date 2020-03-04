package com.lhj.blog.mapper.annotation;

import com.lhj.model.system.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysUserMapper {

    @Select("select * from sys_user")
    public List<SysUser> queryList(SysUser user);

    @Select("select * from sys_user where sid = #{id}")
    public SysUser queryById(String id);

    @Options(useGeneratedKeys = true,keyProperty = "sid")
    @Insert("inster into sys_user(user_cd) values(#{userCd})")
    public int inster(SysUser user);

    @Delete("delete from sys_user where sid=#{id}")
    public int deleteById(String id);

    @Update("update sys_user set sex=#{sex} where sid=#{sid}")
    public int update(SysUser user);
}
