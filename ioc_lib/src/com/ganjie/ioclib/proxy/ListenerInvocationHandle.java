package com.ganjie.ioclib.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/***
 * 方法 
 * @author Administrator
 *
 */
public class ListenerInvocationHandle implements InvocationHandler {

	private Object targer;//目标对象
	private HashMap<String,Method> methods = new HashMap<String,Method>();
	public ListenerInvocationHandle(Object targer){
		this.targer = targer;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		if(targer != null){
			String methodName = method.getName();//拿到 onClick
			method = methods.get(methodName);//得到对应的Toast方法
			if(method != null){
				return method.invoke(targer, args);
			}
			
		}
		return null;
	}
	
	/***
	 * 绑定方法
	 * @param name
	 * @param method
	 */
	public void addMethod(String name,Method method){
		methods.put(name, method);
	}
	

	public Object getTarger() {
		return targer;
	}

	public void setTarger(Object targer) {
		this.targer = targer;
	}
	
}
