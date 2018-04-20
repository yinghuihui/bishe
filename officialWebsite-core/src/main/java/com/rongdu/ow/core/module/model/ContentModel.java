package com.rongdu.ow.core.module.model;

import com.rongdu.ow.core.module.domain.Content;

/**
 * 
 * @author chenjianwei@erongdu.com
 * @version 1.0.0
 * @date 2017年8月10日 下午5:52:03
 * Copyright 杭州融都科技股份有限公司 金融创新事业部 此处填写项目英文简称  All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class ContentModel extends Content{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 栏目名称
	 */
	private String programaName;
	
	/**
	 * 获取栏目名称
	 *
	 * @return programaName
	 */
	public String getProgramaName(){
		return programaName;
	}

	/**
	 * 设置栏目名称
	 * 
	 * @param programaName 要设置的栏目名称
	 */
	public void setProgramaName(String programaName){
		this.programaName = programaName;
	}
}
