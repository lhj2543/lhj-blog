package com.lhj.system.support;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Md5Supper {

    public static  String getMd5Value(String value , int hashIterations,String byteSourceStr){
        String resutl = value;
        try{
            String hashAlgorithName = "MD5";
            ByteSource byteSource = ByteSource.Util.bytes(byteSourceStr);
            Object obj = new SimpleHash(hashAlgorithName, value, byteSource, hashIterations);
            resutl = obj.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  resutl;
    }

}
