package com.qhit.test.proxy1;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by tp on 2018/12/5.
 */
public class proxyfandianproxy implements InvocationHandler {
    private  Object object;
    public Object bind(Object object){
        this.object=object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("点菜前");
        Object result=method.invoke(object,args);
        System.out.println("点菜后");
        return result;
    }
}
