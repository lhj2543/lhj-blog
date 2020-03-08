package com.lhj.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Md5Test {

    @Test
    public void md5Test() {

        String hashAlgorithName = "MD5";
        String password = "123456";
        int hashIterations = 2;
        ByteSource byteSource = ByteSource.Util.bytes("administrator");
        Object obj = new SimpleHash(hashAlgorithName, password, byteSource, hashIterations);
        System.err.println("加密之后的密码" + obj);

    }

}
