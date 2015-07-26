package com.ganjie.ioclib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/***
 * 布局的注入
 * @author Administrator
 *
 */
@Target(ElementType.TYPE)//指定  注解使用的位置
@Retention(RetentionPolicy.RUNTIME)// 编译程序将注解存储再Class中，让虚拟机通过反射机制
public @interface ContentView {

	int value();
}
