package com.yxm.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AAnnotation {
  String color();//此方法是共有的，抽象的，不用加其他修饰符，类似于接口中方法
  String value();
}
