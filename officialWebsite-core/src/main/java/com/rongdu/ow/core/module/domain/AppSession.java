package com.rongdu.ow.core.module.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * appSessinon记录实体
 * 
 * @author caitt
 * @version 1.0.0
 * @date 2017-05-12 15:46:15
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class AppSession implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	* 
	*/
	private String token;

	/**
	* 
	*/
	private String refreshToken;

	/**
	* 
	*/
	private Integer userId;

	/**
	* 
	*/
	private Date expireTime;

	/**
	* 
	*/
	private Date lastAccessTime;

	/**
	* 
	*/
	private Integer status;

	/**
	* 
	*/
	private String session;

	/**
	* 
	*/
	private String errData;

	/**
	* 
	*/
	private Integer loginType;

	/**
	* 
	*/
	private Integer loginId;

	/**
	 * 获取主键Id
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键Id
	 * 
	 * @param 要设置的主键Id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取
	 *
	 * @return
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 设置
	 * 
	 * @param token
	 *            要设置的
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 获取
	 *
	 * @return
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * 设置
	 * 
	 * @param refreshToken
	 *            要设置的
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * 获取
	 *
	 * @return
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置
	 * 
	 * @param userId
	 *            要设置的
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获取
	 *
	 * @return
	 */
	public Date getExpireTime() {
		return expireTime;
	}

	/**
	 * 设置
	 * 
	 * @param expireTime
	 *            要设置的
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * 获取
	 *
	 * @return
	 */
	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	/**
	 * 设置
	 * 
	 * @param lastAccessTime
	 *            要设置的
	 */
	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	/**
	 * 获取
	 *
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置
	 * 
	 * @param status
	 *            要设置的
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取
	 *
	 * @return
	 */
	public String getSession() {
		return session;
	}

	/**
	 * 设置
	 * 
	 * @param session
	 *            要设置的
	 */
	public void setSession(String session) {
		this.session = session;
	}

	/**
	 * 获取
	 *
	 * @return
	 */
	public String getErrData() {
		return errData;
	}

	/**
	 * 设置
	 * 
	 * @param errData
	 *            要设置的
	 */
	public void setErrData(String errData) {
		this.errData = errData;
	}

	/**
	 * 获取
	 *
	 * @return
	 */
	public Integer getLoginType() {
		return loginType;
	}

	/**
	 * 设置
	 * 
	 * @param loginType
	 *            要设置的
	 */
	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	/**
	 * 获取
	 *
	 * @return
	 */
	public Integer getLoginId() {
		return loginId;
	}

	/**
	 * 设置
	 * 
	 * @param loginId
	 *            要设置的
	 */
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

}