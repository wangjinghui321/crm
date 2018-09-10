package com.zzuli.crm.utils;

import java.util.UUID;

/**
 * 文件上传的工具类
 * @author 阿辉
 *
 */
public class UploadUtils {
	/**
	 * 解决目录重复问题
	 * @param fileName
	 * @return
	 */
	public static String getUuidFileNames(String fileName){
		//获取. 所在的位置
		int index = fileName.lastIndexOf(".");
		//使用 . 去截取文件名，获取到 .xxx扩展名
		String extions = fileName.substring(index);
		//随机生成一个uuid,去除随机生成的字符串中的 - ,加上扩展名
		return UUID.randomUUID().toString().replace("-", "")+extions;
	}
	
	/**
	 * 目录分离的方法
	 * @param args
	 */
	public static String getPath(String uuidFileName){
		int code1 = uuidFileName.hashCode();
		int d1 = code1 & 0xf;//作为以及目录
		int code2 = code1 >>> 4;
		int d2 = code2 & 0xf;//作为二级目录
		return "/"+d1+"/"+d2;
	}
	
	public static void main(String[] args) {
		System.out.println(getUuidFileNames("aa.txt"));
	}
	
	
}
