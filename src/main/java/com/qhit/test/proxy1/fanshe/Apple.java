package com.qhit.test.proxy1.fanshe;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tp on 2018/12/12.
 */
public class Apple {
    private  Integer price;
    private  String name;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //正常的调用
        Apple apple=new Apple();
        apple.setName("苹果1");
        apple.setPrice(12);
        System.out.println("名称是:"+apple.getName()+"价格是："+apple.getPrice());
        //使用反射调用

        //获取bean的路径名称
        String name = Apple.class.getName();
        System.out.println(name);
        Class  aClass = Class.forName(name);
       Apple apple1 = (Apple) aClass.newInstance();
        Method setName = aClass.getMethod("setName", String.class);
        //执行方法
        setName.invoke(apple1,"22");

    }
}
