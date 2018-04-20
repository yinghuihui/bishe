package com.rongdu.ow.user.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.rongdu.ow.core.common.util.DateUtil;
import com.rongdu.ow.core.common.util.JsonUtil;
import com.rongdu.ow.core.common.util.MapUtil;
import com.rongdu.ow.core.module.domain.AppSession;
import com.rongdu.ow.core.module.domain.User;
import com.rongdu.ow.core.module.mapper.AppSessionMapper;
import com.rongdu.ow.core.module.mapper.UserMapper;

/**
 * Created by lsk on 2016/9/21.
 */
@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AppDbSession {

	private static final Logger logger = LoggerFactory.getLogger(AppDbSession.class);
	
	private int liveMin = 60 * 24 * 7;

	@Resource
	private EhCacheCacheManager cacheManager;
	@Resource
	private AppSessionMapper appSessionMapper;
	@Resource
	private UserMapper userMapper;

	private String cacheName = "appSession";

	public boolean remove(String token) {
		Cache cache = cacheManager.getCache(cacheName);
		cache.evict(token);

		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("token", token);
		AppSession rec = appSessionMapper.findSelective(paramMap);
		if (rec == null) {
			return false;
		}

		appSessionMapper.delete(rec.getId());
		return true;
	}

	private Map toMap(String data) {
		return JSONObject.parseObject(data, LinkedHashMap.class);
	}

	public Object access(String token) {

		Cache cache = getCache();
		AppSession rec = cache.get(token, AppSession.class);
		if (rec == null) {
			logger.info("query db session");
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("token", token);
			rec = appSessionMapper.findSelective(paramMap);
			cache.put(token, rec);
		}

		if (rec == null){
			return new Object[][] { { "code", 413 }, { "msg", "token不存在" } };
		}
			

		if (rec.getStatus()== 0) {
			appSessionMapper.deleteByToken(token);
			return toMap(rec.getErrData());
		}

		Date now = new Date();

		if (now.getTime() > rec.getExpireTime().getTime()) {
			appSessionMapper.deleteByToken(token);
			return new Object[][] { { "code", 411 }, { "msg", "token已过期" } };
		}

		rec.setExpireTime(DateUtil.dateAddMins(now, liveMin));
		rec.setLastAccessTime(now);

		cache.put(token, rec);

		AppSession appSession = new AppSession();
		appSession.setId(rec.getId());
		appSession.setExpireTime(rec.getExpireTime());
		appSession.setLastAccessTime(rec.getLastAccessTime());
		appSessionMapper.update(appSession);

		return new AppSessionBean(toMap(rec.getSession()));

	}

	public AppSessionBean create(HttpServletRequest request, String loginname) {
		return create(request, loginname, 1);
	}

	private Cache getCache() {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			try {
				cacheManager.getCacheManager().addCache(cacheName);
			} catch (Exception e) {
				logger.warn("添加cache失败", e);
			}
			cache = cacheManager.getCache(cacheName);
		}
		return cache;
	}

	public AppSessionBean create(HttpServletRequest request, String loginname,
			int loginType) {

		User user = userMapper.findByLoginName(loginname);
		Long userId = user.getId();

		String token = UUID.randomUUID().toString().replaceAll("-", "");
		String refreshToken = user.getUuid();

		Map<String,Object> session = MapUtil.array2Map(new Object[][] {
				{
						"front",
						new Object[][] { { "userId", userId },
								{ "token", token },
								{ "refreshToken", refreshToken } } },
				{ "userData", user } });

		AppSession oSession = appSessionMapper.selectOneByUserId(userId);
		if (oSession != null) {
			getCache().evict(oSession.getToken());
			
			Map<String,Object> errDate = new HashMap<String, Object>();
			errDate.put("code", 410);
			errDate.put("msg", "您的账号已在其他设备登录");
			oSession.setStatus(0);
			oSession.setErrData(JSONObject.toJSONString(errDate));
			appSessionMapper.update(oSession);
		}

		Date now = new Date();
		AppSession newSession = new AppSession();;
		newSession.setToken(token);
		newSession.setRefreshToken(refreshToken);
		newSession.setUserId(userId.intValue());
		newSession.setExpireTime(DateUtil.dateAddMins(now, liveMin));
		newSession.setLastAccessTime(now);
		newSession.setStatus(1);
		newSession.setLoginType(loginType);
		newSession.setSession(JsonUtil.toString(session));
		appSessionMapper.save(newSession);

		return new AppSessionBean(session);

	}

	public Map<String, Object> getUserData(String token) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("token", token);
		AppSession rec = appSessionMapper.findSelective(paramMap);
		return toMap(rec.getSession());
	}

	public Object autoLogin(HttpServletRequest request, String refreshToken) {

		Map<String,Object> params = new HashMap<String, Object>();
		params.put("uuid", refreshToken);
		User rec = userMapper.findSelective(params);
		if (rec == null) {
			return new Object[][] { { "success", false }, { "msg", "refreshToken不存在" } };
		}
		
		String loginname = rec.getLoginName();

		AppSessionBean sessionBean = create(request, loginname, 1);

		return sessionBean;

	}

	public AppSession findLastAppSession(Long userId) {
		AppSession oSession = appSessionMapper.selectOneByUserId(userId);
		return 	 oSession ;
	}
}
