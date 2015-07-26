package com.ganjie.ioclib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)//再另外的注解 使用
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {
	
	//设置事件的三要素
	// btn.setOnClickListener(listener);
	/***
	 * setOnClickListener 方法名称
	 * @return
	 */
	String listenerSetter();
	/***
	 * listener 的类型
	 * @return
	 */
	Class<?> listenerType();//  监听的class
	/***
	 * listener 中的回调方法名称
	 * @return
	 */
	String callBackOnClick();
}
