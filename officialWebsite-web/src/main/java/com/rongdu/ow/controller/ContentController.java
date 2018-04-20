package com.rongdu.ow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.rongdu.ow.controller.base.BaseController;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.util.RdPage;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.module.domain.Content;
import com.rongdu.ow.core.module.service.ContentService;

/**
 * 
 * @author chenjianwei@erongdu.com
 * @version 1.0.0
 * @date 2017年8月10日 上午10:50:13 Copyright 杭州融都科技股份有限公司 金融创新事业部 此处填写项目英文简称 All
 *       Rights Reserved 官方网站：www.erongdu.com 未经授权不得进行修改、复制、出售及商业使用
 */

@Controller
@Scope("prototype")
@SuppressWarnings("restriction")
public class ContentController extends BaseController {

	
	@Resource
	private ContentService contentService;

	/**
	 * 获取内容
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/web/content/find.htm", method = { RequestMethod.GET })
	public void find(@RequestParam(value = "id", required = false) long id)
			throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		int count = contentService.getById(id).getCount();
		paramMap.put("count", ++count);
		contentService.updateSelective(paramMap);
		Content content = contentService.getById(id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, content);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}

	/**
	 * 根据栏目id获取内容列表(新闻类，根据时间排序)
	 * 
	 * @param programaId
	 * @param current
	 * @param pageSize
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/web/content/listbyprogramaid.htm", method = { RequestMethod.GET })
	public void listByProgramaId(
			@RequestParam(value = "programaId", required = false) int programaId,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "current", required = false) int current,
			@RequestParam(value = "pageSize", required = false) int pageSize)
			throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("programaId", programaId);
		paramMap.put("remark", remark);
		Page<Content> page = contentService.listByProgramaId(paramMap, current,
				pageSize);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("list", page);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, dataMap);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	
	/**
	 * 获取logo
	 * 
	 * @param response
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/modules/web/content/getlogo.htm", method = { RequestMethod.GET })
	public void getLogo(@RequestParam(value = "remark", required = false) int remark) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("keyword", "logo");
		paramMap.put("remark", remark);
		Content content = contentService.getLogo(paramMap);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, content);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}
	
	/**
	 * 根据栏目id获取内容（首页内容，根据sort排序）
	 * 
	 * @param programaId
	 * @param current
	 * @param pageSize
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/modules/web/content/sortbyprogramaid.htm", method = { RequestMethod.GET })
	public void sortByProgramaId(
			@RequestParam(value = "programaId", required = false) int programaId,
			@RequestParam(value = "remark", required = false) int remark,
			@RequestParam(value = "current", required = false) int current,
			@RequestParam(value = "pageSize", required = false) int pageSize)
			throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("programaId", programaId);
		paramMap.put("remark", remark);
		Page<Content> page = contentService.sortByProgramaId(paramMap, current,
				pageSize);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("list", page);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constant.RESPONSE_DATA, dataMap);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, result);
	}

}
