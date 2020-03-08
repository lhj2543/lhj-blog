package com.lhj.system.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.util.HashMap;
import java.util.Map;

/*

* */
public class CustomUsernamePasswordToken extends UsernamePasswordToken{

    private Map<String,Object> other =new HashMap<String,Object>();

    public CustomUsernamePasswordToken(String username, String password){
        super(username,password);
    }

    public CustomUsernamePasswordToken(String username, String password,Map<String,Object>  other){
        super(username,password);
        this.other = other;
    }

    public Map<String, Object> getOther() {
        return other;
    }

    public void setOther(Map<String, Object> other) {
        this.other = other;
    }
}
