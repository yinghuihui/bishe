package com.rongdu.ow;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.rongdu.ow.controller.ContentController;
import com.rongdu.ow.core.common.context.Constant;
import com.rongdu.ow.core.common.context.Global;
import com.rongdu.ow.core.common.util.DateUtil;
import com.rongdu.ow.core.common.util.ServletUtils;
import com.rongdu.ow.core.module.domain.ClUserBaseInfo;
import com.rongdu.ow.core.module.service.ClUserBaseInfoService;

@Controller
@Scope("prototype")
public class UserbaseInfoController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserbaseInfoController.class);
	@Resource
	private ClUserBaseInfoService clUserBaseInfoService;
	
	
	@RequestMapping(value = "/modules/user/userbaseinfo/save.htm", method = { RequestMethod.POST })
		public void save(@RequestParam(value="frontPic",required=true) MultipartFile frontPic,
				@RequestParam(value="obversePic",required=true) MultipartFile obversePic,
				@RequestParam(value="name",required=true) String name,
				@RequestParam(value="idNo",required=true) String idNo,
				@RequestParam(value="degree",required=true) String degree,
				@RequestParam(value="address",required=true) String address,HttpServletRequest request,
				HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		long userId = (Long) request.getSession().getAttribute("userId");
		ClUserBaseInfo baseInfo = new ClUserBaseInfo();
		String frontPicPath = save(frontPic);
		String obversePicPath = save(obversePic);
		baseInfo.setFrontImg(frontPicPath);
		baseInfo.setBackImg(obversePicPath);
		baseInfo.setUserId(userId);
		baseInfo.setRealName(name);
		baseInfo.setIdNo(idNo);
		baseInfo.setEducation(degree);
		baseInfo.setLiveAddr(address);
		int result = clUserBaseInfoService.updateByUserId(baseInfo);
		if (result > 0) {
			resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		} else {
			resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			resultMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
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
