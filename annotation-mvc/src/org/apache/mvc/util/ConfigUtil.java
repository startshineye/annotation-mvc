package org.apache.mvc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigUtil {
   //�������ļ��ж�ȡ����
	public static void main(String[] args) throws IOException {
		String filePath="annotation-mvc\\src\\config\\config.properties";
		//1.ע�⵱ʹ��getClass()����������getClassLoader()ʱ��ԴǮ��"/"��������
		 InputStream in = ConfigUtil.class.getResourceAsStream("/config/config.properties");
		 
	/*	 //2.ʹ��classLoader
		 InputStream in2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/config.properties");
        */  Properties pr = new Properties();	
         pr.load(in);
         String REDIRECT_PREFIX  = pr.getProperty("REDIRECT_PREFIX");
         String SCANNER_PATH = pr.getProperty("SCANNER_PATH");
         String PAGE_PREFIX  = pr.getProperty("PAGE_PREFIX");
         String PAGE_SUFFIX = pr.getProperty("PAGE_SUFFIX");
         String UTF8 = pr.getProperty("UTF8");
         String MVC_CONTENT = pr.getProperty("MVC_CONTENT");
         System.out.println(REDIRECT_PREFIX+" "+SCANNER_PATH+" "+PAGE_PREFIX+" "+PAGE_SUFFIX+" "+UTF8+" "+MVC_CONTENT);
	}
	
	public static String getContextPath(){
		
		return null;
	}
}



