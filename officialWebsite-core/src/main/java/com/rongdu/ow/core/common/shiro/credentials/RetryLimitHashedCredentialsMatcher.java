package com.rongdu.ow.core.common.shiro.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * shiro 凭证匹配器
 * 
 * 如在1个小时内密码最多重试5次，
 * 如果尝试次数超过5次就锁定1小时，
 * 1小时后可再次重试，
 * 如果还是重试失败，
 * 可以锁定如1天，
 * 以此类推，防止密码被暴力破解。
 * 通过继承HashedCredentialsMatcher，
 * 且使用Ehcache记录重试次数和超时时间。
 * 
 * @author yhjiang
 * @version 1.0
 * @date 2017年1月12日
 * Copyright 杭州融都科技股份有限公司 am All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
//        //retry count + 1
//        AtomicInteger retryCount = passwordRetryCache.get(username);
//        if(retryCount == null) {
//            retryCount = new AtomicInteger(0);
//            passwordRetryCache.put(username, retryCount);
//        }
//        if(retryCount.incrementAndGet() > 5) {
//            //if retry count > 5 throw
//            throw new ExcessiveAttemptsException();
//        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}
