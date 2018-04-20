package com.rongdu.ow.core.common.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.rongdu.ow.core.module.domain.SysUser;


/**
 * 密码工具类
 * @author yhjiang
 * @version 1.0
 * @date 2017年1月12日
 * Copyright 杭州融都科技股份有限公司 am All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }


    public void encryptPassword(SysUser user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), hashIterations).toHex();

        user.setPassword(newPassword);
    }
    
    public static String encryptPassword(String salt, String password) {

        password = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(salt), hashIterations).toHex();
        return  password;
    }

}

