package com.qhit.test.proxy1.proxycglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by tp on 2018/12/5.
 */
public class proxyCglib2 implements MethodInterceptor {
//    public Object getInstance(Object object){
//        Enhancer enhancer = new Enhancer();              //创建加强器，用于创建动态代理
//        enhancer.setSuperclass(object.getClass());
//        e
//
//    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return null;
    }
}
