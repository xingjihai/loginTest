package com.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CglibTest {
	public void test(){
		System.out.println("hello world");
	}
	public static void main(String[] args) {
		Enhancer enhancer=new Enhancer();
		enhancer.setSuperclass(CglibTest.class);
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
					throws Throwable {
				System.out.println("=== before ====");
				Object result = methodProxy.invokeSuper(o, objects);
				System.out.println("=== after ====");
				return result;
			}
		});
		((CglibTest) enhancer.create()).test();
	}

}
