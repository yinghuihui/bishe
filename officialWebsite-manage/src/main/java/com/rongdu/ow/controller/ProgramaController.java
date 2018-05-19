package com.rongdu.ow.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.util.JsonUtil;
import com.rongdu.ow.core.common.util.ListUtil;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.module.domain.Programa;
import com.rongdu.ow.core.module.service.ProgramaService;

 /**
 * 栏目Controller
 * 

 */
@Controller
@Scope("prototype")
public class ProgramaController extends BaseController {

	@Resource
	private ProgramaService programaService;
	
	/**
	 * 新增栏目
	 * @param programa
	 * @throws Exception
	 */
	@RequestMapping(value="/modules/manage/programa/save.htm", method={RequestMethod.POST})
	public void save(HttpServletResponse response,@RequestBody Programa programa) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (programa.getParentId() == null) {
			programa.setParentId(0);
		}
		programa.setAddTime(new Date());
		programa.setUpdateTime(new Date());
		int result = programaService.insert(programa);
		if (result==1) {
			resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		} else {
			resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}
		ServletUtils.writeToResponse(response, resultMap);
	}
	
	/**
	 * 编辑栏目
	 * @param programa
	 * @throws Exception
	 */
	@RequestMapping(value="/modules/manage/programa/update.htm", method={RequestMethod.POST})
	public void update(HttpServletResponse response, @RequestBody Programa programa) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		programa.setUpdateTime(new Date());
		int result = programaService.updateById(programa);
		if (result==1) {
			resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		} else {
			resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}
		ServletUtils.writeToResponse(response, resultMap);	
	}
	
	/**
	 * 显示栏目
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value="/modules/manage/programa/enable.htm", method={RequestMethod.POST})
	public void enable(@RequestParam(value = "id") long id) {
		Map<String, Object> paramaMap = new HashMap<String, Object>();
		paramaMap.put("id", id);
		paramaMap.put("state", 10);
		boolean result=programaService.updateSelective(paramaMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (result) {
			resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "启用成功");
		} else {
			resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "启用失败");
		}
		ServletUtils.writeToResponse(response, resultMap);	
	}
		
	/**
	 * 隐藏栏目
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value="/modules/manage/programa/disable.htm", method={RequestMethod.POST})
	public void disable(@RequestParam(value = "id") long id) {
		Map<String, Object> paramaMap = new HashMap<String, Object>();
		paramaMap.put("id", id);
		paramaMap.put("state", 20);
		boolean result=programaService.updateSelective(paramaMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (result) {
			resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "禁用成功");
		} else {
			resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "禁用失败");
		}
		ServletUtils.writeToResponse(response, resultMap);	
	}
	
	/**
	 * 栏目列表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/programa/list.htm", method={RequestMethod.GET})
	public void findAllPrograma(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="searchParams",required=false) String searchParams) throws Exception {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		List<Map<String, Object>> list = programaService.list(params);
		list = ListUtil.list2Tree(list, "value", "parentId");
		list = ListUtil.treeForExt(list, null, null, true);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, list);
		res.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response, res);
	}
}
