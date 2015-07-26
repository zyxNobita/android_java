package com.ganjie.ioclib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.view.View;

@Target(ElementType.METHOD)//方法
@Retention(RetentionPolicy.RUNTIME)// 运行编译源码class     SOURCE
@EventBase(listenerSetter="setOnClickListener",listenerType = View.OnClickListener.class,callBackOnClick="onClick")
public @interface OnClick {

	int[] value();
}
