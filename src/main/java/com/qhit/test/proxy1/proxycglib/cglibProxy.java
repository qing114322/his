package com.qhit.test.proxy1.proxycglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by tp on 2018/12/5.
 */
public class cglibProxy implements MethodInterceptor {


    public  Object getInstance(Object object){
        //创建加强器，用于创建动态代理类
        Enhancer enhancer=new Enhancer();
        //为加强器指定要代理的业务类
        enhancer.setSuperclass(object.getClass());
        //设置回调，会被intercept方法拦截
        enhancer.setCallback(this);
        //创建动态代理对象并返回
            return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用前");
        Object result = methodProxy.invokeSuper(objects, objects);
        System.out.println("调用后");
        return result;
    }
}
