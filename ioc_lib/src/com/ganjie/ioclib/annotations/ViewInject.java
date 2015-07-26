package com.ganjie.ioclib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 控件的注入
 * @author Administrator
 *
 */

@Target(ElementType.FIELD)//使用到属性上
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInject {
	
	int value();
}
