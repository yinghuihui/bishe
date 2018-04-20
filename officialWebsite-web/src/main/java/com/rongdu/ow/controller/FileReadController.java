package com.rongdu.ow.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rongdu.ow.controller.base.BaseController;
import com.rongdu.ow.core.common.util.OrderNoUtil;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.service.ContentService;

@Controller
@Scope("prototype")
public class FileReadController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FileReadController.class);

	@Resource
	private ContentService contentService;

	/**
	 * 文件读取
	 * 
	 * @param request
	 * @param response
	 * @param path
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/modules/common/sysContractAttachment/readImg.htm")
	public String readImg(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "path", required = true) String path)
			throws IOException {
		path = URLDecoder.decode(path, "UTF-8");
		if (StringUtil.isBlank(path)) {
			return null;
		}
		File file = new File(path);
		// 如果图片不存在 返回Null
		if (!file.exists()) {
			logger.debug("文件不存在");
			return null;
		}
		if (path.toLowerCase().endsWith(".jpg")
				|| path.toLowerCase().endsWith(".png")) {
			// 表明生成的响应是图片
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("image/jpeg");
		} else if (path.toLowerCase().endsWith(".gif")) {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("image/gif");
		} else if (path.toLowerCase().endsWith(".xls")) {
			String fileName = OrderNoUtil.getSerialNumber() + ".xls";
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			response.setContentType("application/vnd.ms-excel");
		} else if (path.toLowerCase().endsWith(".xlsx")) {
			String fileName = OrderNoUtil.getSerialNumber() + ".xlsx";
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		} else if (path.toLowerCase().endsWith(".pdf")) {
			response.setContentType("application/pdf");
		} else if (path.toLowerCase().endsWith(".doc")) {
			String fileName = OrderNoUtil.getSerialNumber() + ".doc";
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			response.setContentType("application/msword");
		} else if (path.toLowerCase().endsWith(".docx")) {
			String fileName = OrderNoUtil.getSerialNumber() + ".docx";
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			response.setContentType("application/msword");
		}
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);

		OutputStream output = response.getOutputStream();// 得到输出流
		InputStream imageIn = new FileInputStream(file);
		copyFile(imageIn, output);
		return null;
	}

	/**
	 * I/O读写 单独使用时多用于文件读取输出到页面
	 * 
	 * @param inStream
	 * @param outStream
	 *            response.getOutputStream
	 */
	public static void copyFile(InputStream inStream, OutputStream outStream) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(inStream);// 输入缓冲流
			bos = new BufferedOutputStream(outStream);// 输出缓冲流
			byte data[] = new byte[4096];// 缓冲字节数
			int size = 0;
			size = bis.read(data);
			while (size != -1) {
				bos.write(data, 0, size);
				size = bis.read(data);
			}
		} catch (IOException e) {
			logger.error("文件读写失败：" + e.getMessage());
		} finally {
			try {
				bis.close();
				bos.flush();
				bos.close();
				outStream.flush();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
