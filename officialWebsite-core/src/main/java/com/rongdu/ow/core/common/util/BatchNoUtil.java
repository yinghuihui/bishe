package com.rongdu.ow.core.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 生成批次号
 * @author ctt
 * @version 1.0.0
 * @date 2016年10月27日 上午11:58:18
 * Copyright 杭州融都科技股份有限公司 齐鑫投  All Rights Reserved
 * 官方网站：www.erongdu.com
 * 研发中心：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class BatchNoUtil {
	
	
	//定义批次号存储的文件名称，一个文件只能对应一种规则的批次号生成
	public static final String BATCHNO_FILE_NAME = "batchNo.dat";
	
	public static final String SERIALNO_FILE_NAME = "serialNo.dat";
	
	//定义文件中存储数据的分隔符
	public final static String FIELD_SEPARATOR = ",";
	
	//定义批次号生成文件的文件路径
	//public final static String RESERVE_FILE_PATH_UP = "/batchNo/files/";
	
	//定义批次号生成规则，从1开始，每天累加
	public final static int BATCHNO_TYPE_ALWAYS = 1;
	//定义批次号生成规则，每日从1开始
	public final static int BATCHNO_TYPE_EVERYDAY = 2;
	
	public static void main(String[] args) {
		String accout = "rsd";
		// 8位日期
		accout=accout+(DateUtil.dateStr(new Date(),DateUtil.DATEFORMAT_STR_012));
		// 3位批次号
		String batchNo = BatchNoUtil.getBatchNo(3,"E:/batchNo/"+BatchNoUtil.BATCHNO_FILE_NAME,BatchNoUtil.BATCHNO_TYPE_ALWAYS,999);
		accout=accout+batchNo;
		for (int i = 0; i < 10; i++) {
			// 4位序列号
			String serialNo = BatchNoUtil.getBatchNo(4,"E:/batchNo/"+BatchNoUtil.SERIALNO_FILE_NAME,BatchNoUtil.BATCHNO_TYPE_EVERYDAY,9999);
		    String result=accout+serialNo;
			System.out.println("---生成的--" + result);
		}
	}
	
	/**
	 * 生成批次号
	 * @param length
	 * @param name
	 * @param type
	 * @return
	 */
	public static String getBatchNo(int length,String name,int type,int max) {
		String batchNo = "";
		try {
			if(BATCHNO_TYPE_ALWAYS == type){
				SerialNumber serial = new FileEveryDaySerialNumber(length, name,max);
				batchNo = serial.getProdSerialNumber();;
			}else if(BATCHNO_TYPE_EVERYDAY == type){
				SerialNumber serial = new FileEveryDaySerialNumber(length, name,max);
				batchNo = serial.getBatchSerialNumber();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return batchNo;
	}
	
}

/**
 * 抽象
 * @author ctt
 * @version 1.0
 * @date 2016年4月26日 上午11:51:07
 * Copyright 杭州融都科技股份有限公司 UFX  All Rights Reserved
 * 官方网站：www.erongdu.com
 * 研发中心：rdc@erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
abstract class SerialNumber {
	/**
	 * 生成项目编号
	 * @return
	 */
	public synchronized String getProdSerialNumber() {
		return processFuProNo();
	}
	
	/**
	 * 生成批次号
	 * @return
	 */
	public synchronized String getBatchSerialNumber() {
		return processBatchNo();
	}

	/**
	 * 生成项目编号
	 * @return
	 */
	protected abstract String processBatchNo();
	
	/**
	 * 生成批次号
	 * @return
	 */
	protected abstract String processFuProNo();
}


abstract class EveryDaySerialNumber extends SerialNumber {

	protected final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	protected DecimalFormat df = null;

	public EveryDaySerialNumber(int width) {
		if (width < 1) {
			throw new IllegalArgumentException("Parameter length must be great than 1!");
		}
		char[] chs = new char[width];
		for (int i = 0; i < width; i++) {
			chs[i] = '0';
		}
		df = new DecimalFormat(new String(chs));
	}

	protected String processBatchNo() {
		Date date = new Date();
		int n = getOrUpdateBatchNumber(date, 1);
		return format(n);
	}
	
	protected String processFuProNo() {
		int n = getOrUpdateFuProdNumber(1);
		return format(n);
	}

	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	protected String format(Date date) {
		return sdf.format(date);
	}

	/**
	 * 格式化编号
	 * @param num
	 * @return
	 */
	protected String format(int num) {
		return df.format(num);
	}

	/**
	 * 读写批次号
	 * @param current
	 * @param start
	 * @return
	 */
	protected abstract int getOrUpdateBatchNumber(Date current, int start);
	
	/**
	 * 读写项目编号
	 * @param start
	 * @return
	 */
	protected abstract int getOrUpdateFuProdNumber(int start);
}

class FileEveryDaySerialNumber extends EveryDaySerialNumber {
	
	private File file = null;
	
	private int end = 0;

	public FileEveryDaySerialNumber(int width, String name, int end) {
		super(width);
		// 批次号文件保存地址
		String fileDir = name.substring(0, name.lastIndexOf("/"));
		name=name.substring(name.lastIndexOf("/")+1,name.length());
        File dir = new File(fileDir);
        if(!dir.exists()){
        	dir.mkdirs();
        }
        String fileSp = System.getProperty("file.separator");
        String fileName = dir+fileSp+name;
		file = new File(fileName);
		this.end = end;
	}

	@Override
	protected int getOrUpdateBatchNumber(Date current, int start) {
		String date = DateUtil.dateStr(current, DateUtil.DATEFORMAT_STR_012);
		int num = start;
		if (file.exists()) {
			List<String> list = FileUtil.readList(file);
			String[] data = list.get(0).split(BatchNoUtil.FIELD_SEPARATOR);
			if (date.equals(data[0])) {
				num = Integer.parseInt(data[1]);
			}
		}
		//不从头开始 文件中的一直为值最大值
		if(num-1 > end){
			num = end;
		}
		FileUtil.rewrite(file, date + BatchNoUtil.FIELD_SEPARATOR + (num + 1));
		return num;
	}
	
	@Override
	protected int getOrUpdateFuProdNumber(int start) {
		int num = start;
		if (file.exists()) {
			List<String> list = FileUtil.readList(file);
			num = Integer.parseInt(list.get(0));
		}
		//不从头开始 文件中的一直为值最大值
		if(num-1 > end){
			num = end;
		} 
		FileUtil.rewrite(file,  String.valueOf(num + 1));
		return num;
	}
}


class FileUtil {
	public static void rewrite(File file, String data) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static List<String> readList(File file) {
		BufferedReader br = null;
		List<String> data = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(file));
			for (String str = null; (str = br.readLine()) != null;) {
				data.add(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
	
}
