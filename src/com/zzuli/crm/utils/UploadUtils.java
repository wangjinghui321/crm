package com.zzuli.crm.utils;

import java.util.UUID;

/**
 * �ļ��ϴ��Ĺ�����
 * @author ����
 *
 */
public class UploadUtils {
	/**
	 * ���Ŀ¼�ظ�����
	 * @param fileName
	 * @return
	 */
	public static String getUuidFileNames(String fileName){
		//��ȡ. ���ڵ�λ��
		int index = fileName.lastIndexOf(".");
		//ʹ�� . ȥ��ȡ�ļ�������ȡ�� .xxx��չ��
		String extions = fileName.substring(index);
		//�������һ��uuid,ȥ��������ɵ��ַ����е� - ,������չ��
		return UUID.randomUUID().toString().replace("-", "")+extions;
	}
	
	/**
	 * Ŀ¼����ķ���
	 * @param args
	 */
	public static String getPath(String uuidFileName){
		int code1 = uuidFileName.hashCode();
		int d1 = code1 & 0xf;//��Ϊ�Լ�Ŀ¼
		int code2 = code1 >>> 4;
		int d2 = code2 & 0xf;//��Ϊ����Ŀ¼
		return "/"+d1+"/"+d2;
	}
	
	public static void main(String[] args) {
		System.out.println(getUuidFileNames("aa.txt"));
	}
	
	
}
