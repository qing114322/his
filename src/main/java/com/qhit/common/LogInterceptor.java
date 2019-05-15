package com.qhit.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.sql.rowset.JoinRowSet;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by tp on 2018/12/12.
 */
@Aspect
@Component
public class LogInterceptor {
       @Before(value = "execution(* com.qhit.*.controller.*.*(..))")
    public  void before(JoinPoint jp){
           //获取类名
           String className = jp.getTarget().getClass().getName();
           //获取方法名
           String methodName = jp.getSignature().getName();
           //参数
           String toString = Arrays.toString(jp.getArgs());
           //时间
           SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
           String format = simpleDateFormat.format(new Date());

           System.out.println(format+"开始执行类名："+className+"下的方法"+methodName+"，输入的参数是："+toString);
       }
       @AfterReturning(returning="result",value = "execution(* com.qhit.*.controller.*.*(..))")
       public void afterreturning(JoinPoint jp,Object result){

        //获取类名
           String className = jp.getTarget().getClass().getName();
           //获取方法名
           String methodName = jp.getSignature().getName();
           //时间
           SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
           String format = simpleDateFormat.format(new Date());
           System.out.println(format+"结束执行类名："+className+"下的方法"+methodName+"返回值是："+result);


       }
}
