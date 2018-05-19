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

import com.rongdu.ow.controller.base.BaseController;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.module.domain.Programa;
import com.rongdu.ow.core.module.service.ProgramaService;

/**
 * 

 */

@Controller
@Scope("prototype")
public class ProgramaController extends BaseController {

	@Resource
	private ProgramaService programaService;
	
	/**
	 * 根据父栏目找出子栏目
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value="/modules/web/programa/listbyparentid.htm", method={RequestMethod.GET})
	public void listByProgramaId(@RequestParam(value="id",required=false) long id, @RequestParam(value="remark",required=false) String remark) throws Exception {
		Map<String, Object> searchParams = new HashMap<String,Object>();
		searchParams.put("remark", remark);
		searchParams.put("parentId", id);
		List<Programa> programa = programaService.listByParentId(searchParams);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, programa);
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}
	
}
