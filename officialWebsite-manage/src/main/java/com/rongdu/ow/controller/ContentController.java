package com.rongdu.ow.controller;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.github.pagehelper.Page;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.context.Global;
import com.rongdu.ow.core.common.util.DateUtil;
import com.rongdu.ow.core.common.util.JsonUtil;
import com.rongdu.ow.core.common.util.RdPage;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.module.domain.Content;
import com.rongdu.ow.core.module.model.ContentModel;
import com.rongdu.ow.core.module.service.ContentService;

/**
 * 栏目内容Controller
 * 

 */
@Controller
@Scope("prototype")
public class ContentController extends ManageBaseController {
	
	public static final Logger logger = LoggerFactory.getLogger(ContentController.class);
	
	@Resource
	private ContentService contentService;

	/**
	 * 新增内容
	 * @param content
	 * @throws Exception
	 */
	@RequestMapping(value="/modules/manage/content/save.htm", method={RequestMethod.POST})
	public void save(@RequestParam(value="programaId",required=false) String programaId,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="writer",required=false) String writer,
			@RequestParam(value="resource",required=false) String resource,
			@RequestParam(value="isStick",required=false) String isStick,
			@RequestParam(value="keyword",required=false) String keyword,
			@RequestParam(value="description",required=false) String description,
			@RequestParam(value="state",required=false) String state,
			@RequestParam(value="remark",required=false) String remark,
			@RequestParam(value="sort",required=false) int sort,
			@RequestParam(value="thumbnail",required=false) MultipartFile thumbnail
			) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String filePath = save(thumbnail);
		Content params = new Content();
		params.setTitle(title);
		params.setSort(sort);
		params.setContent(content);
		params.setDescription(description);
		params.setKeyword(keyword);
		params.setIsStick(Integer.valueOf(isStick));
		params.setRemark(remark);
		params.setProgramaId(Integer.valueOf(programaId));
		params.setResource(resource);
		params.setState(Integer.valueOf(state));
		params.setWriter(writer);
		params.setThumbnail(filePath);
		params.setAddTime(new Date());
		params.setCount(0);
		//默认显示，不置顶
		if (params.getState() == null) {
			params.setState(10);
		}
		if (params.getIsStick() == null) {
			params.setIsStick(20);
		}
		int result = contentService.insert(params);
		if (result > 0) {
			resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "添加成功");
		} else {
			resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "添加失败");
		}
		ServletUtils.writeToResponse(response, resultMap);
	}

	/**
	 * 查询内容
	 * @param current
	 * @param pageSize
	 * @param searchParams
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/modules/manage/content/list.htm", method={RequestMethod.GET})
	public void list(@RequestParam(value="searchParams",required=false) String searchParams,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String, Object> params = JsonUtil.parse(searchParams, Map.class);
		Page<ContentModel> page = contentService.list(params, current, pageSize);
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("list", page);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constant.RESPONSE_DATA, dataMap);
		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		result.put(Constant.RESPONSE_CODE_MSG, "获取成功");
		ServletUtils.writeToResponse(response,result);
	}

	/**
	 * 编辑内容
	 * @param content
	 * @throws Exception
	 */
	@RequestMapping(value="/modules/manage/content/update.htm", method={RequestMethod.POST})
	public void update(@RequestParam(value="programaId",required=false) String programaId,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="writer",required=false) String writer,
			@RequestParam(value="resource",required=false) String resource,
			@RequestParam(value="isStick",required=false) String isStick,
			@RequestParam(value="keyword",required=false) String keyword,
			@RequestParam(value="description",required=false) String description,
			@RequestParam(value="state",required=false) String state,
			@RequestParam(value="remark",required=false) String remark,
			@RequestParam(value="sort",required=false) String sort,
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="thumbnail",required=false) MultipartFile thumbnail
			) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		if (thumbnail != null) {
			String filePath = save(thumbnail);
			params.put("thumbnail", filePath);
		}
		params.put("title", title);
		params.put("content", content);
		params.put("programaId", programaId);
		params.put("id", id);
		params.put("sort", sort);
		params.put("remark", remark);
		params.put("state", state);
		params.put("description", description);
		params.put("keyword", keyword);
		params.put("isStick", isStick);
		params.put("resource", resource);
		params.put("writer", writer);
		int result = contentService.updateSelective(params);
		if (result > 0) {
			resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "编辑成功");
		} else {
			resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "编辑失败");
		}
		ServletUtils.writeToResponse(response, resultMap);
	}
	
	private String save(MultipartFile file) {
		// 文件名称
		String picName = file.getOriginalFilename();
		CommonsMultipartFile cf = (CommonsMultipartFile) file;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File newFile = (File) fi.getStoreLocation();
		logger.debug("图片----------"+newFile);
		//获取文件名后缀
		String prefix=picName.substring(picName.lastIndexOf(".")+1);
		//重写文件名
		String fileName = System.currentTimeMillis()+String.valueOf((int)((Math.random()*9+1)*100000))+"."+prefix;
		//判断当前系统，确定存储路径
		String s = File.separator;
		String filePath = s+"data"+s+"image"+s+DateUtil.dateStr(DateUtil.getNow(), DateUtil.DATEFORMAT_STR_012)+s+fileName;
		if (s.equals("\\")) {
			filePath = "D:"+filePath;
			filePath = filePath.replaceAll("\\\\","/");
		}
		File files = new File(filePath);
		if (!files.exists()) {
			try {
				files.mkdirs();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		try {
			file.transferTo(files);
		} catch (IllegalStateException | IOException e) {
			logger.error(e.getMessage(), e);
		}	
		return Global.getValue("read_image")+"?path="+filePath;
	}
	
}
