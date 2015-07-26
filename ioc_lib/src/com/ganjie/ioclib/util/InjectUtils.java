package com.ganjie.ioclib.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.ganjie.ioclib.annotations.ContentView;
import com.ganjie.ioclib.annotations.EventBase;
import com.ganjie.ioclib.annotations.ViewInject;
import com.ganjie.ioclib.proxy.ListenerInvocationHandle;

import android.app.Activity;
import android.view.View;

/***
 * 注解类
 * @author 
 *
 */
public class InjectUtils {

	private static final String METHOD_FIND_VIEW_BY_ID="findViewById";
	private static final String METHOD_SET_CONTENT_VIEW="setContentView";
	public static void inject(Activity activity){
		injectContentView(activity);//注入 
		injectViews(activity);
		injectEvents(activity);
	}

	/***
	 * 事件注入
	 * @param activity
	 */
	private static void injectEvents(Activity activity) {
		// TODO Auto-generated method stub
		Class<? extends Activity> clazz=activity.getClass();//反射获得对象
		Method[] methods = clazz.getMethods();//得到所有的方法
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();//得到方法的注解
			for (Annotation annotation : annotations) {
				Class<? extends Annotation> annotationType = annotation.annotationType();//得到OnClik注解
				if(annotationType != null){
					EventBase eventBase = annotationType.getAnnotation(EventBase.class);//得到注解的注解
					if(eventBase != null){
						String listenerSetter = eventBase.listenerSetter();
						Class<?> listenerType = eventBase.listenerType();
						String methodName = eventBase.callBackOnClick();
						try {
							Method valluMethod = annotationType.getDeclaredMethod("value");
							int[] viewIds = (int[]) valluMethod.invoke(annotation, null);
							//设置代理
							ListenerInvocationHandle handler = new ListenerInvocationHandle(activity);
							handler.addMethod(methodName, method);
							//通过 Proxy，生成listenerType类型的代理对象
							Object listenerProxy = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, handler);
						
							for (int viewId : viewIds) {
								View view = activity.findViewById(viewId);
								Method setter = view.getClass().getMethod(listenerSetter, listenerType);
								setter.invoke(view, listenerProxy);
							}
							
						} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				
				}
			}
		}
		
		
		
		
		
	}

	/***
	 * 注解  注入控件
	 * @param activity
	 */
	private static void injectViews(Activity activity) {
		// TODO Auto-generated method stub
		Class<? extends Activity> clazz = activity.getClass();//反射获得对象
		Field[] fields = clazz.getDeclaredFields();//获得对象的属性
		for(Field field : fields){
			ViewInject viewInject = field.getAnnotation(ViewInject.class);
			if(viewInject != null){
				int viewId = viewInject.value();
				// 1、通过反射获取到控件
				try {
					Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID, int.class);
					Object view = method.invoke(activity, viewId);
					field.setAccessible(true);//设置私有属性可访问
					field.set(activity, view);
				} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 2、
//				activity.findViewById(viewId);
			}
		}
	}

	/**
	 * 注解  设置  布局
	 * @param activity
	 */
	private static void injectContentView(Activity activity) {
		// TODO Auto-generated method stub
		Class<? extends Activity> clazz = activity.getClass();//反射获得对象
		ContentView viewInject = clazz.getAnnotation(ContentView.class);
		if(viewInject != null){
			int viewLayoutId = viewInject.value();//拿到注解的value
			// 1、通过反射设置布局
			try {
				Method method = clazz.getMethod(METHOD_SET_CONTENT_VIEW, int.class);
				method.invoke(activity, viewLayoutId);
			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//2、直接对象调用方法
//			activity.setContentView(viewLayoutId);
		}
	}
}
