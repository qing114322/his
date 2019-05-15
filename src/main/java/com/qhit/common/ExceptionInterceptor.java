package com.qhit.common;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by tp on 2018/12/15.
 */
@Component
@Aspect
public class ExceptionInterceptor {
    @AfterThrowing(value = "execution(* com.qhit.*.controller.*.*(..))",throwing = "e")
    public  void handlerException(Exception e){
        //	将e.printStackTrace()中的信息放到StringWriter输出流中
        StringWriter stringWriter=new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter,true));
        String str = stringWriter.toString();
        //将字符串按照“\r\n”进行分割，并且只显示前四行
        String[] split = str.split("\r\n");
        int len=split.length>4?4:split.length;
        for (int i = 0; i <len ; i++) {
            System.out.println(split[i]);
        }
    }

}
