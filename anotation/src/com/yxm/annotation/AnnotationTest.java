package com.yxm.annotation;
/**
 * 注解基本使用
 * @author yxm
 * @date 2017-1-2
 */
public class AnnotationTest {
public static void main(String[] args) {
	 //对一个类进行检查运用反射,运用 反射首先要搞到类的字节码
	//1.获取当前的注解
	boolean annotationPresent = B.class.isAnnotationPresent(AAnnotation.class);
	//2.如果存在注解,则打印出注解对象
	if(annotationPresent){
		//获取注解对象
		AAnnotation aa = B.class.getAnnotation(AAnnotation.class);
		//获取注解对象中的属性
		System.out.println(aa.color());//red
	}
  }
   
}
