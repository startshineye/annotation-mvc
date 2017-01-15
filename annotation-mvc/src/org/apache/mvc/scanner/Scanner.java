package org.apache.mvc.scanner;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;

import org.apache.mvc.bean.ActionBean;
import org.springframework.annotation.Controller;
import org.springframework.annotation.RequestMapper;
import org.springframework.annotation.ResponseBody;

/**
 * 扫描器类
 * @author yxm
 * @date 2017-1-15
 *
 */
public class Scanner {
	/**
	 * 扫描路径下所有文件
	 * @param path
	 * @param actionBeanMap
	 * @return
	 */
  public static Map<String,ActionBean> scannerPackage(String path,Map<String,ActionBean> actionBeanMap) throws Exception{
	  System.out.println("路径为:"+path);
	  
	  String packagePath = path.replace(".","/");
	  URL url = Thread.currentThread().getContextClassLoader().getResource(packagePath);
	  File file = new File(url.getFile());
	  if(!file.exists()){
		  throw new RuntimeException("路径错误!");
	  }
	  
	  //目录
	  if(file.isDirectory()){
		  File[] files = file.listFiles();
		  for (File fi: files) {
			if(fi.isDirectory()){
				scannerPackage(path + "." + fi.getName(), actionBeanMap);
			}
			if (fi.isFile()) {
                scannerClass(path + "." + fi.getName(), actionBeanMap);
            }
		}
	  }
	  
	  //文件
	  if(file.isFile()){
		 scannerClass(path+"."+file.getName(), actionBeanMap); 
	  }
	  
	  return actionBeanMap;
  }
  
  /**
   * 扫描类中的所有方法
   */
  public static void scannerClass(String filePath,Map<String, ActionBean> actionBeanMap) throws Exception{
	  String path = filePath;
	  if(filePath.endsWith(".class")){
		  path = filePath.substring(0,filePath.lastIndexOf(".class"));
	  }
	  
	  Class clazz = Class.forName(path);
	  
	  //非controller注解类跳过
	  if(!clazz.isAnnotationPresent(Controller.class)){
		  return;
	  }
	  
	  Method[] methods = clazz.getDeclaredMethods();
	  for (Method method : methods) {
		  if (method.isAnnotationPresent(RequestMapper.class)) {
              ActionBean ab = scannerMethod(clazz, method);
              actionBeanMap.put(ab.getPath(), ab);
          }
	}
	  
  }
  
  /**
   * 得到方法参数及方法返回类型等并封装为ActionBean
   */
  public static ActionBean scannerMethod(Class cc, Method method){
	      RequestMapper requestMapper = method.getAnnotation(RequestMapper.class);
	      Controller controller = (Controller)cc.getAnnotation(Controller.class);
	      
	      ActionBean actionBean = new ActionBean();
	      actionBean.setClassPath(cc.getName());
	      actionBean.setMethod(method.getName());
	      actionBean.setTypes(requestMapper.method());
	      actionBean.setPath(controller.value() + requestMapper.value());
	      actionBean.setParams(method.getParameterTypes());
	      actionBean.setResponseBody(method.isAnnotationPresent(ResponseBody.class));
          return actionBean;
  }
}
