package com.rongdu.ow.core.common.util.ueditor;

import javax.servlet.http.HttpServletRequest;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.ConfigManager;
import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;
import com.baidu.ueditor.upload.Uploader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 重写百度编辑器图片上传
 * 图片上传指定路径：
 * windows：D:\data\image\日期/文件名
 * linux: /data/image/日期/文件名			
 */
public class UeditorActionEnter extends ActionEnter{
	private HttpServletRequest request = null;
	private String rootPath = null;
	private String contextPath = null;
	private String actionType = null;
	private ConfigManager configManager = null;

	public UeditorActionEnter(HttpServletRequest request, String rootPath)
	{
		super(request, rootPath);
		this.request = request;
		this.rootPath = rootPath;
		this.actionType = request.getParameter("action");
		this.contextPath = request.getContextPath();
		this.configManager = ConfigManager.getInstance(this.rootPath, this.contextPath, request.getRequestURI());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String invoke()
	{
		if ((this.actionType == null) || (!ActionMap.mapping.containsKey(this.actionType))) {
			return new BaseState(false, 101).toJSONString();
		}

		if ((this.configManager == null) || (!this.configManager.valid())) {
			return new BaseState(false, 102).toJSONString();
		}

		State state = null;

		int actionCode = ActionMap.getType(this.actionType);

		Map conf = null;

		switch (actionCode)
		{
		case 0:
			return this.configManager.getAllConfig().toString();
		case 1:
		case 2:
		case 3:
		case 4:
			conf = this.configManager.getConfig(actionCode);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(new Date());
			//判断当前系统，确定存储路径
			String s = File.separator;
			String savePath = s+"data"+s+"image"+s+date+s+(String)conf.get("savePath");
			if (s.equals("\\")) {
				savePath = "E:"+savePath;
			}
			conf.put("rootPath", "");
			conf.put("savePath", savePath);
			state = new Uploader(this.request, conf).doExec();
			break;
		case 5:
			conf = this.configManager.getConfig(actionCode);
			String[] list = this.request.getParameterValues((String)conf.get("fieldName"));
			state = new ImageHunter(conf).capture(list);
			break;
		case 6:
		case 7:
			conf = this.configManager.getConfig(actionCode);
			int start = getStartIndex();
			state = new FileManager(conf).listFile(start);
		}

		return state.toJSONString();
	}

}
