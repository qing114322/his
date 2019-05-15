package com.qhit.common;

import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseUser.pojo.BaseUser;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by tp on 2018/12/14.
 */
@Component
@Aspect
public class AuthorityInterceptor {

    @Before("execution(* com.qhit.*.controller.*.*(..))")
    public  void before(){
        //	从普通类中获取request的方式：
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

//        HttpSession session = request.getSession();
//        BaseUser sessionUser = (BaseUser) session.getAttribute("sessionUser");
//        //用户发送的请求
//        String requestURI = request.getRequestURI();
//        //查询用户的权限
//        List<BaseFunction> baseFunctionList = sessionUser.getBaseFunctionList();
//        if(sessionUser!=null){
//            boolean flag=false;
//            for(BaseFunction baseFun:baseFunctionList){
//                if(baseFun.getUrl()!=null&&requestURI.indexOf(baseFun.getUrl())!=-1){
//                    flag=true;
//                }
//            }
//            request.setAttribute("qx",flag);
//        }

    }
}
